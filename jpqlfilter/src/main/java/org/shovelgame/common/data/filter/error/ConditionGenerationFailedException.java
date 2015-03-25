/**
 * 
 */
package org.shovelgame.common.data.filter.error;

import java.util.List;


/**
 * @author MacikJ
 *
 */
public class ConditionGenerationFailedException extends Exception {

  private List<ConditionError> brokenConditions;

  public ConditionGenerationFailedException(String message, List<ConditionError> brokenConditions) {
    super(message);
    this.brokenConditions = brokenConditions;
  }
 
  
  public List<ConditionError> getBrokenConditions() {
    return brokenConditions;
  }
  
  
}
