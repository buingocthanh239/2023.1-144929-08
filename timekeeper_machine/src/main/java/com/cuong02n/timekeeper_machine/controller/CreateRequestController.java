package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Timestamp;

public class CreateRequestController {
    public TextField startHour;
    public TextField startMinute;
    public TextField endHour;
    public TextField endMinute;
    public TextField commentField;
    public Button applyButton;
    public Button closeButton;

    public TextField dayField;
    public TextField monthField;
    public TextField yearField;
    IDBConnector idbConnector;
    InformationOfficeModel data;

    void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public void onClickConfirmQuestion(ActionEvent actionEvent) {
        ViewNavigator.closeStage(actionEvent);
        try {
            String description = commentField.getText();
            String content =
                    "Đề nghị sửa ngày: "
                            + dayField.getText() + "/"
                            + monthField.getText() + "/"
                            + yearField.getText()
                            + " đi làm từ " + startHour.getText() + ":" + startMinute.getText()
                            + " đến " + endHour.getText() + ":" + endMinute.getText();

            int userId = App.user.getUserId();

            Timestamp requestTime = new Timestamp(System.currentTimeMillis());
            TimekeepingRequest timekeepingRequest = new TimekeepingRequest();
            timekeepingRequest.setUserId(userId);
            timekeepingRequest.setRequestTime(requestTime);
            timekeepingRequest.setContent(content);
            timekeepingRequest.setDescription(description);
            idbConnector.insertTimekeepingRequest(timekeepingRequest);

            PopupController.notify("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            PopupController.notify(e.getMessage());
        }
    }

    public void onClickCloseButton(ActionEvent actionEvent) {
        ViewNavigator.closeStage(actionEvent);
    }

    public void showData(InformationOfficeModel data) {
        this.data = data;
        Timestamp start = data.start;
        Timestamp end = data.end;
        startHour.setText(start.toLocalDateTime().getHour() + "");
        startMinute.setText(start.toLocalDateTime().getMinute() + "");
        endMinute.setText(end.toLocalDateTime().getMinute() + "");
        endHour.setText(end.toLocalDateTime().getHour() + "");
        dayField.setText(start.toLocalDateTime().getDayOfMonth() + "");
        monthField.setText(start.toLocalDateTime().getMonth().getValue() + "");
        yearField.setText(start.toLocalDateTime().getYear() + "");
    }
}
