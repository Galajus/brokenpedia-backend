package pl.galajus.brokenpediabackend.user.post.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.post.model.PostViewer;
import pl.galajus.brokenpediabackend.user.post.repostitory.PostRepository;
import pl.galajus.brokenpediabackend.user.post.repostitory.PostViewerRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostViewerService {

    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    private final PostViewerRepository postViewerRepository;
    private final PostRepository postRepository;

    @Transactional
    public void incrementPostViews(Long postId, HttpServletRequest request) {

        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                doIncrement(postId, ip);
                return;
            }
        }
        log.info("User ip to increment views not found");
    }

    public void doIncrement(Long postId, String viewerIp) {
        if (postViewerExist(postId, viewerIp)) {
            return;
        }
        postViewerRepository.save(PostViewer.builder()
                .postId(postId)
                .viewerIp(viewerIp)
                .build());
        postRepository.incrementPostViews(postId);
    }

    public Boolean postViewerExist(Long postId, String viewerIp) {
        return postViewerRepository.existsPostViewerByPostIdAndViewerIp(postId, viewerIp);
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void cleanPostViewer() {
        log.info("POST VIEWS CLEARED");
        postViewerRepository.deleteAll();
    }

}
