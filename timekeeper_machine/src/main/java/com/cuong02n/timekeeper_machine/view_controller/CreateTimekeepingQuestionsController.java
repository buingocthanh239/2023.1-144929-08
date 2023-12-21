package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.cuong02n.timekeeper_machine.App.stg;

public class CreateTimekeepingQuestionsController {
    public void onClickComfirmCreateQuestionButton(ActionEvent actionEvent) {
    }

    public void onClickcCloseCreateQuestionButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ShowDetailTimekeepingInformationByDayOfficeForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
    }
}
