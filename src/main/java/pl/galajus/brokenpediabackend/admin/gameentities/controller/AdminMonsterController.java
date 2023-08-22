package pl.galajus.brokenpediabackend.admin.gameentities.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.gameentities.controller.mapper.AdminMonsterMapper;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonster;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonsterType;
import pl.galajus.brokenpediabackend.admin.gameentities.model.dto.AdminMonsterWithoutDropsDto;
import pl.galajus.brokenpediabackend.admin.gameentities.service.AdminMonsterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/monsters")
public class AdminMonsterController {

    private final AdminMonsterService adminMonsterService;

    @GetMapping
    public List<AdminMonsterWithoutDropsDto> getAll() {
        return AdminMonsterMapper.mapAdminMonstersToMonstersWithoutDropsDto(adminMonsterService.getAll());
    }

    @GetMapping("/types")
    public List<AdminMonsterType> getAllMonstersTypes() {
        return adminMonsterService.getAllMonstersTypes();
    }

    @GetMapping("/{id}")
    public AdminMonster getMonsterById(@PathVariable Long id) {
        return adminMonsterService.getMonsterById(id);
    }

    @PostMapping
    public AdminMonster createMonster(@RequestBody AdminMonster adminMonster) {
        return adminMonsterService.createMonster(adminMonster);
    }

    @PutMapping
    public AdminMonster updateMonster(@RequestBody AdminMonster adminMonster) {
        return adminMonsterService.updateMonster(adminMonster);
    }

    @DeleteMapping("/{id}")
    public void deleteMonster(@PathVariable Long id) {
        adminMonsterService.deleteMonster(id);
    }

}
