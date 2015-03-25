/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * Rozsireni tridy {@link Filter} o implementaci rozhrani {@link Map} přes které se lze
 * dostat na všechny podmínky a nastavení.
 * 
 * @author MacikJ
 */
public class MapBasedFilter extends Filter {

  
  private static final Logger log = LoggerFactory.getLogger(MapBasedFilter.class);
  
  /**
   * Označení 
   */
  private String keyForPageNumber = "page";
  private String keyForPageSize = "pagesize";
  private String keyForEnablePagigng = "pagingstate";
  /**
   * jmeno parametru kdyz jsou vsechny informace pro trideni v jednom parametru
   * pr. sort=sloupec(+)
   */
  private String keyForSortCompressed = "sort";
  
  private String keyForSortBy = "sortBy";
  private String keyForSortOrder = "sortOrder";
  
  private String keyForVisibleColumns = "columns";
  
  protected String keyForFilterId = "filterId";
  
  protected String keyForFilterName = "filterName";
  
  //^.+\(([+-]{1})\)$
  private String sortAscendingSuffix = "(+)";
  private String sortDescendingSuffix = "(-)";
  
  
  /**
   * Nastavi klice ktere se budou ignorovat - nebudou brany jako podminky.
   */
  private Set<String> ignoreKeys = new HashSet<String>();
  
  /**
   * Klice ktere jsou povolene - zbyle budou ignorovany. Pokud je null nebo prazdny Set
   * tak je ignorovano. 
   */
  private Set<String> enabledKeys = null;
  
  
  //***************************************************************
  //**
  //**   From Map
  //**
  //***************************************************************


  public MapBasedFilter() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  
  
  public MapBasedFilter(Sort defaultSortBy) {
    super(defaultSortBy);
    // TODO Auto-generated constructor stub
  }



  public MapBasedFilter(Sort defaultSortBy, PageSetting pageSetting) {
    super(defaultSortBy, pageSetting);
    // TODO Auto-generated constructor stub
  }
  

  public MapBasedFilter(Map<String, String> defaultConditions, Sort defaultSortBy,
      PageSetting pageSetting) {
    super(defaultConditions, defaultSortBy, pageSetting);
    
    // TODO Auto-generated constructor stub
  }

  


//  @Override
  public String get(Object key) {
    if (keyForPageNumber.equals(key)) {
      return new Integer(getPage().getCurrentPageNumber()).toString();
    } else if (keyForPageSize.equals(key)) {
      return new Integer(getPage().getObjectsPerPage()).toString();
    } else if (keyForSortCompressed.equals(key)) {
      return encodeSortBy(getSortBy());      
    } else if (keyForSortBy.equals(key)) {
      return getSortBy().getColumn();
    } else if (keyForSortOrder.equals(key)) {
      return getSortBy().getSortDirection().name().toLowerCase();
    } else if (keyForEnablePagigng.equals(key)) {
      return new Boolean( getPage().isPagingEnabled()).toString();
    } else if (keyForVisibleColumns.equals(key)) {
      if (visibleColumns!=null) {
        return convertToString(this.visibleColumns.toArray(new String[visibleColumns.size()]));
      } else{ 
        return "";
      } 
    } else if (keyForFilterId.equals(key)) {
      Long filterId = getFilterId();
      return filterId!=null ? filterId.toString() : "";
    } else if (keyForFilterName.equals(key)) {
      String filterName = getFilterName();
      Long filterId = getFilterId();
      return filterName!=null && filterId!=null ? filterName : "";
    } else  {         
      return getConditions().get(key);
    }     
  }
  
