package pl.galajus.brokenpediabackend.user.suggestion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.security.exception.RequestAuthorizationException;
import pl.galajus.brokenpediabackend.user.suggestion.model.Suggestion;
import pl.galajus.brokenpediabackend.user.suggestion.model.SuggestionStatus;
import pl.galajus.brokenpediabackend.user.suggestion.service.SuggestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping("/add")
    public ResponseEntity<String> postSuggestion(@Valid @RequestBody Suggestion suggestion) {
        suggestionService.saveSuggestion(suggestion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{status}")
    public List<Suggestion> getSuggestionsByStatus(@PathVariable SuggestionStatus status) {
        if (status.equals(SuggestionStatus.UNREAD)) {
            throw new RequestAuthorizationException("UNAUTHORIZED");
        }
        return suggestionService.getSuggestionsByStatus(status);
    }

}
