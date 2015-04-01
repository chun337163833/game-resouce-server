// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.MinionModel;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MinionModel_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MinionModel.entityManager;
    
    public static final List<String> MinionModel.fieldNames4OrderClauseFilter = java.util.Arrays.asList("minionAttributes");
    
    public static final EntityManager MinionModel.entityManager() {
        EntityManager em = new MinionModel().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MinionModel.countMinionModels() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MinionModel o", Long.class).getSingleResult();
    }
    
    public static List<MinionModel> MinionModel.findAllMinionModels() {
        return entityManager().createQuery("SELECT o FROM MinionModel o", MinionModel.class).getResultList();
    }
    
    public static List<MinionModel> MinionModel.findAllMinionModels(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MinionModel o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MinionModel.class).getResultList();
    }
    
    public static MinionModel MinionModel.findMinionModel(Long id) {
        if (id == null) return null;
        return entityManager().find(MinionModel.class, id);
    }
    
    public static List<MinionModel> MinionModel.findMinionModelEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MinionModel o", MinionModel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<MinionModel> MinionModel.findMinionModelEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MinionModel o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MinionModel.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MinionModel.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MinionModel.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MinionModel attached = MinionModel.findMinionModel(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MinionModel.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MinionModel.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MinionModel MinionModel.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MinionModel merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
