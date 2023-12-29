package com.cuong02n.timekeeper_machine.model;

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
        // TODO:
    }

}
