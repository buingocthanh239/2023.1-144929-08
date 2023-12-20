package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.HikariConnector;
import com.cuong02n.timekeeper_machine.model.Action;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class timekeepingInformationByOfficerController implements Initializable {
    public TableView<InformationOfficeModel> timekeepingInformationOfficerTableView;
    public TableColumn<InformationOfficeModel, String> dayCol;
    public TableColumn<InformationOfficeModel, String> morningCol;
    public TableColumn<InformationOfficeModel, String> afternoonCol;
    public TableColumn<InformationOfficeModel, Double> timeLateCol;
    public TableColumn<InformationOfficeModel, Double> timeEarlyCol;

    public TableColumn<InformationOfficeModel, Void> showDetailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vector<Action> actions = new Vector<>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Timestamp start = new Timestamp(calendar.getTimeInMillis());
            Timestamp end = new Timestamp(System.currentTimeMillis());
            actions = HikariConnector.getInstance().getActionByTimeStampAndUserId(start, end, user.getUserId());
        } catch (Exception e) {
        }
        morningCol.setCellValueFactory(new PropertyValueFactory<>("morning"));
        afternoonCol.setCellValueFactory(new PropertyValueFactory<>("afternoon"));
        timeLateCol.setCellValueFactory(new PropertyValueFactory<>("timeLate"));
        timeEarlyCol.setCellValueFactory(new PropertyValueFactory<>("timeEarly"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));

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
                stg.setScene(new Scene(fxmlLoader.load()));
            }
        });
        Vector<InformationOfficeModel> informationOfficeModels = new Vector<>();
        for (int i = 0, indexRoot = 0; i < actions.size(); i++) {
            while (i<actions.size() && (actions.get(i).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear() ==
                    actions.get(indexRoot).getActionTime().toLocalDateTime().toLocalDate().getDayOfYear())) {
                i++;
            }
            informationOfficeModels.add(new InformationOfficeModel(actions.get(indexRoot).getActionTime(), actions.get(i - 1).getActionTime()));
            indexRoot = i;
        }
        ObservableList<InformationOfficeModel> observableList = FXCollections.observableList(informationOfficeModels);


        timekeepingInformationOfficerTableView.setItems(observableList);
    }


}
