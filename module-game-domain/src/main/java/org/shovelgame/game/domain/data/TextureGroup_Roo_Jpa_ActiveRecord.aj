// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shovelgame.game.domain.data.TextureGroup;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TextureGroup_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TextureGroup.entityManager;
    
    public static final List<String> TextureGroup.fieldNames4OrderClauseFilter = java.util.Arrays.asList("GROUP_CLASSES", "GROUP_BACKGROUNDS", "GROUP_HUD", "GROUP_ICONS", "GROUP_SKILLS");
    
    public static final EntityManager TextureGroup.entityManager() {
        EntityManager em = new TextureGroup().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TextureGroup.countTextureGroups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TextureGroup o", Long.class).getSingleResult();
    }
    
    public static List<TextureGroup> TextureGroup.findAllTextureGroups() {
        return entityManager().createQuery("SELECT o FROM TextureGroup o", TextureGroup.class).getResultList();
    }
    
    public static List<TextureGroup> TextureGroup.findAllTextureGroups(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TextureGroup o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TextureGroup.class).getResultList();
    }
    
    public static TextureGroup TextureGroup.findTextureGroup(String id) {
        if (id == null || id.length() == 0) return null;
        return entityManager().find(TextureGroup.class, id);
    }
    
    public static List<TextureGroup> TextureGroup.findTextureGroupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TextureGroup o", TextureGroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<TextureGroup> TextureGroup.findTextureGroupEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TextureGroup o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TextureGroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TextureGroup.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TextureGroup.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TextureGroup attached = TextureGroup.findTextureGroup(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TextureGroup.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TextureGroup.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TextureGroup TextureGroup.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TextureGroup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}