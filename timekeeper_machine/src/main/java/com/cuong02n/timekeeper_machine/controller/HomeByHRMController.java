package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class HomeByHRMController implements Initializable {

    public Button timekeepingInformationButton;
    public Button timekeepingQuestionsButton;
    public Button companyTimekeepingInformationButton;
    public Button timekeepingStatisticsButton;
    public Label roleLabel;
    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    @FXML
    public Label fullNameLabel;
    @FXML
    public Label roomLabel;
    @FXML
    public Label idLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomLabel.setText(String.valueOf(user.getRoomId()));
        idLabel.setText(String.valueOf(user.getUserId()));

        fullNameLabel.setText(user.getFullName());
        roleLabel.setText("QUẢN TRỊ VIÊN");
    }
    public void onClickTimekeepingStatisticsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingStatisticsForm3.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }

    public void onClickCompanyTimekeepingInformationButton(ActionEvent actionEvent) throws Exception {
        ViewNavigator.gotoCompanyTimeKeepingOfficer();
    }

    public void onClickTimekeepingQuestionsButton(ActionEvent actionEvent) throws Exception {
        ViewNavigator.gotoTimeKeepingQuestionForm();
    }
    public void onClickTimekeepingInformationButton(ActionEvent actionEvent) throws Exception {
        ViewNavigator.gotoTimeKeepingInformationForm();
    }


}
