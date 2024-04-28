package pl.galajus.brokenpediabackend.admin.category.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.post.model.AdminPost;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class AdminCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String categorySlug;
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<AdminPost> posts;

    @PreRemove
    public void removeAdminPosts() {
        this.posts.forEach(p -> p.removeCategory(this));
        this.posts.clear();
    }

}
