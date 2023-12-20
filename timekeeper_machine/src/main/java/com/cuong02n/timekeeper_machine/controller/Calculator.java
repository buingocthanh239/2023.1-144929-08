package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationOfficer;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationWorker;

import java.util.Vector;

public class Calculator {
    static SummarizeInformationOfficer getSummarizeInformationOfficer(Vector<InformationOfficeModel> actions) {
        SummarizeInformationOfficer data = new SummarizeInformationOfficer();
        for (InformationOfficeModel x : actions) {
            data.early += x.getTimeEarly();
            data.late += x.getTimeLate();
            if (Boolean.parseBoolean(x.getAfternoon())) data.workingSession++;
            if (Boolean.parseBoolean(x.getMorning())) data.workingSession++;
        }
        return data;
    }

}
