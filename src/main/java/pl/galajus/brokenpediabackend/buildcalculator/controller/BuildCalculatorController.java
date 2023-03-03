package pl.galajus.brokenpediabackend.buildcalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.buildcalculator.model.ClassSkill;
import pl.galajus.brokenpediabackend.buildcalculator.service.ClassSkillService;

import java.util.List;

@RestController
@RequestMapping("/builds")
@RequiredArgsConstructor
public class BuildCalculatorController {

    private final ClassSkillService classSkillService;

    @GetMapping("/initData")
    public List<ClassSkill> getInitData() {
        return classSkillService.getAll();
    }

}
