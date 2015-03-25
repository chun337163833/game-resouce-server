package org.shovelgame.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.shovelgame.domain.annotation.EntityInterface;
import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@RooJavaBean
@RooJpaActiveRecord(finders = {"findTxUsersByEmail","findTxUsersByUserName","findTxUsersByCellPhone"}, persistenceUnit = "puMainDB", inheritanceType="SINGLE_TABLE", table="tx_user")
@Modifiable
@Table(indexes= {
		@Index(columnList="cellPhone", name = "ix_tx_user_cellphone"),
})
@EntityInterface
public abstract class TxUser implements Serializable {
	private static final long serialVersionUID = -659958432687233729L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 30)
	private String userName;
	
	private String password;	
	
	@NotNull
	@Size(min = 1, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 50)
	private String lastName;
	private Long cellPhone;
	
//	@NotNull
	@Size(min = 1, max = 50)
	@Email
	private String email;

	@ManyToMany
	private List<Role> roles = new ArrayList<>();
	
	@Resource(name = "passwordEncoder")
	@Transient
	private ShaPasswordEncoder encoder;

	@Value("${sha.salt}")
	@Transient
	private String salt;	
	
	@Transient
	private String matchPassword;

	@PrePersist
	public void hashPassword() {
		this.password = this.encoder.encodePassword(this.password, this.salt);
		this.matchPassword = this.encoder.encodePassword(this.matchPassword, this.salt);
	}
	
	@AssertFalse(message = "{validation.user.exist}")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean isUserExist() {
		if (StringUtils.isEmpty(this.userName))
			return false;
		List<TxUser> users = TxUser.findTxUsersByUserName(this.userName).getResultList();
		if (users != null && !users.isEmpty() && id==null) {
			return true;
		}
		return false;
	}	
	
	@AssertTrue(message = "{validation.password.notmatch}")
	public boolean isPasswordsMatch() {
		if (!StringUtils.isEmpty(this.password) && !StringUtils.isEmpty(this.matchPassword)) {
			return this.password.equals(this.matchPassword);
		}
		return true;
	}
}
