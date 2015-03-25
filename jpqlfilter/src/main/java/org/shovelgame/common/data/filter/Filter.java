/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.shovelgame.common.data.filter.store.StoredFilter;
import org.shovelgame.common.data.filter.store.StoredFilterService;

/**
 * Store filter conditions, paging and sorting options.
 * 
 * @author MacikJ
 */
public class Filter implements IFilter, Serializable {

  private Map<String, String> defaultConditions = new HashMap<String, String>();

  private Sort defaultSortBy;

  private PageSetting defaultPage;

  private Map<String, String> conditions;

  private AdvancedConditions advanced;

  private Sort sortBy;

  private PageSetting page;
  
  private List<Association> associations = new ArrayList<Association>();
  
  private List<ConditionReplacement> conditionReplacement = new ArrayList<ConditionReplacement>(); 
  
  private volatile StoredFilterService storedFilterService;   
  
  private Long filterId;
  
  private String filterName;
  
  
  /**
   * Adds distinct to select query.
   */
  private boolean addDistinctToQuery = false;
  
  /**
   * <p>Group conditions to</p>  
   */
  private Map<String, Set<String>> orConditions = new HashMap<String, Set<String>>();

  public String defaultConditionType;
  
  public String defaultParameterType;
  
  /**
   * Seznam sloupcu ktere se maji zobrazit. Filtr s nimi nic nedela pouze nese informaci.
   */
  protected List<String> visibleColumns;
  
  /**
   * Default constructor.
   */
  public Filter() {    
    this(null, null, null);    
  }

  
  
  
  public Filter(Sort defaultSortBy) {
    this(null, defaultSortBy, null);
    this.defaultSortBy = defaultSortBy;
  }




  /**
   * Constructor.
   * 
   * @param defaultSortBy default sort column
   * @param pageSetting default page settings
   */
  public Filter(Sort defaultSortBy, PageSetting pageSetting) {
    this(null, defaultSortBy, pageSetting);
  }

  /**
   * Constructor.
   * 
   * @param defaultConditions default conditions
   * @param defaultSortBy default sort column
   * @param pageSetting default page settings
   */
  public Filter(Map<String, String> defaultConditions, Sort defaultSortBy, PageSetting pageSetting) {
    super();
    if (defaultConditions!=null && defaultConditions.size()>0) {
      this.defaultConditions.putAll(defaultConditions);
    }
    
    if (defaultSortBy != null) {
      this.defaultSortBy = defaultSortBy;
    } else {
      this.defaultSortBy = new Sort();
    }
    
    if (pageSetting != null) {
      this.defaultPage = pageSetting;
    } else {
      this.defaultPage = new PageSetting();
    }

    initialize();
  }

  /**
   * Initialize filtr to default setting.
   */
  public void initialize() {
    this.conditions = new SimpleObservableHashMap(new SimpleObservableHashMap.OnMapChange() {
      public void mapChanged() {
        page.setCurrentPageNumber(PageSetting.INITIAL_PAGE_NUMBER);
      }
    });
    this.sortBy = defaultSortBy;
    this.page = defaultPage;
  }

  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.IFilter#getConditions()
   */
  @Override
  public Map<String, String> getConditions() {
    return conditions;
  }
  
  @Override
  public Map<String, String> getDefaultConditions() {
    return Collections.unmodifiableMap(this.defaultConditions);
  }
  
  public void addDefaultCondition(String conditionName, String conditionValue) {    
    this.defaultConditions.put(conditionName, conditionValue);
  }
  
  public void addDefaultConditions(Map<String, String> conditions) {    
    this.defaultConditions.putAll(conditions);
  }
  
  /**
   * Set conditions (replace current).
   * 
   * @param conditions conditions
   */
  public void setConditions(Map<String, String> conditions) {
    this.conditions = conditions;
  }

  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.IFilter#getAdvanced()
   */
  @Override
  public AdvancedConditions getAdvanced() {
    if (advanced == null) {
      advanced = new AdvancedConditionsImpl();
    }
    return advanced;
  }

  public void addAdvancedCondition(CustomCondition customCondition) {
    getAdvanced().add(customCondition);
  }
  


  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.IFilter#getSortBy()
   */
  @Override
  public Sort getSortBy() {
    return sortBy;
  }

  /**
   * Setter for field sortBy.
   * 
   * @param sortBy the sortBy to set
   */
  public void setSortBy(Sort sortBy) {
    this.sortBy = sortBy;
  }

  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.IFilter#getPage()
   */
  @Override
  public PageSetting getPage() {
    return page;
  }

  /**
   * Setter for field page.
   * 
   * @param page the page to set
   */
  public void setPage(PageSetting page) {
    this.page = page;
  }
  

  /* (non-Javadoc)
   * @see cz.tsystems.common.data.filter.IFilter#getOrConditions()
   */
  @Override
  public Map<String, Set<String>> getOrConditions() {
    return orConditions;
  }

  /**
   * Add group of conditions (names of conditions). Conditions in group are 'joined' with OR operator.
   * 
   * @param groupName name of group (only for identifying of group) 
   * @param columnNames names of field in group
   */
  public void addOrGroup(String groupName, String [] columnNames) {
    Set<String> columnNamesSet = new HashSet<String>(columnNames.length);
    for (String tmp : columnNames) {
      columnNamesSet.add(tmp);
    }
    this.orConditions.put(groupName, columnNamesSet);
  }

