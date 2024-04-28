package pl.galajus.brokenpediabackend.user.skill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.common.model.Profession;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;
import pl.galajus.brokenpediabackend.user.skill.controller.mapper.SkillMapper;
import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.SkillDifficulty;
import pl.galajus.brokenpediabackend.user.skill.model.dto.ClassSkillDto;
import pl.galajus.brokenpediabackend.user.skill.service.ClassSkillService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final ClassSkillService classSkillService;

    @GetMapping
    public List<ClassSkillDto> getSkills() {
        return SkillMapper.mapToClassSkillDto(classSkillService.getAll());
    }

    @GetMapping("/{id}")
    public ClassSkill getClassSkill(@PathVariable Long id) {
        return classSkillService.getWithBasicsAndEffects(id);
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
