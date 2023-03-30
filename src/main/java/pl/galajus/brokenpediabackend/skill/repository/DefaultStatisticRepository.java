package pl.galajus.brokenpediabackend.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.skill.model.DefaultStatistic;

public interface DefaultStatisticRepository extends JpaRepository<DefaultStatistic, Long> {
}
