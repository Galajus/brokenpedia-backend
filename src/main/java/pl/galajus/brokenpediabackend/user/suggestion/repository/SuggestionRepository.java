package pl.galajus.brokenpediabackend.user.suggestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.suggestion.model.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
