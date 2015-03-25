/**
 * 
 */
package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ognl.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author MacikJ
 *
 */
public class DateConverter implements TypeConverter {
  
  private static final Logger log = LoggerFactory.getLogger(DateConverter.class);
  
  private String dateFormat = "yyyy-MM-dd";
  
  
  
  public DateConverter() {
    super();
    // TODO Auto-generated constructor stub
  }

  public DateConverter(String dateFormat) {
    super();
    this.dateFormat = dateFormat;
  }

  public Object convertValue(Map context, Object value, Class toType) {
//    if ( Date.class.isAssignableFrom(toType) ) {
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
      try {
        return sdf.parse((String) value);
      } catch (ParseException e) {
        log.error("Conversion failed!", e);
        throw new RuntimeException(e);
      }
//    } else {
//      throw new UnsupportedOperationException("Conversion to type " + toType.getName() + " is not supported!");
//    }
    
  }

  public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {
    return convertValue(context, value, toType);
  }
  
  
  
}
