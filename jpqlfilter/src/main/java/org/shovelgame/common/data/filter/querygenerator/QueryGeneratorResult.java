package org.shovelgame.common.data.filter.querygenerator;

import java.util.List;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Result of query generator - store queries to select data, data count and query parameters value.
 * 
 * @author MacikJ
 * 
 */
public class QueryGeneratorResult {

  private String selectCountQuery;

  private String selectDataQuery;

  private List<ConditionGeneratorContext.KeyValue> parameters;

  private ConditionGeneratorContext cgContext;
  
  /**
   * Constructor.
   * 
   * @param selectCountQuery query for selecting count of all rows (filtered rows)
   * @param selectDataQuery query for selecting data
   * @param parameters parameters in query
   */
  public QueryGeneratorResult(String selectCountQuery, String selectDataQuery,
      List<KeyValue> parameters, ConditionGeneratorContext cgContext) {
    super();
    this.selectCountQuery = selectCountQuery;
    this.selectDataQuery = selectDataQuery;
    this.parameters = parameters;
    this.cgContext = cgContext; 
  }

  
  /** Getter for field cgContext.
   * @return the cgContext
   */
  public ConditionGeneratorContext getCgContext() {
    return cgContext;
  }

  /**
   * Getter for field selectCountQuery.
   * 
   * @return the selectCountQuery
   */
  public String getSelectCountQuery() {
    return selectCountQuery;
  }

  /**
   * Getter for field selectDataQuery.
   * 
   * @return the selectDataQuery
   */
  public String getSelectDataQuery() {
    return selectDataQuery;
  }

  /**
   * Getter for field parameters.
   * 
   * @return the parameters
   */
  public List<ConditionGeneratorContext.KeyValue> getParameters() {
    return parameters;
  }


  public void setSelectCountQuery(String selectCountQuery) {
    this.selectCountQuery = selectCountQuery;
  }


  public void setSelectDataQuery(String selectDataQuery) {
    this.selectDataQuery = selectDataQuery;
  }
  
}
