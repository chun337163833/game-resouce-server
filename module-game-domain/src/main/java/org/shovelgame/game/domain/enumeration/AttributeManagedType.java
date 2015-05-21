package org.shovelgame.game.domain.enumeration;

public enum AttributeManagedType {
	
	/**
	 * Total health of character. When health fall equal or under zero, character died.
	 */
	Health, 
	
	/**
	 * Total mana of character which is used of most skills.
	 */
	Mana,
	
	/**
	 * Power of skills which are based on PHYSICALPOWER
	 */
	PhysicalPower,
	
	/**
	 * Determine chance of critical strike for skills based on PHYSICALPOWER
	 */
	PhysicalCritChance,
	
	/**
	 * Decrease PHYSICALRESISTANCE of enemy before total damage is decreased.
	 */
	PhysicalPenetration,
	
	/**
	 * Decrease total calculated physical damage.
	 */
	PhysicalResistance,
	
	/**
	 * Decrease PHYSICALCRITCHANCE of enemy before is calculated.
	 */
	PhysicalCritResistance,
	
	/**
	 * Power of skills which are based on SPELLPOWER
	 */
	SpellPower,
	
	/**
	 * Determine chance of critical strike for skills based on SPELLPOWER
	 */
	SpellCritChance,
	
	/**
	 * Decrease SPELLRESISTANCE of enemy before total damage is decreased.
	 */
	SpellPenetration,
	
	/**
	 * Decrease total calculated spell damage.
	 */
	SpellResistance,
	
	/**
	 * Decrease SPELLCRITCHANCE of enemy before is calculated.
	 */
	SpellCritResistance,
	
	/**
	 * Increase total damage when strike is critical.
	 */
	CriticalDamage,
	
	
}
