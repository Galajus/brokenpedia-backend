package pl.galajus.brokenpediabackend.user.gameentities.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.user.gameentities.model.Monster;

import java.util.List;
import java.util.Optional;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @Query(value = "select m from Monster m " +
            "left join fetch m.legendaryDrops",
    countQuery = "select m from Monster m " +
            "left join m.legendaryDrops")
    List<Monster> findAllWithLegendaryItems();

    @Query(value = "select m from Monster m " +
            "left join fetch m.legendaryDrops " +
            "where m.id in ?1")
    Optional<Monster> findByIdWithItems(Long id);
}
