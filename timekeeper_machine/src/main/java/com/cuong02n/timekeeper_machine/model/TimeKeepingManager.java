package com.cuong02n.timekeeper_machine.model;

import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;

import java.sql.Timestamp;
import java.util.Vector;

public class TimeKeepingManager {
    private static TimeKeepingManager ins;

    public static TimeKeepingManager getInstance() {
        if (ins == null) {
            ins = new TimeKeepingManager(DatabaseManager.hikariConnector);
        }
        return ins;
    }

    IDBConnector idbConnector;

    TimeKeepingManager(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public SummarizeInformationOfficer getSummarizeOfficerById(int userId, Timestamp start, Timestamp end) throws Exception {
        var data = idbConnector.getActionByTimeStampAndUserId(start, end, userId);
        return calculateSummarizeByAction(transformActionToOfficeModel(data));
    }

    public Vector<SummarizeInformationOfficer> getSummarizeOfficeByListId(Vector<Integer> ids, Timestamp start, Timestamp end) throws Exception{
        Vector<SummarizeInformationOfficer> data= new Vector<>();
        for(int id : ids){
            data.add(getSummarizeOfficerById(id,start,end));
        }
        return data;
    }
    public  SummarizeInformationOfficer calculateSummarizeByAction(Vector<InformationOfficeModel> actions) {
        SummarizeInformationOfficer data = new SummarizeInformationOfficer();
        for (InformationOfficeModel x : actions) {
            data.userId = x.userId;
            data.name = x.name;
            data.setEarly(data.getEarly() + x.getTimeEarly());
            data.setLate(data.getLate() + x.getTimeLate());
            data.setEarlyAndLate(data.getLate()+ data.getEarly());
            if (x.getAfternoon()) data.setWorkingSession(data.getWorkingSession() + 1);
            if (x.getMorning()) data.setWorkingSession(data.getWorkingSession() + 1);
        }
        return data;
    }

    public  Vector<InformationOfficeModel> transformActionToOfficeModel(Vector<Action> actions) {
        Vector<InformationOfficeModel> informationOfficeModels = new Vector<>();
        for (int i = 0, indexRoot = 0; i < actions.size(); i++) {
            while (i < actions.size() && (actions.get(i).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear() ==
                                          actions.get(indexRoot).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear())) {
                i++;
            }
            informationOfficeModels.add(new InformationOfficeModel(actions.get(i - 1).getUserId(), actions.get(i - 1).getName(), actions.get(indexRoot).getActionTime(), actions.get(i - 1).getActionTime()));
            indexRoot = i;
        }
        return informationOfficeModels;
    }


}
