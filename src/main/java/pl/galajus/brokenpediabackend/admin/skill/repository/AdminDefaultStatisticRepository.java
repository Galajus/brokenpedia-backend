package pl.galajus.brokenpediabackend.admin.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminDefaultStatistic;

public interface AdminDefaultStatisticRepository extends JpaRepository<AdminDefaultStatistic, Long> {
}
