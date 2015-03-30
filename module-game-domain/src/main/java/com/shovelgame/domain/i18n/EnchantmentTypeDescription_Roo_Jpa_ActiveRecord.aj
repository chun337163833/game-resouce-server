// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.i18n;

import com.shovelgame.domain.i18n.EnchantmentTypeDescription;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EnchantmentTypeDescription_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager EnchantmentTypeDescription.entityManager;
    
    public static final List<String> EnchantmentTypeDescription.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager EnchantmentTypeDescription.entityManager() {
        EntityManager em = new EnchantmentTypeDescription().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EnchantmentTypeDescription.countEnchantmentTypeDescriptions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EnchantmentTypeDescription o", Long.class).getSingleResult();
    }
    
    public static List<EnchantmentTypeDescription> EnchantmentTypeDescription.findAllEnchantmentTypeDescriptions() {
        return entityManager().createQuery("SELECT o FROM EnchantmentTypeDescription o", EnchantmentTypeDescription.class).getResultList();
    }
    
    public static List<EnchantmentTypeDescription> EnchantmentTypeDescription.findAllEnchantmentTypeDescriptions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EnchantmentTypeDescription o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EnchantmentTypeDescription.class).getResultList();
    }
    
    public static EnchantmentTypeDescription EnchantmentTypeDescription.findEnchantmentTypeDescription(Long id) {
        if (id == null) return null;
        return entityManager().find(EnchantmentTypeDescription.class, id);
    }
    
    public static List<EnchantmentTypeDescription> EnchantmentTypeDescription.findEnchantmentTypeDescriptionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EnchantmentTypeDescription o", EnchantmentTypeDescription.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<EnchantmentTypeDescription> EnchantmentTypeDescription.findEnchantmentTypeDescriptionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EnchantmentTypeDescription o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EnchantmentTypeDescription.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void EnchantmentTypeDescription.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EnchantmentTypeDescription.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EnchantmentTypeDescription attached = EnchantmentTypeDescription.findEnchantmentTypeDescription(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EnchantmentTypeDescription.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EnchantmentTypeDescription.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EnchantmentTypeDescription EnchantmentTypeDescription.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EnchantmentTypeDescription merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
