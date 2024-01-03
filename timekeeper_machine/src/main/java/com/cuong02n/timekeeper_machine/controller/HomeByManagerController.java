package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.user;

@SuppressWarnings("all")
public class HomeByManagerController implements Initializable {
    public Label fullNameLabel;
    public Label roleLabel;
    public Label roomIdLabel;
    public Label userIdLabel;
    private IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public void onClickTimekeepingInformationButton(ActionEvent ignoreActionEvent) throws Exception {
        ViewNavigator.gotoTimeKeepingInformationForm();
    }

    public void onClickRoomInformationButton(ActionEvent ignoreActionEvent) throws Exception {
        ViewNavigator.gotoRoomInformationForm(App.user.getRoomId());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullNameLabel.setText(user.getFullName());
        roomIdLabel.setText(String.valueOf(user.getRoomId()));
        userIdLabel.setText(String.valueOf(user.getUserId()));
        roleLabel.setText("TRƯỞNG PHÒNG");

    }
}
