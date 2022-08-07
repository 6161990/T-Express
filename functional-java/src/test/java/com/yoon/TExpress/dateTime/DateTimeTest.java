package com.yoon.TExpress.dateTime;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeTest {

    /** 기존_자바_에서_제공하던_Date의_문제점 */

    @Test
    void date가_time을_제공한다() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        System.out.println(date.getTime()); // 1659912829795 기계의 시간
        System.out.println(calendar.getTime()); // Mon Aug 08 07:53:49 KST 2022 - 인류의 시간
    }

    /** thread safe 하지 않다 */
    @Test
    void time_set_할_수_있다() throws InterruptedException {
        Thread.sleep(1000*3);
        Date date = new Date();
        long time = date.getTime();
        Date after3Seconds = new Date();
        System.out.println(after3Seconds); // Mon Aug 08 07:57:14 KST 2022
        after3Seconds.setTime(time);

        assertEquals(after3Seconds, after3Seconds);
    }

    @Test
    void bug_위험성이_높다() {
        Calendar calendar = new GregorianCalendar(2021, 8,14);
        System.out.println(calendar.getTime()); // Tue Sep 14 00:00:00 KST 2021

        Calendar calendar2 = new GregorianCalendar(2021, Calendar.SEPTEMBER,14);
        System.out.println(calendar2.getTime()); // Tue Sep 14 00:00:00 KST 2021

        Calendar calendar3 = new GregorianCalendar(2021, 0,14);
        System.out.println(calendar3.getTime()); // Thu Jan 14 00:00:00 KST 2021

        /** month 가 0 부터 시작한다. */

    }
}
