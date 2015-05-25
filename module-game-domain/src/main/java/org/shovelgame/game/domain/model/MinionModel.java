package org.shovelgame.game.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
// @RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "minion_model", schema = "model", sequenceName = "model.minion_model_id_seq")
@RooToString(excludeFields = { "minions", "minionSkills", "minionTraits",
		"attributes", "qualityGrade", "minionAttributes", "specialization",
		"missionRewardMinions", "missionRewards" })
public class MinionModel {

	@OneToMany(mappedBy = "minionModel")
	private Set<MinionAttribute> minionAttributes;

	@OneToMany(mappedBy = "minionModel")
	private Set<MinionSkill> minionSkills;

	@OneToMany(mappedBy = "minionModel")
	private Set<MinionTrait> minionTraits;

	@ManyToOne
	@JoinColumn(name = "specialization", referencedColumnName = "id", nullable = false)
	private MinionSpecialization specialization;

	@Column(name = "name", length = 50)
	@NotNull
	private String name;

	@Column(name = "image_bundle_name", length = 50)
	@NotNull
	private String imageBundleName;

	@Column(name = "price")
	private Integer price;
}
