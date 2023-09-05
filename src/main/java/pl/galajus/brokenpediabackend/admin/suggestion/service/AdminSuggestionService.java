package pl.galajus.brokenpediabackend.admin.suggestion.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestion;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestionStatus;
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

    @Transactional
    public void updateSuggestionStatus(Long id, AdminSuggestionStatus status) {
        adminSuggestionRepository.updateStatus(id, status);
    }

    @Transactional
    public void updateAdminComment(Long id, String comment) {
        adminSuggestionRepository.updateAdminComment(id, comment);
    }
}
