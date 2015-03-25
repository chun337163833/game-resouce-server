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
public class BooleanAsNumberConverter implements TypeConverter {

  private static final Logger log = LoggerFactory.getLogger(BooleanAsNumberConverter.class);

  public BooleanAsNumberConverter() {
    super();

  }

  public Object convertValue(Map context, Object value, Class toType) {
    if ( value==null) {
      return null;
    } else {
      return Boolean.TRUE.equals(value) ? 1 : 0;
    }
  }

  public Object convertValue(Map context, Object target, Member member, String propertyName,
      Object value, Class toType) {
    return convertValue(context, value, toType);
  }

}
