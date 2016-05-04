/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * @author bashar
 */
public class DateTimeUtils extends org.joda.time.DateTimeUtils {
    private DateTimeUtils() {
    };

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.longDate();
    public static final DateTimeFormatter DATE_FORMATTER_SHORT = DateTimeFormat.shortDate();
    public static final DateTimeFormatter DATE_FORMATTER_DB = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMATTER_MEDIUM = DateTimeFormat.mediumDate();
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.mediumDateTime();
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("h:mm a");
    public static final DateTimeFormatter TIME_FORMATTER_DB = DateTimeFormat.forPattern("H:mm");
    public static final DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormat.forPattern("h:mm a");
    public static final PeriodFormatter PERIOD_FORMATTER = PeriodFormat.getDefault();
    public static final DateTimeFormatter DATE_TIME_FORMATTER_DB = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static Duration getDuration(LocalDateTime start, LocalDateTime end) {
        long startMillis = start.getMillisOfDay();
        long endMillis = end.getMillisOfDay();

        if (endMillis <= startMillis)
            endMillis = endMillis + DateTimeConstants.MILLIS_PER_DAY;

        return new Duration(endMillis - startMillis);
    }

    public static Duration getDuration(LocalTime first, LocalTime second) {
        long start = first.getMillisOfDay();
        long end = second.getMillisOfDay();

        if (end <= start)
            end = end + DateTimeConstants.MILLIS_PER_DAY;

        return new Duration(end - start);
    }

    /**
     * Inclusive of start but exclusive of end [start, end)
     * 
     * @param time
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetween(LocalTime time, LocalTime start, LocalTime end) {
        if (start == null)
            start = new LocalTime(4, 0);
        if (end == null)
            end = new LocalTime(4, 0);

        // Case matches starting point
        if (time.equals(start) || time.equals(end)) {
            return true;
        }
        // Case when both start and end are in same day
        else if (end.isAfter(start)) {
            return time.isAfter(start) && time.isBefore(end);
        }
        // Case when end is in following day
        else if (end.isBefore(start)) {
            return time.isAfter(start) || time.isBefore(end);
        }
        // Case when same
        else if (end.isEqual(start)) {
            return true;
        }
        return false;
    }

    public static boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
        if (start == null && end == null)
            return true;
        else if (start == null)
            return date.isEqual(end) || date.isBefore(end);
        else if (end == null)
            return date.isEqual(start) || date.isAfter(start);
        else
            return date.isEqual(start) || date.isEqual(end) || (date.isAfter(start) && date.isBefore(end));
    }

    public static boolean isBetween(DateTime dateTime, DateTime start, Duration duration) {
        return isBetween(dateTime, start, start.plus(duration));
    }

    /**
     * Inclusive of start but exclusive of end [start, end)
     * 
     * @param dateTime
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetween(DateTime dateTime, DateTime start, DateTime end) {
        if (start == null && end == null)
            return true;
        else if (start == null)
            return dateTime.isBefore(end);
        else if (end == null)
            return dateTime.isEqual(start) || dateTime.isAfter(start);
        else
            return dateTime.isEqual(start) || dateTime.equals(end)
                    || (dateTime.isAfter(start) && dateTime.isBefore(end));
    }

    /**
     * Inclusive of start but exclusive of end [start, end)
     * 
     * @param dateTime
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetween(LocalDateTime dateTime, LocalDateTime start, LocalDateTime end) {
        if (start == null && end == null)
            return true;
        else if (start == null)
            return dateTime.isBefore(end);
        else if (end == null)
            return dateTime.isEqual(start) || dateTime.isAfter(start);
        else
            return dateTime.isEqual(start) || dateTime.equals(end)
                    || (dateTime.isAfter(start) && dateTime.isBefore(end));
    }

    public static int getMinuteOfDay(LocalDateTime localDateTime) {
        return (localDateTime.getHourOfDay() * 60) + localDateTime.getMinuteOfHour();
    }
}
