/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shovelgame.common.data.filter.querygenerator.SimpleJPQLFilterGenerator.EntityAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Context for condition generator - store query parts and condition values.
 * 
 * @author MacikJ
 * 
 */
public class ConditionGeneratorContext {

  private static final Logger LOG = LoggerFactory.getLogger(ConditionGeneratorContext.class);

  private List<EntityAlias> entitiesInQuery = Collections.emptyList();
  


  private StringBuilder selectPart = new StringBuilder();

  private StringBuilder fromPart = new StringBuilder();

  private StringBuilder wherePart = new StringBuilder();

  private List<KeyValue> values = new ArrayList<KeyValue>();

  private Map<String, Set<String>> orConditions;

  private Map<String, StringBuilder> orConditionsQueryPart = new HashMap<String, StringBuilder>();
  
  private int valueSequence;
  
  

  
  
  public ConditionGeneratorContext(int valueSequence) {
    super();
    this.valueSequence = valueSequence;
  }
  
  /** Getter for field entitiesInQuery.
   * @return the entitiesInQuery
   */
  public List<EntityAlias> getEntitiesInQuery() {
    return entitiesInQuery;
  }

  
  /** Setter for field entitiesInQuery.
   * @param entitiesInQuery the entitiesInQuery to set
   */
  public void setEntitiesInQuery(List<EntityAlias> entitiesInQuery) {
    this.entitiesInQuery = entitiesInQuery;
  }
  
  
  /**
   * <p>
   * Add parameter value (used id query condition).
   * </p>
   * 
   * @param name parameter name
   * @param value parameter value
   */
  public void addValue(String name, Object value) {
    this.values.add(new KeyValue(name, value));
  }

  /**
   * Generator for unique parameter ID.
   * 
   * @return next value
   */
  public int nextValueId() {
    return valueSequence++;
  }

  /**
   * Getter for field selectPart.
   * 
   * @return the selectPart
   */
  public StringBuilder getSelectPart() {
    return selectPart;
  }

  /**
   * Getter for field fromPart.
   * 
   * @return the fromPart
   */
  public StringBuilder getFromPart() {
    return fromPart;
  }

  /**
   * <p>
   * Append query where part to query
   * </p>
   * .
   * 
   * @param contion query part to append
   */
  public void addWherePartCondition(String conditionName, String contion) {
    //find condition by name in OR groups
    String groupForCondition = null;
    for (Map.Entry<String, Set<String>> conditionGroupE : this.orConditions.entrySet()) {
      if (conditionGroupE.getValue().contains(conditionName)) {
        Assert.isNull(groupForCondition, "conditionName must be unique");
        groupForCondition = conditionGroupE.getKey();
      }
    }
    if (groupForCondition == null) {
      //AND
      if (this.wherePart.length() != 0) {
        this.wherePart.append(" and ");
      }
      this.wherePart.append(contion);
    } else {
      //OR
      StringBuilder sb = this.orConditionsQueryPart.get(groupForCondition);
      if (sb == null) {
        sb = new StringBuilder();
        this.orConditionsQueryPart.put(groupForCondition, sb);
      } else {
        sb.append(" or ");
      }
      sb.append(contion);
    }

  }

  /**
   * Getter for field wherePart.
   * 
   * @return the wherePart
   */
  public StringBuilder getWherePart() {
    if (this.orConditionsQueryPart.size() > 0) {
      StringBuilder result = new StringBuilder();
      result.append(wherePart);      
      for (Map.Entry<String, StringBuilder> conditionE : this.orConditionsQueryPart.entrySet()) {
        result.append(" and ( ");
        result.append(conditionE.getValue());
        result.append(" ) ");
      }
      return result;
    } else {
      return wherePart;  
    }    
  }

  /**
   * Getter for field values.
   * 
   * @return the values
   */
  public List<KeyValue> getValues() {
    return values;
  }

  /**
   * Setter for field orConditions.
   * 
   * @param orConditions the orConditions to set
   */
  public void setOrConditions(Map<String, Set<String>> orConditions) {
    this.orConditions = orConditions;
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
   * Store key - value pair.
   * 
   * @author MacikJ
   */
  public static class KeyValue {

    private String key;
    private Object value;

    private KeyValue(String key, Object value) {
      super();
      this.key = key;
      this.value = value;
    }

    /**
     * Getter for field key.
     * 
     * @return the key
     */
    public String getKey() {
      return key;
    }

    /**
     * Getter for field value.
     * 
     * @return the value
     */
    public Object getValue() {
      return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((key == null) ? 0 : key.hashCode());
      return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (!(obj instanceof KeyValue)) return false;
      KeyValue other = (KeyValue) obj;
      if (key == null) {
        if (other.key != null) return false;
      } else if (!key.equals(other.key)) return false;
      return true;
    }

  }
}
