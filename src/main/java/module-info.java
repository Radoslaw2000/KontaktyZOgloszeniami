module com.example.kck {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kck to javafx.fxml;
    exports com.example.kck;
}