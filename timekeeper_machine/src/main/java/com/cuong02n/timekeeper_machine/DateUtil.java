package com.cuong02n.timekeeper_machine;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

public class DateUtil {
    public static LocalTime startMorning = LocalTime.of(8, 0);
    public static LocalTime endMorning = LocalTime.of(12,0);
    public static LocalTime startAfternoon = LocalTime.of(13,0);
    public static LocalTime endAfternoon = LocalTime.of(17,0);
    public static boolean isMorning(Timestamp timestamp){
        LocalTime time = timestamp.toLocalDateTime().toLocalTime();
        return time.isBefore(LocalTime.of(12,30));
    }

    public static long morningLate(Timestamp start){
        if(start.toLocalDateTime().toLocalTime().isAfter(startMorning)){
            return Duration.between(start.toLocalDateTime().toLocalTime(),startMorning).getSeconds();
        }
        return 0;
    }
    public static long afternoonLate(Timestamp start){
        if(start.toLocalDateTime().toLocalTime().isAfter(startAfternoon)){
            return Duration.between(start.toLocalDateTime().toLocalTime(),endMorning).getSeconds();
        }
        return 0;
    }
    public static long morningEarly(Timestamp end){
        if(end.toLocalDateTime().toLocalTime().isBefore(endMorning)){
            return Duration.between(end.toLocalDateTime().toLocalTime(),endMorning).getSeconds();
        }
        return 0;
    }
    public static long afternoonEarly(Timestamp end){
        if(end.toLocalDateTime().toLocalTime().isBefore(endAfternoon)){
            return Duration.between(end.toLocalDateTime().toLocalTime(),endAfternoon).getSeconds();
        }
        return 0;
    }

}
