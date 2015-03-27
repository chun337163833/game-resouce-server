package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.HeroTypeCode;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "hero_type", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroModels", "heroTypeDescriptions" })
public class HeroType {

    @Column(name = "code", length = 50, unique = true)
    @NotNull
    @Enumerated
    private HeroTypeCode code;
}
