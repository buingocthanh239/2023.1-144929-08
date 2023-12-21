package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class ShowDetailTimekeepingInformationByDayOfficerHMRController {
    public void onClickEditButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditTimekeepingInformationForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ShowDetailOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
