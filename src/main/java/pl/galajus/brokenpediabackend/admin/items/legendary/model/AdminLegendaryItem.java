package pl.galajus.brokenpediabackend.admin.items.legendary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import pl.galajus.brokenpediabackend.admin.gameentities.model.AdminMonster;

import java.util.List;

@Entity
@Getter
@Table(name = "legendary_item")
public class AdminLegendaryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "legendary_item_monster",
            joinColumns = @JoinColumn(name = "legendary_item_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_id"))
    private List<AdminMonster> droppingMonsters;
    @Enumerated(value = EnumType.STRING)
    private AdminItemType type;
    @Enumerated(value = EnumType.STRING)
    private AdminItemFamily family;
    @ManyToOne
    private AdminItemSet itemSet;
    private Integer weight;
    private Integer rank;
    private Integer capacity;
    private Long value;

    private Integer requiredLevel;
    private Integer requiredStrength;
    private Integer requiredDexterity;
    private Integer requiredPower;
    private Integer requiredKnowledge;
    private Integer damage;
    @Enumerated(value = EnumType.STRING)
    private AdminDamageType damageType;

    private Integer strength;
    private Integer dexterity;
    private Integer power;
    private Integer knowledge;
    private Integer health;
    private Integer mana;
    private Integer stamina;

    private Integer armorSlashing;
    private Integer armorCrushing;
    private Integer armorPiercing;
    private Integer mentalResistance;
    private Integer fireResistance;
    private Integer energyResistance;
    private Integer coldResistance;

    public void removeDroppingMonster(AdminMonster adminMonster) {
        this.droppingMonsters.remove(adminMonster);
    }
}
