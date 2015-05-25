package org.shovelgame.game.domain.model;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.enumeration.ItemType;
import org.shovelgame.game.domain.i18n.ItemModelDescription;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
//@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "item_model", schema = "model", sequenceName = "model.item_model_id_seq")
@RooToString(excludeFields = { "items", "itemModelDescriptions", "itemEnchantments", "attributeType", "qualityGrade", "missionRewardItems", "missionRewards" })
public class ItemModel {

    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ItemType type;

    @OneToMany(mappedBy = "itemModel")
    private Set<ItemModelDescription> itemModelDescriptions;

    @OneToMany(mappedBy = "itemModel")
    private Set<ItemEnchantment> itemEnchantments;

    @ManyToOne
    @JoinColumn(name = "attribute_type", referencedColumnName = "id", nullable = false)
    private AttributeType attributeType;

    @Column(name = "value", precision = 10, scale = 5)
    @NotNull
    private BigDecimal value;

    @Column(name = "max_enchant")
    private Integer maxEnchant;

    @Column(name = "icon_name", length = 50)
    @NotNull
    private String iconName;

    @Column(name = "price")
    private Integer price;
}
