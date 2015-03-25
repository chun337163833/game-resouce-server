package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;


/**
 * Generate greater ('>') condition.
 * 
 * @author MacikJ
 */
public class EqualDateWithoutTime extends AbstractConditionGenerator {

  /**
   * Construct condition.
   */

  public EqualDateWithoutTime() {
    
  }

  public void generateCondition(ConditionGeneratorContext context, String conditionName, Object value) {
    int paramNumber = context.nextValueId();
    String paramId = "c" + paramNumber;
    context.addWherePartCondition(
        conditionName, "trunc(" + conditionName  + ") = trunc(" + " :" + paramId + ")");
    context.addValue(paramId, value);    
  }
  
}
