package pl.galajus.brokenpediabackend.admin.gameentities.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonster;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonsterType;
import pl.galajus.brokenpediabackend.admin.gameentities.repository.AdminMonsterRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMonsterService {

    private final AdminMonsterRepository adminMonsterRepository;

    public List<AdminMonster> getAll() {
        return adminMonsterRepository.findAll();
    }

    public List<AdminMonsterType> getAllMonstersTypes() {
        return Arrays.stream(AdminMonsterType.values()).toList();
    }

    public AdminMonster getMonsterById(Long id) {
        return adminMonsterRepository.findById(id).orElseThrow();
    }

    public AdminMonster createMonster(AdminMonster adminMonster) {
        return adminMonsterRepository.save(adminMonster);
    }

    public AdminMonster updateMonster(AdminMonster adminMonster) {
        return adminMonsterRepository.save(adminMonster);
    }

    @Transactional
    public void deleteMonster(Long id) {
        AdminMonster adminMonster = adminMonsterRepository.findByIdWithLegendaryDrops(id).orElseThrow();
        adminMonster.removeLegendaryDrops();
        adminMonsterRepository.delete(adminMonster);
    }
}
