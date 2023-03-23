package pl.galajus.brokenpediabackend.buildcalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class BuildSkillStatData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SkillStatType skillStatType;
    private Long skillStatId; //ClassSkill or stats
    private Integer level;
    private Long buildDetailsId;
}
