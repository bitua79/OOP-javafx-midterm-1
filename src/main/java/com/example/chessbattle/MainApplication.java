package com.example.chessbattle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(MainApplication.class.getResource("sample.fxml"));
        String directory = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\main\\resources\\com\\example\\chessbattle\\photos\\Logo.png";
        stage.getIcons().add(new Image(directory));
        Scene scene = new Scene(fxmlLoader, 1320, 700);
        scene.getStylesheets().add(getClass().getResource("style/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ChessBattle");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}