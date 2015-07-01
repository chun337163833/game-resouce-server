package org.shovelgame.engine.skill;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Skill {
	SkillAlgorithm value();
}
