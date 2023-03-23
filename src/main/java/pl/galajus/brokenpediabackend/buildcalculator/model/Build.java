package pl.galajus.brokenpediabackend.buildcalculator.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID ownerUuid;
    @Length(max = 30)
    private String buildName;
    @Length(max = 150)
    private String shortDescription;
    @Length(max = 6000)
    private String description;

    @OneToMany
    @JoinColumn(name = "buildId")
    private Set<BuildLiker> liking;
    private Boolean hidden;
    private Boolean pvpBuild;
    @OneToOne(cascade = CascadeType.ALL)
    private BuildDetails buildDetails;

}
