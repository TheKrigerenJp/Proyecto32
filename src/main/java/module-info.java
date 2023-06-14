module com.example.proyecto32 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.fazecast.jSerialComm;

    opens com.example.proyecto32 to javafx.fxml;
    exports com.example.proyecto32;
}