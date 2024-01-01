package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.user;

public class HomeByOfficerController implements Initializable {
    public Label fullNameLabel;
    public Label roomLabel;
    public Label idLabel;
    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullNameLabel.setText(user.getFullName());
        roomLabel.setText(String.valueOf(user.getRoomId()));
        idLabel.setText(String.valueOf(user.getUserId()));
    }
    public void onClickButton(ActionEvent actionEvent) throws Exception{
        ViewNavigator.gotoTimeKeepingInformationForm();
    }
}
