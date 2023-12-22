package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.model.User;
import com.cuong02n.timekeeper_machine.view_controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static Stage stg;
    public static User user = null;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        stage.setTitle("Phần mềm quản lý chấm công");


        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setScene(scene);
        stage.show();

        var controller = fxmlLoader.<LoginController>getController();
        controller.setDBConnector(DatabaseManager.hikariConnector);
    }

    public static void main(String[] args) {
        launch();
    }
}