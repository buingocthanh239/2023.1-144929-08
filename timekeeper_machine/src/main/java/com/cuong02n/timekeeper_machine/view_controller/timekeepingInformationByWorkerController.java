package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.model.InformationWorkerModel;
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

public class timekeepingInformationByWorkerController implements Initializable {
    public TableView<InformationWorkerModel> timekeepingInformationWorkerTableview;
    public TableColumn<InformationWorkerModel, String> dayCol;
    public TableColumn<InformationWorkerModel, Double> shift1Col;
    public TableColumn<InformationWorkerModel, Double> shift2Col;
    public TableColumn<InformationWorkerModel, Double> shift3Col;

    public TableColumn<InformationWorkerModel, Void> showDetailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shift1Col.setCellValueFactory(new PropertyValueFactory<>("shift1"));
        shift2Col.setCellValueFactory(new PropertyValueFactory<>("shift2"));
        shift3Col.setCellValueFactory(new PropertyValueFactory<>("shift3"));
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
                        InformationWorkerModel rowData = getTableView().getItems().get(getIndex());
                        try {
                            showDetail(rowData);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            private void showDetail(InformationWorkerModel rowData) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailTimekeepingInformationByDayWorkerForm.fxml"));
                stg.setScene(new Scene(fxmlLoader.load()));
            }
        });

        timekeepingInformationWorkerTableview.setItems(observableList);
    }

    ObservableList<InformationWorkerModel> observableList = FXCollections.observableArrayList(
            new InformationWorkerModel(null,null)// todo
    );
}