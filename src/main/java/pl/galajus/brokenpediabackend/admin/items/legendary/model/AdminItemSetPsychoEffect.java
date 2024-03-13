package pl.galajus.brokenpediabackend.admin.items.legendary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;

@Entity
@Getter
@Table(name = "item_set_psycho_effect")
public class AdminItemSetPsychoEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PsychoEffect effect;
    private Float value;
}
