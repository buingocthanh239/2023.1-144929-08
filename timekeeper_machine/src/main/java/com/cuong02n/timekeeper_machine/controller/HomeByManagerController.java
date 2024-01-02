package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;

public class HomeByManagerController {
    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    public void onClickTimekeepingInformationButton(ActionEvent actionEvent) throws Exception {
        ViewNavigator.gotoTimeKeepingInformationForm();
    }

    public void onClickRoomInformationButton(ActionEvent actionEvent) throws Exception {
        ViewNavigator.gotoRoomInformationForm(App.user.getRoomId());
    }
}
