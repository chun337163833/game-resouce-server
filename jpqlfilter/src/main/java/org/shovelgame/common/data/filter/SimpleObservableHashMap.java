package org.shovelgame.common.data.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * SimpleObservableHashMap class
 * 
 * @author dusakk
 *
 */
public class SimpleObservableHashMap extends HashMap<String, String> {

  private static final long serialVersionUID = 1L;
  private OnMapChange listener;

  
  /**
   * Constructor
   * 
   * Call super class constructor
   * 
   * @param listener - OnMapChange
   */
  public SimpleObservableHashMap(OnMapChange listener) {
    super();
    this.listener = listener;
  }
  /**
   * Constructor
   * 
   * Call super class constructor
   * 
   * @param listener - OnMapChange
   */
  public SimpleObservableHashMap(Map<String, String> initContent, OnMapChange listener) {
    super(initContent);
    this.listener = listener;
  }
  
  @Override
  public String put(String key, String value) {
    if (value != null) {
      if (value.length() != 0) {
        String result = super.put(key, value);
        listener.mapChanged();
        return result;
      } else {
        return remove(key);
      }
    } else {
      return null;
    }
  }

  @Override
  public void putAll(Map m) {
    super.putAll(m);

    listener.mapChanged();
  }
  
  /**
   * OnMapChange interface
   * 
   * @author dusakk
   */
  public interface OnMapChange {
    
    void mapChanged();

  }
  
  
}