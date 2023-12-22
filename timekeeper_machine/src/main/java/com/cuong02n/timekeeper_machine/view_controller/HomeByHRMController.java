package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class HomeByHRMController {

    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    @FXML
    public Button timekeepingInformationButton;
    @FXML
    public Label fullNameLabel;
    @FXML
    public Label roomLabel;
    @FXML
    public Label idLabel;

    @FXML
    public void initialize(){
        fullNameLabel.setText(user.getFullName());
        roomLabel.setText(String.valueOf(user.getRoomId()));
        idLabel.setText(String.valueOf(user.getUserId()));

    }
    public void onClickTimekeepingStatisticsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingStatisticsForm3.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickCompanyTimekeepingInformationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("companyTimekeepingInformationUnitIsOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickTimekeepingQuestionsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingQuestionsForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
    public void onClickTimekeepingInformationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingInformationByOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
