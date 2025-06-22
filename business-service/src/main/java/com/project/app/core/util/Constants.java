package com.project.app.core.util;

public class Constants {
	private Constants() {
		// Private constructor to prevent instantiation
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static final String REGEX_DATE = "^\\d{4}-((1[0-2])|(0[1-9]))-((0[1-9])|([1-2][0-9])|(3[0-1]))$";
	public static final String REGEX_CODE = "^[0-9\\w-!@#$%^&*]{6,10}$";
	public static final String REGEX_EMAIL = "^[a-zA-Z]+[a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@{1}[a-zA-Z]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
	public static final String REGEX_PHONE = "^[\\d]{11}$";
	public static final int MAX_SAME_TYPE_DIPLOMA = 3;
	public static final float AREA_MIN = 0f;
	public static final Byte MIN_AGE = 0;
	public static final String REGEX_STRING = ".*[a-zA-Z]+.*";

	// URL paths
	public static final String BASE_PATH = "src/store/file/";
	public static final String LOCAL_URL = "http://localhost:8093";
	public static final String PRODUCTION_URL = "http://103.97.125.64:8093";
	public static final String PRODUCTION_URL_FE = "http://sls-public.abp.vn/";
	public static final String SERVER_URL_FILE_API = "http://103.97.125.64:8091/api";

	// Sentiment labels
	public static final String POSITIVE = "Tích cực";
	public static final String NEGATIVE = "Tiêu cực";
	public static final String NEUTRAL = "Trung tính";

	// Source types
	public static final String SOCIAL = "SOCIAL";
	public static final String NEWS = "NEWS";
}
