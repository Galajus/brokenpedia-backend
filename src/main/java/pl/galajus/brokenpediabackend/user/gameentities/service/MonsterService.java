package pl.galajus.brokenpediabackend.user.gameentities.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.gameentities.model.Monster;
import pl.galajus.brokenpediabackend.user.gameentities.repostiory.MonsterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;

    public List<Monster> getAllMonsters() {
        return monsterRepository.findAllWithLegendaryItems();
    }

}
