package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.controller.Calculator;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class TimekeepingInformationByOfficerController {
    IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    public TableView<InformationOfficeModel> timekeepingInformationOfficerTableView;
    public TableColumn<InformationOfficeModel, String> dayCol;
    public TableColumn<InformationOfficeModel, String> morningCol;
    public TableColumn<InformationOfficeModel, String> afternoonCol;
    public TableColumn<InformationOfficeModel, Double> timeLateCol;
    public TableColumn<InformationOfficeModel, Double> timeEarlyCol;

    public TableColumn<InformationOfficeModel, Void> showDetailCol;

    void loadDataFromDatabase() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Timestamp start = new Timestamp(calendar.getTimeInMillis());
        Timestamp end = new Timestamp(System.currentTimeMillis());
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
        Vector<InformationOfficeModel> informationOfficeModels = Calculator.transformDataToDisplayOfficer(actions);
        timekeepingInformationOfficerTableView.setItems(FXCollections.observableList(informationOfficeModels));
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
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailTimekeepingInformationByDayOfficeForm.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load(),450,300));
                stage.setTitle("Gửi thắc mắc chấm công");
                stage.show();
                var controller = fxmlLoader.<ShowDetailTimekeepingInformationByDayOfficerController>getController();
                controller.showData(rowData);
                controller.setStage(stage);
                controller.setDBConnector(idbConnector);
            }
        });
    }


}
