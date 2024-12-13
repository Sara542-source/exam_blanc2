module org.example.examblanc_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;


    opens org.example.examblanc_3 to javafx.fxml;
    exports org.example.examblanc_3;
}