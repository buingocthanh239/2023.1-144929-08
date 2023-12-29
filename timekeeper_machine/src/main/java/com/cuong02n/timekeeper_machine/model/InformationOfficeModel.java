package com.cuong02n.timekeeper_machine.model;

import com.cuong02n.timekeeper_machine.util.DateUtil;

import java.sql.Timestamp;
import java.time.LocalDate;

import static com.cuong02n.timekeeper_machine.util.Calculator.round;
import static java.lang.Math.abs;

public class InformationOfficeModel {
    private LocalDate day;
    private boolean morning;
    private boolean afternoon;
    private double timeLate;
    private double timeEarly;
    public int userId;
    public String name;
    public Timestamp start;
    public Timestamp end;

    @Override
    public String toString() {
        return "Ngày " + day + " \n"
               + "Bạn đi làm từ " + start.toString() + " đến " + end.toString() + "\n"
               + "Máy chấm công nhận được là " + (morning ? "buổi sáng, " : "") + (morning ? "buổi chiều " : "") + "\n"
               + "Thời gian đi muộn là " + timeLate + "\n"
               + "Thời gian về sớm là " + timeEarly + "\n";

    }


    public InformationOfficeModel(int userId, String name, Timestamp start, Timestamp end) {
        this.end = end;
        this.start = start;
        this.userId = userId;
        this.name = name;
        boolean workInMorning = DateUtil.isMorning(start);
        boolean workInAfternoon = !DateUtil.isMorning(end);
        long late = 0L;
        long early = 0L;
        if (workInMorning) {
            late += abs(DateUtil.morningLate(start));
            early += abs(DateUtil.morningEarly(end));
        }

        if (workInAfternoon) {
            late += abs(DateUtil.afternoonLate(start));
            early += abs(DateUtil.afternoonEarly(end));
        }
        this.day = start.toLocalDateTime().toLocalDate();
        this.morning = workInMorning;
        this.afternoon = workInAfternoon;
        this.timeLate = round(late / 60.0 / 60);
        this.timeEarly = round(early / 60.0 / 60);
    }

    public InformationOfficeModel() {

    }

    public boolean getMorning() {
        return morning;
    }

    public boolean getAfternoon() {
        return afternoon;
    }

    public double getTimeLate() {
        return timeLate;
    }

    public double getTimeEarly() {
        return timeEarly;
    }

    public LocalDate getDay() {
        return day;
    }

}
