/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;



/**
 * Simplify {@see Condition} usage.
 * @author MacikJ
 *
 */
public class ConditionHelper implements Condition {

  private ConditionGenerator conditionGenerator;
  
  private Object value;
  
  private String name;

  
  
  
  
  /**
   * Constructor.
   * @param conditionGenerator generator context
   * @param value value of condition
   * @param name name of column on which is condition
   */
  public ConditionHelper( ConditionGenerator conditionGenerator, String name, Object value ) {
    super();
    this.conditionGenerator = conditionGenerator;
    this.value = value;
    this.name = name;
  }





  
  /** Getter for field conditionGenerator.
   * @return the conditionGenerator
   */
  public ConditionGenerator getConditionGenerator() {
    return conditionGenerator;
  }





  
  /** Getter for field value.
   * @return the value
   */
  public Object getValue() {
    return value;
  }





  
  /** Getter for field name.
   * @return the name
   */
  public String getName() {
    return name;
  }


  
  
}
