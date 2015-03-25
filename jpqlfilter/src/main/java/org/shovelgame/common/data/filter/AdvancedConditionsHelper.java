/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Helper for simplified usage of <code>AdvancedConditions</code>.
 * @author MacikJ
 */
@Deprecated
public abstract class AdvancedConditionsHelper implements AdvancedConditions {

  private static final Logger LOG = LoggerFactory.getLogger( AdvancedConditionsHelper.class );

  private Map<String, String> conditions = new HashMap<String, String>();
  
    
  /** Getter for field conditions.
   * @return the conditions
   */
  public Map<String, String> getConditions() {
    return conditions;
  }
  
}
