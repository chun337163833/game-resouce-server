/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.Entity;

import org.shovelgame.common.data.filter.querygenerator.SimpleJPQLFilterGenerator.EntityAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;



/**
 * Parse path through properies of entities. 
 * @author MacikJ
 */
public class EntityPathParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(EntityPathParser.class);

  
    
  private List<EntityPathItem> entityPath = new ArrayList<EntityPathItem>();
  
  private EntityAlias alias;
  
  private String entityPathString;
  
  /**
   * Parse path.
   * @param alias root entity
   * @param entityPath property path with start in root entity
   */
  public EntityPathParser(EntityAlias alias, String entityPath) {
    this.alias = alias;
    this.entityPathString = entityPath;
    Entity marker = alias.getClass().getAnnotation(Entity.class);
    if (marker == null) {
      LOG.warn("Class passed to " + this.getClass().getSimpleName() + " hasn't a Entity annotation!");
    }
    StringTokenizer tokenizer = new StringTokenizer(entityPath, SimpleJPQLFilterGenerator.PROPERTY_SEPARATOR_CHAR, false);
    parsePath(alias, tokenizer);
  }
  
   
//  public String getFromPart() {
//    if(joins.size() > 0) {
//      StringBuilder result = new StringBuilder();
//      for(String join : joins) {
//        result.append(" ").append(join).append(" ");
//      }
//      return result.toString();
//    }
//    else {
//      return " ";
//    }
//  }
  
  private void parsePath(EntityAlias alias, StringTokenizer tokenizer) {
    Class<?> clazz = alias.getEntityClass();
    StringBuilder pathAlias = new StringBuilder(alias.getAlias());
    while ( tokenizer.hasMoreElements() ) {
      String propertyName = tokenizer.nextToken();
      LOG.debug("Looking for property: " + propertyName);
                 
      //note: find field in all classes not only in classes annotated with Entity and MappedSuperClass!
      Field field = ReflectionUtils.findField(clazz, propertyName);
      if (field == null) {
        throw new IllegalArgumentException(
            "NonExisting field '" + propertyName + "' on '" + clazz + "' passed to token processor");
      }      
       
      EntityPathItem entityPathItem = new EntityPathItem(
          pathAlias.toString(), clazz, field);

                  
      //set variables for next iteration
      clazz = field.getType();
      pathAlias.append(SimpleJPQLFilterGenerator.PROPERTY_SEPARATOR_CHAR).append(propertyName);
  
      
      if( clazz.getAnnotation(Entity.class) !=null ) {
        this.entityPath.add(entityPathItem);
      }
    }
  }
  
  

  /** Getter for field entityPath.
   * @return the entityPath
   */
  public List<EntityPathItem> getEntityPath() {
    return entityPath;
  }


  
  /** Getter for field alias.
   * @return the alias
   */
  public EntityAlias getAlias() {
    return alias;
  }


  
  /** Getter for field entityPathString.
   * @return the entityPathString
   */
  public String getEntityPathString() {
    return entityPathString;
  }
}
