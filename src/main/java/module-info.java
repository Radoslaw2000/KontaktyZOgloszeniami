module com.example.kck {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kck to javafx.fxml;
    exports com.example.kck;
}