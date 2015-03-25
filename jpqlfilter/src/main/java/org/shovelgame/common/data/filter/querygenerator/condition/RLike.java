package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate LIKE condition with % on right side of value (columnName LIKE 'value%').
 * Pokud jmeno property obsahuje ',' bude to povazovano za oddelovac property a 
 * bude vygenerovana podminka ve tvaru (columnName1 LIKE 'value%' OR columnName2 LIKE 'value%')
 * 
 * @author MacikJ
 * 
 */
public class RLike extends AbstractConditionGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(RLike.class);
          
  protected boolean addToLeft = false;
  protected boolean addToRight = true;
  
  /**
   * generateCondition method
   * 
   * @param context - ConditionGeneratorContext
   * @param lConditionName - String
   * @param value - Object
   * 
   */
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
    String[] conditionNames = conditionName.split("[,]");
        
    int paramNumber = context.nextValueId();
    String paramId = "c" + paramNumber;
    String lValue = String.valueOf(value).replace('*', '%').toLowerCase();
    
    if (addToRight) {
      if (!lValue.endsWith("%")) {
        lValue = lValue + "%";
      }
    }
    if (addToLeft) {
      if (!lValue.startsWith("%")) {
        lValue = "%" + lValue;
      }
    }
    
    StringBuilder hqlPart = new StringBuilder("(");
    for (int i=0, m=conditionNames.length; i<m; i++ ) {
      String lConditionName = conditionNames[i];
      lConditionName = lConditionName.trim();      
      hqlPart.append("LOWER(" +lConditionName + ") LIKE :" + paramId);      
      context.addValue(paramId, lValue);
      if ((i+1) < m) {
        hqlPart.append(" OR ");
      }
    }
    hqlPart.append(")");
    context.addWherePartCondition(conditionName, hqlPart.toString());    
  }

}
