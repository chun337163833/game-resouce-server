package org.shovelgame.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.shovelgame.domain.common.Entity;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJpaActiveRecord(persistenceUnit = "puMainDB")
public class OtherUser extends TxUser {
	private static final long serialVersionUID = -659958432687233728L;
}
