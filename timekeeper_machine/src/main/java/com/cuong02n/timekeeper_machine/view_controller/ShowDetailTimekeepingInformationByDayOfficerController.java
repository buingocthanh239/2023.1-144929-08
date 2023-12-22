package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.stg;

public class ShowDetailTimekeepingInformationByDayOfficerController{


    @FXML
    public Label detailsInformationLabel;
    Stage stage;
    IDBConnector idbConnector;

    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        stage.hide();
    }

    public void onClickComplainButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("createTimekeepingQuestionsForm.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        var controller = fxmlLoader.<CreateTimekeepingQuestionsController>getController();
        controller.setStage(stage);
        controller.setDBConnector(idbConnector);
    }
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector = idbConnector;
    }

    public void showData(InformationOfficeModel data){
        detailsInformationLabel.setText(data.toString());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
