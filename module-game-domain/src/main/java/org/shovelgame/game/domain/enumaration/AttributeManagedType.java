package org.shovelgame.game.domain.enumaration;

public enum AttributeManagedType {
	
	/**
	 * Total health of character. When health fall equal or under zero, character died.
	 */
	HEALTH, 
	
	/**
	 * Total mana of character which is used of most skills.
	 */
	MANA,
	
	/**
	 * Power of skills which are based on PHYSICAL_POWER
	 */
	PHYSICAL_POWER,
	
	/**
	 * Determine chance of critical strike for skills based on PHYSICAL_POWER
	 */
	PHYSICAL_CRIT_CHANCE,
	
	/**
	 * Decrease PHYSICAL_RESISTANCE of enemy before total damage is decreased.
	 */
	PHYSICAL_PENETRATION,
	
	/**
	 * Decrease total calculated physical damage.
	 */
	PHYSICAL_RESISTANCE,
	
	/**
	 * Decrease PHYSICAL_CRIT_CHANCE of enemy before is calculated.
	 */
	PHYSICAL_CRIT_RESISTANCE,
	
	/**
	 * Power of skills which are based on SPELL_POWER
	 */
	SPELL_POWER,
	
	/**
	 * Determine chance of critical strike for skills based on SPELL_POWER
	 */
	SPELL_CRIT_CHANCE,
	
	/**
	 * Decrease SPELL_RESISTANCE of enemy before total damage is decreased.
	 */
	SPELL_PENETRATION,
	
	/**
	 * Decrease total calculated spell damage.
	 */
	SPELL_RESISTANCE,
	
	/**
	 * Decrease SPELL_CRIT_CHANCE of enemy before is calculated.
	 */
	SPELL_CRIT_RESISTANCE,
	
	/**
	 * Increase total damage when strike is critical.
	 */
	CRITICAL_DAMAGE,
	
	
}
