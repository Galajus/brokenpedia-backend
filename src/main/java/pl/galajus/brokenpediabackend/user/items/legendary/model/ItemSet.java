package pl.galajus.brokenpediabackend.user.items.legendary.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import pl.galajus.brokenpediabackend.user.common.model.Profession;

import java.util.List;

@Entity
@Getter
public class ItemSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Profession requiredClass;
    @OneToMany(mappedBy = "itemSet")
    private List<LegendaryItem> setLegendaryItems;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemSetId")
    private List<ItemSetPsychoEffect> psychoEffects;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemSetId")
    private List<ItemSetCustomEffect> customEffects;


}
