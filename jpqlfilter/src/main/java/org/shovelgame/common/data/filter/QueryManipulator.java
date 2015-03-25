package org.shovelgame.common.data.filter;

import org.shovelgame.common.data.filter.querygenerator.QueryGeneratorResult;

public interface QueryManipulator {
  
  public QueryGeneratorResult manipulateQuery(QueryGeneratorResult qgr, IFilter filter);

}
