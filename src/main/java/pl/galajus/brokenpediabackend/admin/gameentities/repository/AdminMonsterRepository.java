package pl.galajus.brokenpediabackend.admin.gameentities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonster;

import java.util.Optional;

public interface AdminMonsterRepository extends JpaRepository<AdminMonster, Long> {

    @Query("select m from AdminMonster m " +
            "left join fetch m.legendaryDrops " +
            "where m.id = ?1")
    Optional<AdminMonster> findByIdWithLegendaryDrops(Long id);

}
