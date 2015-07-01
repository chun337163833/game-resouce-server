package org.shovelgame.engine.skill;

import java.math.BigDecimal;

import org.shovelgame.engine.session.command.BigData;

public class SkillResult implements BigData {

	private BigDecimal resultValue;
	private BigDecimal resistedValue;

	private boolean critical;

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

}
