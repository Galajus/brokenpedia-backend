package pl.galajus.brokenpediabackend.user.gameentities.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.gameentities.controller.mapper.MonsterMapper;
import pl.galajus.brokenpediabackend.user.gameentities.model.dto.MonsterDto;
import pl.galajus.brokenpediabackend.user.gameentities.service.MonsterService;

import java.util.List;

@RestController
@RequestMapping("/monsters")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping
    public List<MonsterDto> getAllMonsters() {
        return MonsterMapper.mapMonstersToMonstersDto(monsterService.getAllMonsters());
    }

}
