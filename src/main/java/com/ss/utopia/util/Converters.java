package com.ss.utopia.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for doing conversions
 */
public class Converters {

    public static final String DB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Convert a date string into a {@code LocalDateTime}, using the MySQL DATETIME format
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateString the date-time string
     * @return the {@code LocalDateTime}
     */
    public static LocalDateTime dateFromString(String dateString) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(DB_DATE_TIME_FORMAT));
    }

    public static String formatDateTimeForDb(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern(DB_DATE_TIME_FORMAT).format(dateTime);
    }

    /**
     * Get a {@code ZoneId} from a city,state (Oakland, CA)
     * @param cityState the city,state
     * @return the zone or null if the city is not supported.
     */
    public static ZoneId getZoneFromCityState(String cityState) {
        Map<String, ZoneId> zones = new HashMap<>();
        ZoneId eastern = ZoneId.of("America/New_York");
        ZoneId pacific = ZoneId.of("America/Los_Angeles");
        ZoneId central = ZoneId.of("America/Chicago");
        zones.put("Oakland, CA", pacific);
        zones.put("San Francisco, CA", pacific);
        zones.put("Los Angeles, CA", pacific);
        zones.put("New York, NY", eastern);
        zones.put("Boston, MA", eastern);
        zones.put("Philadelphia, PA", eastern);
        zones.put("Dallas, TX", central);
        zones.put("Las Vegas, NV", pacific);
        zones.put("Miami, FL", eastern);
        return zones.get(cityState);
    }

    /**
     * Get the local date time from the UTC date-time
     * @param utcTime the UTC {@code LocalDateTime}
     * @param cityState the city,state
     * @return the local date-time
     * @throws IllegalArgumentException if the city,state is not supported
     */
    public static LocalDateTime getLocalTimeFromUtc(LocalDateTime utcTime, String cityState) throws IllegalArgumentException {
        ZoneId zone = getZoneFromCityState(cityState);
        if (zone == null) {
            throw new IllegalArgumentException(cityState + " not supported");
        }
        OffsetDateTime offsetTime = utcTime.atOffset(ZoneOffset.UTC);
        return offsetTime.atZoneSameInstant(zone).toLocalDateTime();
    }

    /**
     * Get the UTC date-time from the local date-time
     * @param localTime the local date-time
     * @param cityState the city,state
     * @return the UTC date-time
     * @throws IllegalArgumentException if the city,state is not supported
     */
    public static LocalDateTime getUtcTimeFromLocal(LocalDateTime localTime, String cityState) throws IllegalArgumentException {
        ZoneId zone = getZoneFromCityState(cityState);
        if (zone == null) {
            throw new IllegalArgumentException(cityState + " not supported");
        }
        ZoneOffset offset = zone.getRules().getOffset(localTime);
        OffsetDateTime offsetTime = OffsetDateTime.of(localTime, offset);
        return offsetTime.atZoneSameInstant(ZoneId.of("Etc/UTC")).toLocalDateTime();
    }
}
