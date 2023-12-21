package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.HikariConnector;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.nio.Buffer;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class LoginFormController {    IDBConnector idbConnector = DatabaseManager.getDBNow();

    @FXML
    TextField passWordField;
    @FXML
    TextField idField;
    @FXML
    public void onClickLoginButton(ActionEvent actionEvent) throws Exception {
        String username = idField.getText();
        String password = passWordField.getText();
        user = idbConnector.verify(username,password);
        if(user ==null){
            // TODO:
            System.out.println("cannot find user");
            return;
        }
        FXMLLoader loader;

        if(user.getRole() == User.ADMIN_ROLE){
            loader  = new FXMLLoader(App.class.getResource("homeByHRMForm.fxml"));
        }else if(user.getRole() == User.ROOM_MANAGER_ROLE){
            loader  = new FXMLLoader(App.class.getResource("homeByUnitHeadForm.fxml"));
        }else if(user.getRole() == User.STAFF_ROLE){
            loader  = new FXMLLoader(App.class.getResource("homeByOfficerForm.fxml"));
        }else {
             loader  = new FXMLLoader(App.class.getResource("homeByWorkerForm.fxml"));
        }
        stg.setScene(new Scene(loader.load()));
    }
}