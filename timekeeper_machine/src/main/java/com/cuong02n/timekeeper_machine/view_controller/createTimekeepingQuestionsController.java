package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class createTimekeepingQuestionsController {
    @FXML
    public Button comfirmCreateQuestionButton;
    @FXML
    public TextField questionContentField;

    public void onClickConfirmCreateQuestionButton(ActionEvent actionEvent) {
        String content = questionContentField.getText();
    }

    public void onClickCloseCreateQuestionButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailTimekeepingInformationByDayOfficeForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
