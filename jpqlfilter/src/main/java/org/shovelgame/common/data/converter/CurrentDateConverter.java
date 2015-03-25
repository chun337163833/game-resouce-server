/**
 * 
 */
package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.Map;

import ognl.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author MacikJ
 */
public class CurrentDateConverter implements TypeConverter {
  
  private static final Logger log = LoggerFactory.getLogger(CurrentDateConverter.class);
  
//  private String dateFormat = "yyyy-MM-dd";
  
  
  
  public CurrentDateConverter() {
    super();
    // TODO Auto-generated constructor stub
  }


  public Object convertValue(Map context, Object value, Class toType) {
	  return new Date();
  }

  public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {
    return convertValue(context, value, toType);
  }
  
  
  
}
