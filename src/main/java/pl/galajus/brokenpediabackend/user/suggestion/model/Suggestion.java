package pl.galajus.brokenpediabackend.user.suggestion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length(min = 3, max = 18)
    private String author;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private SuggestionType type;
    @NotBlank
    @Length(min = 15, max = 5000)
    private String suggestion;
    @Enumerated(value = EnumType.STRING)
    private SuggestionStatus status;
    private String adminComment;
}
