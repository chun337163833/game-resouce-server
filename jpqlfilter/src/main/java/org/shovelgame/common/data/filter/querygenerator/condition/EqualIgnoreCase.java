package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate LIKE condition with % on left and right side of value (columnName LIKE '%value%').
 * 
 * @author MacikJ
 * 
 */
public class EqualIgnoreCase extends AbstractConditionGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(EqualIgnoreCase.class);
          
  /**
   * generateCondition method
   * 
   * @param context - ConditionGeneratorContext
   * @param conditionName - String
   * @param value - Object
   * 
   */
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
    int paramNumber = context.nextValueId();
    String paramId = "c" + paramNumber;
    String lValue = String.valueOf(value).toLowerCase();
    
    context.addWherePartCondition(conditionName, "LOWER(" +conditionName + ") = :" + paramId);
    context.addValue(paramId, lValue);
  }

}
