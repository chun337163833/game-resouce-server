package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.shovelgame.game.domain.enumeration.Position;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "trait" })
@RooJpaActiveRecord(versionField = "", table = "trait_target", schema = "model", sequenceName = "model.trait_target_id_seq")
public class TraitTarget {

    @Column(name = "position", length = 50)
    @NotNull
    @Enumerated
    private Position position;
}
