package pl.galajus.brokenpediabackend.admin.suggestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestion;

public interface AdminSuggestionRepository extends JpaRepository<AdminSuggestion, Long> {
}
