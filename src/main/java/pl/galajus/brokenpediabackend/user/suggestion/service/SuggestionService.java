package pl.galajus.brokenpediabackend.user.suggestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.common.utils.SanitizeUtils;
import pl.galajus.brokenpediabackend.user.suggestion.model.Suggestion;
import pl.galajus.brokenpediabackend.user.suggestion.model.SuggestionStatus;
import pl.galajus.brokenpediabackend.user.suggestion.repository.SuggestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public void saveSuggestion(Suggestion suggestion) {
        suggestion.setAuthor(SanitizeUtils.cleanBasic(suggestion.getAuthor()));
        suggestion.setSuggestion(SanitizeUtils.cleanBasic(suggestion.getSuggestion()));
        suggestion.setStatus(SuggestionStatus.UNREAD);
        suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getSuggestionsByStatus(SuggestionStatus status) {
        return suggestionRepository.findAllByStatus(status);
    }

}
