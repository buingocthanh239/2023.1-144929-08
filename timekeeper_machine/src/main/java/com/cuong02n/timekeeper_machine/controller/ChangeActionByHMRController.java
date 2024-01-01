package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Stack;

import static com.cuong02n.timekeeper_machine.App.stg;

public class ChangeActionByHMRController {
    public TextField startHour;
    public TextField startMinute;
    public TextField endHour;
    public TextField endMinute;
    public Button applyButton;
    public Button closeButton;
    public Label dayLabel;
    private IDBConnector idbConnector;
    public InformationOfficeModel data;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public void onClickCloseButton(ActionEvent actionEvent) throws IOException {
        Node source = (Node)actionEvent.getSource();
        Stage stage =(Stage) source.getScene().getWindow();
        stage.close();
    }

    public void showData(InformationOfficeModel rowData) {
        data = rowData;
        Timestamp start = rowData.start;
        Timestamp end = rowData.end;
        startHour.setText(start.toLocalDateTime().getHour() + "");
        endHour.setText(end.toLocalDateTime().getHour() + "");
        startMinute.setText(start.toLocalDateTime().getMinute() + "");
        endMinute.setText(end.toLocalDateTime().getMinute() + "");

        dayLabel.setText(start.toLocalDateTime().toLocalDate().toString());
    }

    public void onApplyEditButton(ActionEvent actionEvent) throws Exception{
        try {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            LocalDate day = data.getDay();
            Timestamp start = Timestamp.valueOf(day.atTime(Integer.parseInt(startHour.getText()), Integer.parseInt(startMinute.getText())));
            Timestamp end = Timestamp.valueOf(day.atTime(Integer.parseInt(endHour.getText()), Integer.parseInt(endMinute.getText())));
            idbConnector.deleteAllActionByDayAndUserId(day, data.userId);
            idbConnector.insertAction(start, data.userId);
            idbConnector.insertAction(end, data.userId);
            ViewNavigator.gotoTimeKeepingInformationForm();
            PopupNotification.notify("Thành công");
        }catch (Exception e){
            PopupNotification.notify(e.getMessage());
            e.printStackTrace();
        }
    }
}
