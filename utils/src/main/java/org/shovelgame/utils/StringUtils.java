package org.shovelgame.utils;

import java.lang.reflect.Method;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.List;

public class StringUtils extends org.springframework.util.StringUtils {

	public static final String EMPTY_STRING = "";

	public static Boolean isNumber(String str) {
		if(isEmpty(str)) return Boolean.FALSE;
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
	
	public static Boolean test(String str, CompareType type, String... tested) {
		try {
			Method m = String.class.getMethod(type.getMethod(), type.getArguments());
			for (String testedStr : tested) {
				Boolean result = (Boolean) ReflectionUtils.invokeMethod(m, str,
						testedStr);
				if (result)
					return Boolean.TRUE;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return Boolean.FALSE;
	}

	public static String[] getArrayFromString(String str, String delimiter) {
		if (str == null || delimiter == null)
			return null;
		return getArrayFromString(str, delimiter,
				new StringConversion<String>() {
					@Override
					public String convert(String str) {
						return str;
					}

					@Override
					public String[] createArray(int length) {
						return new String[length];
					}
				});
	}

	public static String getStringFromArray(String[] array, String delimiter) {
		return getStringFromArray(array, delimiter,
				new TypeConversion<String>() {
					@Override
					public String convert(String str) {
						return str;
					}
				});
	}

	public static <T extends Object> String getStringFromArray(T[] array,
			String delimiter, TypeConversion<T> conversion) {
		return getStringFromArray(Arrays.asList(array), delimiter, conversion);
	}

	public static <T extends Object> String getStringFromArray(List<T> array,
			String delimiter, TypeConversion<T> conversion) {
		StringBuilder _result = new StringBuilder();
		for (int i = 0; i < array.size(); i++) {
			T t = array.get(i);
			_result.append(conversion.convert(t));
			if (array.size() > i + 1) {
				_result.append(delimiter);
			}
		}
		return _result.toString();
	}

	public static <T extends Object> T[] getArrayFromString(String str,
			String delimiter, StringConversion<T> conversion) {
		String[] deps = str.split(delimiter);
		T[] departments = conversion.createArray(deps.length);
		for (int i = 0; i < deps.length; i++) {
			departments[i] = conversion.convert(deps[i].trim());
		}
		return departments;
	}

	public static String clearWhiteSpaces(String string) {
		return string.replace(" ", "");
	}

	public interface StringConversion<E> {
		public E convert(String str);

		public E[] createArray(int length);
	}

	public interface TypeConversion<E> {
		public String convert(E str);
	}

	public enum CompareType {
		CONTAINS("contains", CharSequence.class), ENDSWITH("endsWith", String.class), STARTWITH("startsWith", String.class), EQUALS(
				"equals", String.class);
		private String method;
		private Class<?>[] arguments;
		

		private CompareType(String method, Class<?>... arguments) {
			this.method = method;
			this.arguments = arguments;
		}


		public String getMethod() {
			return method;
		}
		public Class<?>[] getArguments() {
			return arguments;
		}
	}
	
	public static String flattenToAscii(String string) {
	    char[] out = new char[string.length()];
	    string = Normalizer.normalize(string, Normalizer.Form.NFD);
	    int j = 0;
	    for (int i = 0, n = string.length(); i < n; ++i) {
	        char c = string.charAt(i);
	        if (c <= '\u007F') out[j++] = c;
	    }
	    return new String(out);
	}		
}
