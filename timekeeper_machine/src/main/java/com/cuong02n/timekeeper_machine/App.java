package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.controller.ViewNavigator;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.model.User;
import com.cuong02n.timekeeper_machine.controller.LoginController;
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
        stg.setResizable(false);
        ViewNavigator.gotoLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}