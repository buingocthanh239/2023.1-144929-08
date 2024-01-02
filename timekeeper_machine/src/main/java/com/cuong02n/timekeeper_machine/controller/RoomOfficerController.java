package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationOfficer;
import com.cuong02n.timekeeper_machine.model.TimeKeepingManager;
import com.cuong02n.timekeeper_machine.util.TimeUtil;
import com.cuong02n.timekeeper_machine.util.Helper;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.stg;

public class RoomOfficerController implements Initializable {
    public TableColumn<SummarizeInformationOfficer, Integer> userIdCol;
    public TableColumn<SummarizeInformationOfficer, String> fullNameCol;
    public TableColumn<SummarizeInformationOfficer, Integer> numberWork;
    public TableColumn<SummarizeInformationOfficer, Double> lateEarlyCol;

    public TableColumn<SummarizeInformationOfficer, Void> showDetailCol;
    public TableView<SummarizeInformationOfficer> companyOfficerTableView;
    public ChoiceBox<String> monthChoiceBox;
    public Label roomIdLabel;
    public ChoiceBox unitChoiceBox;

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
                // Set styles for the button
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
                        SummarizeInformationOfficer rowData = getTableView().getItems().get(getIndex());
                        try {
                            showDetail(rowData);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            private void showDetail(SummarizeInformationOfficer rowData) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailOfficerForm.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stg.setScene(scene);
            }
        });

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

    public void loadData(Timestamp start,Timestamp end,int roomId){
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
    public void onClickHomeButton(MouseEvent mouseEvent) throws Exception {
        ViewNavigator.gotoHomeForm();
    }

    public void onClickWatchButton(MouseEvent mouseEvent) {
        Timestamp start = Helper.getTimeStamp(monthChoiceBox.getSelectionModel().getSelectedItem());
        Timestamp end = TimeUtil.getStartTimeOfNextMonth(start);
        loadData(start, end);
    }

    public void setHideIcon() {
        roomIdLabel.setVisible(false);
        unitChoiceBox.setVisible(false);
    }
}
