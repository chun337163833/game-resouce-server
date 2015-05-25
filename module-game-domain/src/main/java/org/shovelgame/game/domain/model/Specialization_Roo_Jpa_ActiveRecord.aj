// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.Specialization;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Specialization_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Specialization.entityManager;
    
    public static final List<String> Specialization.fieldNames4OrderClauseFilter = java.util.Arrays.asList("type");
    
    public static final EntityManager Specialization.entityManager() {
        EntityManager em = new Specialization().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Specialization.countSpecializations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Specialization o", Long.class).getSingleResult();
    }
    
    public static List<Specialization> Specialization.findAllSpecializations() {
        return entityManager().createQuery("SELECT o FROM Specialization o", Specialization.class).getResultList();
    }
    
    public static List<Specialization> Specialization.findAllSpecializations(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Specialization o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Specialization.class).getResultList();
    }
    
    public static Specialization Specialization.findSpecialization(Long id) {
        if (id == null) return null;
        return entityManager().find(Specialization.class, id);
    }
    
    public static List<Specialization> Specialization.findSpecializationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Specialization o", Specialization.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Specialization> Specialization.findSpecializationEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Specialization o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Specialization.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Specialization.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Specialization.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Specialization attached = Specialization.findSpecialization(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Specialization.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Specialization.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Specialization Specialization.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Specialization merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}