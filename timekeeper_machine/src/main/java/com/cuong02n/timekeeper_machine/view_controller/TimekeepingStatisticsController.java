package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;

public class TimekeepingStatisticsController {    IDBConnector idbConnector = DatabaseManager.getDBNow();

    public void onClickCsvButton(ActionEvent actionEvent) {
    }

    public void onClickExcelButton(ActionEvent actionEvent) {
    }
}
