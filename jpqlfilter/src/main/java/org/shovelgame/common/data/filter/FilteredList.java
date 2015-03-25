/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.shovelgame.common.data.filter.error.ConditionGenerationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * List of filtered objects (entities). Store data, filter conditions used when data was load and
 * number of all records (used for paging).
 * 
 * @param <T> - entity element
 * 
 * @author MacikJ
 */
public class FilteredList<T>  {

  private static final Logger LOG = LoggerFactory.getLogger(FilteredList.class);

  private IFilter filter;

  private List<T> data;

  private long fullDataSize;
  
  private ConditionGenerationFailedException exception;

  /**
   * Constructor.
   * 
   * @param filter filter to used for data load
   * @param data loaded data
   * @param fullDataSize number of all records
   */
  public FilteredList(IFilter filter, List<T> data, long fullDataSize) {
    super();
    this.filter = filter;
    this.data = data;
    this.fullDataSize = fullDataSize;
  }

  
  
  public FilteredList(IFilter filter, ConditionGenerationFailedException exception) {
    super();
    this.filter = filter;
    this.exception = exception;
    this.data = Collections.emptyList();
    this.fullDataSize = 0;
  }



  /**
   * Getter for field filter.
   * 
   * @return the filter
   */
  public IFilter getFilter() {
    return filter;
  }

  /**
   * Getter for field data.
   * 
   * @return the data
   */
  public List<T> getData() {
    return data;
  }

  /**
   * Getter for field fullDataSize.
   * 
   * @return the fullDataSize
   */
  public long getFullDataSize() {
    return fullDataSize;
  }
  
  public boolean isPagingEnabled() {
    return this.filter.getPage().isPagingEnabled();
  }
  
  public int getCurrentPage() {
    return this.filter.getPage().getCurrentPageNumber();
  }
  
  public int getPageCount() {
    int pageSize = this.filter.getPage().getObjectsPerPage();
    long totalSize = getFullDataSize();    
    return (int) totalSize / pageSize + ((totalSize % pageSize) > 0 ? 1 : 0 );
  }
  
  
  public ConditionGenerationFailedException getException() {
    return exception;
  }
  
  public boolean isError() {
    return getException() !=null;
  }
  
    
}