  /**
   * Vrati String nebo String[].
   * @param key jmeno
   * @return
   */
  public Object get2(Object key) {
    if (keyForPageNumber.equals(key)) {
      return new Integer(getPage().getCurrentPageNumber()).toString();
    } else if (keyForPageSize.equals(key)) {
      return new Integer(getPage().getObjectsPerPage()).toString();
    } else if (keyForSortCompressed.equals(key)) {
      return encodeSortBy(getSortBy());      
    } else if (keyForSortBy.equals(key)) {
      return getSortBy().getColumn();
    } else if (keyForSortOrder.equals(key)) {
      return getSortBy().getSortDirection().name().toLowerCase();
    } else if (keyForEnablePagigng.equals(key)) {
      return new Boolean( getPage().isPagingEnabled()).toString();
    } else if (keyForVisibleColumns.equals(key)) {
      if (visibleColumns!=null) {
        return visibleColumns.toArray(new String[visibleColumns.size()]);
      } else {
        return null;
      }
    } else if (keyForFilterId.equals(key)) {
      Long filterId = getFilterId();
      return filterId!=null ? filterId.toString() : "";
    } else if (keyForFilterName.equals(key)) {
      String filterName = getFilterName();
      return filterName!=null ? filterName : "";
    } else  {         
      return getConditions2().get(key);
    }     
  }

  /**
   * 
   */
//  @Override
  public String put(String key, String value) {
    if (isKeySupported(key)) {    
      String oldValue = get(key);
      if (keyForPageNumber.equals(key)) {
        getPage().setCurrentPageNumber(Integer.parseInt(value));      
      } else if (keyForPageSize.equals(key)) {
        getPage().setObjectsPerPage(Integer.parseInt(value));      
      } else if (keyForSortCompressed.equals(key)) {
        setSortBy(decodeSortBy(value));      
      } else if (keyForSortBy.equals(key)) {
        getSortBy().setColumn(value);
      } else if (keyForSortOrder.equals(key)) {
        if (SortDirection.ASC.name().toLowerCase().equals(value.toLowerCase())) {
          getSortBy().setSortDirection(SortDirection.ASC);
        } else if (SortDirection.DESC.name().toLowerCase().equals(value.toLowerCase())) {
          getSortBy().setSortDirection(SortDirection.DESC);
        } else {
          log.error("Unknown sort order '" + value + "'. Ignoring and switching to default - ASC!" );
          getSortBy().setSortDirection(SortDirection.ASC);
        }      
      } else if (keyForEnablePagigng.equals(key)) {
        getPage().setPagingEnabled(Boolean.parseBoolean(value));      
      } else if (keyForVisibleColumns.equals(key)) {       
        String[] visibleColumnsA = convertToStringArray(value);
        visibleColumns = new ArrayList<String>(Arrays.asList(visibleColumnsA));
      } else if (keyForFilterId.equals(key)) {
        Long filterId = getFilterId();
        setFilterId(new Long(value));
        return filterId!=null ? filterId.toString() : "";
      } else if (keyForFilterName.equals(key)) {
        String filterName = getFilterName();
        setFilterName(value);
        return filterName!=null ? filterName : "";
      } else  {
        return getConditions().put(key, value);  
      }    
      return oldValue;
    } else {
      return null;
    }
  }

  public void putAll(Map<String, String> values) {
    for (Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator(); iterator.hasNext();) {
      Map.Entry<String, String> entry = iterator.next();
      put(entry.getKey(), entry.getValue());
    }
  }

  /** Test zda je klic podminky podporovan.
   * @param key
   * @return
   */
  protected boolean isKeySupported(String key) {
    if (ignoreKeys.contains(key)) {
      return false;
    }
    if (enabledKeys != null && enabledKeys.size() > 0) {
      return enabledKeys.contains(key);
    }
    return true;
  }
  
//  @Override
/*  public void putAll(Map<? extends String, ? extends String> m) {
    Set<?> mes = m.entrySet();
    for (Iterator<?> iterator = m.entrySet().iterator(); iterator.hasNext();) {
      Map.Entry<String,String> me = (Map.Entry<String,String>)iterator.next();
      put(me.getKey(), me.getValue());      
    }     
  }*/

  
//  @Override
//  public void putAll((Map<? extends String, ? extends String> m) {
//    for (Iterator<Map.Entry<String,String>> iterator = m.entrySet().iterator(); iterator.hasNext();) {
//      Map.Entry<String,String> me = (Map.Entry<String,String>) iterator.next();
//      put(me.getKey(), me.getValue());      
//    }    
//  }


  

