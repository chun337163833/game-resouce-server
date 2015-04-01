// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.MinionTraitPK;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MinionTrait_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MinionTrait.entityManager;
    
    public static final List<String> MinionTrait.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager MinionTrait.entityManager() {
        EntityManager em = new MinionTrait().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MinionTrait.countMinionTraits() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MinionTrait o", Long.class).getSingleResult();
    }
    
    public static List<MinionTrait> MinionTrait.findAllMinionTraits() {
        return entityManager().createQuery("SELECT o FROM MinionTrait o", MinionTrait.class).getResultList();
    }
    
    public static List<MinionTrait> MinionTrait.findAllMinionTraits(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MinionTrait o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MinionTrait.class).getResultList();
    }
    
    public static MinionTrait MinionTrait.findMinionTrait(MinionTraitPK id) {
        if (id == null) return null;
        return entityManager().find(MinionTrait.class, id);
    }
    
    public static List<MinionTrait> MinionTrait.findMinionTraitEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MinionTrait o", MinionTrait.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<MinionTrait> MinionTrait.findMinionTraitEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MinionTrait o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MinionTrait.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MinionTrait.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MinionTrait.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MinionTrait attached = MinionTrait.findMinionTrait(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MinionTrait.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MinionTrait.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MinionTrait MinionTrait.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MinionTrait merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}