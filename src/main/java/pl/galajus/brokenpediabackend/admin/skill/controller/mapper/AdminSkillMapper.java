package pl.galajus.brokenpediabackend.admin.skill.controller.mapper;

import pl.galajus.brokenpediabackend.admin.skill.model.AdminClassSkill;
import pl.galajus.brokenpediabackend.admin.skill.model.dto.AdminClassSkillDto;

import java.util.List;

public class AdminSkillMapper {

    public static List<AdminClassSkillDto> mapToClassSkillDto(List<AdminClassSkill> skills) {
        return skills.stream().map(skill ->
                        AdminClassSkillDto.builder()
                                .id(skill.getId())
                                .name(skill.getName())
                                .requirements(skill.getRequirements())
                                .formula(skill.getFormula())
                                .level(skill.getLevel())
                                .beginLevel(skill.getBeginLevel())
                                .minLevel(skill.getMinLevel())
                                .maxLevel(skill.getMaxLevel())
                                .image(skill.getImage())
                                .profession(skill.getProfession())
                                .build())
                .toList();
    }

    public static AdminClassSkill mapToClassSkill(AdminClassSkillDto adminClassSkillDto) {
        return AdminClassSkill.builder()
                .id(adminClassSkillDto.getId())
                .level(adminClassSkillDto.getLevel())
                .name(adminClassSkillDto.getName())
                .requirements(adminClassSkillDto.getRequirements())
                .formula(adminClassSkillDto.getFormula())
                .profession(adminClassSkillDto.getProfession())
                .beginLevel(adminClassSkillDto.getBeginLevel())
                .minLevel(adminClassSkillDto.getMinLevel())
                .maxLevel(adminClassSkillDto.getMaxLevel())
                .image(adminClassSkillDto.getImage())
                .build();
    }

}
