package com.cuong02n.timekeeper_machine.util;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TimeUtil {
    public static LocalTime startMorning = LocalTime.of(8, 0);
    public static LocalTime endMorning = LocalTime.of(12, 0);
    public static LocalTime startAfternoon = LocalTime.of(13, 0);
    public static LocalTime endAfternoon = LocalTime.of(17, 0);


    public static LocalTime shift1Start = LocalTime.of(6,0);
    public static LocalTime shift2Start = LocalTime.of(10,0);
    public static LocalTime shift3Start = LocalTime.of(14,0);
    public static LocalTime shift3End = LocalTime.of(18,0);

    public static long getShift1Work(Timestamp start,Timestamp end){
        return 0L;
    }
    public static boolean isMorning(Timestamp timestamp) {
        LocalTime time = timestamp.toLocalDateTime().toLocalTime();
        return time.isBefore(LocalTime.of(12, 30));
    }

    public static long morningLate(Timestamp start) {
        if (start.toLocalDateTime().toLocalTime().isAfter(startMorning)) {
            return Duration.between(start.toLocalDateTime().toLocalTime(), startMorning).getSeconds();
        }
        return 0;
    }

    public static long afternoonLate(Timestamp start) {
        if (start.toLocalDateTime().toLocalTime().isAfter(startAfternoon)) {
            return Duration.between(start.toLocalDateTime().toLocalTime(), endMorning).getSeconds();
        }
        return 0;
    }

    public static long morningEarly(Timestamp end) {
        if (end.toLocalDateTime().toLocalTime().isBefore(endMorning)) {
            return Duration.between(end.toLocalDateTime().toLocalTime(), endMorning).getSeconds();
        }
        return 0;
    }

    public static long afternoonEarly(Timestamp end) {
        if (end.toLocalDateTime().toLocalTime().isBefore(endAfternoon)) {
            return Duration.between(end.toLocalDateTime().toLocalTime(), endAfternoon).getSeconds();
        }
        return 0;
    }

    public static List<Integer> getListMonthToNow(){
        return null;
    }

    public static Timestamp getNow(){
        return new Timestamp(System.currentTimeMillis());
    }
    public static Timestamp getStartTimeThisDay(){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }
    public static Timestamp getStartTimeOfDay(int year,int month,int day){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay().withYear(year).withMonth(month).withDayOfMonth(day));
    }
    public static Timestamp getStartTimeOfDay(Timestamp timestamp){
        return Timestamp.valueOf(timestamp.toLocalDateTime().toLocalDate().atStartOfDay());
    }
    public static Timestamp getStartTimeThisMonth(){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay().withDayOfMonth(1));
    }
    public static Timestamp getStartTimeOfMonth(int year,int month){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay().withYear(year).withMonth(month).withDayOfMonth(1));
    }
    public static Timestamp getStartTimeOfNextMonth(Timestamp timestamp){
        if(timestamp.getTime()==0) return getNow();
        return Timestamp.valueOf(timestamp.toLocalDateTime().withDayOfMonth(1).plusMonths(1));
    }
    public static Timestamp getStartTimeOfMonthLastYear(int month){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay().minusYears(1).withMonth(month).withDayOfMonth(1));
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().getMonth().getValue());
    }
}
