package com.ss.utopia.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ConvertersTest {

    @Test
    public void testDateFromString() {
         final String dateString = "2021-08-03 10:20:30";
         LocalDateTime dateTime = Converters.dateFromString(dateString);

         assertThat(dateTime.getYear()).isEqualTo(2021);
         assertThat(dateTime.getMonth().getValue()).isEqualTo(8);
         assertThat(dateTime.getMonth()).isEqualTo(Month.AUGUST);
         assertThat(dateTime.getDayOfMonth()).isEqualTo(3);
         assertThat(dateTime.getHour()).isEqualTo(10);
         assertThat(dateTime.getMinute()).isEqualTo(20);
         assertThat(dateTime.getSecond()).isEqualTo(30);
    }

    @Test
    public void testDateFromStringWrongFormat() {
        final String dateString = "08-03-2021 10:20:30";
        assertThatExceptionOfType(DateTimeParseException.class)
                .isThrownBy(() -> Converters.dateFromString(dateString));
    }

    @Test
    public void testFormatDateTimeForDb() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 8, 3, 10, 10, 10);
        assertThat(Converters.formatDateTimeForDb(dateTime))
                .isEqualTo("2021-08-03 10:10:10");
    }

    @Test
    public void testGetZoneFromCityState() {
        ZoneId pacificZone = Converters.getZoneFromCityState("Oakland, CA");
        assertThat(pacificZone).isNotNull();
        assertThat(pacificZone.getId()).isEqualTo("America/Los_Angeles");

        ZoneId easternZone = Converters.getZoneFromCityState("Boston, MA");
        assertThat(easternZone).isNotNull();
        assertThat(easternZone.getId()).isEqualTo("America/New_York");

        ZoneId notSupported = Converters.getZoneFromCityState("San Diego, CA");
        assertThat(notSupported).isNull();
    }

    @Test
    public void testGetLocalTimeFromUtc() {
        LocalDateTime utcNow = LocalDateTime.now();
        LocalDateTime pacificTime = Converters.getLocalTimeFromUtc(utcNow, "Oakland, CA");
        assertThat(utcNow.minusHours(7)).isEqualTo(pacificTime);
    }

    @Test
    public void testGetLocalTimeFromUtcNotSupportedCity() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Converters.getLocalTimeFromUtc(LocalDateTime.now(), "San Diego, CA"));
    }

    @Test
    public void testGetUtcTimeFromLocal() {
        LocalDateTime localTime = LocalDateTime.now();
        LocalDateTime utcTime = Converters.getUtcTimeFromLocal(localTime, "Oakland, CA");
        assertThat(localTime.plusHours(7)).isEqualTo(utcTime);
    }

    @Test
    public void testGetUtcTimeFromLocalNotSupportedCity() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Converters.getUtcTimeFromLocal(LocalDateTime.now(), "San Diego, CA"));
    }
}
