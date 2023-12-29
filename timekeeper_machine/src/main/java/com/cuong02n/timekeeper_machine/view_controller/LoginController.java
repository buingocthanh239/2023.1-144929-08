package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class LoginController {
    private IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    @FXML
    TextField passWordField;
    @FXML
    TextField idField;

    @FXML
    public void onClickLoginButton(ActionEvent actionEvent) throws Exception {
        String username = idField.getText();
        String password = passWordField.getText();
        user = idbConnector.verify(username, password);
        if (user == null) {
            // TODO:
            System.out.println("cannot find user");
            return;
        }
        ViewNavigator.gotoHomeForm();
    }
}