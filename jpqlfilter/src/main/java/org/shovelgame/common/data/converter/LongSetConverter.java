/**
 * 
 */
package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ognl.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Konvertor do Setu Longu - vetsinou pole ID.
 * @author MacikJ
 */
public class LongSetConverter implements TypeConverter {
  
  private static final Logger log = LoggerFactory.getLogger(LongSetConverter.class);
  
  public Object convertValue(Map context, Object ovalue, Class toType) {
    if (ovalue != null) {      
      String value = (String) ovalue;
      if (value.startsWith("[") && value.endsWith("]")) {
        value = value.substring(1, value.length()-1);
      }
      String[] values = value.split(",");
      Set<Long> lvalues = new HashSet<Long>(values.length);
      for (String val: values) {
        lvalues.add(new Long(val.trim()));
      }      
      return lvalues;
    } else {
      return null;
    }
  }

  public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {
    return convertValue(context, value, toType);
  }
  
}
