// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.i18n.SkillName;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SkillName_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SkillName.entityManager;
    
    public static final List<String> SkillName.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager SkillName.entityManager() {
        EntityManager em = new SkillName().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SkillName.countSkillNames() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SkillName o", Long.class).getSingleResult();
    }
    
    public static List<SkillName> SkillName.findAllSkillNames() {
        return entityManager().createQuery("SELECT o FROM SkillName o", SkillName.class).getResultList();
    }
    
    public static List<SkillName> SkillName.findAllSkillNames(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SkillName o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SkillName.class).getResultList();
    }
    
    public static SkillName SkillName.findSkillName(Long id) {
        if (id == null) return null;
        return entityManager().find(SkillName.class, id);
    }
    
    public static List<SkillName> SkillName.findSkillNameEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SkillName o", SkillName.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<SkillName> SkillName.findSkillNameEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SkillName o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SkillName.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SkillName.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SkillName.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SkillName attached = SkillName.findSkillName(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SkillName.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SkillName.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SkillName SkillName.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SkillName merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
