/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.shovelgame.common.data.filter.querygenerator.condition.Condition;


/**
 * @author MacikJ
 *
 */
public class AdvancedConditionsImpl implements AdvancedConditions, Serializable {

  private List<Condition> conditions = new ArrayList<Condition>();
  
  
  public void add(Condition condition) {
    conditions.add(condition);
  }
  
  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.AdvancedConditions#conditions()
   */
  @Override
  public List<Condition> conditions() {   
    return conditions;
  }

  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.AdvancedConditions#clear()
   */
  @Override
  public void clear() {
    conditions.clear();
  }

}
