package pl.galajus.brokenpediabackend.admin.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.admin.category.model.AdminCategory;

import java.util.List;

public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {

    List<AdminCategory> findAllByIdIn(List<Long> ids);

}
