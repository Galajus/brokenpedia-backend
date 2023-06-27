package pl.galajus.brokenpediabackend.user.category.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.post.model.Post;

import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String categorySlug;
    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

}