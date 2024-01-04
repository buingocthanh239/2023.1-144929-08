package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.TimeKeepingManager;
import com.cuong02n.timekeeper_machine.util.TimeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ShowDetailOfficerController implements Initializable {
    @FXML
    public Label labelText;
    public TableView<InformationOfficeModel> timekeepingInformationOfficerTableview;
    public TableColumn<InformationOfficeModel, String> dayCol;
    public TableColumn<InformationOfficeModel, String> morningCol;
    public TableColumn<InformationOfficeModel, String> afternoonCol;
    public TableColumn<InformationOfficeModel, Double> timeLateCol;
    public TableColumn<InformationOfficeModel, Double> timeEarlyCol;
    public TableColumn<InformationOfficeModel, Void> showDetailCol;
    IDBConnector idbConnector;
    ObservableList<InformationOfficeModel> observableList = FXCollections.observableArrayList(
//            new InformationOfficeModel("1/1/2023", "có", "không", 0, 0)
    );

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        morningCol.setCellValueFactory(new PropertyValueFactory<>("morning"));
        afternoonCol.setCellValueFactory(new PropertyValueFactory<>("afternoon"));
        timeLateCol.setCellValueFactory(new PropertyValueFactory<>("timeLate"));
        timeEarlyCol.setCellValueFactory(new PropertyValueFactory<>("timeEarly"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));

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
                        InformationOfficeModel rowData = getTableView().getItems().get(getIndex());
                        try {
                            showDetail(rowData);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            private void showDetail(InformationOfficeModel rowData) throws Exception {
                ViewNavigator.showDetailOfficer(rowData);
            }
        });
        timekeepingInformationOfficerTableview.setItems(observableList);
    }

    public void loadData(int userId, int year, int month) throws Exception {
        labelText.setText("Xem chi tiết chấm công nhân viên " + userId);
        Timestamp startMonth = TimeUtil.getStartTimeOfMonth(year, month);
        Timestamp endMonth = TimeUtil.getStartTimeOfNextMonth(startMonth);
        System.out.println(startMonth);
        System.out.println(endMonth);
        var actions = idbConnector.getActionByTimeStampAndUserId(startMonth, endMonth, userId);
        var data = TimeKeepingManager.getInstance().transformActionToOfficeModel(actions);
        timekeepingInformationOfficerTableview.setItems(FXCollections.observableList(data));
    }
}
