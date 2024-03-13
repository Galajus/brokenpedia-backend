package pl.galajus.brokenpediabackend.admin.orb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "orb")
@Getter
public class AdminOrb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String effect;
    @Enumerated(value = EnumType.STRING)
    private AdminOrbType type;
    private Double startBonus;
    private String shortName;
}
