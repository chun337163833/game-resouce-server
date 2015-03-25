package org.shovelgame.common.data.filter.querygenerator.condition;

import java.util.Date;
import java.util.GregorianCalendar;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;


/**
 * Generate lesser ('<=') condition. Sets time part to 23:59:59.999
 * 
 * @author haveljan
 */
public class LesserOrEqualDate extends SimpleConditionGenerator {

  /**
   * Construct condition.
   */

  public LesserOrEqualDate() {
    super("<=");
  }

  @Override
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
   GregorianCalendar tmp = new GregorianCalendar();
   tmp.setTime((Date) value);
   tmp.set(GregorianCalendar.HOUR_OF_DAY, 23);
   tmp.set(GregorianCalendar.MINUTE, 59);
   tmp.set(GregorianCalendar.SECOND, 59);
   tmp.set(GregorianCalendar.MILLISECOND, 999);
   
    super.generateCondition(context, conditionName, tmp.getTime());
  }
}

