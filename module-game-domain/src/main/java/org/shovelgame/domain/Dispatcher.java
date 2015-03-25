package org.shovelgame.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.shovelgame.domain.common.Entity;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJpaActiveRecord(persistenceUnit = "puMainDB")
public class Dispatcher extends TxUser {
	private static final long serialVersionUID = -359958422687238729L;
}
