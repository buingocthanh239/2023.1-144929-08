package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.user;

public class ShowDetailByDayController implements Initializable {


    @FXML
    public Label detailsInformationLabel;
    public Button continueButton;
    IDBConnector idbConnector;
    InformationOfficeModel data;


    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        stage.close();
    }

    public void onClickComplainButton(ActionEvent actionEvent) throws IOException {
        ViewNavigator.closeStage(actionEvent);
        try {
            if (user.getRole() != User.ADMIN_ROLE) {
                ViewNavigator.gotoCreateRequest(data);
            } else {
                ViewNavigator.gotoChangeRequest(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public void showData(InformationOfficeModel data) {
        this.data = data;
        detailsInformationLabel.setText(data.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (user.getRole() == User.ADMIN_ROLE) {
            continueButton.setText("Chỉnh sửa");
        }
    }
}
