module com.cuong02n.timekeeper_machine {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.zaxxer.hikari;
//    requires mysql.connector.j;
    opens com.cuong02n.timekeeper_machine to javafx.fxml;
    exports com.cuong02n.timekeeper_machine;
    exports com.cuong02n.timekeeper_machine.view_controller;
    opens com.cuong02n.timekeeper_machine.view_controller to javafx.fxml;
}