package org.shovelgame.spring.convert;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

/**
 * Converts between calendar a string, class is not necessary because spring does it via Date
 * 
 * @author PsencikO
 *
 */
public class StringToCalendarConverter implements GenericConverter {
	public Calendar convert(String source) {
		if(StringUtils.isEmpty(source))
			return null;
		return new GregorianCalendar();
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return new HashSet<ConvertiblePair>(Arrays.asList(
				new ConvertiblePair(String.class, Calendar.class),
				new ConvertiblePair(Calendar.class, String.class)		
				));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {		
		DateTimeFormat dtf = null;
		
		if(targetType.getType().equals(Calendar.class)) {
			dtf = targetType.getAnnotation(DateTimeFormat.class);
		} else {
			dtf = sourceType.getAnnotation(DateTimeFormat.class);
		}
		
		DateFormatter formatter = new DateFormatter();
		if(dtf!=null) {
			formatter.setIso(dtf.iso());
			formatter.setPattern(dtf.pattern());
			formatter.setStylePattern(dtf.style());
		}
		
		if(source instanceof String && !StringUtils.isEmpty((String)source)) {
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse((String)source, Locale.getDefault()));
				return c;
			} catch (ParseException e) {
				throw new ConversionFailedException(sourceType, targetType, source, e);
			}
		} else if(source instanceof Calendar) {
			return formatter.print(((Calendar)source).getTime(), Locale.getDefault());
		}
		
		return null;
	}
}
