/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



/**
 * Generate from part in hql from path of properties through entities.
 * Use <code>inner join</code> or <code>left join</code</code> as join for
 * relationships. 
 * 
 * 
 * 
 * @author MacikJ
 */
public class AssociationPathGenerator {

  public static final String JPQL_LEFT_JOIN_OPERATOR = " left join ";
  public static final String JPQL_INNER_JOIN_OPERATOR = " join ";
  
  private boolean renderAssociationPathIfNoneIsOptional = true;
  
  /**
   * @see AssociationPathGenerator
   * @param epp path of entities
   * @return
   */
  public final String generateAssociationPath( EntityPathParser epp ) {
    if ( epp == null || epp.getEntityPath().size() == 0  ) {
      return null;
    }
    
    List<EntityPathItem> entityPath = epp.getEntityPath();
    StringBuilder sb = new StringBuilder(entityPath.get(0).getAlias());
    boolean isAnyOptional = false;
    for (EntityPathItem epi : entityPath) {
      Field field = epi.getProperty();      
      boolean isAssociationOptional = false;
      ManyToOne m2oAnnotation = field.getAnnotation(ManyToOne.class);
      if (m2oAnnotation != null) {
        isAssociationOptional = m2oAnnotation.optional();           
      } else {
        OneToOne o2oAnnotation = field.getAnnotation(OneToOne.class); 
        if (o2oAnnotation != null) {
          isAssociationOptional = o2oAnnotation.optional();
        }
      }
              
      if (isAssociationOptional) {
        isAnyOptional = true;
        sb.append(JPQL_LEFT_JOIN_OPERATOR);
      } else {
        sb.append(JPQL_INNER_JOIN_OPERATOR);
      }
      sb.append(epi.getAlias())          
        .append(SimpleJPQLFilterGenerator.PROPERTY_SEPARATOR_CHAR)
        .append(epi.getProperty().getName());        

    }
    
    if (!isAnyOptional && ! renderAssociationPathIfNoneIsOptional) {
        return null;
    } else {
      return sb.toString();
    }
    
  }
  
}
