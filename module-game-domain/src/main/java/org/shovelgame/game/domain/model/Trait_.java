package org.shovelgame.game.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;
import org.shovelgame.game.domain.enumeration.TraitAlgorithm;

@Generated(value="Dali", date="2015-05-21T19:52:22.878+0200")
@StaticMetamodel(Trait.class)
public class Trait_ {
	public static volatile SingularAttribute<Trait, SkillAlgorithm> affectedSkillAlg;
	public static volatile SingularAttribute<Trait, TraitAlgorithm> alg;
}
