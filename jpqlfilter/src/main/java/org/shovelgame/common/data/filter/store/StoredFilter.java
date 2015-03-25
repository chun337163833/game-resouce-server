package org.shovelgame.common.data.filter.store;

import java.util.List;
import java.util.Map;

import org.shovelgame.common.data.filter.Sort;


public interface StoredFilter {
  
  
  Long getId();
  
  String getName();
  
  Map<String, String> getConditions();
  
  Sort getSort();
  
  List<String> getVisibleColumns();
  
  

}
