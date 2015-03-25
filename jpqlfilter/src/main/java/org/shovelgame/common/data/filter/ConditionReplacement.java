/**
 * 
 */
package org.shovelgame.common.data.filter;


/**
 * Nese informaci o nahrazeni podminky. Slouží k přidání dodatečných informací k podmínce - např. že
 * podmínka je vazbená na jinou entitu, nebo se má použít konvertor apod.
 * 
 * @author MacikJ
 */
public class ConditionReplacement {

  private String oldConditionName;
  
  private String newConditionName;

  
  /**
   * 
   * @param oldConditionName podmínka se kterou se pracuje zvenčí
   * @param newConditionName podmínka se kterou 
   */
  public ConditionReplacement(String oldConditionName, String newConditionName) {
    super();
    this.oldConditionName = oldConditionName;
    this.newConditionName = newConditionName;
  }


  public String getOldConditionName() {
    return oldConditionName;
  }

  
  public String getNewConditionName() {
    return newConditionName;
  }
  
  
  
}
