package com.cuong02n.timekeeper_machine.controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.InformationOfficeModel;
import com.cuong02n.timekeeper_machine.model.User;
import com.cuong02n.timekeeper_machine.util.TimeUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;
import static com.cuong02n.timekeeper_machine.util.TimeUtil.getStartTimeOfNextMonth;
import static com.cuong02n.timekeeper_machine.util.TimeUtil.getStartTimeThisMonth;

public class ViewNavigator {
    static IDBConnector idbConnector = DatabaseManager.hikariConnector;

    public static void gotoHomeForm() throws Exception {
        FXMLLoader loader;
        if (user.getRole() == User.ADMIN_ROLE) {
            loader = new FXMLLoader(App.class.getResource("homeByHRMForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByHRMController>getController();
            controller.setDBConnector(idbConnector);
        } else if (user.getRole() == User.ROOM_MANAGER_ROLE) {
            loader = new FXMLLoader(App.class.getResource("homeByManagerForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByManagerController>getController();
            controller.setDBConnector(idbConnector);
        } else if (user.getRole() == User.OFFICER_ROLE) {
            loader = new FXMLLoader(App.class.getResource("homeByOfficerForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByOfficerController>getController();
            controller.setDBConnector(idbConnector);
        } else {
            loader = new FXMLLoader(App.class.getResource("homeByWorkerForm.fxml"));
            stg.setScene(new Scene(loader.load()));
            var controller = loader.<HomeByWorkerController>getController();
            controller.setDBConnector(idbConnector);
        }
    }

    public static void gotoTimeKeepingInformationForm() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingInformationByOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
        var controller = fxmlLoader.<TimekeepingInformationByOfficerController>getController();

        controller.setDBConnector(idbConnector);
        controller.loadData(getStartTimeThisMonth(), TimeUtil.getNow());
    }

    public static void gotoRoomInformationForm(int roomId) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("roomOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
        var controller = fxmlLoader.<RoomOfficerController>getController();
        controller.setDBConnector(idbConnector);
        controller.setHideIcon();

        controller.loadData(getStartTimeThisMonth(),getStartTimeOfNextMonth(getStartTimeThisMonth()),roomId);
    }

    public static void gotoTimeKeepingQuestionForm() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingQuestionsForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
        var controller = fxmlLoader.<TimekeepingQuestionsController>getController();
        controller.setDBConnector(idbConnector);
        controller.showData();
    }

    public static void showDetailTimekeepingOfficer(InformationOfficeModel rowData) throws Exception {
        FXMLLoader fxmlLoader;
        Stage stage = new Stage();
        fxmlLoader = new FXMLLoader(App.class.getResource("showDetailByDayOfficeForm.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Chi tiết");
        stage.show();

        var controller = fxmlLoader.<ShowDetailADayController>getController();
        controller.setDBConnector(idbConnector);
        controller.showData(rowData);
    }

    public static void gotoCreateRequest(InformationOfficeModel data) throws Exception {
        FXMLLoader fxmlLoader;
        Stage stage = new Stage();
        fxmlLoader = new FXMLLoader(App.class.getResource("createTimekeepingQuestionsForm.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Gửi thắc mắc chấm công");
        stage.show();
        var controller = fxmlLoader.<CreateTimekeepingQuestionsController>getController();
        controller.setDBConnector(idbConnector);
        controller.showData(data);
    }

    public static void gotoChangeRequest(InformationOfficeModel data) throws Exception {
        FXMLLoader fxmlLoader;
        Stage stage = new Stage();
        fxmlLoader = new FXMLLoader(App.class.getResource("changeTimeActionByHMRFrom.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Chỉnh sửa chấm công");
        stage.show();
        var controller = fxmlLoader.<ChangeActionByHMRController>getController();
        controller.setDBConnector(idbConnector);
        controller.showData(data);
    }

    public static void gotoCompanyTimeKeepingOfficer() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("roomOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));

        var controller = fxmlLoader.<RoomOfficerController>getController();
        controller.setDBConnector(idbConnector);
        controller.loadData(getStartTimeThisMonth(), TimeUtil.getStartTimeOfNextMonth(getStartTimeThisMonth()));
    }

    public static void gotoCompanyTimeKeepingOfficer(int year, int month) throws Exception {

    }

    public static void closeStage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public static void showDetailOfficer(int userId,int year,int month) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("showDetailOfficerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));

        var controller =fxmlLoader.<ShowDetailOfficerController>getController();
        controller.setDBConnector(idbConnector);
        controller.loadData(userId,year,month);
    }
}
