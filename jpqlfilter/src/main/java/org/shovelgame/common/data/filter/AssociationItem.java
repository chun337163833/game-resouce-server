/**
 * 
 */
package org.shovelgame.common.data.filter;

/**
 * Jeden join v celé asociaci.
 * 
 * @author MacikJ
 */
public class AssociationItem {

  private String property;

  private String alias;

  private AssociationType associationType = AssociationType.LEFT_JOIN;

  /**
   * 
   * @param property jaka property reprezentuje vazu 
   * @param alias alias vazbene entity v query
   */
  public AssociationItem(String property, String alias) {
    super();
    this.property = property;

    this.alias = alias;
  }

  public AssociationItem(String property, String alias, AssociationType associationType) {
    super();
    this.property = property;
    this.alias = alias;
    this.associationType = associationType;
  }

  public String getProperty() {
    return property;
  }

  public String getAlias() {
    return alias;
  }

  public AssociationType getAssociationType() {
    return associationType;
  }

}
