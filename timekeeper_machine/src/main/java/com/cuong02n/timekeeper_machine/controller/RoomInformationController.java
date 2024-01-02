package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.model.TimeKeepingManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.SummarizeInformationOfficer;
import com.cuong02n.timekeeper_machine.util.TimeUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
import java.util.ResourceBundle;
import java.util.Vector;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class RoomInformationController implements Initializable {
    IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector = idbConnector;
    }
    @FXML
    public TableColumn<SummarizeInformationOfficer, Integer> showId;
    @FXML
    public TableColumn<SummarizeInformationOfficer, String> showName;
    @FXML
    public TableColumn<SummarizeInformationOfficer, Integer> showWorkSession;
    @FXML
    public TableColumn<SummarizeInformationOfficer, Double> showX;
    @FXML
    private TableView<SummarizeInformationOfficer> companyOfficerTableView;
    @FXML
    private TableColumn<SummarizeInformationOfficer, Void> showDetailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");
        showId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        showName.setCellValueFactory(new PropertyValueFactory<>("name"));
        showWorkSession.setCellValueFactory(new PropertyValueFactory<>("workingSession"));
        showX.setCellValueFactory(new PropertyValueFactory<>("earlyAndLate"));
        try {
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

            int roomId = idbConnector.findRoomIdByUserId(user.getUserId());
            Vector<Integer> ids = idbConnector.getListUserIdByRoomId(roomId);

            Timestamp start = TimeUtil.getStartTimeThisMonth();
            Timestamp end = TimeUtil.getNow();
            Vector<SummarizeInformationOfficer> dataToDisplay = TimeKeepingManager.getInstance().getSummarizeOfficeByListId(ids,start,end);
            companyOfficerTableView.setItems(FXCollections.observableList(dataToDisplay));
            System.out.println(dataToDisplay.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
