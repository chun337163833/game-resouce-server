// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model.reward;

import java.util.List;
import org.shovelgame.game.domain.model.reward.DiamondReward;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DiamondReward_Roo_Jpa_ActiveRecord {
    
    public static final List<String> DiamondReward.fieldNames4OrderClauseFilter = java.util.Arrays.asList("diamonds");
    
    public static long DiamondReward.countDiamondRewards() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DiamondReward o", Long.class).getSingleResult();
    }
    
    public static List<DiamondReward> DiamondReward.findAllDiamondRewards() {
        return entityManager().createQuery("SELECT o FROM DiamondReward o", DiamondReward.class).getResultList();
    }
    
    public static List<DiamondReward> DiamondReward.findAllDiamondRewards(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM DiamondReward o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, DiamondReward.class).getResultList();
    }
    
    public static DiamondReward DiamondReward.findDiamondReward(Long id) {
        if (id == null) return null;
        return entityManager().find(DiamondReward.class, id);
    }
    
    public static List<DiamondReward> DiamondReward.findDiamondRewardEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DiamondReward o", DiamondReward.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<DiamondReward> DiamondReward.findDiamondRewardEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM DiamondReward o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, DiamondReward.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public DiamondReward DiamondReward.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DiamondReward merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
