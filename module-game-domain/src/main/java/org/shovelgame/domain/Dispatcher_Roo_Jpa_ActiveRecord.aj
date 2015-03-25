// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.shovelgame.domain.Dispatcher;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Dispatcher_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext(unitName = "puMainDB")
    transient EntityManager Dispatcher.entityManager;
    
    public static final List<String> Dispatcher.fieldNames4OrderClauseFilter = java.util.Arrays.asList("serialVersionUID");
    
    public static long Dispatcher.countDispatchers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Dispatcher o", Long.class).getSingleResult();
    }
    
    public static List<Dispatcher> Dispatcher.findAllDispatchers() {
        return entityManager().createQuery("SELECT o FROM Dispatcher o", Dispatcher.class).getResultList();
    }
    
    public static List<Dispatcher> Dispatcher.findAllDispatchers(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Dispatcher o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Dispatcher.class).getResultList();
    }
    
    public static Dispatcher Dispatcher.findDispatcher(Long id) {
        if (id == null) return null;
        return entityManager().find(Dispatcher.class, id);
    }
    
    public static List<Dispatcher> Dispatcher.findDispatcherEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Dispatcher o", Dispatcher.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Dispatcher> Dispatcher.findDispatcherEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Dispatcher o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Dispatcher.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Dispatcher Dispatcher.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Dispatcher merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}