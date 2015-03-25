/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.HashMap;
import java.util.Map;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.shovelgame.common.data.filter.querygenerator.condition.Condition;
import org.shovelgame.common.data.filter.querygenerator.condition.ConditionGenerator;


/**
 * @author MacikJ
 *
 */
public class CustomCondition implements Condition, ConditionGenerator {
  
  public String conditionId;
  public String hqlWherePartCondition;
  public Map<String, Object> values;
  
  public CustomCondition(String conditionId, String hqlWherePartCondition) {
    this(conditionId, hqlWherePartCondition, new HashMap<String, Object>(0));    
  }
  
  public CustomCondition(String conditionId, String hqlWherePartCondition, Map<String, Object> values) {
    this.conditionId = conditionId;
    this.hqlWherePartCondition = hqlWherePartCondition;
    this.values = values;
  }

  @Override
  public String getName() {    
    return conditionId;
  }
  
  @Override
  public Object getValue() {   
    return null;
  }
  
  
  @Override
  public ConditionGenerator getConditionGenerator() {    
    return this;
  }
  
  @Override
  public void generateCondition(ConditionGeneratorContext context, String conditionName, Object value) {            
    context.addWherePartCondition(conditionId, hqlWherePartCondition);   
    for (Map.Entry<String, Object> me: values.entrySet()) {
      context.addValue(me.getKey(), me.getValue());
    }    
  }
  
}
