// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.i18n.TraitName;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TraitName_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TraitName.entityManager;
    
    public static final List<String> TraitName.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager TraitName.entityManager() {
        EntityManager em = new TraitName().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TraitName.countTraitNames() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TraitName o", Long.class).getSingleResult();
    }
    
    public static List<TraitName> TraitName.findAllTraitNames() {
        return entityManager().createQuery("SELECT o FROM TraitName o", TraitName.class).getResultList();
    }
    
    public static List<TraitName> TraitName.findAllTraitNames(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TraitName o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TraitName.class).getResultList();
    }
    
    public static TraitName TraitName.findTraitName(Long id) {
        if (id == null) return null;
        return entityManager().find(TraitName.class, id);
    }
    
    public static List<TraitName> TraitName.findTraitNameEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TraitName o", TraitName.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<TraitName> TraitName.findTraitNameEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TraitName o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TraitName.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TraitName.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TraitName.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TraitName attached = TraitName.findTraitName(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TraitName.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TraitName.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TraitName TraitName.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TraitName merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}