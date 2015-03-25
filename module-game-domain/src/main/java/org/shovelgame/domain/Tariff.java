package org.shovelgame.domain;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.shovelgame.domain.annotation.EntityInterface;
import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.domain.modification.ModificationInfoListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@RooJavaBean
@RooJpaActiveRecord(finders={"findTariffsByBasicDiscount"}, persistenceUnit = "puMainDB")
@Modifiable
@EntityInterface
public class Tariff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1, max=30)
	private String tariffName;
	
	@NotNull
	private Integer discount;
	
	private Boolean basicDiscount;
	
	@AssertFalse(message="{validation.tariff.basicDiscountExist}")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean isBasicDiscountExist() {
		if(basicDiscount) {
			return !CollectionUtils.isEmpty(Tariff.findTariffsByBasicDiscount(true).getResultList());
		}
		return false;
	}
	
}
