package org.shovelgame.common.data.filter.querygenerator.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate LIKE condition with % on left and right side of value (columnName LIKE '%value%').
 * 
 * @author MacikJ
 * 
 */
public class LRLike extends RLike {

  private static final Logger LOG = LoggerFactory.getLogger(LRLike.class);
          
  
  public LRLike() {
    addToLeft = true;
    addToRight = true;
  }

}
