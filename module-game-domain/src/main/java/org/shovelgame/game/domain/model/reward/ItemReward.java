package org.shovelgame.game.domain.model.reward;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.shovelgame.game.domain.model.ItemModel;
import org.shovelgame.game.domain.model.MissionReward;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

//@RooJpaActiveRecord
//@RooJavaBean
public class ItemReward extends MissionReward {


//	@ManyToOne
//    @JoinColumn(name = "item", referencedColumnName = "id")
//    private ItemModel item;
	
}
