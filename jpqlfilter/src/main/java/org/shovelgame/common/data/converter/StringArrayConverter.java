/**
 * 
 */
package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ognl.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Konvertor pro pole stringu.
 * @author MacikJ
 */
public class StringArrayConverter implements TypeConverter {
  
  private static final Logger log = LoggerFactory.getLogger(StringArrayConverter.class);
  
  public Object convertValue(Map context, Object ovalue, Class toType) {
    if (ovalue != null) {      
      String value = (String) ovalue;
      if (value.startsWith("[") && value.endsWith("]")) {
        value = value.substring(1, value.length()-1);
      }
      String[] values = value.split(",");
      List<String> lvalues = new ArrayList<String>(values.length);
      for (String val: values) {
        lvalues.add(val.trim());
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
