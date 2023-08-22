package pl.galajus.brokenpediabackend.user.skill.controller.mapper;

import pl.galajus.brokenpediabackend.user.skill.model.ClassSkill;
import pl.galajus.brokenpediabackend.user.skill.model.dto.ClassSkillDto;

import java.util.List;

public class SkillMapper {

    public static List<ClassSkillDto> mapToClassSkillDto(List<ClassSkill> skills) {
        return skills.stream().map(skill ->
                        ClassSkillDto.builder()
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

    public static ClassSkill mapToClassSkill(ClassSkillDto classSkillDto) {
        return ClassSkill.builder()
                .id(classSkillDto.getId())
                .level(classSkillDto.getLevel())
                .name(classSkillDto.getName())
                .requirements(classSkillDto.getRequirements())
                .formula(classSkillDto.getFormula())
                .profession(classSkillDto.getProfession())
                .beginLevel(classSkillDto.getBeginLevel())
                .minLevel(classSkillDto.getMinLevel())
                .maxLevel(classSkillDto.getMaxLevel())
                .image(classSkillDto.getImage())
                .build();
    }

}
