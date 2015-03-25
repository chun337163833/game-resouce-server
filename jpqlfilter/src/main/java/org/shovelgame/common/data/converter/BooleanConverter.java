package org.shovelgame.common.data.converter;

import java.lang.reflect.Member;
import java.util.Map;

import ognl.TypeConverter;

/**
 * Boolean converter for struts<br/>
 * Converter works with values TRUE and FALSE<br/>
 * There are two main methods.
 * <p>
 * <b>ConvertFromString</b> - In the first time controls if parameter toClass is Boolean. Then this
 * procedure get first member from value and compare it with strings TRUE_VALUE and FALSE_VALUE
 * <p>
 * <b>ConvertToString</b> - In the first time controls if parameter o is Boolean. Then converts
 * parameter o to string.
 * 
 * @author FlanderkaD
 * @version 1.0.0
 */
public class BooleanConverter implements TypeConverter {
  
  /**
   * String representations for values true and false (case insensitive) 
   */
  private static final String TRUE_VALUE = "TRUE";
  private static final String FALSE_VALUE = "FALSE";

  /**
   * Everything start here
   * 
   * @param context Map of contexts
   * @param target target
   * @param member member
   * @param propertyName name of property
   * @param value Value of object
   * @param toType Destination type
   * @return Converted value
   */
  public Object convertValue(Map context, Object target, Member member, String propertyName,
      Object value, Class toType) {
    return convertValue(context, value, toType);
  }

  /**
   * Decide which appropriate method fromString or toString will be called.
   *
   * @param context Map of contexts
   * @param o Value
   * @param toClass Destination type
   * @return Converted value
   */
  public Object convertValue(Map context, Object o, Class toClass) {
    if (toClass.equals(String.class)) {
      return convertToString(context, o);
    } else if (o instanceof String[]) {
      return convertFromString(context, (String[]) o, toClass);
    } else if (o instanceof String) {
      return convertFromString(context, new String[] { (String) o }, toClass);
    } else {
      return performFallbackConversion(context, o, toClass);
    }
  }

  /**
   * Possibility to fall back to another converter or method
   * @param context Map of contexts
   * @param o Value of object
   * @param toClass Destination type
   * @return String representation of object
   */
  protected Object performFallbackConversion(Map context, Object o, Class toClass) {
    throw new IllegalArgumentException("Format is not valid (FallbackConversion) ");
  }

  /**
   * In the first time controls if parameter toClass is Boolean. Then this procedure get first member
   * from value and compare it with strings TRUE_VALUE and FALSE_VALUE
   * 
   * @param context Map of contexts
   * @param values Array of string values
   * @param toClass Destination type
   * @return Boolean value
   */
  public Object convertFromString(Map context, String[] values, Class toClass) {
    if (toClass != Boolean.class) {
      throw new IllegalArgumentException("Could not parse boolean, target class is not Boolean");
    }

    if (values != null && values.length > 0 && values[0] != null && values[0].length() > 0) {
      String value = values[0];
      if (value.equalsIgnoreCase(TRUE_VALUE)) {
        return new Boolean(true);
      } else if (value.equalsIgnoreCase(FALSE_VALUE)) {
        return new Boolean(false);
      } else {
        throw new IllegalArgumentException("Could not parse boolean, value is not valid");
      }
    }
    return null;
  }

  /**
   * In the first time controls if parameter o is Boolean. Then converts parameter o to string.
   * 
   * @param context Map of contexts
   * @param o Value of object
   * @return Converted string value
   */
  public String convertToString(Map context, Object o) {
    if (o instanceof Boolean) {
      Boolean b = (Boolean) o;
      return b ? TRUE_VALUE : FALSE_VALUE;
    }
    return "";
  }

}
