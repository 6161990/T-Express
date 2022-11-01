package com.yoon.TExpress.dateTime;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeTest {

    @Test
    void 기계의_시간() {
        Instant instant = Instant.now(); // 2022-08-08T22:25:53.484032Z
        ZonedDateTime utc = instant.atZone(ZoneId.of("UTC")); // 2022-08-08T22:25:53.484032Z[UTC]

        assertEquals(instant, utc.toInstant());
    }

    @Test
    void 기계의_시간_내_서버가_속해있는_zone() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = instant.atZone(zoneId); // 2022-08-09T07:28:49.693198+09:00[Asia/Seoul]
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1); // 2022-08-09T07:33:07.670429+09:00[Asia/Seoul]


        assertEquals(zoneId.getId(), "Asia/Seoul");
        assertEquals(zonedDateTime.getZone().getId(), "Asia/Seoul");
    }

    @Test
    void 특정_zone_시간을_보고_싶을_때_두_가지_방법() {
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println("Los_Angeles is " + zonedDateTime1);

        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("America/Los_Angeles"));
        System.out.println("Los_Angeles is " + zonedDateTime);
    }

    @Test
    void 인간의_시간() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birthDay = LocalDateTime.of(1995, 2, 21, 0, 0, 0);
    }


}
