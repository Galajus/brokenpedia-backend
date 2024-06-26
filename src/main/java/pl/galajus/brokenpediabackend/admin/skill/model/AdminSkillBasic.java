package pl.galajus.brokenpediabackend.admin.skill.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skill_basic")
public class AdminSkillBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer skillLevel;
    private Integer damage;
    private Integer hitChance;
    private Integer manaCost;
    private Integer staminaCost;
    private Integer roundsTime;
    private Integer effectRoundsTime;
    private Integer additionalEffectChance;
    //Bowman fire shot, Sheed Chi attack, Druid dispel, Meteorite skill, shadow hit skill, Face od death skill
    private String specialEffectDescription;
    private Integer specialEffectValue;
    @Enumerated(value = EnumType.STRING)
    private AdminSkillDifficulty adminSkillDifficulty;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skillBasicId", insertable = false, updatable = false)
    private List<AdminSkillPsychoEffect> adminSkillPsychoEffects;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skillBasicId", insertable = false, updatable = false)
    private List<AdminSkillCustomEffect> adminSkillCustomEffects;
    private Long classSkillId;

}
