package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.*;
import com.cuong02n.timekeeper_machine.util.Calculator;
import com.cuong02n.timekeeper_machine.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.user;

public class TimekeepingInformationByOfficerController implements Initializable {
    public Label earlyLateLabel;
    public Label totalWorkLabel;
    public ImageView homeButton;
    public TableView<InformationOfficeModel> timekeepingInformationOfficerTableView;
    public TableColumn<InformationOfficeModel, String> dayCol;
    public TableColumn<InformationOfficeModel, String> morningCol;
    public TableColumn<InformationOfficeModel, String> afternoonCol;
    public TableColumn<InformationOfficeModel, Double> timeLateCol;
    public TableColumn<InformationOfficeModel, Double> timeEarlyCol;
    public TableColumn<InformationOfficeModel, Void> showDetailCol;
    IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeButton.setOnMouseClicked(event -> {
            try {
                ViewNavigator.gotoHomeForm();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    void loadDataFromDatabase() throws Exception {
        Timestamp start = DateUtil.getStartTimeThisMonth();
        Timestamp end = DateUtil.getNow();
        Vector<Action> actions = idbConnector.getActionByTimeStampAndUserId(start, end, user.getUserId());
        setButtonOpenForARow();
        showDataFromDataLoaded(actions);
    }

    void showDataFromDataLoaded(Vector<Action> actions) {
        morningCol.setCellValueFactory(new PropertyValueFactory<>("morning"));
        afternoonCol.setCellValueFactory(new PropertyValueFactory<>("afternoon"));
        timeLateCol.setCellValueFactory(new PropertyValueFactory<>("timeLate"));
        timeEarlyCol.setCellValueFactory(new PropertyValueFactory<>("timeEarly"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        Vector<InformationOfficeModel> informationOfficeModels = TimeKeepingManager.getInstance().transformActionToOfficeModel((actions));
        timekeepingInformationOfficerTableView.setItems(FXCollections.observableList(informationOfficeModels));

        SummarizeInformationOfficer summarize = TimeKeepingManager.getInstance().calculateSummarizeByAction(informationOfficeModels);
        totalWorkLabel.setText("" + Calculator.round(summarize.getWorkingSession()));
        earlyLateLabel.setText("" + Calculator.round(summarize.getEarlyAndLate()));
    }

    void setButtonOpenForARow() {
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
                        InformationOfficeModel rowData = getTableView().getItems().get(getIndex());
                        try {
                            showDetail(rowData);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            private void showDetail(InformationOfficeModel rowData) throws IOException {
                try {
                    ViewNavigator.showDetailTimekeepingOfficer(rowData);
                }catch (Exception e){
                    PopupNotification.notify(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }


}
