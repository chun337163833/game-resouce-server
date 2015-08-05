package org.shovelgame.engine.skill;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;
import org.shovelgame.utils.ReflectionUtils;
import org.shovelgame.utils.StringUtils;

public class SkillExecutor {

	private String scanPackage;
	private Map<SkillAlgorithm, Class<ISkill>> skills;
	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}
	
	@PostConstruct
	@SuppressWarnings("unchecked")
	public void scan() throws Exception {		
		if(StringUtils.isEmpty(this.scanPackage)) {
			throw new SkillScanningException("Scan package cannot be null.");
		}
		this.skills = new HashMap<>();
		List<Class<?>> classesForScan = ReflectionUtils.findClasses(Skill.class, this.scanPackage);
		
		for(Class<?> cls: classesForScan) {
			Skill ann = cls.getDeclaredAnnotation(Skill.class);
			this.skills.put(ann.value(), (Class<ISkill>) cls);
		}
	}
	public SkillResult execute(BattleSkill skill, BattleMinion source, BattleMinion target) throws SkillUsageException {
		try {
			ISkill iSkill = instantiate(skill);
			return iSkill.process(source, target);
		} catch (Exception e) {
			throw new SkillUsageException(e);
		}
	}
	
	private ISkill instantiate(BattleSkill skill) throws Exception {
		SkillAlgorithm alg = skill.getMinionSkill().getSkill().getAlg();
		Class<ISkill> cls = this.skills.get(alg);
		try {
			if(cls == null) {
				throw new Exception(String.format("Skill definition for algorithm %s not exist.", alg));
			}
			Constructor<ISkill> constructor = cls.getConstructor(BattleSkill.class);
			return constructor.newInstance(skill);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new Exception(String.format("Constructor of class %s with argument of type %s not found or not visible", cls.getName(), BattleSkill.class.getName()));
		}
	}
	
}
