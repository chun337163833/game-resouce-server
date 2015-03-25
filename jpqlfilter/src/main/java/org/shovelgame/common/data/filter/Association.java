/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reprezentuje asociaci (skupinu joinu) v query. Asociaci je potřeba nadefinovat pokud se filtruje, nebo
 * třídí přes jinou entitu než hlavní - z hlavní entity však musí vést asociace na tuto entitu.  
 * 
 * @author MacikJ
 */
public class Association {

  private List<AssociationItem> associations;

  private boolean optional = true;
  
  private Map<String,String> additionalConditions = new HashMap<String, String>(0);
  

  /**
   * Konstruktor který umožňuje vytvořit asociaci přes více entit.
   */
  public Association(List<AssociationItem> associations) {
    super();
    this.associations = associations;
  }

  /**
   * Slouží k vytvoření jednoho joinu z hlavní entity na entitu vazbenou pres
   * property <code>property</code> 
   */
  public Association(String property) {
    associations = new ArrayList<AssociationItem>();
    associations.add(new AssociationItem(property, property));
  }
  
  /**
   * Slouží k vytvoření jednoho joinu z hlavní entity na entitu vazbenou pres
   * property <code>property</code> která bude mít v 
   * query <code>alias</code> 
   */
  public Association(String property, String alias) {
    associations = new ArrayList<AssociationItem>();
    associations.add(new AssociationItem(property, alias));
  }
  
  /**
   * Nastaví zda bude vazba použita vždy nebo pouze pokud ji nějaká podmínka použije (default).
   */
  public Association setOptional(boolean optional) {
    this.optional = optional;
    return this;
  }
  
  /**
   * Prida dodatecnou podminku. Podminka bude pouzita pouze pokud bude pouzita i asociace. 
   * @param name
   * @param value
   * @return
   */
  public Association addAdditionalCondition(String name, String value) {
    this.additionalConditions.put(name, value);
    return this;
  }
  
  public List<AssociationItem> getAssociations() {
    return associations;
  }

  
  public boolean isOptional() {
    return optional;
  }

  
  public Map<String, String> getAdditionalConditions() {
    return additionalConditions;
  }
  
  
  

}
