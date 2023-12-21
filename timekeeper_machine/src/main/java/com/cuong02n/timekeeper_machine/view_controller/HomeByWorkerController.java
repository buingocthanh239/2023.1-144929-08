package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class HomeByWorkerController {
    public void onClickTimekeepingInformationButton(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TimekeepingInformationByWorkerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
