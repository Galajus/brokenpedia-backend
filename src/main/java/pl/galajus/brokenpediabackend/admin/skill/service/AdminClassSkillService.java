package pl.galajus.brokenpediabackend.admin.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminClassSkill;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillBasic;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillCustomEffect;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillPsychoEffect;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminClassSkillRepository;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillBasicRepository;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillCustomEffectRepository;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillPsychoEffectRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminClassSkillService {

    private final AdminClassSkillRepository adminClassSkillRepository;
    private final AdminSkillBasicRepository adminSkillBasicRepository;
    private final AdminSkillPsychoEffectRepository adminSkillPsychoEffectRepository;
    private final AdminSkillCustomEffectRepository adminSkillCustomEffectRepository;

    @Transactional
    public AdminClassSkill save(AdminClassSkill adminClassSkill) {
        return adminClassSkillRepository.save(adminClassSkill);
    }

    public List<AdminClassSkill> getAll() {
        return adminClassSkillRepository.findAll();
    }

    public List<AdminClassSkill> getAllWithBasicsAndEffects() {
        List<AdminClassSkill> adminClassSkills = adminClassSkillRepository.findAll();
        List<AdminSkillBasic> adminSkillBasics = adminSkillBasicRepository.findAll();
        List<AdminSkillPsychoEffect> psychoEffects = adminSkillPsychoEffectRepository.findAll();
        List<AdminSkillCustomEffect> customEffects = adminSkillCustomEffectRepository.findAll();

        return mapClassSkills(adminClassSkills, adminSkillBasics, psychoEffects, customEffects);
    }

    public AdminClassSkill getWithBasicsAndEffects(Long id) {
        AdminClassSkill adminClassSkill = adminClassSkillRepository.findById(id).orElseThrow();
        List<AdminSkillBasic> adminSkillBasics = adminSkillBasicRepository.findAllByClassSkillId(adminClassSkill.getId());

        List<Long> basicIds = adminSkillBasics.stream().map(AdminSkillBasic::getId).toList();

        List<AdminSkillPsychoEffect> adminSkillPsychoEffects = adminSkillPsychoEffectRepository.findAllBySkillBasicIdIn(basicIds);
        List<AdminSkillCustomEffect> adminSkillCustomEffects = adminSkillCustomEffectRepository.findAllBySkillBasicIdIn(basicIds);

        return mapClassSkill(adminClassSkill, adminSkillBasics, adminSkillPsychoEffects, adminSkillCustomEffects);
    }

    private AdminClassSkill mapClassSkill(AdminClassSkill adminClassSkill, List<AdminSkillBasic> adminSkillBasics,
                                          List<AdminSkillPsychoEffect> psychoEffects, List<AdminSkillCustomEffect> customEffects) {
        mapPsychoEffects(adminSkillBasics, psychoEffects);
        mapCustomEffects(adminSkillBasics, customEffects);
        mapBasic(adminClassSkill, adminSkillBasics);

        return adminClassSkill;
    }

    private List<AdminClassSkill> mapClassSkills(List<AdminClassSkill> adminClassSkills, List<AdminSkillBasic> adminSkillBasics,
                                                 List<AdminSkillPsychoEffect> psychoEffects, List<AdminSkillCustomEffect> customEffects) {
        mapPsychoEffects(adminSkillBasics, psychoEffects);
        mapCustomEffects(adminSkillBasics, customEffects);
        mapBasics(adminClassSkills, adminSkillBasics);

        return adminClassSkills;
    }

    private void mapPsychoEffects(List<AdminSkillBasic> adminSkillBasics, List<AdminSkillPsychoEffect> psychoEffects) {
        adminSkillBasics.forEach(adminSkillBasic -> {
            List<AdminSkillPsychoEffect> effects = psychoEffects.stream()
                    .filter(psychoEffect -> Objects.equals(psychoEffect.getSkillBasicId(), adminSkillBasic.getId()))
                    .toList();
            adminSkillBasic.setAdminSkillPsychoEffects(effects);
        });
    }

    private void mapCustomEffects(List<AdminSkillBasic> adminSkillBasics, List<AdminSkillCustomEffect> customEffects) {
        adminSkillBasics.forEach(adminSkillBasic -> {
            List<AdminSkillCustomEffect> effects = customEffects.stream()
                    .filter(psychoEffect -> Objects.equals(psychoEffect.getSkillBasicId(), adminSkillBasic.getId()))
                    .toList();
            adminSkillBasic.setAdminSkillCustomEffects(effects);
        });
    }

    private void mapBasics(List<AdminClassSkill> adminClassSkills, List<AdminSkillBasic> adminSkillBasics) {
        adminClassSkills.forEach(adminClassSkill -> {
            List<AdminSkillBasic> basics = adminSkillBasics.stream()
                    .filter(adminSkillBasic -> Objects.equals(adminClassSkill.getId(), adminSkillBasic.getClassSkillId()))
                    .toList();
            adminClassSkill.setAdminSkillBasics(basics);
        });
    }

    private void mapBasic(AdminClassSkill adminClassSkill, List<AdminSkillBasic> adminSkillBasics) {
        List<AdminSkillBasic> basics = adminSkillBasics.stream()
                .filter(adminSkillBasic -> Objects.equals(adminClassSkill.getId(), adminSkillBasic.getClassSkillId()))
                .toList();
        adminClassSkill.setAdminSkillBasics(basics);

    }

}
