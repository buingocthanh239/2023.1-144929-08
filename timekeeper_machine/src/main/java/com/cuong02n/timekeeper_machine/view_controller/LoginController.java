package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

public class LoginController {
    private IDBConnector idbConnector;
    public void setDBConnector(IDBConnector idbConnector){
        this.idbConnector=idbConnector;
    }
    @FXML
    TextField passWordField;
    @FXML
    TextField idField;
    public void test(){
        System.out.println("test function");
    }
    public void initialize(){
        System.out.println("init function");
    }
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