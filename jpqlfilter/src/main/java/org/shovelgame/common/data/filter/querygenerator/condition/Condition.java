/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

/**
 * Represents one condition. Can be used for implementation of non trivial conditions and special
 * cases.
 * 
 * @author MacikJ
 */
public interface Condition {

  /**
   * Getter for conditionGenerator field
   * 
   * @return condition generator to generate current condition.
   */
  ConditionGenerator getConditionGenerator();

  /**
   * Getter for field value
   * 
   * @return value of condition
   */
  Object getValue();

  /**
   * Getter for name field
   * 
   * @return name of condition
   */
  String getName();

}
