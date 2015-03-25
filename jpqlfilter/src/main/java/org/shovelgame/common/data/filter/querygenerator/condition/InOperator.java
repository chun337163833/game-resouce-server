/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;

/**
 * Implementation of IN operator
 * 
 * @author MacikJ
 * 
 */
public class InOperator extends AbstractConditionGenerator {

 

  /**
   * Constructor
   * 
   */
  public InOperator() {
    super();

  }
  
  /**
   * generateCondition method
   * 
   * @param context - ConditionGeneratorContext
   * @param conditionName - String
   * @param value - Object
   */
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
    int paramNumber = context.nextValueId();
    String paramId = "c" + paramNumber;
    context.addWherePartCondition(
        conditionName, conditionName + " " + "in ( " + " :" + paramId + " )");
    context.addValue(paramId, value);
  }

}
