package com.ss.utopia.util;

import java.time.LocalDateTime;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormattersTest {

    @Test
    public void testFormatDateTimeForDb() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 8, 3, 10, 10, 10);
        assertThat(Formatters.formatDateTimeForDb(dateTime))
                .isEqualTo("2021-08-03 10:10:10");
    }
}
