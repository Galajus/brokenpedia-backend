package pl.galajus.brokenpediabackend.user.build.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.user.common.model.Profession;

import java.util.List;

@Entity
@Getter
@Setter
public class BuildDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    private Integer level;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "buildDetailsId")
    private List<BuildSkillStatData> skillStatData;
}
