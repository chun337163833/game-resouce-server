// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.shovelgame.domain.Banner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Banner_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext(unitName = "puMainDB")
    transient EntityManager Banner.entityManager;
    
    public static final List<String> Banner.fieldNames4OrderClauseFilter = java.util.Arrays.asList("id", "name", "image");
    
    public static final EntityManager Banner.entityManager() {
        EntityManager em = new Banner().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Banner.countBanners() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Banner o", Long.class).getSingleResult();
    }
    
    public static List<Banner> Banner.findAllBanners() {
        return entityManager().createQuery("SELECT o FROM Banner o", Banner.class).getResultList();
    }
    
    public static List<Banner> Banner.findAllBanners(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Banner o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Banner.class).getResultList();
    }
    
    public static Banner Banner.findBanner(Long id) {
        if (id == null) return null;
        return entityManager().find(Banner.class, id);
    }
    
    public static List<Banner> Banner.findBannerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Banner o", Banner.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Banner> Banner.findBannerEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Banner o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Banner.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Banner.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Banner.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Banner attached = Banner.findBanner(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Banner.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Banner.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Banner Banner.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Banner merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
