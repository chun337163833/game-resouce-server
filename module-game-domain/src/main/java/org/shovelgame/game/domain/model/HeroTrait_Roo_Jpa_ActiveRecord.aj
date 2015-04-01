// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.HeroTrait;
import org.shovelgame.game.domain.model.HeroTraitPK;
import org.springframework.transaction.annotation.Transactional;

privileged aspect HeroTrait_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager HeroTrait.entityManager;
    
    public static final List<String> HeroTrait.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager HeroTrait.entityManager() {
        EntityManager em = new HeroTrait().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long HeroTrait.countHeroTraits() {
        return entityManager().createQuery("SELECT COUNT(o) FROM HeroTrait o", Long.class).getSingleResult();
    }
    
    public static List<HeroTrait> HeroTrait.findAllHeroTraits() {
        return entityManager().createQuery("SELECT o FROM HeroTrait o", HeroTrait.class).getResultList();
    }
    
    public static List<HeroTrait> HeroTrait.findAllHeroTraits(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HeroTrait o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HeroTrait.class).getResultList();
    }
    
    public static HeroTrait HeroTrait.findHeroTrait(HeroTraitPK id) {
        if (id == null) return null;
        return entityManager().find(HeroTrait.class, id);
    }
    
    public static List<HeroTrait> HeroTrait.findHeroTraitEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM HeroTrait o", HeroTrait.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<HeroTrait> HeroTrait.findHeroTraitEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HeroTrait o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HeroTrait.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void HeroTrait.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void HeroTrait.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            HeroTrait attached = HeroTrait.findHeroTrait(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void HeroTrait.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void HeroTrait.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public HeroTrait HeroTrait.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        HeroTrait merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}