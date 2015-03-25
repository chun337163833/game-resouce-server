/**
 * 
 */
package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import ognl.TypeConverter;

import org.shovelgame.common.data.filter.error.ConditionValueConversionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * Konvertor pro datumy ve formatu NOW+1D (NOW+-1DWM)
 * @author MacikJ
 */
public class SpecialDateConverter implements TypeConverter {
  
  private static final Logger log = LoggerFactory.getLogger(SpecialDateConverter.class);
  
  
  
  private static String NOW = "NOW";
  
  
  
  
  
  public SpecialDateConverter() {
    super();
    // TODO Auto-generated constructor stub
  }


  public Object convertValue(Map context, Object value, Class toType) {
    Assert.isInstanceOf(String.class, value);
    String val = (String) value;
    GregorianCalendar date = new GregorianCalendar();
    
    Assert.isTrue(val.startsWith(NOW), "Unknown date format. NOW reqired.");

    if (NOW.equals(val)) {
      return new Date();
    } 
    
    //PARSING NOW+1D
    String dateStr = val.substring(NOW.length(), val.length()-1);
    dateStr = dateStr.replace('+', ' ').trim();
    int dateOffset =Integer.parseInt(dateStr);
    String dwm = val.substring(val.length()-1);
    if ("D".equals(dwm)) {
      date.roll(GregorianCalendar.DATE, dateOffset);
    } else if ("W".equals(dwm)) {
      date.roll(GregorianCalendar.WEEK_OF_MONTH, dateOffset);
    } else if ("M".equals(dwm)) {
      date.roll(GregorianCalendar.MONTH, dateOffset);
    } else {
      throw new UnsupportedOperationException("Unknown date constant " + dwm);
    }
    return date.getTime();
  }

  public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {
    return convertValue(context, value, toType);
  }
  
  
  
}