  /**
   * Prida novou asociaci k dotazu. TODO Asociace bude pridana k podmince pouze pokud
   * ji nejaka podminka, nebo trideni pouzije.
   * @param association
   */
  public void addAssociation(Association... association) {
    for (Association a: association) {
      this.associations.add(a);
    }
  }
  
  /**
   * Prida novou asociaci k dotazu za pouziti defaultnich jmen.
   * @param association
   */
  public void addAssociation(String association) {
    this.associations.add(new Association(association));
  }
  
  /**
   * Prida novou asociaci k dotazu.
   * @param associationPath jmeno property kde je vazba
   * @param alias alias vazby v hql
   * @param optional pokud je true muze byt vazba odebrana pokud neni pouzita v podminkach 
   */
  public void addAssociation(String associationPath, String alias, boolean optional) {
    Association association = new Association(associationPath, alias); 
    association.setOptional(optional);
    this.associations.add(association);
  }

  @Override
  public List<Association> getAssociations() {
    return associations;
  }

  /**
   * Prida nastaveni pro nahrazeni podminky. Lze napriklad zmenit typ sloupce, nebo vyuzit asociaci
   * aniz by tuto informaci nesla view vrstva.
   * napr. "name" -> "localizedMessage.name"
   * @param conditionReplacement
   */
  public void addConditionReplacement(ConditionReplacement... conditionReplacement) {    
    for (ConditionReplacement cr: conditionReplacement) {
      this.conditionReplacement.add(cr);
    }
  }
  
  public void addConditionReplacement(String oldConditionName, String newConditionName) {    
    this.conditionReplacement.add(new ConditionReplacement(oldConditionName, newConditionName));
  }
  
  
  public List<ConditionReplacement> getConditionReplacements() {
    return conditionReplacement;
  }




  
  public String getDefaultConditionType() {
    return defaultConditionType;
  }




  
  public void setDefaultConditionType(String defaultConditionType) {
    this.defaultConditionType = defaultConditionType;
  }




  
  public String getDefaultParameterType() {
    return defaultParameterType;
  }




  
  public void setDefaultParameterType(String defaultParameterType) {
    this.defaultParameterType = defaultParameterType;
  }

  
  public List<String> getVisibleColumns() {
    return visibleColumns;
  }
  
  
  public void setVisibleColumns(List<String> visibleColumns) {
    this.visibleColumns = visibleColumns;
  }
  
  
 
  
  public Long getFilterId() {
    return filterId;
  }




  
  public void setFilterId(Long filterId) {
    this.filterId = filterId;
  }




  
  public String getFilterName() {
    return filterName;
  }




  
  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }




  /**
   * Nacte podmikny apod. z ulozeneho filtru pomoci StoredFilterService.
   * @param filterId ID filtru z ktereheho nacist nastaveni
   */
  public void loadFromStore(Long filterId) {
    StoredFilter storedFilter = this.storedFilterService.findById(filterId);
    if (storedFilter==null) {
      throw new EntityNotFoundException("filter with id=" + filterId + " not found.");
    }
    this.filterId = storedFilter.getId();
    this.filterName = storedFilter.getName();
    this.conditions = new HashMap<String, String>(storedFilter.getConditions());
    List<String> lVisibleColumns = storedFilter.getVisibleColumns();
    if (lVisibleColumns!=null) {
      this.visibleColumns = new ArrayList<String>(lVisibleColumns);
    }
    Sort lSort = storedFilter.getSort();
    if (lSort !=null) {
      this.sortBy = lSort;
    }
  }
  
  
  /**
   * Ulozi filter do uloziste. Pokud Id== null bude vytvoren novy. 
   */
  public void saveToStore() {
    Long filterId = this.storedFilterService.save(createStoredFilter());
    this.filterId = filterId;
  }
  
  /**
   * Ulozi filter do uloziste. Pokud Id== null bude vytvoren novy. 
   */
  public void deleteFromStore() {    
    this.storedFilterService.remove(createStoredFilter());
  }
  
  protected StoredFilter createStoredFilter() {
    return new StoredFilter() {      
      @Override
      public List<String> getVisibleColumns() {
        List<String> tmp = Filter.this.getVisibleColumns();
        if (tmp!=null) {
          return Collections.unmodifiableList(tmp);
        } else {
          return null;
        }
      }
      
      @Override
      public Sort getSort() {        
        return Filter.this.getSortBy();
      }
      
      @Override
      public String getName() {        
        return Filter.this.getFilterName();
      }
      
      @Override
      public Long getId() {
        return Filter.this.getFilterId();
      }
      
      @Override
      public Map<String, String> getConditions() {        
        return Collections.unmodifiableMap(Filter.this.conditions);
      }
    };
  }
  
  /**
   * {@link #addDistinctToQuery}
   * @param addDistinctToQuery addDistinctToQuery
   */
  public void setAddDistinctToQuery(boolean addDistinctToQuery) {
    this.addDistinctToQuery = addDistinctToQuery;
  }
  
  
  /**
   * {@link #addDistinctToQuery}
   * @return addDistinctToQuery 
   */
  public boolean isAddDistinctToQuery() {
    return addDistinctToQuery;
  }
  
  public void setStoredFilterService(StoredFilterService storedFilterService) {
    this.storedFilterService = storedFilterService;
  }
  
  
  public StoredFilterService getStoredFilterService() {
    return storedFilterService;
  }
}