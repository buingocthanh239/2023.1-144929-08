package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class ShowDetailTimekeepingInformationByDayOfficerController {
    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TimekeepingInformationByOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickComplainButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CreateTimekeepingQuestionsForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
