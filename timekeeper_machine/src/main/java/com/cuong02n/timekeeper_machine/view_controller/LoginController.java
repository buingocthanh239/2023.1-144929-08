package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class LoginController {
    private IDBConnector idbConnector;

    public void setDBConnector(IDBConnector idbConnector) {
        this.idbConnector = idbConnector;
    }

    @FXML
    TextField passWordField;
    @FXML
    TextField idField;

    @FXML
    public void onClickLoginButton(ActionEvent actionEvent) throws Exception {
        String username = idField.getText();
        String password = passWordField.getText();
        user = idbConnector.verify(username, password);
        if (user == null) {
            // TODO:
            System.out.println("cannot find user");
            return;
        }
        FXMLLoader loader;

        if (user.getRole() == User.ADMIN_ROLE) {
            loader = new FXMLLoader(App.class.getResource("homeByHRMForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByHRMController>getController();
            controller.setDBConnector(this.idbConnector);
        } else if (user.getRole() == User.ROOM_MANAGER_ROLE) {
            loader = new FXMLLoader(App.class.getResource("homeByManagerForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByManagerdController>getController();
            controller.setDBConnector(this.idbConnector);
        } else if (user.getRole() == User.OFFICER_ROLE) {
            loader = new FXMLLoader(App.class.getResource("homeByOfficerForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByOfficerController>getController();
            controller.setDBConnector(this.idbConnector);
        } else {
            loader = new FXMLLoader(App.class.getResource("homeByWorkerForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByWorkerController>getController();
            controller.setDBConnector(this.idbConnector);
        }

    }
}