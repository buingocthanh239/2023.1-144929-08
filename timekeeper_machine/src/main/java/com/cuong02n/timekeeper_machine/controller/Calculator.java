package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.model.*;

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

    public static Vector<InformationOfficeModel> transformDataToDisplayOfficer(Vector<Action> actions) {
        Vector<InformationOfficeModel> informationOfficeModels = new Vector<>();
        for (int i = 0, indexRoot = 0; i < actions.size(); i++) {
            while (i < actions.size() && (actions.get(i).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear() ==
                                          actions.get(indexRoot).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear())) {
                i++;
            }
            informationOfficeModels.add(new InformationOfficeModel(actions.get(indexRoot).getActionTime(), actions.get(i - 1).getActionTime()));
            indexRoot = i;
        }
        return informationOfficeModels;
    }

    public static Vector<InformationWorkerModel> transformDataToDisplayWorker(Vector<Action> actions){
        Vector<InformationWorkerModel> informationWorkerModels = new Vector<>();
        for (int i = 0, indexRoot = 0; i < actions.size(); i++) {
            while (i < actions.size() && (actions.get(i).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear() ==
                                          actions.get(indexRoot).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear())) {
                i++;
            }
            informationWorkerModels.add(new InformationWorkerModel(actions.get(indexRoot).getActionTime(), actions.get(i - 1).getActionTime()));
            indexRoot = i;
        }
        return informationWorkerModels;
    }

}
