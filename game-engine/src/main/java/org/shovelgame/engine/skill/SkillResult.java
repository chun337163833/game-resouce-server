package org.shovelgame.engine.skill;

import java.math.BigDecimal;

import org.shovelgame.engine.session.command.BigData;
import org.shovelgame.engine.session.command.parameters.UseSkillParameters;

public class SkillResult implements BigData {

	private BigDecimal resultValue;
	private BigDecimal resistedValue;
	private UseSkillParameters metadata;
	private boolean critical;
	private boolean swap;
	private boolean overtime;
	private boolean tick;
	private boolean temporary;
	private boolean resisted;

	public BigDecimal getResultValue() {
		return resultValue;
	}

	public void setResultValue(BigDecimal resultValue) {
		this.resultValue = resultValue;
	}

	public BigDecimal getResistedValue() {
		return resistedValue;
	}

	public void setResistedValue(BigDecimal resistedValue) {
		this.resistedValue = resistedValue;
	}

	public boolean isCritical() {
		return critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	public UseSkillParameters getMetadata() {
		return metadata;
	}

	public void setMetadata(UseSkillParameters metadata) {
		this.metadata = metadata;
	}

	public boolean isSwap() {
		return swap;
	}

	public void setSwap(boolean swap) {
		this.swap = swap;
	}

	public boolean isOvertime() {
		return overtime;
	}

	public void setOvertime(boolean overtime) {
		this.overtime = overtime;
	}

	public boolean isTick() {
		return tick;
	}

	public void setTick(boolean tick) {
		this.tick = tick;
	}

	public boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

	public boolean isResisted() {
		return resisted;
	}

	public void setResisted(boolean resisted) {
		this.resisted = resisted;
	}

}
