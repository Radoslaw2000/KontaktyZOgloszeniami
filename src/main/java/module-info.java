module com.example.kck {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kck to javafx.fxml;
    exports com.example.kck;
    exports com.example.kck.controllers;
    opens com.example.kck.controllers to javafx.fxml;
}