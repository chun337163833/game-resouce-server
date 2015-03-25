/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.shovelgame.common.data.entitymanager.DefaultEntityManagerAccessor;
import org.shovelgame.common.data.entitymanager.EntityManagerAccessor;
import org.shovelgame.common.data.filter.error.ConditionGenerationFailedException;
import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.shovelgame.common.data.filter.querygenerator.QueryGeneratorResult;
import org.shovelgame.common.data.filter.querygenerator.SimpleJPQLFilterGenerator;
import org.shovelgame.common.data.filter.querygenerator.SimpleJPQLFilterGenerator.EntityAlias;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MacikJ
 * 
 */
public class FilterUtil {

  private EntityManagerAccessor entityManagerAccessor = new DefaultEntityManagerAccessor();

  private ConversionService conversionService;

  private SimpleJPQLFilterGenerator simpleJPQLFilterGenerator = new SimpleJPQLFilterGenerator();

  public <E> FilteredList<E> findByFilter(Class<E> entityClass, IFilter filter) {
    return this.findByFilter(entityClass, filter, null);
  }
  
  //@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
  public <E> FilteredList<E> findByFilter(Class<E> entityClass, IFilter filter, QueryManipulator qm) {
    try {
      List<EntityAlias> entitiesInQuery = new ArrayList<EntityAlias>();
      entitiesInQuery.add(new EntityAlias("e", entityClass));
      if (conversionService != null) {
        this.simpleJPQLFilterGenerator.setSpringConversionService(conversionService);
      }

      QueryGeneratorResult qgr = this.simpleJPQLFilterGenerator.generateQuery(filter, entitiesInQuery);
      if (qm!=null) {
        qgr = qm.manipulateQuery(qgr, filter);
      }
      List<E> resultList = this.getResultList(filter, qgr);

      // if there is nothing selected a isn't it first page, we have to select the previous page
      int currentPageNumber = filter.getPage().getCurrentPageNumber();
      if (resultList != null && resultList.size() == 0 && currentPageNumber > 1) {
        filter.getPage().setCurrentPageNumber(currentPageNumber - 1);
        qgr = this.simpleJPQLFilterGenerator.generateQuery(filter, entitiesInQuery);
        if (qm!=null) {
          qgr = qm.manipulateQuery(qgr, filter);
        }
        resultList = this.getResultList(filter, qgr);
      }

      // select of all rows count
      long recordCount;
      PageSetting pageSettings = filter.getPage();
      if (pageSettings.getCurrentPageNumber() == 1 && resultList.size() < pageSettings.getObjectsPerPage()) {
        recordCount = resultList.size();
      } else {
        Query selectRecordCount = entityManagerAccessor.getEntityManager().createQuery(qgr.getSelectCountQuery());
        fillQueryWithParametres(selectRecordCount, qgr.getParameters());
        recordCount = ((Number) selectRecordCount.getSingleResult()).longValue();
      }

      FilteredList<E> fList = new FilteredList<E>(filter, resultList, recordCount);
      return fList;
    } catch (ConditionGenerationFailedException e) {
      return new FilteredList<E>(filter, e);
    }
  }

  /**
   * Method get result list by filter and query generator
   * 
   * @param filter
   *          Filter
   * @param qgr
   *          QueryGeneratorResult
   * @return list of result
   */
  @SuppressWarnings("unchecked")
  public <E> List<E> getResultList(IFilter filter, QueryGeneratorResult qgr) {
    PageSetting pageSettings = filter.getPage();
    List<E> resultList;
    Query selectDataQuery = entityManagerAccessor.getEntityManager().createQuery(qgr.getSelectDataQuery());
    fillQueryWithParametres(selectDataQuery, qgr.getParameters());
    if (pageSettings.isPagingEnabled()) {
      selectDataQuery.setFirstResult((pageSettings.getCurrentPageNumber() - 1) * pageSettings.getObjectsPerPage());
      selectDataQuery.setMaxResults(pageSettings.getObjectsPerPage());
    }
    resultList = selectDataQuery.getResultList();

    return resultList;
  }

  public void fillQueryWithParametres(Query query, List<ConditionGeneratorContext.KeyValue> values) {
    int m = values.size();
    for (int i = 0; i < m; i++) {
      ConditionGeneratorContext.KeyValue tmp = values.get(i);
      query.setParameter(tmp.getKey(), tmp.getValue());
    }
  }

  public EntityManagerAccessor getEntityManagerAccessor() {
    return entityManagerAccessor;
  }

  public ConversionService getConversionService() {
    return conversionService;
  }

  public SimpleJPQLFilterGenerator getSimpleJPQLFilterGenerator() {
    return simpleJPQLFilterGenerator;
  }

  public void setEntityManagerAccessor(EntityManagerAccessor entityManagerAccessor) {
    this.entityManagerAccessor = entityManagerAccessor;
  }

  public void setJPQLFilterGenerator(SimpleJPQLFilterGenerator jpqlFilterGenerator) {
    this.simpleJPQLFilterGenerator = jpqlFilterGenerator;
  }

  public void setConversionService(ConversionService conversionService) {
    this.conversionService = conversionService;
  }

}
