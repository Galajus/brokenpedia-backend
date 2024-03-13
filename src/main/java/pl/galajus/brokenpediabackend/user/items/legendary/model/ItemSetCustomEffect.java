package pl.galajus.brokenpediabackend.user.items.legendary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
@Entity
@Getter
public class ItemSetCustomEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String effect;
    private Float value;
    private Long itemSetId;
}
