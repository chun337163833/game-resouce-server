/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.List;

import org.shovelgame.common.data.filter.querygenerator.condition.Condition;


/**
 * Interface for class that is used as part of filter to realize advanced conditions.
 * 
 * @author MacikJ
 */
public interface AdvancedConditions {

  /**
   * conditions method
   * 
   * @return List of advanced conditions.
   */
  List<Condition> conditions();

  /**
   * reset advanced filter
   */
  void clear();
  
  void add(Condition condition);

}
