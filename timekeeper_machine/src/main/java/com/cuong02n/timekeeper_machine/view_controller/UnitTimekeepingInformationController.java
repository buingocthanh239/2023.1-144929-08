package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
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
import java.util.ResourceBundle;

import static com.cuong02n.timekeeper_machine.App.stg;

public class UnitTimekeepingInformationController implements Initializable {
    @FXML
    private TableView<InformationOfficeModel> companyOfficerTableView;
    @FXML
    private TableColumn<InformationOfficeModel, Void> showDetailCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ShowDetailOfficerForm.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stg.setScene(scene);
            }
        });

        companyOfficerTableView.setItems(createSampleData());
    }

    private ObservableList<InformationOfficeModel> createSampleData() {
        ObservableList<InformationOfficeModel> data = FXCollections.observableArrayList();
        // Add sample data, adjust this according to your needs
        for (int i = 1; i <= 5; i++) {
            data.add(new InformationOfficeModel());
        }
        return data;
    }
}
