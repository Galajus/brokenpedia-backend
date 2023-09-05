package pl.galajus.brokenpediabackend.admin.suggestion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestion;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestionStatus;
import pl.galajus.brokenpediabackend.admin.suggestion.service.AdminSuggestionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/suggestions")
public class AdminSuggestionController {

    private final AdminSuggestionService adminSuggestionService;

    @GetMapping
    public List<AdminSuggestion> getSuggestions() {
        return adminSuggestionService.getAllSuggestions();
    }

    @PutMapping("/change-status")
    public void changeStatus(@RequestParam Long id,
                                        @RequestParam AdminSuggestionStatus status) {
        adminSuggestionService.updateSuggestionStatus(id, status);
    }

    @PutMapping("/change-comment")
    public void changeStatus(@RequestParam Long id,
                                        @RequestParam String comment) {
        adminSuggestionService.updateAdminComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteSuggestion(@PathVariable Long id) {
        adminSuggestionService.deleteSuggestion(id);
    }

}
