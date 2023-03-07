package pl.galajus.brokenpediabackend.buildcalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.buildcalculator.model.DefaultStatistic;

public interface DefaultStatisticRepository extends JpaRepository<DefaultStatistic, Long> {
}
