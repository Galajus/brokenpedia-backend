package pl.galajus.brokenpediabackend.user.drif.model;

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
public class Drif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortName;
    private Double startPower;
    private Double psychoGrowByLevel;
    @Enumerated(value = EnumType.STRING)
    private DrifCategory category;
    @Enumerated(value = EnumType.STRING)
    private PsychoEffect psychoMod;
}
