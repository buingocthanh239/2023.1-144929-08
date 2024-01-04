package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.controller.ViewNavigator;
import com.cuong02n.timekeeper_machine.database.HikariMySqlTemplate;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class App extends Application {
    public static final IDBConnector hikariConnector = HikariMySqlTemplate.getInstance();
    public static Stage stg;
    public static User user = null;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stg.setResizable(false);
        ViewNavigator.gotoLogin();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        launch();
    }
}