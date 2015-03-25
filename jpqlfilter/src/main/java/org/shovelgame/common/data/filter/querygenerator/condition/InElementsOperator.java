/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import java.util.Collection;
import java.util.Iterator;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;

/**
 * Implementation of IN operator
 * 
 * @author MacikJ
 * 
 */
public class InElementsOperator extends AbstractConditionGenerator {

 

  /**
   * Constructor
   * 
   */
  public InElementsOperator() {
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
    if (value instanceof Collection) {
      Collection values = (Collection)value;
      context.addOrGroup("InElementsOperator_" + conditionName, new String[]{conditionName});
      int i=0;
      for (Iterator iterator = values.iterator(); iterator.hasNext();) {
        Object object = (Object) iterator.next();
        String paramId = "c" + paramNumber + "_" + i;
        context.addWherePartCondition(
            conditionName, " :" + paramId + " " + "in elements (" + conditionName + ")");
        context.addValue(paramId, object);
        i++;
      }      
    } else {
      String paramId = "c" + paramNumber;
      context.addWherePartCondition(
          conditionName, " :" + paramId + " " + "in elements (" + conditionName + ")");
      context.addValue(paramId, value);
    }
    
  }

}
