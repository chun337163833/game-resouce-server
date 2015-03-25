// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.shovelgame.domain.TxUser;

privileged aspect TxUser_Roo_Finder {
    
    public static Long TxUser.countFindTxUsersByCellPhone(Long cellPhone) {
        if (cellPhone == null) throw new IllegalArgumentException("The cellPhone argument is required");
        EntityManager em = TxUser.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM TxUser AS o WHERE o.cellPhone = :cellPhone", Long.class);
        q.setParameter("cellPhone", cellPhone);
        return ((Long) q.getSingleResult());
    }
    
    public static Long TxUser.countFindTxUsersByEmail(String email) {
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        EntityManager em = TxUser.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM TxUser AS o WHERE o.email = :email", Long.class);
        q.setParameter("email", email);
        return ((Long) q.getSingleResult());
    }
    
    public static Long TxUser.countFindTxUsersByUserName(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = TxUser.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM TxUser AS o WHERE o.userName = :userName", Long.class);
        q.setParameter("userName", userName);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<TxUser> TxUser.findTxUsersByCellPhone(Long cellPhone) {
        if (cellPhone == null) throw new IllegalArgumentException("The cellPhone argument is required");
        EntityManager em = TxUser.entityManager();
        TypedQuery<TxUser> q = em.createQuery("SELECT o FROM TxUser AS o WHERE o.cellPhone = :cellPhone", TxUser.class);
        q.setParameter("cellPhone", cellPhone);
        return q;
    }
    
    public static TypedQuery<TxUser> TxUser.findTxUsersByCellPhone(Long cellPhone, String sortFieldName, String sortOrder) {
        if (cellPhone == null) throw new IllegalArgumentException("The cellPhone argument is required");
        EntityManager em = TxUser.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM TxUser AS o WHERE o.cellPhone = :cellPhone");
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" ").append(sortOrder);
            }
        }
        TypedQuery<TxUser> q = em.createQuery(queryBuilder.toString(), TxUser.class);
        q.setParameter("cellPhone", cellPhone);
        return q;
    }
    
    public static TypedQuery<TxUser> TxUser.findTxUsersByEmail(String email) {
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        EntityManager em = TxUser.entityManager();
        TypedQuery<TxUser> q = em.createQuery("SELECT o FROM TxUser AS o WHERE o.email = :email", TxUser.class);
        q.setParameter("email", email);
        return q;
    }
    
    public static TypedQuery<TxUser> TxUser.findTxUsersByEmail(String email, String sortFieldName, String sortOrder) {
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        EntityManager em = TxUser.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM TxUser AS o WHERE o.email = :email");
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" ").append(sortOrder);
            }
        }
        TypedQuery<TxUser> q = em.createQuery(queryBuilder.toString(), TxUser.class);
        q.setParameter("email", email);
        return q;
    }
    
    public static TypedQuery<TxUser> TxUser.findTxUsersByUserName(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = TxUser.entityManager();
        TypedQuery<TxUser> q = em.createQuery("SELECT o FROM TxUser AS o WHERE o.userName = :userName", TxUser.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public static TypedQuery<TxUser> TxUser.findTxUsersByUserName(String userName, String sortFieldName, String sortOrder) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = TxUser.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM TxUser AS o WHERE o.userName = :userName");
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" ").append(sortOrder);
            }
        }
        TypedQuery<TxUser> q = em.createQuery(queryBuilder.toString(), TxUser.class);
        q.setParameter("userName", userName);
        return q;
    }
    
}
