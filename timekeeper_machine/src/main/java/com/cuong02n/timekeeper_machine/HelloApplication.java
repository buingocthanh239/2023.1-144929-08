package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.view_controller.HomeFormController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Phần mềm quản lý chấm công");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {

    }

    public static void main(String[] args) {
        launch();
    }
}