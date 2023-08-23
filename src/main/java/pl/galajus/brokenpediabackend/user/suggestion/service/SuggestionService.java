package pl.galajus.brokenpediabackend.user.suggestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.common.service.SanitizeService;
import pl.galajus.brokenpediabackend.user.suggestion.model.Suggestion;
import pl.galajus.brokenpediabackend.user.suggestion.repository.SuggestionRepository;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public void saveSuggestion(Suggestion suggestion) {
        suggestion.setAuthor(SanitizeService.cleanBasic(suggestion.getAuthor()));
        suggestion.setSuggestion(SanitizeService.cleanBasic(suggestion.getSuggestion()));
        suggestionRepository.save(suggestion);
    }

}
