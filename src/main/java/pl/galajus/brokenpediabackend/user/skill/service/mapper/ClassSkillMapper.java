package pl.galajus.brokenpediabackend.user.skill.service.mapper;

import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.SkillBasic;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCustomEffect;
import pl.galajus.brokenpediabackend.user.skill.model.SkillPsychoEffect;

import java.util.List;
import java.util.Objects;

public class ClassSkillMapper {

    public static ClassSkill mapClassSkill(ClassSkill classSkill, List<SkillBasic> skillBasics,
                                           List<SkillPsychoEffect> psychoEffects, List<SkillCustomEffect> customEffects) {
        mapPsychoEffects(skillBasics, psychoEffects);
        mapCustomEffects(skillBasics, customEffects);
        mapBasic(classSkill, skillBasics);

        return classSkill;
    }

    public static List<ClassSkill> mapClassSkills(List<ClassSkill> classSkills, List<SkillBasic> skillBasics,
                                                  List<SkillPsychoEffect> psychoEffects, List<SkillCustomEffect> customEffects) {
        mapPsychoEffects(skillBasics, psychoEffects);
        mapCustomEffects(skillBasics, customEffects);
        mapBasics(classSkills, skillBasics);

        return classSkills;
    }

    private static void mapPsychoEffects(List<SkillBasic> skillBasics, List<SkillPsychoEffect> psychoEffects) {
        skillBasics.forEach(skillBasic -> {
            List<SkillPsychoEffect> effects = psychoEffects.stream()
                    .filter(psychoEffect -> Objects.equals(psychoEffect.getSkillBasicId(), skillBasic.getId()))
                    .toList();
            skillBasic.setSkillPsychoEffects(effects);
        });
    }

    private static void mapCustomEffects(List<SkillBasic> skillBasics, List<SkillCustomEffect> customEffects) {
        skillBasics.forEach(skillBasic -> {
            List<SkillCustomEffect> effects = customEffects.stream()
                    .filter(psychoEffect -> Objects.equals(psychoEffect.getSkillBasicId(), skillBasic.getId()))
                    .toList();
            skillBasic.setSkillCustomEffects(effects);
        });
    }

    private static void mapBasics(List<ClassSkill> classSkills, List<SkillBasic> skillBasics) {
        classSkills.forEach(classSkill -> {
            List<SkillBasic> basics = skillBasics.stream()
                    .filter(skillBasic -> Objects.equals(classSkill.getId(), skillBasic.getClassSkillId()))
                    .toList();
            classSkill.setSkillBasics(basics);
        });
    }

    private static void mapBasic(ClassSkill classSkill, List<SkillBasic> skillBasics) {
        List<SkillBasic> basics = skillBasics.stream()
                .filter(skillBasic -> Objects.equals(classSkill.getId(), skillBasic.getClassSkillId()))
                .toList();
        classSkill.setSkillBasics(basics);

    }

}
