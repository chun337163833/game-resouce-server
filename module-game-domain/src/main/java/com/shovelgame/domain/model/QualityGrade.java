package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.Grade;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "quality_grade", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroModels", "minionModels", "seekerModels", "itemModels" })
public class QualityGrade {

    @Id
    @Column(name = "id")
    @Enumerated
    private Grade grade;
}
