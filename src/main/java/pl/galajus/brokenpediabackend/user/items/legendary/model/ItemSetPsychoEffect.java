package pl.galajus.brokenpediabackend.user.items.legendary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.common.model.PsychoEffect;

@Entity
@Getter
public class ItemSetPsychoEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PsychoEffect effect;
    private Float value;
    private Long itemSetId;

}
