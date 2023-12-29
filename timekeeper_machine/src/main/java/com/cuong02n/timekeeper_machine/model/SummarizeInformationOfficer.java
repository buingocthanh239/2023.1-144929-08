package com.cuong02n.timekeeper_machine.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SummarizeInformationOfficer {
    public int workingSession;
    public double early;
    public double late;
    public int userId;
    public String name;
    public double earlyAndLate;

    public SummarizeInformationOfficer(int workingSession, double early, double late, int userId, String name, double earlyAndLate) {
        this.workingSession = workingSession;
        this.early = early;
        this.late = late;
        this.userId = userId;
        this.name = name;
        this.earlyAndLate = earlyAndLate;
    }

    public SummarizeInformationOfficer() {
        this(0,0,0,0,"",0);
    }

    @Override
    public String toString() {
        return workingSession + " " + early + " " + late + " " + userId + " " + name;
    }

    public int getWorkingSession() {
        return workingSession;
    }

    public void setWorkingSession(int workingSession) {
        this.workingSession = workingSession;
    }

    public double getEarly() {
        return early;
    }

    public void setEarly(double early) {
        this.early = early;
    }

    public double getLate() {
        return late;
    }

    public void setLate(double late) {
        this.late = late;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEarlyAndLate() {
        return earlyAndLate;
    }

    public void setEarlyAndLate(double earlyAndLate) {
        this.earlyAndLate = earlyAndLate;
    }
}
