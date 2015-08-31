package org.shovelgame.game.domain.data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "texture_group", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "textures" })
@NamedQueries({
		@NamedQuery(name = "findTexturGroupsGreaterThenVersionGroupByTextureGroup", query = "select e from TextureGroup e left join e.textures as texture where texture.version.value > :version group by e.id") })
public class TextureGroup {

	@Transient
	public static final String GROUP_CLASSES = "classes";
	@Transient
	public static final String GROUP_BACKGROUNDS = "backgrounds";
	@Transient
	public static final String GROUP_HUD = "hud";
	@Transient
	public static final String GROUP_ICONS = "icons";
	@Transient
	public static final String GROUP_SKILLS = "skills";

	public static List<TextureGroup> findTexturGroupsGreaterThenVersionGroupByTextureGroup(BigDecimal version) {
		TypedQuery<TextureGroup> query = entityManager()
				.createNamedQuery("findTexturGroupsGreaterThenVersionGroupByTextureGroup", TextureGroup.class);
		query.setParameter("version", version);
		return query.getResultList();
	}

}
