package pl.galajus.brokenpediabackend.admin.skill.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;

@Entity
@Getter
@Table(name = "skill_psycho_effect")
public class AdminSkillPsychoEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PsychoEffect psychoEffect;
    private Float value;
    private Long skillBasicId;

}
