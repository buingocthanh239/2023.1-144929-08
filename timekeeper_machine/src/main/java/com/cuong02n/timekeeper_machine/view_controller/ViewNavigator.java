package com.cuong02n.timekeeper_machine.view_controller;

import com.cuong02n.timekeeper_machine.App;
import com.cuong02n.timekeeper_machine.database.DatabaseManager;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;
import com.cuong02n.timekeeper_machine.util.DateUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import static com.cuong02n.timekeeper_machine.App.stg;
import static com.cuong02n.timekeeper_machine.App.user;

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

    public static void gotoTimeKeepingInformationForm() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingInformationByOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
        var controller = fxmlLoader.<TimekeepingInformationByOfficerController>getController();

        controller.setDBConnector(idbConnector);
        controller.loadDataFromDatabase();


    }

    public static void gotoTimeKeepingQuestionForm() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("timekeepingQuestionsForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));
        var controller = fxmlLoader.<TimekeepingQuestionsController>getController();
        controller.setDBConnector(idbConnector);
        controller.showData();
    }

    public static void gotoCompanyTimeKeepingOfficer() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("companyTimekeepingInformationUnitIsOfficerForm.fxml"));
        stg.setScene(new Scene(fxmlLoader.load()));

        var controller = fxmlLoader.<CompanyTimekeepingInformationRoomOfficerController>getController();
        controller.setDBConnector(idbConnector);
        controller.loadData(DateUtil.getStartTimeThisMonth(),DateUtil.getStartTimeOfNextMonth(DateUtil.getStartTimeThisMonth()));
    }
}
