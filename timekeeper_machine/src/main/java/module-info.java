module com.cuong02n.timekeeper_machine {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.zaxxer.hikari;
//    requires mysql.connector.j;
    opens com.cuong02n.timekeeper_machine to javafx.fxml;
    exports com.cuong02n.timekeeper_machine;
    exports com.cuong02n.timekeeper_machine.controller;
    opens com.cuong02n.timekeeper_machine.controller to javafx.fxml;
    exports com.cuong02n.timekeeper_machine.model;
    opens com.cuong02n.timekeeper_machine.model to javafx.fxml;
    exports com.cuong02n.timekeeper_machine.util;
    opens com.cuong02n.timekeeper_machine.util to javafx.fxml;
}