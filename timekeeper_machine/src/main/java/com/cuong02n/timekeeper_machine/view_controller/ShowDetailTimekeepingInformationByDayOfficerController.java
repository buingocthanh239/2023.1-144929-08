package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class ShowDetailTimekeepingInformationByDayOfficerController {
    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingInformationByOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickComplainButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("createTimekeepingQuestionsForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
