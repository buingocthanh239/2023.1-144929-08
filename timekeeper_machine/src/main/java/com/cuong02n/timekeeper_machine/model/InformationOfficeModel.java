package com.cuong02n.timekeeper_machine.model;

import com.cuong02n.timekeeper_machine.DateUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;

import static java.lang.Math.abs;

public class InformationOfficeModel {

    private SimpleStringProperty day;
    private SimpleStringProperty morning;
    private SimpleStringProperty afternoon;
    private SimpleDoubleProperty timeLate;
    private SimpleDoubleProperty timeEarly;
    public SimpleIntegerProperty userId;
    public SimpleStringProperty name;
    public Timestamp start;
    public Timestamp end;

    @Override
    public String toString() {
        return "Ngày "+day.get()+" \n"
                +"Bạn đi làm từ "+start.toString()+" đến "+end.toString()+"\n"
                +"Máy chấm công nhận được là " + ((Boolean.parseBoolean(morning.get()))?"buổi sáng, ":"")+((Boolean.parseBoolean(morning.get()))?"buổi chiều ":"")+"\n"
                +"Thời gian đi muộn là "+timeLate.get()+"\n"
                +"Thời gian về sớm là "+timeEarly.get()+"\n";

    }

    public InformationOfficeModel(String day, String morning, String afternoon, double timeLate, double timeEarly) {
        this.day = new SimpleStringProperty(day);
        this.morning = new SimpleStringProperty(morning);
        this.afternoon = new SimpleStringProperty(afternoon);
        this.timeLate = new SimpleDoubleProperty(timeLate);
        this.timeEarly = new SimpleDoubleProperty(timeEarly);
    }

    public InformationOfficeModel(SimpleIntegerProperty userId, SimpleStringProperty name, Timestamp start, Timestamp end) {
        this.end = end;
        this.start = start;
        this.userId = userId;
        this.name = name;
        boolean workInMorning = DateUtil.isMorning(start);
        boolean workInAfternoon = !DateUtil.isMorning(end);
        long late = 0L;
        long early = 0L;
        if (workInMorning) {
            //TODO: abstract
            late += abs(DateUtil.morningLate(start));
            early += abs(DateUtil.morningEarly(end));
        }

        if (workInAfternoon) {
            late += abs(DateUtil.afternoonLate(start));
            early += abs(DateUtil.afternoonEarly(end));
        }
        this.day = new SimpleStringProperty(String.valueOf(start.toLocalDateTime().toLocalDate()));
        this.morning = new SimpleStringProperty(String.valueOf(workInMorning));
        this.afternoon = new SimpleStringProperty(String.valueOf(workInAfternoon));
        this.timeLate = new SimpleDoubleProperty(late / 60.0 / 60);
        this.timeEarly = new SimpleDoubleProperty(early / 60.0 / 60);
    }

    public InformationOfficeModel() {

    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getMorning() {
        return morning.get();
    }

    public SimpleStringProperty morningProperty() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning.set(morning);
    }

    public String getAfternoon() {
        return afternoon.get();
    }

    public SimpleStringProperty afternoonProperty() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon.set(afternoon);
    }

    public double getTimeLate() {
        return timeLate.get();
    }

    public SimpleDoubleProperty timeLateProperty() {
        return timeLate;
    }

    public void setTimeLate(double timeLate) {
        this.timeLate.set(timeLate);
    }

    public double getTimeEarly() {
        return timeEarly.get();
    }

    public SimpleDoubleProperty timeEarlyProperty() {
        return timeEarly;
    }

    public void setTimeEarly(double timeEarly) {
        this.timeEarly.set(timeEarly);
    }
}
