package org.shovelgame.common.data.filter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Rozhraní fitru které používá generátor podmínek.
 * @author MacikJ
 */
public interface IFilter {

  /**
   * Return default (programaticall) conditions.
   * 
   * @return all conditions
   */
  public abstract Map<String, String> getDefaultConditions();
  
  /**
   * Return all conditions.
   * 
   * @return all conditions
   */
  public abstract Map<String, String> getConditions();

  /**
   * Getter for field advanced.
   * 
   * @return the advanced
   */
  public abstract AdvancedConditions getAdvanced();

  /**
   * Getter for field sortBy.
   * 
   * @return the sortBy
   */
  public abstract Sort getSortBy();

  /**
   * Getter for field page.
   * 
   * @return the page
   */
  public abstract PageSetting getPage();

  /** Getter for field orConditions.
   * @return the orConditions
   */
  public abstract Map<String, Set<String>> getOrConditions();
  
  
  public List<Association> getAssociations();
  
  public List<ConditionReplacement> getConditionReplacements();
  
  
  public String getDefaultConditionType();
  
  public String getDefaultParameterType();
  
  public Long getFilterId();
  
  public String getFilterName();
  
  public List<String> getVisibleColumns();
  
  public void setVisibleColumns(List<String> visibleColumns);
  
  
  public boolean isAddDistinctToQuery();
    
  
  /**
   * {@link Filter.#addDistinctToQuery}
   * @param addDistinctToQuery addDistinctToQuery
   */
  public void setAddDistinctToQuery(boolean addDistinctToQuery);
  

}