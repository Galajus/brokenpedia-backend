package pl.galajus.brokenpediabackend.admin.drif.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import pl.galajus.brokenpediabackend.admin.common.model.AdminPsychoEffect;

@Entity
@Table(name = "drif")
@Getter
public class AdminDrif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortName;
    private Double startPower;
    private Double psychoGrowByLevel;
    @Enumerated(value = EnumType.STRING)
    private AdminDrifCategory category;
    @Enumerated(value = EnumType.STRING)
    private AdminPsychoEffect psychoMod;
}
