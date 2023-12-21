package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class EditTimekeepingInformationController {

    public void onClickSaveButton(ActionEvent actionEvent) {
    }

    public void onClickCloseEditFormButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ShowDetailTimekeepingInformationByDayOfficeHMRForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
