package org.shovelgame.common.data.entitymanager;

import javax.persistence.EntityManager;


public interface EntityManagerAccessor {
  public EntityManager getEntityManager();
}
