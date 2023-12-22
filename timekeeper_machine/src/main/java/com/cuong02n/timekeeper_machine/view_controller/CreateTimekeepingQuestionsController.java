package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.HikariConnector;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;

import static com.cuong02n.timekeeper_machine.App.stg;

public class CreateTimekeepingQuestionsController {
    IDBConnector idbConnector ;
    Stage popUpParent;
    @FXML
    public Button comfirmCreateQuestionButton;
    @FXML
    public TextField questionContentField;

    void setDBConnector(IDBConnector idbConnector){
        this.idbConnector = idbConnector;
    }
    public void onClickConfirmCreateQuestionButton(ActionEvent actionEvent) throws Exception{
        String content = questionContentField.getText();
        int userId = App.user.getUserId();
        Timestamp requestTime = new Timestamp(System.currentTimeMillis());
        TimekeepingRequest timekeepingRequest = new TimekeepingRequest();
        timekeepingRequest.setUserId(userId);
        timekeepingRequest.setRequestTime(requestTime);
        timekeepingRequest.setContent(content);
        idbConnector.insertTimekeepingRequest(timekeepingRequest);
    }

    public void onClickCloseCreateQuestionButton(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailTimekeepingInformationByDayOfficeForm.fxml"));
//        pop.setScene(new Scene(fxmlLoader.load()));
        popUpParent.hide();
    }
    public void setStage(Stage stage){
        popUpParent = stage;
    }
}
