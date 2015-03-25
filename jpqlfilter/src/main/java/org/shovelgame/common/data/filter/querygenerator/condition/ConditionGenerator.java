/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;

/**
 * Interface for condition (in query) generator. 
 * 
 * @author MacikJ
 */
public interface ConditionGenerator {

  /**
   * generateCondition method
   * 
   * @param context - ConditionGeneratorContext
   * @param conditionName - String
   * @param value - Object
   * 
   */
  void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value);

}
