package com.yoon.TExpress.dateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

class DurationAndPeriod {

    @Test
    void 휴먼용기간_Period() {
        LocalDate today = LocalDate.now();
        LocalDate myBirthday = LocalDate.of(2024, Month.JANUARY, 21);

        Period period = Period.between(today, myBirthday);

        Period until = today.until(myBirthday);

        Assertions.assertEquals(period.getDays(),  until.get(ChronoUnit.DAYS));
        Assertions.assertEquals(period.get(ChronoUnit.DAYS),  until.get(ChronoUnit.DAYS));

    }

    @Test
    void 기계용기간_Duration() {
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println(between); // PT10S
        System.out.println(between.getSeconds()); // 10
    }
}
