package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate LIKE condition with % on left and right side of value (columnName LIKE '%value%') 
 * and remove spaces from condition.
 * 
 * @author MacikJ
 * 
 */
public class LRLikeStripSpaces extends AbstractConditionGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(LRLikeStripSpaces.class);
          
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
    String lValue = String.valueOf(value).replace('*', '%').toLowerCase();
    if (!lValue.endsWith("%")) {
      lValue = lValue + "%";
    }
    if (!lValue.startsWith("%")) {
      lValue = "%" + lValue;
    }
    lValue = lValue.replace(" ", "");
    context.addWherePartCondition(conditionName, "LOWER(" +conditionName + ") LIKE :" + paramId);
    context.addValue(paramId, lValue);
  }

}
