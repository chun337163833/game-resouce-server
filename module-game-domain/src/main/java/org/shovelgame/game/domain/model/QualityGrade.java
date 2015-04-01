package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import org.shovelgame.game.domain.enumaration.Grade;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "quality_grade", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroModels", "itemModels", "minionModels", "seekerModels" })
public class QualityGrade {

    @Id
    @Column(name = "id")
    @Enumerated
    private Grade id;
}
