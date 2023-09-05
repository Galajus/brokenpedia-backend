package pl.galajus.brokenpediabackend.admin.suggestion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "suggestion")
public class AdminSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    @Enumerated(value = EnumType.STRING)
    private AdminSuggestionType type;
    private String suggestion;
    @Enumerated(value = EnumType.STRING)
    private AdminSuggestionStatus status;
    private String adminComment;
}
