package pl.galajus.brokenpediabackend.admin.items.legendary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminDamageType;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLegendaryItemDto {

    private Long id;
    private String name;
    private AdminItemType type;
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

}
