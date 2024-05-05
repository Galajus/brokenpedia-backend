package pl.galajus.brokenpediabackend.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import pl.galajus.brokenpediabackend.user.security.exception.RequestAuthorizationException;
import pl.galajus.brokenpediabackend.user.security.model.PediaUserDetails;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final PediaUserDetailsService userDetailsService;
    private final String secret;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  PediaUserDetailsService userDetailsService,
                                  String secret, HandlerExceptionResolver exceptionResolver) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.secret = secret;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(request);
        } catch (RequestAuthorizationException ex) {
            exceptionResolver.resolveException(request, response, null, ex);
            return;
        }
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            String userUuid = verifyJWT(token);

            if (userUuid != null) {
                PediaUserDetails userDetails = (PediaUserDetails) userDetailsService.loadUserByUuid(userUuid);
                return new UsernamePasswordAuthenticationToken(userDetails.getUuid(), null, userDetails.getAuthorities());
            }
        }
        return null;
    }

    private String verifyJWT(String token) throws RequestAuthorizationException {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new RequestAuthorizationException(ex.getMessage());
        }
    }

}
