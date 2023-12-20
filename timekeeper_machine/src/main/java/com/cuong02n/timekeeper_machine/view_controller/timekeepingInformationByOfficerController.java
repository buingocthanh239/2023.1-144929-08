package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
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
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.stg;

public class timekeepingInformationByOfficerController implements Initializable {
        public TableView<examInformationOfficeDB> timekeepingInformationOfficerTableview;
        public TableColumn<examInformationOfficeDB, String> dayCol;
        public TableColumn<examInformationOfficeDB, String> morningCol;
        public TableColumn<examInformationOfficeDB, String> afternoonCol;
        public TableColumn<examInformationOfficeDB, Double> timeLateCol;
        public TableColumn<examInformationOfficeDB, Double> timeEarlyCol;


        public TableColumn<examInformationOfficeDB, Void> showDetailCol;

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
                            examInformationOfficeDB rowData = getTableView().getItems().get(getIndex());
                            try {
                                showDetail(rowData);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }

                private void showDetail(examInformationOfficeDB rowData) throws IOException {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailTimekeepingInformationByDayOfficeForm.fxml"));
                    stg.setScene(new Scene(fxmlLoader.load()));
                }
            });

            timekeepingInformationOfficerTableview.setItems(observableList);
        }

        ObservableList<examInformationOfficeDB> observableList = FXCollections.observableArrayList(
                new examInformationOfficeDB("1/1/2023", "có", "không",0,0)
        );
    }
