package org.shovelgame.game.domain.i18n;
import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "language", schema = "i18n")
@RooToString(excludeFields = { "attributeTypeDescriptions", "enchantmentTypeDescriptions", "heroTypeDescriptions", "itemModelDescriptions", "missionDescriptions", "skillDescriptions", "specializationDescriptions", "traitDescriptions", "minionSpecializationDescriptions", "seekerSpecializationDescriptions" })
public class Language {

    @Id
    @Column(name = "id", length = 2)
    private String id;
}