  /**
   * Zakoduje sort by do hodnoty pro okolni svet.
   * tvar:
   *   <ul>
   *     <li>{@code sortColumn} - pro normalni ascending trideni</li>
   *     <li>sortColumn(-) - pro descedning</li>
   *   </ul>
   * 
   * 
   * @return zakodovany tvar
   */
  protected String encodeSortBy(Sort sortBy) {
    if (sortBy != null) {
      String column = sortBy.getColumn();
      SortDirection sortDirection= sortBy.getSortDirection();
      if (SortDirection.DESC.equals(sortDirection)) {
        return column + "(-)"; 
      } else {
        return column;
      }
    } else {
      return null;
    }
  }
  
  protected Sort decodeSortBy(String sortBy) {
    Assert.hasText(sortBy);
    SortDirection sortDirection;    
    sortBy = sortBy.trim();
    String column = sortBy;
    if (sortBy.endsWith(sortAscendingSuffix)) {
      sortDirection= SortDirection.ASC;
      column = sortBy.substring(0, sortBy.length() - sortAscendingSuffix.length());
    } else if (sortBy.endsWith(sortDescendingSuffix)) {
      sortDirection= SortDirection.DESC;
      column = sortBy.substring(0, sortBy.length() - sortDescendingSuffix.length());
    } else {
      sortDirection= SortDirection.ASC;
    }
    
    return new Sort(column, sortDirection);
  }
    
  /**
   * Přidá podmínky (včetně nastavení stránkování a třídění) do filtru. 
   * @param condiditons
   */
  public void addConditions(Map<String, String[]> condiditons) {
    for (Iterator<Map.Entry<String, String[]>> iterator = condiditons.entrySet().iterator() ; iterator.hasNext() ;) {
      Map.Entry<String,String[]> conditionsMe = (Map.Entry<String,String[]>)iterator.next();      
      if (conditionsMe.getKey().startsWith("_")) {//kompatibilita s WebDataBinder (spring mvc) - defaultni hodnoty budou vynechany
        continue;
      }
      String[] conditionsMeValue =  conditionsMe.getValue();
      if (conditionsMeValue.length>1) {//jedna se o pole - nalezeno vice hodnot pro jednu podminku
        String tmp = convertToString(conditionsMeValue);        
        this.put(conditionsMe.getKey(), tmp);        
      } else {
        if (!"".equals(conditionsMeValue[0])) {
          this.put(conditionsMe.getKey(), conditionsMeValue[0]);
        }
      }      
            
    }    
  }

  /**
   * Vrati podminky ale odebere ty prazdne a prazne pole.
   */
//  @Override
//  public Map<String, String> getConditions() {
//    Map<String, String>  conds = super.getConditions();
//    Map<String, String> result = new HashMap<String, String>(conds.size());
//    for (Map.Entry<String, String> condE: conds.entrySet())  {
//      String condValue = condE.getValue();
//      if (condValue!=null && condValue.length()>0 && !"[]".equals(condValue)) {
//        result.put(condE.getKey(), condValue);
//      }
//    }
//    return result;
//  }
  
  /**
   * Vrati podminky tak aby byly kompatibilni s parametry v requestu. Jedna se hlavne 
   * o problem s listem podminek.
   */
  public Map<String, String[]> getConditions2() {
    Map<String, String> lconds = super.getConditions();
    Map<String, String[]> result = new HashMap<String, String[]>();
    for (Map.Entry<String, String> condE: lconds.entrySet()) {
      String condValue = condE.getValue();
      result.put(condE.getKey(), convertToStringArray(condValue));
    }
    return result;
  }
  
  /**
   * Pokud string je ve formatu pole ("[1,2]") tak ho prevede na pole
   * jinak vrati pole s jenou polozou condValue
   * @param strings
   * @return
   */
  protected String[] convertToStringArray(String strings) {
    if ( strings.indexOf("[")==0 && (strings.indexOf("]")== (strings.length()-1)) ) {
      strings = strings.substring(1, strings.length()-1);
      String[] result = strings.split("[,]");
      return result;
    } else {
      return new String[]{strings};
    }
  }
  
