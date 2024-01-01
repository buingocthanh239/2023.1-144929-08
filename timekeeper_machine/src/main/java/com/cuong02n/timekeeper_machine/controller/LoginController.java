package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

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
            PopupNotification.notify("SAI MẬT KHẨU");
            return;
        }
        ViewNavigator.gotoHomeForm();
    }
}