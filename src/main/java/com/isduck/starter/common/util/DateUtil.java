package com.isduck.starter.common.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final ZoneId READ_TIME_ZONE = ZoneId.systemDefault();
    private static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getStringWithZone(ZonedDateTime datetime) {
        if (datetime == null) return "";
        return datetime.format(DEFAULT_FORMATTER.withZone(READ_TIME_ZONE));
    }
}
