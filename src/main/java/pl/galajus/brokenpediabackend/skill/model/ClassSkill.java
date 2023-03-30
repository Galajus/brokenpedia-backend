package pl.galajus.brokenpediabackend.skill.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.common.model.Profession;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;
    private Integer maxLevel;
    private Integer minLevel;
    private Integer beginLevel;
    private String name;
    private String image;
    @Enumerated(value = EnumType.STRING)
    private Profession profession;
    @OneToMany
    @JoinColumn(name = "classSkillId", insertable = false, updatable = false)
    List<SkillBasic> skillBasics;
}
