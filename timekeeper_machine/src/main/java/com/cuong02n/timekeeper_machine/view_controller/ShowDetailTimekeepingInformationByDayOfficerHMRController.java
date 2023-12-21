package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class ShowDetailTimekeepingInformationByDayOfficerHMRController {    IDBConnector idbConnector = DatabaseManager.getDBNow();

    public void onClickEditButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editTimekeepingInformationForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
