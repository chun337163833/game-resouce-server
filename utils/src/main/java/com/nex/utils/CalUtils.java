package com.nex.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalUtils {
	public static Calendar yearBefore(Calendar c) {
		Calendar ca = (Calendar) c.clone();
		ca.add(Calendar.YEAR, -1);
		return ca;
	}
	
	public static Calendar addYear(Calendar c) {
		Calendar ca = (Calendar) c.clone();
		ca.add(Calendar.YEAR, 1);
		return ca;
	}	
	
	public static Calendar addDays(Calendar c, int days) {
		Calendar ca = (Calendar) c.clone();
		ca.add(Calendar.DATE, days);
		return ca;
	}
	
	public static Calendar addSeconds(Calendar c, int seconds) {
		Calendar ca = (Calendar) c.clone();
		ca.add(Calendar.SECOND, seconds);
		return ca;
	}	
	
	public static Calendar todayNoTime() {
		Calendar c = new GregorianCalendar();
		resetTime(c);
		return c;
	}
	
	public static void resetTime(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
	}
	
	public static Calendar toCalendar(Date d) {
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		return c;
	}
}
