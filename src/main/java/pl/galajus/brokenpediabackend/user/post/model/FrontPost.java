package pl.galajus.brokenpediabackend.user.post.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.category.model.Category;
import pl.galajus.brokenpediabackend.user.profile.model.PublicProfile;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "post")
public class FrontPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isPublic;
    private LocalDateTime creationDate;
    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    @OneToOne
    private PublicProfile author;
    private String slug;
    private String title;
    private String description;
    private String image;

}
