package pl.galajus.brokenpediabackend.user.suggestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.galajus.brokenpediabackend.user.suggestion.model.Suggestion;
import pl.galajus.brokenpediabackend.user.suggestion.model.SuggestionStatus;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    List<Suggestion> findAllByStatus(SuggestionStatus status);
}
