package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import static com.cuong02n.timekeeper_machine.App.stg;

public class HomeByOfficerController {
    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    public void onClickButton(ActionEvent actionEvent) throws Exception{

        ViewNavigator.gotoTimeKeepingInformationForm();
    }
}
