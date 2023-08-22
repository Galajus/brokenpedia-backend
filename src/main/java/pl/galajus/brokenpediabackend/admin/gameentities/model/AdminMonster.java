package pl.galajus.brokenpediabackend.admin.gameentities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminLegendaryItem;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "monster")
public class AdminMonster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private AdminMonsterType type;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, mappedBy = "droppingMonsters")
    @JsonIgnore
    private List<AdminLegendaryItem> legendaryDrops;

    public void removeLegendaryDrops() {
        this.legendaryDrops.forEach(drop -> drop.removeDroppingMonster(this));
        this.legendaryDrops.clear();
    }
}
