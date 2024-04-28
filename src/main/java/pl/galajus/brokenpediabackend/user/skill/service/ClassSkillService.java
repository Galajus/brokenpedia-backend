package pl.galajus.brokenpediabackend.user.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.SkillBasic;
import pl.galajus.brokenpediabackend.user.skill.model.SkillCustomEffect;
import pl.galajus.brokenpediabackend.user.skill.model.SkillPsychoEffect;
import pl.galajus.brokenpediabackend.user.skill.repository.ClassSkillRepository;
import pl.galajus.brokenpediabackend.user.skill.repository.SkillBasicRepository;
import pl.galajus.brokenpediabackend.user.skill.repository.SkillCustomEffectRepository;
import pl.galajus.brokenpediabackend.user.skill.repository.SkillPsychoEffectRepository;
import pl.galajus.brokenpediabackend.user.skill.service.mapper.ClassSkillMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassSkillService {

    private final ClassSkillRepository classSkillRepository;
    private final SkillBasicRepository skillBasicRepository;
    private final SkillPsychoEffectRepository skillPsychoEffectRepository;
    private final SkillCustomEffectRepository skillCustomEffectRepository;

    @Transactional
    public ClassSkill save(ClassSkill classSkill) {
        return classSkillRepository.save(classSkill);
    }

    public List<ClassSkill> getAll() {
        return classSkillRepository.findAll();
    }

    public List<ClassSkill> getAllWithBasicsAndEffects() {
        List<ClassSkill> classSkills = classSkillRepository.findAll();
        List<SkillBasic> skillBasics = skillBasicRepository.findAll();
        List<SkillPsychoEffect> psychoEffects = skillPsychoEffectRepository.findAll();
        List<SkillCustomEffect> customEffects = skillCustomEffectRepository.findAll();

        return ClassSkillMapper.mapClassSkills(classSkills, skillBasics, psychoEffects, customEffects);
    }

    public ClassSkill getWithBasicsAndEffects(Long id) {
        ClassSkill classSkill = classSkillRepository.findById(id).orElseThrow();
        List<SkillBasic> skillBasics = skillBasicRepository.findAllByClassSkillId(classSkill.getId());

        List<Long> basicIds = skillBasics.stream().map(SkillBasic::getId).toList();

        List<SkillPsychoEffect> skillPsychoEffects = skillPsychoEffectRepository.findAllBySkillBasicIdIn(basicIds);
        List<SkillCustomEffect> skillCustomEffects = skillCustomEffectRepository.findAllBySkillBasicIdIn(basicIds);

        return ClassSkillMapper.mapClassSkill(classSkill, skillBasics, skillPsychoEffects, skillCustomEffects);
    }

}
