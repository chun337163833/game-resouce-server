/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Store sort setting - data can be sorted by one column only.
 * 
 * @author MacikJ
 */
public class Sort implements Serializable {

  private static final Logger LOG = LoggerFactory.getLogger(Sort.class);

  private String column;

  private SortDirection direction;

  /**
   * Default constructor.
   */
  public Sort() {
    super();
    direction = SortDirection.ASC;
  }

  
  
  public Sort(String column) {
    super();
    this.column = column;
    this.direction = SortDirection.ASC;
  }



  /**
   * Constructor.
   * 
   * @param column column which is sorted
   * @param direction direction of sort
   */
  public Sort(String column, SortDirection direction) {
    super();
    this.column = column;
    this.direction = direction;
  }

  public Sort(Sort sort) {
    this.column = sort.column;
    this.direction = sort.direction;
  }
  
  /**
   * Getter for field column.
   * 
   * @return the column
   */
  public String getColumn() {
    return column;
  }

  /**
   * Setter for field column.
   * 
   * @param column the column to set
   */
  public void setColumn(String column) {
    if (!"".equals(column)) {
      this.column = column;
    }
  }

  /**
   * Getter for field direction.
   * 
   * @return the direction
   */
  public SortDirection getSortDirection() {
    if (direction != null) {
      return direction;
    } else {
      return SortDirection.ASC;
    }
  }

  /**
   * Setter for field direction.
   * 
   * @param direction the direction to set
   */
  public void setSortDirection(SortDirection direction) {
    this.direction = direction;
  }

}
