package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PopupNotification {
    @FXML
    public Label messageLabel;

    // static
    public static void notify(String message) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("popupNotification.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),350,150);
            stage.setScene(scene);

            stage.show();
            var controller = fxmlLoader.<PopupNotification>getController();
            controller.setStage(stage);
            controller.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Stage stage;
    void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public Label messengerLabel;
    public void setMessage(String message){
        this.messengerLabel.setText(message);
    }
    public void onClickCloseButton(MouseEvent mouseEvent) {
        stage.hide();
    }
}
