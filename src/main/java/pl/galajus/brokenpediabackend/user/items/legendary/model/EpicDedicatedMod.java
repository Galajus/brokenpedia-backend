package pl.galajus.brokenpediabackend.user.items.legendary.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;

@Entity
@Getter
public class EpicDedicatedMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String epicName;
    @Enumerated(value = EnumType.STRING)
    private PsychoEffect effect;

}
