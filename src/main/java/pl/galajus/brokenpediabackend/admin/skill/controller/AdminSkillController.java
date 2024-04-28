package pl.galajus.brokenpediabackend.admin.skill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.common.model.AdminProfession;
import pl.galajus.brokenpediabackend.admin.skill.controller.mapper.AdminSkillMapper;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminClassSkill;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillBasic;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillDifficulty;
import pl.galajus.brokenpediabackend.admin.skill.model.dto.AdminClassSkillDto;
import pl.galajus.brokenpediabackend.admin.skill.service.AdminClassSkillService;
import pl.galajus.brokenpediabackend.admin.skill.service.AdminSkillBasicService;
import pl.galajus.brokenpediabackend.admin.skill.service.AdminSkillCustomEffectService;
import pl.galajus.brokenpediabackend.admin.skill.service.AdminSkillPsychoEffectService;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/skills")
@RequiredArgsConstructor
public class AdminSkillController {

    private final AdminClassSkillService adminClassSkillService;
    private final AdminSkillBasicService adminSkillBasicService;
    private final AdminSkillPsychoEffectService adminSkillPsychoEffectService;
    private final AdminSkillCustomEffectService adminSkillCustomEffectService;

    @GetMapping
    public List<AdminClassSkillDto> getSkills() {
        return AdminSkillMapper.mapToClassSkillDto(adminClassSkillService.getAll());
    }

    @GetMapping("/{id}")
    public AdminClassSkill getClassSkill(@PathVariable Long id) {
        return adminClassSkillService.getWithBasicsAndEffects(id);
    }

    @PostMapping
    public AdminClassSkill updateClassSkill(@RequestBody AdminClassSkillDto adminClassSkillDto) {
        return adminClassSkillService.save(AdminSkillMapper.mapToClassSkill(adminClassSkillDto));
    }

    @PostMapping("/basic")
    public AdminSkillBasic updateSkillBasic(@RequestBody AdminSkillBasic adminSkillBasic) {
        return adminSkillBasicService.saveSkillBasic(adminSkillBasic);
    }

    @PutMapping("/basic")
    public AdminSkillBasic addSkillBasic(@RequestBody AdminSkillBasic adminSkillBasic) {
        return adminSkillBasicService.saveSkillBasic(adminSkillBasic);
    }

    @DeleteMapping("/basic/{id}")
    public void deleteSkillBasic(@PathVariable Long id) {
        this.adminSkillBasicService.delete(id);
    }

    @DeleteMapping("/custom")
    public void deleteCustomEffects(@RequestBody List<Long> ids) {
        adminSkillCustomEffectService.deleteAllWithIds(ids);
    }

    @DeleteMapping("/psycho")
    public void deletePsychoEffects(@RequestBody List<Long> ids) {
        adminSkillPsychoEffectService.deleteAllWithIds(ids);
    }

    @GetMapping("/psycho-effects")
    public List<PsychoEffect> getPsychoEffect() {
        return Arrays.stream(PsychoEffect.values()).toList();
    }

    @GetMapping("/professions")
    public List<AdminProfession> getProfessions() {
        return Arrays.stream(AdminProfession.values()).toList();
    }
    @GetMapping("/difficulties")
    public List<AdminSkillDifficulty> getDifficulties() {
        return Arrays.stream(AdminSkillDifficulty.values()).toList();
    }
}
