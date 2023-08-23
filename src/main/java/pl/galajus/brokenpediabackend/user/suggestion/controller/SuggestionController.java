package pl.galajus.brokenpediabackend.user.suggestion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.suggestion.model.Suggestion;
import pl.galajus.brokenpediabackend.user.suggestion.service.SuggestionService;

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

}
