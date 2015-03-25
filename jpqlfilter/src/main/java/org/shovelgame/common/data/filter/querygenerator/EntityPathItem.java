/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator;

import java.lang.reflect.Field;

import org.shovelgame.common.data.filter.querygenerator.SimpleJPQLFilterGenerator.EntityAlias;

/**
 * Represents association between two entities (unidirectional).
 * 
 * @author MacikJ
 *
 */
public class EntityPathItem extends EntityAlias {
  
  private Field property;

  /**
   * Constructor
   * @param alias alias of entity in JPQL
   * @param entityClass class of entity
   * @param property property of class that holds association to second entity
   */
  public EntityPathItem(String alias, Class entityClass, Field property) {
    super(alias, entityClass);
    this.property = property;
  }

  
  public Field getProperty() {
    return property;
  }


  
  
}