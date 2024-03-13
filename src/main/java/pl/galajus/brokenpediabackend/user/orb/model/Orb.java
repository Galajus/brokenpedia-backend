package pl.galajus.brokenpediabackend.user.orb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Orb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String effect;
    @Enumerated(value = EnumType.STRING)
    private OrbType type;
    private Double startBonus;
    private String shortName;
}
