// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.model.Grade;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Grade_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Grade.entityManager;
    
    public static final List<String> Grade.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager Grade.entityManager() {
        EntityManager em = new Grade().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Grade.countGrades() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Grade o", Long.class).getSingleResult();
    }
    
    public static List<Grade> Grade.findAllGrades() {
        return entityManager().createQuery("SELECT o FROM Grade o", Grade.class).getResultList();
    }
    
    public static List<Grade> Grade.findAllGrades(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Grade o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Grade.class).getResultList();
    }
    
    public static Grade Grade.findGrade(Character id) {
        if (id == null) return null;
        return entityManager().find(Grade.class, id);
    }
    
    public static List<Grade> Grade.findGradeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Grade o", Grade.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Grade> Grade.findGradeEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Grade o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Grade.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Grade.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Grade.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Grade attached = Grade.findGrade(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Grade.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Grade.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Grade Grade.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Grade merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
