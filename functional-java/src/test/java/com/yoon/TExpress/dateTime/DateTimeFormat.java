package com.yoon.TExpress.dateTime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
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
