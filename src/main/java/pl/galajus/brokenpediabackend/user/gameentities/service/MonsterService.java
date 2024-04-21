package pl.galajus.brokenpediabackend.user.gameentities.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.gameentities.model.Monster;
import pl.galajus.brokenpediabackend.user.gameentities.repostiory.MonsterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;

    @Cacheable(cacheNames = "MonstersWithRars")
    public List<Monster> getAllMonsters() {
        return monsterRepository.findAllWithLegendaryItems();
    }

}
