package org.shovelgame.game.domain.enumeration;

public enum AttributeManagedType {
	
	
	
	/**
	 * Total health of character. When health fall equal or under zero, character died.
	 */
	Health(null), 
	
	/**
	 * Total mana of character which is used of most skills.
	 */
	Mana(null),
	
	/**
	 * Power of skills which are based on PHYSICALPOWER
	 */
	PhysicalPower(SkillType.PhysicalPower),
	
	/**
	 * Determine chance of critical strike for skills based on PHYSICALPOWER
	 */
	PhysicalCritChance(SkillType.PhysicalPower),
	
	/**
	 * Decrease PHYSICALRESISTANCE of enemy before total damage is decreased.
	 */
	PhysicalPenetration(SkillType.PhysicalPower),
	
	/**
	 * Decrease total calculated physical damage.
	 */
	PhysicalResistance(SkillType.PhysicalPower),
	
	/**
	 * Decrease PHYSICALCRITCHANCE of enemy before is calculated.
	 */
	PhysicalCritResistance(SkillType.PhysicalPower),
	
	/**
	 * Power of skills which are based on SPELLPOWER
	 */
	SpellPower(SkillType.SpellPower),
	
	/**
	 * Determine chance of critical strike for skills based on SPELLPOWER
	 */
	SpellCritChance(SkillType.SpellPower),
	
	/**
	 * Decrease SPELLRESISTANCE of enemy before total damage is decreased.
	 */
	SpellPenetration(SkillType.SpellPower),
	
	/**
	 * Decrease total calculated spell damage.
	 */
	SpellResistance(SkillType.SpellPower),
	
	/**
	 * Decrease SPELLCRITCHANCE of enemy before is calculated.
	 */
	SpellCritResistance(SkillType.SpellPower),
	
	/**
	 * Increase total damage when strike is critical.
	 */
	CriticalDamage(null);
	
	private SkillType affection;
	
	
	private AttributeManagedType(SkillType affection) {
		this.affection = affection;
	}


	public SkillType getAffection() {
		return affection;
	}
	
}
