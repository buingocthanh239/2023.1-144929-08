package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.model.User;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Phần mềm quản lý chấm công");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {launch();}
}