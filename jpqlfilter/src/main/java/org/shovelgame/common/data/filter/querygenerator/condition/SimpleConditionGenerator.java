/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;

/**
 * Base class for simple conditions.
 * 
 * @author MacikJ
 * 
 */
public class SimpleConditionGenerator extends AbstractConditionGenerator {

  private String conditionSymbol;

  /**
   * Constructor
   * 
   * @param conditionSymbol symbol for condition (=, <=, ...)
   */
  public SimpleConditionGenerator(String conditionSymbol) {
    super();
    this.conditionSymbol = conditionSymbol;
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
        conditionName, conditionName + " " + this.conditionSymbol + " :" + paramId);
    context.addValue(paramId, value);
  }

}
