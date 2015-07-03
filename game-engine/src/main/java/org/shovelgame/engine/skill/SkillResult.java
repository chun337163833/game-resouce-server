package org.shovelgame.engine.skill;

import java.math.BigDecimal;

import org.shovelgame.engine.session.command.BigData;
import org.shovelgame.engine.session.command.parameters.UseSkillParameters;

public class SkillResult implements BigData {

	private BigDecimal resultValue;
	private BigDecimal resistedValue;
	private UseSkillParameters metadata;
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

	public UseSkillParameters getMetadata() {
		return metadata;
	}

	public void setMetadata(UseSkillParameters metadata) {
		this.metadata = metadata;
	}
}
