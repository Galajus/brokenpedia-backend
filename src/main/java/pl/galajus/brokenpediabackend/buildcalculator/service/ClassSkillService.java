package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.ClassSkill;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillBasic;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillCustomEffect;
import pl.galajus.brokenpediabackend.buildcalculator.model.SkillPsychoEffect;
import pl.galajus.brokenpediabackend.buildcalculator.repository.ClassSkillRepository;
import pl.galajus.brokenpediabackend.buildcalculator.repository.SkillBasicRepository;
import pl.galajus.brokenpediabackend.buildcalculator.repository.SkillCustomEffectRepository;
import pl.galajus.brokenpediabackend.buildcalculator.repository.SkillPsychoEffectRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClassSkillService {

    private final ClassSkillRepository classSkillRepository;
    private final SkillBasicRepository skillBasicRepository;
    private final SkillPsychoEffectRepository skillPsychoEffectRepository;
    private final SkillCustomEffectRepository skillCustomEffectRepository;

    public List<ClassSkill> getAll() {
        return classSkillRepository.findAll();
    }

    public List<ClassSkill> getAllWithBasicsAndEffects() {
        List<ClassSkill> classSkills = classSkillRepository.findAll();
        List<SkillBasic> skillBasics = skillBasicRepository.findAll();
        List<SkillPsychoEffect> psychoEffects = skillPsychoEffectRepository.findAll();
        List<SkillCustomEffect> customEffects = skillCustomEffectRepository.findAll();

        return mapClassSkills(classSkills, skillBasics, psychoEffects, customEffects);
    }

    private List<ClassSkill> mapClassSkills(List<ClassSkill> classSkills, List<SkillBasic> skillBasics,
                                            List<SkillPsychoEffect> psychoEffects, List<SkillCustomEffect> customEffects) {

        mapPsychoEffects(skillBasics, psychoEffects);
        mapCustomEffects(skillBasics, customEffects);
        mapBasics(classSkills, skillBasics);

        return classSkills;
    }

    private void mapPsychoEffects(List<SkillBasic> skillBasics, List<SkillPsychoEffect> psychoEffects) {
        skillBasics.forEach(skillBasic -> {
            List<SkillPsychoEffect> effects = psychoEffects.stream()
                    .filter(psychoEffect -> Objects.equals(psychoEffect.getSkillBasicId(), skillBasic.getId()))
                    .toList();
            skillBasic.setSkillPsychoEffects(effects);
        });
    }

    private void mapCustomEffects(List<SkillBasic> skillBasics, List<SkillCustomEffect> customEffects) {
        skillBasics.forEach(skillBasic -> {
            List<SkillCustomEffect> effects = customEffects.stream()
                    .filter(psychoEffect -> Objects.equals(psychoEffect.getSkillBasicId(), skillBasic.getId()))
                    .toList();
            skillBasic.setSkillCustomEffects(effects);
        });
    }

    private void mapBasics(List<ClassSkill> classSkills, List<SkillBasic> skillBasics) {
        classSkills.forEach(classSkill -> {
            List<SkillBasic> basics = skillBasics.stream()
                    .filter(skillBasic -> Objects.equals(classSkill.getId(), skillBasic.getClassSkillId()))
                    .toList();
            classSkill.setSkillBasics(basics);
        });
    }

}
