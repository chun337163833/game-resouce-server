// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.HeroModel;
import org.springframework.transaction.annotation.Transactional;

privileged aspect HeroModel_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager HeroModel.entityManager;
    
    public static final List<String> HeroModel.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager HeroModel.entityManager() {
        EntityManager em = new HeroModel().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long HeroModel.countHeroModels() {
        return entityManager().createQuery("SELECT COUNT(o) FROM HeroModel o", Long.class).getSingleResult();
    }
    
    public static List<HeroModel> HeroModel.findAllHeroModels() {
        return entityManager().createQuery("SELECT o FROM HeroModel o", HeroModel.class).getResultList();
    }
    
    public static List<HeroModel> HeroModel.findAllHeroModels(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HeroModel o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HeroModel.class).getResultList();
    }
    
    public static HeroModel HeroModel.findHeroModel(Long id) {
        if (id == null) return null;
        return entityManager().find(HeroModel.class, id);
    }
    
    public static List<HeroModel> HeroModel.findHeroModelEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM HeroModel o", HeroModel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<HeroModel> HeroModel.findHeroModelEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HeroModel o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HeroModel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void HeroModel.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void HeroModel.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            HeroModel attached = HeroModel.findHeroModel(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void HeroModel.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void HeroModel.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public HeroModel HeroModel.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        HeroModel merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}