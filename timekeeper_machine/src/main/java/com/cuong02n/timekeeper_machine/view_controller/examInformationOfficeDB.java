package com.cuong02n.timekeeper_machine.view_controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class examInformationOfficeDB {
    private SimpleStringProperty day;
    private SimpleStringProperty morning;
    private SimpleStringProperty afternoon;
    private SimpleDoubleProperty timeLate;
    private SimpleDoubleProperty timeEarly;

    public examInformationOfficeDB(String day, String morning, String afternoon, double timeLate, double timeEarly) {
        this.day = new SimpleStringProperty(day);
        this.morning = new SimpleStringProperty(morning);
        this.afternoon = new SimpleStringProperty(afternoon);
        this.timeLate = new SimpleDoubleProperty(timeLate);
        this.timeEarly = new SimpleDoubleProperty(timeEarly);
    }

    public examInformationOfficeDB() {

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
