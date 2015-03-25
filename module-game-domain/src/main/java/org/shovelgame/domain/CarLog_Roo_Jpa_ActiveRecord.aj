// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.shovelgame.domain.CarLog;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CarLog_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext(unitName = "puMainDB")
    transient EntityManager CarLog.entityManager;
    
    public static final List<String> CarLog.fieldNames4OrderClauseFilter = java.util.Arrays.asList("id", "logDate", "logText", "tachometer", "serviceName", "price", "car");
    
    public static final EntityManager CarLog.entityManager() {
        EntityManager em = new CarLog().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long CarLog.countCarLogs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CarLog o", Long.class).getSingleResult();
    }
    
    public static List<CarLog> CarLog.findAllCarLogs() {
        return entityManager().createQuery("SELECT o FROM CarLog o", CarLog.class).getResultList();
    }
    
    public static List<CarLog> CarLog.findAllCarLogs(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CarLog o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CarLog.class).getResultList();
    }
    
    public static CarLog CarLog.findCarLog(Long id) {
        if (id == null) return null;
        return entityManager().find(CarLog.class, id);
    }
    
    public static List<CarLog> CarLog.findCarLogEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CarLog o", CarLog.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<CarLog> CarLog.findCarLogEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CarLog o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CarLog.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void CarLog.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void CarLog.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            CarLog attached = CarLog.findCarLog(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void CarLog.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void CarLog.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public CarLog CarLog.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CarLog merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
