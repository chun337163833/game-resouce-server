// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.model.MinionSkill;
import org.shovelgame.game.domain.model.MinionSkillPK;
import org.springframework.transaction.annotation.Transactional;

privileged aspect MinionSkill_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MinionSkill.entityManager;
    
    public static final List<String> MinionSkill.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager MinionSkill.entityManager() {
        EntityManager em = new MinionSkill().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MinionSkill.countMinionSkills() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MinionSkill o", Long.class).getSingleResult();
    }
    
    public static List<MinionSkill> MinionSkill.findAllMinionSkills() {
        return entityManager().createQuery("SELECT o FROM MinionSkill o", MinionSkill.class).getResultList();
    }
    
    public static List<MinionSkill> MinionSkill.findAllMinionSkills(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MinionSkill o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MinionSkill.class).getResultList();
    }
    
    public static MinionSkill MinionSkill.findMinionSkill(MinionSkillPK id) {
        if (id == null) return null;
        return entityManager().find(MinionSkill.class, id);
    }
    
    public static List<MinionSkill> MinionSkill.findMinionSkillEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MinionSkill o", MinionSkill.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<MinionSkill> MinionSkill.findMinionSkillEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM MinionSkill o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, MinionSkill.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MinionSkill.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MinionSkill.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MinionSkill attached = MinionSkill.findMinionSkill(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MinionSkill.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MinionSkill.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MinionSkill MinionSkill.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MinionSkill merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
