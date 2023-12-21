package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.controller.Calculator;
import com.cuong02n.timekeeper_machine.database.HikariConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationOfficer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class unitTimekeepingInformationController implements Initializable {
    @FXML
    public TableColumn showId;
    @FXML
    public TableColumn showName;
    @FXML
    public TableColumn showWorkSession;
    @FXML
    public TableColumn showX;
    @FXML
    private TableView<SummarizeInformationOfficer> companyOfficerTableView;
    @FXML
    private TableColumn<SummarizeInformationOfficer, Void> showDetailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
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

            int roomId = HikariConnector.getInstance().findRoomIdByUserId(user.getUserId());
            Vector<Integer> ids = HikariConnector.getInstance().getUserByRoomId(roomId);
            Vector<SummarizeInformationOfficer> dataToDisplay = new Vector<>();
            for(int userId : ids){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Timestamp start = new Timestamp(calendar.getTimeInMillis());
                Timestamp end = new Timestamp(System.currentTimeMillis());
                var actions = HikariConnector.getInstance().getActionByTimeStampAndUserId(start, end, userId);
                dataToDisplay.add(Calculator.getSummarizeInformationOfficer(Calculator.transformDataToDisplayOfficer(actions)));
            }
            companyOfficerTableView.setItems(FXCollections.observableList(dataToDisplay));
            System.out.println(dataToDisplay.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
