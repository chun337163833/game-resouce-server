/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Store setting of page. 
 * @author MacikJ
 */
public class PageSetting implements Serializable {

  private static final Logger LOG = LoggerFactory.getLogger(PageSetting.class);

  public static final int INITIAL_PAGE_NUMBER = 1;

  private int objectsPerPage = 10; // TODO from spring

  private boolean pagingEnabled = true;

  private int currentPageNumber = INITIAL_PAGE_NUMBER; // first page

  /**
   * Default constructor.
   */
  public PageSetting() {
    super();    
  }

  /**
   * Constructor.
   * 
   * @param objectsPerPage page size
   * @param pagingEnabled true if paging is enabled
   */
  public PageSetting(int objectsPerPage, boolean pagingEnabled) {
    super();
    this.objectsPerPage = objectsPerPage;
    this.pagingEnabled = pagingEnabled;
  }

  /**
   * Getter for field objectsPerPage.
   * 
   * @return the objectsPerPage
   */
  public int getObjectsPerPage() {
    return objectsPerPage;
  }

  /**
   * Setter for field objectsPerPage.
   * 
   * @param objectsPerPage
   *          the objectsPerPage to set
   */
  public void setObjectsPerPage(int objectsPerPage) {
    this.objectsPerPage = objectsPerPage;    
  }

  /**
   * Getter for field pagingEnabled.
   * 
   * @return the pagingEnabled
   */
  public boolean isPagingEnabled() {
    return pagingEnabled;
  }

  /**
   * Setter for field pagingEnabled.
   * 
   * @param pagingEnabled
   *          the pagingEnabled to set
   */
  public void setPagingEnabled(boolean pagingEnabled) {
    this.pagingEnabled = pagingEnabled;
  }

  /**
   * Getter for field currentPageNumber.
   * 
   * @return the currentPageNumber
   */
  public int getCurrentPageNumber() {
    return currentPageNumber;
  }

  /**
   * Setter for field currentPageNumber.
   * 
   * @param currentPageNumber
   *          the currentPageNumber to set
   */
  public void setCurrentPageNumber(int currentPageNumber) {
    this.currentPageNumber = currentPageNumber;
  }

}
