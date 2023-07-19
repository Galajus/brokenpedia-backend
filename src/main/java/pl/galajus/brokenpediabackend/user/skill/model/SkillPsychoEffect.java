package pl.galajus.brokenpediabackend.user.skill.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;

@Entity
@Getter
public class SkillPsychoEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PsychoEffect psychoEffect;
    private Float value;
    private Long skillBasicId;

}
