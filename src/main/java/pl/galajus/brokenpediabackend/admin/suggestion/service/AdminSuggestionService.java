package pl.galajus.brokenpediabackend.admin.suggestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestion;
import pl.galajus.brokenpediabackend.admin.suggestion.repository.AdminSuggestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSuggestionService {

    private final AdminSuggestionRepository adminSuggestionRepository;

    public List<AdminSuggestion> getAllSuggestions() {
        return adminSuggestionRepository.findAll();
    }

    public void deleteSuggestion(Long id) {
        adminSuggestionRepository.deleteById(id);
    }
}
