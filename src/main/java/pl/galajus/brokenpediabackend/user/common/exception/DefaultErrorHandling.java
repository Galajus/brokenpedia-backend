package pl.galajus.brokenpediabackend.user.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.galajus.brokenpediabackend.user.security.exception.RequestAuthorizationException;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class DefaultErrorHandling {

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseBody
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DefaultErrorDto(
                        new Date(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({RequestAuthorizationException.class})
    @ResponseBody
    public ResponseEntity<?> handleTokenExpiredException(RequestAuthorizationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new DefaultErrorDto(
                        new Date(),
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ResponseEntity<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new DefaultErrorDto(
                        new Date(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ));
    }

}
