package com.project.app.core.util;

public class DateUtils {
	private DateUtils() {
		// Private constructor to prevent instantiation
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static final String NORMAL_DATE = "yyyy-MM-dd";
	public static final String NORMAL_DATE_TIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD = "yyyy/MM/dd";
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm";
	public static final String DATE_TIME_FORMAT_HH_MM = "HH:mm";
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS_SSSSSSXXX = "yyyy-MM-dd HH:mm:ss.SSSSSSXXX";
	public static final String DATE_TIME_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd.MM.yyyy";
	public static final String TIME_FORMAT_12_HOUR = "h a";
	public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";
}
