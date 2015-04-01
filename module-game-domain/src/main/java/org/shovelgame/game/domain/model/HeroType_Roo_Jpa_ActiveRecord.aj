// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.HeroType;
import org.springframework.transaction.annotation.Transactional;

privileged aspect HeroType_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager HeroType.entityManager;
    
    public static final List<String> HeroType.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager HeroType.entityManager() {
        EntityManager em = new HeroType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long HeroType.countHeroTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM HeroType o", Long.class).getSingleResult();
    }
    
    public static List<HeroType> HeroType.findAllHeroTypes() {
        return entityManager().createQuery("SELECT o FROM HeroType o", HeroType.class).getResultList();
    }
    
    public static List<HeroType> HeroType.findAllHeroTypes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HeroType o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HeroType.class).getResultList();
    }
    
    public static HeroType HeroType.findHeroType(Long id) {
        if (id == null) return null;
        return entityManager().find(HeroType.class, id);
    }
    
    public static List<HeroType> HeroType.findHeroTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM HeroType o", HeroType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<HeroType> HeroType.findHeroTypeEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HeroType o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HeroType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void HeroType.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void HeroType.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            HeroType attached = HeroType.findHeroType(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void HeroType.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void HeroType.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public HeroType HeroType.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        HeroType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}