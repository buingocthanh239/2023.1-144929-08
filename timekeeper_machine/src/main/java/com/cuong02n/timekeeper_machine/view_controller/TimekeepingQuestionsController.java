package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
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

public class TimekeepingQuestionsController implements Initializable {
    IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector = idbConnector;
    }

    @FXML
    private TableView<examInformationQuestionDB> timekeepingInformationQuestionTableView;

    @FXML
    private TableColumn<examInformationQuestionDB, Void> deleteCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        deleteCol.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("Xóa");
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
                        examInformationQuestionDB rowData = getTableView().getItems().get(getIndex());
                        try {
                            deleteRow(rowData);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            private void deleteRow(examInformationQuestionDB rowData) throws IOException {
                int rowIndex = timekeepingInformationQuestionTableView.getItems().indexOf(rowData);
                timekeepingInformationQuestionTableView.getItems().remove(rowIndex);
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingQuestionsForm.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stg.setScene(scene);
            }
        });
        timekeepingInformationQuestionTableView.setItems(createSampleData());
    }


    private ObservableList<examInformationQuestionDB> createSampleData() {
        ObservableList<examInformationQuestionDB> data = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            data.add(new examInformationQuestionDB());
        }
        return data;
    }
}