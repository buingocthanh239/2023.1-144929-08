package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.Room;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationOfficer;
import com.cuong02n.timekeeper_machine.model.TimeKeepingManager;
import com.cuong02n.timekeeper_machine.model.User;
import com.cuong02n.timekeeper_machine.util.Helper;
import com.cuong02n.timekeeper_machine.util.TimeUtil;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.user;

@SuppressWarnings("all")
public class RoomOfficerController implements Initializable {
    public TableColumn<SummarizeInformationOfficer, Integer> userIdCol;
    public TableColumn<SummarizeInformationOfficer, String> fullNameCol;
    public TableColumn<SummarizeInformationOfficer, Integer> numberWork;
    public TableColumn<SummarizeInformationOfficer, Double> lateEarlyCol;

    public TableColumn<SummarizeInformationOfficer, Void> showDetailCol;
    public TableView<SummarizeInformationOfficer> companyOfficerTableView;
    public ChoiceBox<String> monthChoiceBox;
    public Label roomIdLabel;
    public ChoiceBox<String> roomChoiceBox;

    IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMonthChoiceBox();
        showDetailCol.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("Mở");

            {
                btn.setStyle("-fx-background-color: #090c9b; -fx-text-fill: #fbfff1; -fx-font-size: 12px;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setAlignment(Pos.CENTER);
                    setGraphic(btn);

                    btn.setOnAction(event -> {
                        try {
                            SummarizeInformationOfficer rowData = getTableView().getItems().get(getIndex());
                            showDetail(rowData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            private void showDetail(SummarizeInformationOfficer rowData) throws Exception {
                LocalDate timeSelected = Helper.getTimeStamp(monthChoiceBox.getSelectionModel().getSelectedItem()).toLocalDateTime().toLocalDate();
                ViewNavigator.showDetailOfficer(rowData.userId, timeSelected.getYear(), timeSelected.getMonthValue());
            }
        });
    }

    public void loadRoomChoiceBox() throws Exception {
        Vector<String> data = new Vector<>();
        Vector<Room> rooms = idbConnector.getListRoom();
        roomChoiceBox.setValue("TOÀN CÔNG TY");
        data.add("TOÀN CÔNG TY");
        for (Room r : rooms) {
            data.add(r.id + ":" + r.description);
        }
        roomChoiceBox.setItems(FXCollections.observableList(data));
    }

    public void loadMonthChoiceBox() {
        Vector<String> data = Helper.getListMonth();
        monthChoiceBox.setValue(data.get(0));
        monthChoiceBox.setItems(FXCollections.observableList(data));
    }


    public void loadData(Timestamp start, Timestamp end) {
        var data = new Vector<SummarizeInformationOfficer>();
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        lateEarlyCol.setCellValueFactory(new PropertyValueFactory<>("earlyAndLate"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberWork.setCellValueFactory(new PropertyValueFactory<>("workingSession"));
        try {
            var userIds = idbConnector.getListUserId();
            for (int userId : userIds) {
                data.add(TimeKeepingManager.getInstance().getSummarizeOfficerById(userId, start, end));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        companyOfficerTableView.setItems(FXCollections.observableList(data));
    }

    public void loadData(Timestamp start, Timestamp end, int roomId) {
        var data = new Vector<SummarizeInformationOfficer>();
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        numberWork.setCellValueFactory(new PropertyValueFactory<>("workingSession"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lateEarlyCol.setCellValueFactory(new PropertyValueFactory<>("earlyAndLate"));
        try {
            var userIds = idbConnector.getListUserIdByRoomId(roomId);
            for (int userId : userIds) {
                data.add(TimeKeepingManager.getInstance().getSummarizeOfficerById(userId, start, end));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        companyOfficerTableView.setItems(FXCollections.observableList(data));
    }

    public void onClickHomeButton(MouseEvent ignoredMouseEvent) throws Exception {
        ViewNavigator.gotoHomeForm();
    }

    public void onClickWatchButton(MouseEvent ignoredMouseEvent) {
        try {
            Timestamp start = Helper.getTimeStamp(monthChoiceBox.getSelectionModel().getSelectedItem());
            Timestamp end = TimeUtil.getStartTimeOfNextMonth(start);
            if (user.getRole() == User.ROOM_MANAGER_ROLE) {
                loadData(start, end, user.getRoomId());
            } else if (user.getRole() == User.ADMIN_ROLE) {
                if (roomChoiceBox.getSelectionModel().getSelectedItem().equals("TOÀN CÔNG TY")) {
                    loadData(start, end);
                } else {
                    int roomId = Integer.parseInt(roomChoiceBox.getSelectionModel().getSelectedItem().split(":")[0]);
                    loadData(start, end, roomId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHideIcon() {
        roomIdLabel.setVisible(false);
        roomChoiceBox.setVisible(false);
    }
}
