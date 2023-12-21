package com.cuong02n.timekeeper_machine.model;

import com.cuong02n.timekeeper_machine.DateUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;

public class InformationWorkerModel {
    private SimpleStringProperty day;
    private SimpleDoubleProperty shift1;
    private SimpleDoubleProperty shift2;
    private SimpleDoubleProperty shift3;

    public InformationWorkerModel(String day, double shift1, double shift2, double shift3) {
        this.day = new SimpleStringProperty(day);
        this.shift1 = new SimpleDoubleProperty(shift1);
        this.shift2 = new SimpleDoubleProperty(shift2);
        this.shift3 = new SimpleDoubleProperty(shift3);
    }

    public InformationWorkerModel(Timestamp start, Timestamp end) {
        boolean workInMorning = DateUtil.isMorning(start);
        System.out.println("start = "+start);
        boolean workInAfternoon = !DateUtil.isMorning(end);
        System.out.println("end = "+end);
        long late = 0L;
        long early = 0L;
        if(workInMorning){
            late += DateUtil.morningLate(start);
            early += DateUtil.morningEarly(end);
        }
        if(workInAfternoon){
            late +=DateUtil.afternoonLate(start);
            early += DateUtil.afternoonEarly(end);
        }
        this.day = new SimpleStringProperty(String.valueOf(start.toLocalDateTime().toLocalDate()));
        this.shift1 = new SimpleDoubleProperty();
        this.shift2 = new SimpleDoubleProperty();
        this.shift3 = new SimpleDoubleProperty();
    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String day) {
        this.day = new SimpleStringProperty(day);
    }

    public double getShift1() {
        return shift1.get();
    }

    public void setShift1(double shift1) {
        this.shift1 = new SimpleDoubleProperty(shift1);
    }

    public double getShift2() {
        return shift2.get();
    }

    public void setShift2(double shift2) {
        this.shift2 = new SimpleDoubleProperty(shift2);
    }

    public double getShift3() {
        return shift3.get();
    }

    public void setShift3(double shift3) {
        this.shift3 = new SimpleDoubleProperty(shift3);
    }
}

