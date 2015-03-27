// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.model.SeekerSpecialization;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SeekerSpecialization_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SeekerSpecialization.entityManager;
    
    public static final List<String> SeekerSpecialization.fieldNames4OrderClauseFilter = java.util.Arrays.asList("type");
    
    public static final EntityManager SeekerSpecialization.entityManager() {
        EntityManager em = new SeekerSpecialization().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SeekerSpecialization.countSeekerSpecializations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SeekerSpecialization o", Long.class).getSingleResult();
    }
    
    public static List<SeekerSpecialization> SeekerSpecialization.findAllSeekerSpecializations() {
        return entityManager().createQuery("SELECT o FROM SeekerSpecialization o", SeekerSpecialization.class).getResultList();
    }
    
    public static List<SeekerSpecialization> SeekerSpecialization.findAllSeekerSpecializations(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SeekerSpecialization o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SeekerSpecialization.class).getResultList();
    }
    
    public static SeekerSpecialization SeekerSpecialization.findSeekerSpecialization(Long id) {
        if (id == null) return null;
        return entityManager().find(SeekerSpecialization.class, id);
    }
    
    public static List<SeekerSpecialization> SeekerSpecialization.findSeekerSpecializationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SeekerSpecialization o", SeekerSpecialization.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<SeekerSpecialization> SeekerSpecialization.findSeekerSpecializationEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SeekerSpecialization o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SeekerSpecialization.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SeekerSpecialization.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SeekerSpecialization.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SeekerSpecialization attached = SeekerSpecialization.findSeekerSpecialization(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SeekerSpecialization.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SeekerSpecialization.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SeekerSpecialization SeekerSpecialization.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SeekerSpecialization merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
