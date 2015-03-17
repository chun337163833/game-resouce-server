// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.tmobile.domain.smspayment;

import com.tmobile.domain.smspayment.SmsPayment;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect SmsPayment_Roo_Finder {
    
    public static Long SmsPayment.countFindSmsPaymentsByErikaId(Long erikaId) {
        if (erikaId == null) throw new IllegalArgumentException("The erikaId argument is required");
        EntityManager em = SmsPayment.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM SmsPayment AS o WHERE o.erikaId = :erikaId", Long.class);
        q.setParameter("erikaId", erikaId);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<SmsPayment> SmsPayment.findSmsPaymentsByErikaId(Long erikaId) {
        if (erikaId == null) throw new IllegalArgumentException("The erikaId argument is required");
        EntityManager em = SmsPayment.entityManager();
        TypedQuery<SmsPayment> q = em.createQuery("SELECT o FROM SmsPayment AS o WHERE o.erikaId = :erikaId", SmsPayment.class);
        q.setParameter("erikaId", erikaId);
        return q;
    }
    
    public static TypedQuery<SmsPayment> SmsPayment.findSmsPaymentsByErikaId(Long erikaId, String sortFieldName, String sortOrder) {
        if (erikaId == null) throw new IllegalArgumentException("The erikaId argument is required");
        EntityManager em = SmsPayment.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM SmsPayment AS o WHERE o.erikaId = :erikaId");
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY ").append(sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" ").append(sortOrder);
            }
        }
        TypedQuery<SmsPayment> q = em.createQuery(queryBuilder.toString(), SmsPayment.class);
        q.setParameter("erikaId", erikaId);
        return q;
    }
    
}