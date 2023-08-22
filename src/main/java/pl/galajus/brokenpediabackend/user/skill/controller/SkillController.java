package pl.galajus.brokenpediabackend.user.skill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.common.model.Profession;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;
import pl.galajus.brokenpediabackend.user.skill.controller.mapper.SkillMapper;
import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.SkillBasic;
import pl.galajus.brokenpediabackend.user.skill.model.SkillDifficulty;
import pl.galajus.brokenpediabackend.user.skill.model.dto.ClassSkillDto;
import pl.galajus.brokenpediabackend.user.skill.service.ClassSkillService;
import pl.galajus.brokenpediabackend.user.skill.service.SkillBasicService;
import pl.galajus.brokenpediabackend.user.skill.service.SkillCustomEffectService;
import pl.galajus.brokenpediabackend.user.skill.service.SkillPsychoEffectService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/skills") //TODO: Common class and admin packages
@RequiredArgsConstructor
public class SkillController {

    private final ClassSkillService classSkillService;
    private final SkillBasicService skillBasicService;
    private final SkillPsychoEffectService skillPsychoEffectService;
    private final SkillCustomEffectService skillCustomEffectService;

    @GetMapping
    public List<ClassSkillDto> getSkills() {
        return SkillMapper.mapToClassSkillDto(classSkillService.getAll());
    }

    @GetMapping("/{id}")
    public ClassSkill getClassSkill(@PathVariable Long id) {
        return classSkillService.getWithBasicsAndEffects(id);
    }

    @PostMapping
    public ClassSkill updateClassSkill(@RequestBody ClassSkillDto classSkillDto) {
        return classSkillService.save(SkillMapper.mapToClassSkill(classSkillDto));
    }

    @PostMapping("/basic")
    public SkillBasic updateSkillBasic(@RequestBody SkillBasic skillBasic) {
        return skillBasicService.saveSkillBasic(skillBasic);
    }

    @PutMapping("/basic")
    public SkillBasic addSkillBasic(@RequestBody SkillBasic skillBasic) {
        return skillBasicService.saveSkillBasic(skillBasic);
    }

    @DeleteMapping("/basic/{id}")
    public void deleteSkillBasic(@PathVariable Long id) {
        this.skillBasicService.delete(id);
    }

    @DeleteMapping("/custom")
    public void deleteCustomEffects(@RequestBody List<Long> ids) {
        skillCustomEffectService.deleteAllWithIds(ids);
    }

    @DeleteMapping("/psycho")
    public void deletePsychoEffects(@RequestBody List<Long> ids) {
        skillPsychoEffectService.deleteAllWithIds(ids);
    }

    @GetMapping("/psycho-effects")
    public List<PsychoEffect> getPsychoEffect() {
        return Arrays.stream(PsychoEffect.values()).toList();
    }

    @GetMapping("/professions")
    public List<Profession> getProfessions() {
        return Arrays.stream(Profession.values()).toList();
    }
    @GetMapping("/difficulties")
    public List<SkillDifficulty> getDifficulties() {
        return Arrays.stream(SkillDifficulty.values()).toList();
    }
}
