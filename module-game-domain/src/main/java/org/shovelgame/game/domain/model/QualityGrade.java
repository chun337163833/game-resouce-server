package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import org.shovelgame.game.domain.enumeration.Grade;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroModels", "itemModels", "minionModels", "seekerModels" })
@RooJpaActiveRecord(versionField = "", table = "quality_grade", schema = "model")
public class QualityGrade {

    @Id
    @Column(name = "id")
    @Enumerated(EnumType.STRING)
    private Grade id;
}
