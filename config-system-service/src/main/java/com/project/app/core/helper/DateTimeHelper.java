package com.project.app.core.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeHelper {
	private DateTimeHelper() {
		// Private constructor to prevent instantiation
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	private static final String TIMEZONE_GMT7 = "GMT+07:00";
	private static final ZoneId ZONE_ID_GMT7 = ZoneId.of(TIMEZONE_GMT7);
	private static final ZoneId ZONE_ID_VIETNAM = ZoneId.of("Asia/Ho_Chi_Minh");

	/**
	 * Converts a timestamp to the start of the day in GMT+07:00 timezone.
	 *
	 * @param inputTimestamp the input timestamp (Long, seconds since Epoch)
	 * @return Long - timestamp representing the start of the day (10 digits, seconds since Epoch), or null if inputTimestamp is null
	 */
	public static final Long toStartOfDay(Long inputTimestamp) {
		if (inputTimestamp == null) {
			return null;
		}
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(inputTimestamp), ZONE_ID_GMT7);
		LocalDate localDate = dateTime.toLocalDate();
		LocalDateTime midnightDateTime = localDate.atStartOfDay();
		ZonedDateTime midnightZonedDateTime = midnightDateTime.atZone(ZONE_ID_GMT7);
		return midnightZonedDateTime.toEpochSecond();
	}

	/**
	 * Converts a timestamp to the end of the day in GMT+07:00 timezone.
	 *
	 * @param inputTimestamp the input timestamp (Long, seconds since Epoch)
	 * @return Long - timestamp representing the end of the day (10 digits, seconds since Epoch), or null if inputTimestamp is null
	 */
	public static final Long toEndOfDay(Long inputTimestamp) {
		if (inputTimestamp == null) {
			return null;
		}
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(inputTimestamp), ZONE_ID_GMT7 );
		LocalDateTime endOfDayDateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.of(23, 59, 59));
		ZonedDateTime midnightZonedDateTime = endOfDayDateTime.atZone(ZONE_ID_GMT7 );
		return midnightZonedDateTime.toEpochSecond();
	}

	/**
	 * Converts a timestamp to a LocalDate in GMT+07:00 timezone.
	 *
	 * @param timestamp the input timestamp (Long, seconds since Epoch)
	 * @return LocalDate - the date corresponding to the timestamp (format yyyy-MM-dd), or null if timestamp is null
	 */
	public static final LocalDate toLocalDate(Long timestamp) {
		if (timestamp != null) {
			LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZONE_ID_GMT7 );
			return dateTime.toLocalDate();
		} else {
			return null;
		}
	}

	/**
	 * Converts a timestamp to a date string in format (dd-MM-yyyy) in the Asia/Ho_Chi_Minh timezone.
	 *
	 * @param date the input timestamp (Long, seconds since Epoch)
	 * @return String - date formatted as (dd-MM-yyyy), or null if date is null
	 */
	public static final String timestampToDateString(Long date) {
		if (date == null) {
			return null;
		}
		Instant instant = Instant.ofEpochSecond(date);
		ZoneId zoneIdVietnam = ZONE_ID_VIETNAM ;
		LocalDateTime vietnamDateTime = LocalDateTime.ofInstant(instant, zoneIdVietnam);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Format dd-MM-yyyy
		return vietnamDateTime.format(formatter);
	}

	/**
	 * Gets the current time in the Asia/Ho_Chi_Minh timezone as a timestamp.
	 *
	 * @return Long - timestamp of the current time (10 digits, seconds since Epoch)
	 */
	public static final Long getCurrentTimestamp() {
		ZoneId zoneId = ZONE_ID_VIETNAM ;
		LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
		ZonedDateTime zonedDateTime = currentDateTime.atZone(zoneId);
		return zonedDateTime.toEpochSecond();
	}

	/**
	 * Gets the current time in UTC+7 timezone as a formatted string.
	 *
	 * @return String - current time formatted as (yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX)
	 */
	public static String getCurrentTimeInUTCPlus7() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC+7"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"); // Format yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX
		return zonedDateTime.format(formatter);
	}

	/**
	 * Parses a date-time string into a LocalDateTime object.
	 *
	 * @param dateTimeString the date-time string (format yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX)
	 * @return LocalDateTime - the parsed LocalDateTime object
	 */
	public static LocalDateTime parseDateTimeString(String dateTimeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"); // Format yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX
		return LocalDateTime.parse(dateTimeString, formatter);
	}

	/**
	 * Gets the current date as a LocalDate in the Asia/Ho_Chi_Minh timezone.
	 *
	 * @return LocalDate - the current date in the Asia/Ho_Chi_Minh timezone
	 */
	public static LocalDate getCurrentLocalDate() {
		ZoneId zoneId = ZoneId.of("UTC");
		LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
		return currentDateTime.toLocalDate();
	}

	/**
	 * Gets the current LocalDateTime in UTC and converts it to a timestamp (seconds since Epoch).
	 *
	 * @return Long - timestamp in seconds since Epoch (10 digits)
	 */
	public static Long getCurrentTimestampInUtc() {
		ZoneId zoneId = ZoneId.of("UTC");
		LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
		ZonedDateTime zonedDateTime = currentDateTime.atZone(zoneId);
		return zonedDateTime.toEpochSecond();
	}

	/**
	 * Gets the start of the day (00:00:00) timestamp in GMT+07:00 timezone.
	 *
	 * @return Long - timestamp representing 00:00:00 of the current day (10 digits, seconds since Epoch)
	 */
	public static Long getStartOfDayTimestamp() {
		ZoneId zoneId = ZONE_ID_GMT7 ;
		LocalDateTime now = LocalDateTime.now(zoneId);
		LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
		ZonedDateTime startOfDayZoned = startOfDay.atZone(zoneId);
		log.info("Start of Day Timestamp: {}", startOfDayZoned.toEpochSecond());
		return startOfDayZoned.toEpochSecond();
	}

	/**
	 * Gets the end of the day (23:59:59) timestamp in GMT+07:00 timezone.
	 *
	 * @return Long - timestamp representing 23:59:59 of the current day (10 digits, seconds since Epoch)
	 */
	public static Long getEndOfDayTimestamp() {
		ZoneId zoneId = ZONE_ID_GMT7 ;
		LocalDateTime now = LocalDateTime.now(zoneId);
		LocalDateTime endOfDay = LocalDateTime.of(now.toLocalDate(), LocalTime.of(23, 59, 59));
		ZonedDateTime endOfDayZoned = endOfDay.atZone(zoneId);
		return endOfDayZoned.toEpochSecond();
	}

	/**
	 * Converts a given LocalDate to the start-of-day timestamp in GMT+07:00 timezone.
	 *
	 * @param localDate the input LocalDate
	 * @return Long - timestamp representing the start of the day (10 digits, seconds since Epoch), or null if localDate is null
	 */
	public static final Long convertLocalDateToStartOfDayTimestamp(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		LocalDateTime midnightDateTime = localDate.atStartOfDay();
		ZonedDateTime midnightZonedDateTime = midnightDateTime.atZone(ZONE_ID_GMT7);
		return midnightZonedDateTime.toEpochSecond();
	}

	/**
	 * Converts a given LocalDate to the end-of-day timestamp in GMT+07:00 timezone.
	 *
	 * @param localDate the input LocalDate
	 * @return Long - timestamp representing the end of the day (10 digits, seconds since Epoch), or null if localDate is null
	 */
	public static final Long convertLocalDateToEndOfDayTimestamp(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		LocalDateTime endOfDayDateTime = localDate.atTime(23, 59, 59);
		ZonedDateTime endOfDayZonedDateTime = endOfDayDateTime.atZone(ZONE_ID_GMT7);
		return endOfDayZonedDateTime.toEpochSecond();
	}

	/*
	 * Converts a timestamp to a date time string in format (HH:mm:ss dd-MM-yyyy) in the Asia/Ho_Chi_Minh timezone.
	 *
	 * @param timestamp the input timestamp (Long, seconds since Epoch)
	 * @return String - date time formatted as (HH:mm:ss dd-MM-yyyy)
	 * */
	public static String timestampToDateTimeString(long timestamp) {
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZONE_ID_VIETNAM);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

		return dateTime.format(formatter);
	}

	/**
	 * Gets the current date and time in the Asia/Ho_Chi_Minh timezone.
	 *
	 * @return LocalDateTime - the current date and time in the Asia/Ho_Chi_Minh timezone
	 */
	public static LocalDateTime getCurrentLocalDateTimeInHCM() {
		ZoneId zoneId = ZONE_ID_VIETNAM ;
		ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
		return zonedDateTime.toLocalDateTime();
	}

	/**
	 * Gets the current date in the Asia/Ho_Chi_Minh timezone.
	 *
	 * @return LocalDate - the current date and time in the Asia/Ho_Chi_Minh timezone
	 */
	public static LocalDate getCurrentLocalDateInHCM() {
		ZoneId zoneId = ZONE_ID_VIETNAM ;
		ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
		return zonedDateTime.toLocalDate();
	}

	/**
	 * Gets the timestamp date and time in the Asia/Ho_Chi_Minh timezone.
	 *
	 * @Param dateTime - the date and time
	 * @return long - the timestamp in the Asia/Ho_Chi_Minh timezone
	 */
	public static long getTimestampInHCM(LocalDateTime dateTime) {
		ZoneId zoneId = ZONE_ID_VIETNAM;
		ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);
		return zonedDateTime.toEpochSecond();
	}

	public static LocalTime  convertEpochToLocalTime(long epochSeconds) {
		return Instant.ofEpochSecond(epochSeconds)
				.atZone(ZoneId.systemDefault())
				.toLocalTime();
	}

	public static LocalDateTime convertEpochSecondToLocalDateTimeHCM(long epochSecond) {
		return LocalDateTime.ofInstant(
				Instant.ofEpochSecond(epochSecond),
				ZONE_ID_VIETNAM
		);
	}

	public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
		if(localDateTime == null) {
			return null;
		}
		return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public static String convertEpochSecondToLocalDateTimeStringHCM(long epochSecond) {
		return convertLocalDateTimeToString(convertEpochSecondToLocalDateTimeHCM(epochSecond));
	}
}