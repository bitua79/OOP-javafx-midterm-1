module com.example.chessbattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.chessbattle to javafx.fxml;
    exports com.example.chessbattle;
}