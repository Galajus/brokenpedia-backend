package pl.galajus.brokenpediabackend.admin.post.model;

import jakarta.persistence.CascadeType;
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
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;
import pl.galajus.brokenpediabackend.user.common.model.PublicProfile;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
public class AdminPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isPublic;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<AdminCategory> categories;
    @OneToOne
    private PublicProfile author;
    private String slug;
    private String title;
    private String description;
    private String content;
    private String image;

    public void addCategory(AdminCategory adminCategory) {
        this.categories.add(adminCategory);
        adminCategory.getPosts().add(this);
    }

    public void removeCategory(Long categoryId) {
        AdminCategory category = this.categories.stream().filter(cat -> cat.getId() == categoryId).findFirst().orElse(null);
        if (category != null) {
            this.categories.remove(category);
            category.getPosts().remove(this);
        }
    }

}
