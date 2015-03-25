package org.shovelgame.common.data.filter.querygenerator.condition;

import java.util.Date;
import java.util.GregorianCalendar;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;


/**
 * Generate greater ('>=') condition. Sets time part to 0:0
 * 
 * @author haveljan
 */
public class GreaterOrEqualDate extends SimpleConditionGenerator {

  /**
   * Construct condition.
   */

  public GreaterOrEqualDate() {
    super(">=");
  }

  @Override
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
    GregorianCalendar tmp = new GregorianCalendar();
    tmp.setTime((Date) value);
    tmp.set(GregorianCalendar.HOUR_OF_DAY, 0);
    tmp.set(GregorianCalendar.MINUTE, 0);
    tmp.set(GregorianCalendar.SECOND, 0);
    tmp.set(GregorianCalendar.MILLISECOND, 0);
   
    super.generateCondition(context, conditionName, tmp.getTime());
  }
}

