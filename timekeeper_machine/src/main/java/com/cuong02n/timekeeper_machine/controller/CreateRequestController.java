package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;


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

    public static void main(String[] args) {
        System.out.println(LocalDate.parse(
                "31/02/2000",                 // Input string.
                DateTimeFormatter              // Define a formatting pattern to match your input string.
                        .ofPattern("dd/MM/uuuu")
                        .withResolverStyle(ResolverStyle.STRICT)  // Specify leniency in tolerating questionable inputs.
        ));
    }

    void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public void onClickConfirmQuestion(ActionEvent actionEvent) {
        try {
            int day = Integer.parseInt(dayField.getText());
            int month = Integer.parseInt(monthField.getText());
            int year = Integer.parseInt(yearField.getText());

            int hourStart = Integer.parseInt(startHour.getText());
            int minuteStart = Integer.parseInt(startMinute.getText());

            int hourEnd = Integer.parseInt(endHour.getText());
            int minuteEnd = Integer.parseInt(endMinute.getText());

            LocalDate date = LocalDate.parse(
                    (day < 10 ? ("0" + day) : day) + "/"
                            + ((month < 10) ? ("0" + month) : month) + "/" + year,
                    DateTimeFormatter
                            .ofPattern("dd/MM/yyyy")
//                            .withResolverStyle(ResolverStyle.STRICT)
            );
            LocalDateTime start = date.atTime(hourStart, minuteStart);
            LocalDateTime end = date.atTime(hourEnd, minuteEnd);
            LocalDateTime now = LocalDateTime.now();
//            assert start.isBefore(end):"Chú ý trước sau";
//            assert (end.isBefore(now)):"Chú ý trước sau";
            // cannot turn on ea
            if (end.isBefore(start) || now.isBefore(end)) {
                throw new Exception("Lỗi");
            }

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

            ViewNavigator.closeStage(actionEvent);
            PopupController.notify("Thành công");
        } catch (Exception e) {
            e.printStackTrace();
            PopupController.notify("Lỗi dữ liệu, hãy nhập lại");
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
