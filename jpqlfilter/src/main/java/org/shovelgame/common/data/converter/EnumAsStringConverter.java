/**
 * 
 */
package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.util.Map;

import ognl.TypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MacikJ
 * 
 */
public class EnumAsStringConverter implements TypeConverter {

  private static final Logger log = LoggerFactory.getLogger(EnumAsStringConverter.class);

  public EnumAsStringConverter() {
    super();
  }

  public Object convertValue(Map context, Object value, Class toType) {
    Enum eVal = (Enum) value;
    return eVal.toString();
  }

  public Object convertValue(Map context, Object target, Member member, String propertyName,
      Object value, Class toType) {
    return convertValue(context, value, toType);
  }

}
