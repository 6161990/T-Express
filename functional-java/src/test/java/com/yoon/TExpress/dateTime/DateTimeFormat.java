package com.yoon.TExpress.dateTime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

class DateTimeFormat {

    @Test
    void DateTimeFormatter() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter mmddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(mmddyyyy)); // 08/09/2022

        LocalDate parse = LocalDate.parse("02/21/1995", mmddyyyy);
        System.out.println(parse); // 1995-02-21
    }

    @Test
    void parseAndCompareMonthTime() {
        String stringTime = "2022-3";

        int splitIndex = stringTime.indexOf('-');

        String key = stringTime.substring(0, splitIndex);
        String value = stringTime.substring(splitIndex + 1);
        LocalDate localDate = LocalDate.of(Integer.parseInt(key), Integer.parseInt(value), 1);

        DateTimeFormatter yyyymm = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDateTime localDate1 = LocalDateTime.of(2021, 3, 1, 0, 0, 0);
        long passingByTime = localDate1.until(localDate, ChronoUnit.MONTHS); // 과거로부터 현재까지 12

        System.out.println("passingByTime : " + passingByTime);

        System.out.println(localDate.format(yyyymm));
    }

    @Test
    void 레거시_API() {
        Date date = new Date();
        Instant instant = date.toInstant();
        Date newDate = Date.from(instant);
        System.out.println(newDate); // Tue Aug 09 07:51:58 KST 2022

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        System.out.println(dateTime); // 2022-08-09T07:51:58.538+09:00[Asia/Seoul]

        GregorianCalendar from = GregorianCalendar.from(dateTime);
        System.out.println(from.getTime()); // Tue Aug 09 07:54:47 KST 2022

    }
}
