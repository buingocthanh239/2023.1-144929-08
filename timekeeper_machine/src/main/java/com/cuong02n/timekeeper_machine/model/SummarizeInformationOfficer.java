package com.cuong02n.timekeeper_machine.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SummarizeInformationOfficer {
    public SimpleIntegerProperty workingSession;
    public SimpleDoubleProperty early;
    public SimpleDoubleProperty late;
    public SimpleIntegerProperty userId;
    public SimpleStringProperty name;
    public SimpleDoubleProperty earlyAndLate;

    public SummarizeInformationOfficer(int workingSession, double early, double late, int userId, String name, double earlyAndLate) {
        this.workingSession = new SimpleIntegerProperty(workingSession);
        this.early = new SimpleDoubleProperty(early);
        this.late = new SimpleDoubleProperty(late);
        this.userId = new SimpleIntegerProperty(userId);
        this.name = new SimpleStringProperty(name);
        this.earlyAndLate = new SimpleDoubleProperty(earlyAndLate);
    }

    public SummarizeInformationOfficer() {

    }

    @Override
    public String toString() {
        return workingSession + " " + early + " " + late + " " + userId + " " + name;
    }

    public int getWorkingSession() {
        return workingSession.get();
    }

    public SimpleIntegerProperty workingSessionProperty() {
        return workingSession;
    }

    public void setWorkingSession(int workingSession) {
        this.workingSession.set(workingSession);
    }

    public double getEarly() {
        return early.get();
    }

    public SimpleDoubleProperty earlyProperty() {
        return early;
    }

    public void setEarly(double early) {
        this.early.set(early);
    }

    public double getLate() {
        return late.get();
    }

    public SimpleDoubleProperty lateProperty() {
        return late;
    }

    public void setLate(double late) {
        this.late.set(late);
    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getEarlyAndLate() {
        return earlyAndLate.get();
    }

    public SimpleDoubleProperty earlyAndLateProperty() {
        return earlyAndLate;
    }

    public void setEarlyAndLate(double earlyAndLate) {
        this.earlyAndLate.set(earlyAndLate);
    }
}
