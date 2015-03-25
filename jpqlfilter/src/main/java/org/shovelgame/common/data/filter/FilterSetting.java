package org.shovelgame.common.data.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Store settings of filter
 * 
 * @author FlanderkaD
 */
public class FilterSetting {

  private static final Logger LOG = LoggerFactory.getLogger(FilterSetting.class);

  public static final int INITIAL_PARAM_SEQUENCE = 0;

  private int currentSequenceValue = INITIAL_PARAM_SEQUENCE; // start value for symbol of parameters

  /**
   * Default constructor
   */
  public FilterSetting() {
    super();
  }

  /**
   * Constructor
   * 
   * @param currentSequenceValue start value for symbol of parameters
   */
  public FilterSetting(int currentSequenceValue) {
    super();
    this.currentSequenceValue = currentSequenceValue;
  }

  /**
   * Getter for field currentSequenceValue.
   * 
   * @return the currentSequenceValue
   */
  public int getCurrentSequenceValue() {
    return currentSequenceValue;
  }

  /**
   * Setter for field currentSequenceValue.
   * 
   * @param currentSequenceValue the currentSequenceValue to set
   */
  public void setCurrentSequenceValue(int currentSequenceValue) {
    this.currentSequenceValue = currentSequenceValue;
  }

}
