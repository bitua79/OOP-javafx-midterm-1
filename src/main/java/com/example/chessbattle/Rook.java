package com.example.chessbattle;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook {
    public Button button;
    public int playerCounter;
    public int enemyCounter;
    private final int period;
    private final int powerHeight;
    private final int powerWidth;

    public Rook() {
        this.button = new Button("rook");
        Image image = new Image(Controller.photo+"rook.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        button.setGraphic(imageView);
        button.setId("orange_notClicked_font20");
        button.setAlignment(Pos.CENTER);
        button.setOpacity(0.9);

        this.playerCounter = 0;
        this.enemyCounter = 0;
        this.period = 10;
        this.powerHeight = 2;
        this.powerWidth = 3;
    }

    public int getPeriod() {
        return period;
    }

    public int getPowerHeight() {
        return powerHeight;
    }

    public int getPowerWidth() {
        return powerWidth;
    }
}
