package pl.galajus.brokenpediabackend.admin.items.legendary.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.common.model.AdminProfession;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "item_set")
public class AdminItemSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private AdminProfession requiredClass;
    @OneToMany(mappedBy = "itemSet")
    private List<AdminLegendaryItem> setLegendaryItems;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemSetId")
    private List<AdminItemSetPsychoEffect> psychoEffects;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemSetId")
    private List<AdminItemSetCustomEffect> customEffects;


    public void removeSetLegendaryItems() {
        setLegendaryItems.forEach(i -> i.removeItemSet(this));
    }
}
