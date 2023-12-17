package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import static com.cuong02n.timekeeper_machine.App.stg;

public class LoginFormController {
    @FXML
    public void onClickLoginButton(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("homeByHRMForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

}