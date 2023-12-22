package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class HomeByWorkerController {
    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    public void onClickTimekeepingInformationButton(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingInformationByWorkerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
