module com.cuong02n.timekeeper_machine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cuong02n.timekeeper_machine to javafx.fxml;
    exports com.cuong02n.timekeeper_machine;
}