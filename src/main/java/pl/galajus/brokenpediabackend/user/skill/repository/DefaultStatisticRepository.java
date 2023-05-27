package pl.galajus.brokenpediabackend.user.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.skill.model.DefaultStatistic;

public interface DefaultStatisticRepository extends JpaRepository<DefaultStatistic, Long> {
}
