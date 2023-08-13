package pl.galajus.brokenpediabackend.user.post.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.post.model.PostViewer;

public interface PostViewerRepository extends JpaRepository<PostViewer, Long> {

    Boolean existsPostViewerByPostIdAndViewerIp(Long postId, String viewerIp);

}
