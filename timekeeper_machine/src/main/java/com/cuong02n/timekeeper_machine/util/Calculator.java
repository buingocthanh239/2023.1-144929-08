package com.cuong02n.timekeeper_machine.util;

import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.InformationWorkerModel;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationOfficer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Vector;

public class Calculator {


    public static double round(double x) {
        return (double) Math.round(x * 100) / 100;
    }

}
