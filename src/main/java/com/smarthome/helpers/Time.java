package com.smarthome.helpers;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class Time {

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    LocalTime now;

    public Time() {
        now = LocalTime.now();
    }

    public void setTime(String time) {
        now = LocalTime.parse(time);
    }


    public static Period getPeriod(LocalDateTime start, LocalDateTime now) {
        return Period.between(start.toLocalDate(), now.toLocalDate());
    }

    public long[] getDuration(LocalDateTime start, LocalDateTime now) {

        Duration duration = Duration.between(start, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }

    public boolean isLeftTheRoom(LocalDateTime start) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(start, now);
        System.out.println("There is no one in the room for " +duration.getSeconds()+ " seconds.");
        return duration.getSeconds() == 5;
    }

    public boolean isGivenTime(String time) {
        LocalTime now = LocalTime.now();
        return Duration.between(LocalTime.parse(time), now).getSeconds() == 0;
    }

    public boolean isGivenTimeIsBetween(String time1, String time2) {
        LocalTime now = LocalTime.now();
        return now.isAfter(LocalTime.parse(time1)) && now.isBefore(LocalTime.parse(time2));
    }
}
