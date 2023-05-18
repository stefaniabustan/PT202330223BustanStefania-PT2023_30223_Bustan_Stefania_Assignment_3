module com.example.assig3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.assig3 to javafx.fxml;
    exports com.example.assig3;
}