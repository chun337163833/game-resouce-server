package org.shovelgame.common.data.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Default behaviour, only one entityManager exists in the whole application
 * This manager will be set by PersistenceContext annotation
 * @author NevyhostenyPa
 *
 */
public class DefaultEntityManager implements EntityManagerAccessor {
  @PersistenceContext
  private EntityManager entityManager;
  
  public EntityManager getEntityManager() {
    return entityManager;
  }
  
  public void setEntityManager(EntityManager entityManager){
    this.entityManager = entityManager;
  }
  
}
