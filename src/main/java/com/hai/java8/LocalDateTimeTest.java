package com.hai.java8;

import org.junit.Test;

import java.time.*;

/**
 * Created by Administrator on 2017/9/8.
 */
public class LocalDateTimeTest {
    final Clock clock = Clock.systemUTC();

    @Test
    public void test1() {
        System.out.println(clock.instant());
        System.out.println(clock.millis());
    }

    @Test
    public void test2() {
        LocalDate date = LocalDate.now();
        LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dateFromClock);
        System.out.println();

        LocalTime time = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);
        System.out.println();

        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTimeFromClock = LocalDateTime.now(clock);
        System.out.println(dateTime);
        System.out.println(dateTimeFromClock);
    }

    @Test
    public void test3() {
        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);
    }

    @Test
    //Duration op
    public void test4() {
        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }

}