  /**
   * Zformatuje pole stringu do jednoho stringu.
   * @param conditionsMeValue
   * @return
   */
  protected String convertToString(String[] conditionsMeValue) {
    StringBuilder sb = new StringBuilder("[");        
    for (int i=0, m=conditionsMeValue.length ; i<m ; i++) {
      String tmp = conditionsMeValue[i];
      if (!"".equals(tmp)) {            
        sb.append(conditionsMeValue[i]);            
        sb.append(",");            
      }                    
    }                                   
    sb.append("]");
    String tmp = sb.toString();        
    tmp = tmp.replace(",]", "]");        
    return tmp;
  }

  
  public String getKeyForPageNumber() {
    return keyForPageNumber;
  }



  
  public void setKeyForPageNumber(String keyForPageNumber) {
    this.keyForPageNumber = keyForPageNumber;
  }



  
  public String getKeyForPageSize() {
    return keyForPageSize;
  }



  
  public void setKeyForPageSize(String keyForPageSize) {
    this.keyForPageSize = keyForPageSize;
  }



  
  public String getKeyForEnablePagigng() {
    return keyForEnablePagigng;
  }



  
  public void setKeyForEnablePagigng(String keyForEnablePagigng) {
    this.keyForEnablePagigng = keyForEnablePagigng;
  }



  
  public String getKeyForSortCompressed() {
    return keyForSortCompressed;
  }



  
  public void setKeyForSortCompressed(String keyForSortCompressed) {
    this.keyForSortCompressed = keyForSortCompressed;
  }



  
  public String getKeyForSortBy() {
    return keyForSortBy;
  }



  
  public void setKeyForSortBy(String keyForSortBy) {
    this.keyForSortBy = keyForSortBy;
  }



  
  public String getKeyForSortOrder() {
    return keyForSortOrder;
  }



  
  public void setKeyForSortOrder(String keyForSortOrder) {
    this.keyForSortOrder = keyForSortOrder;
  }



  
  public String getSortAscendingSuffix() {
    return sortAscendingSuffix;
  }



  
  public void setSortAscendingSuffix(String sortAscendingSuffix) {
    this.sortAscendingSuffix = sortAscendingSuffix;
  }



  
  public String getSortDescendingSuffix() {
    return sortDescendingSuffix;
  }



  
  public void setSortDescendingSuffix(String sortDescendingSuffix) {
    this.sortDescendingSuffix = sortDescendingSuffix;
  }



  @Deprecated
  public void setIgnoreKeys(Set<String> ignoreKeys) {
    this.ignoreKeys.addAll(ignoreKeys);
  }
  
  public void addIgnoreKeys(Set<String> ignoreKeys) {
    this.ignoreKeys.addAll(ignoreKeys);
  }

  public void addIgnoreKey(String ignoreKey) {
    this.ignoreKeys.add(ignoreKey);
  }
  
  
  public Set<String> getEnabledKeys() {
    return Collections.unmodifiableSet(enabledKeys);    
  }
  
  public void addEnabledKeys(String... enabledKey) {
    if (this.enabledKeys == null) {
      this.enabledKeys = new HashSet<String>();
    }
    for (String tmp: enabledKey) {
      this.enabledKeys.add(tmp);
    }    
  }
  
  public void addEnabledKeys(Collection<String> enabledKey) {
    if (this.enabledKeys == null) {
      this.enabledKeys = new HashSet<String>();
    }
    for (String tmp: enabledKey) {
      this.enabledKeys.add(tmp);
    }    
  }
  
  
  public Set<String> getIgnoreKeys() {
    return Collections.unmodifiableSet(ignoreKeys);
  }



  
  public String getKeyForVisibleColumns() {
    return keyForVisibleColumns;
  }



  
  public void setKeyForVisibleColumns(String keyForVisibleColumns) {
    this.keyForVisibleColumns = keyForVisibleColumns;
  }



  
  public String getKeyForFilterId() {
    return keyForFilterId;
  }



  
  public void setKeyForFilterId(String keyForFilterId) {
    this.keyForFilterId = keyForFilterId;
  }



  
  public String getKeyForFilterName() {
    return keyForFilterName;
  }



  
  public void setKeyForFilterName(String keyForFilterName) {
    this.keyForFilterName = keyForFilterName;
  }
  
  
  
}
