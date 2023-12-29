package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.Vector;

public class TimekeepingQuestionsController implements Initializable {
    public TableColumn<TimekeepingRequest, Integer> STT;
    public TableColumn<TimekeepingRequest, Void> manageCol;
    public TableColumn<TimekeepingRequest, Integer> userIdCol;
    public TableColumn<TimekeepingRequest, String> fullNameCol;
    public TableColumn<TimekeepingRequest, Timestamp> timeCol;
    public TableColumn<TimekeepingRequest, String> contentCol;
    public TableColumn<TimekeepingRequest, String> statusCol;

    IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    @FXML
    private TableView<TimekeepingRequest> tableData;

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        manageCol.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("Đã xử lý");

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
                        try {
                            TimekeepingRequest data = getTableView().getItems().get(getIndex());
                            idbConnector.setStatusByRequestId(data.getRequestId());
                            data.setStatus("Đã xử lý");
                            showData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });

    }

    void showData() {
        var data = getFromDatabase();
        STT.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("requestTime"));
        contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<TimekeepingRequest> observableList = FXCollections.observableList(data);
        tableData.setItems(observableList);
    }

    private Vector<TimekeepingRequest> getFromDatabase() {
        try {
            return idbConnector.getRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}