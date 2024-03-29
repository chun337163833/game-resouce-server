// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.i18n.MissionDescription;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MissionDescription_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MissionDescription.entityManager;
    
    public static final List<String> MissionDescription.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager MissionDescription.entityManager() {
        EntityManager em = new MissionDescription().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MissionDescription.countMissionDescriptions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MissionDescription o", Long.class).getSingleResult();
    }
    
    public static List<MissionDescription> MissionDescription.findAllMissionDescriptions() {
        return entityManager().createQuery("SELECT o FROM MissionDescription o", MissionDescription.class).getResultList();
    }
    
    public static List<MissionDescription> MissionDescription.findAllMissionDescriptions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MissionDescription o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MissionDescription.class).getResultList();
    }
    
    public static MissionDescription MissionDescription.findMissionDescription(Long id) {
        if (id == null) return null;
        return entityManager().find(MissionDescription.class, id);
    }
    
    public static List<MissionDescription> MissionDescription.findMissionDescriptionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MissionDescription o", MissionDescription.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<MissionDescription> MissionDescription.findMissionDescriptionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MissionDescription o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MissionDescription.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MissionDescription.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MissionDescription.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MissionDescription attached = MissionDescription.findMissionDescription(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MissionDescription.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MissionDescription.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MissionDescription MissionDescription.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MissionDescription merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
