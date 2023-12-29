package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;

public class TimekeepingStatisticsController {
    IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector = idbConnector;
    }
    public void onClickCsvButton(ActionEvent actionEvent) {
        PopupNotification.notify("Bọn em chuẩn bị làm chức năng này");
    }

    public void onClickExcelButton(ActionEvent actionEvent) {
        PopupNotification.notify("Bọn em chuẩn bị làm chức năng này");
    }
}
