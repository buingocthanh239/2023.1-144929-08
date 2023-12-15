package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import static com.cuong02n.timekeeper_machine.HelloApplication.stg;

public class LoginFormController {
    @FXML
    private Label welcomeText;
    @FXML
    public void onClickLoginButton(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homeForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

}