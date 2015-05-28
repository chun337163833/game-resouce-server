package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.RewardInterface;
import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.enumeration.ItemType;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.finders.Reward;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(finders = { "findItemModelsByRarityAndType" }, versionField = "", table = "item_model", schema = "model", sequenceName = "model.item_model_id_seq")
@RooToString(excludeFields = { "items", "itemModelDescriptions", "itemEnchantments", "attributeType", "qualityGrade", "missionRewardItems", "missionRewards" })
@RewardInterface
public class ItemModel {

    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ItemType type;

    @Transient
    private Set<MissionReward> missionRewards;

    @Column(name = "rarity", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @Override
    @Transactional
    public void claim(Player player) {
        Item item = new Item();
        item.setOwner(player);
        item.setItemModel(this);
        item.persist();
    }
}
