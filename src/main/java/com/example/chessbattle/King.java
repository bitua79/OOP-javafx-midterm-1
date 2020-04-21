package com.example.chessbattle;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King {
    public Button button;
    public int playerCounter;
    public int enemyCounter;
    private final int period;
    private final int powerHeight;
    private final int powerWidth;

    public King() {
        this.button = new Button("king");
        Image image = new Image(Controller.photo+"king.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        button.setGraphic(imageView);
        button.setId("orange_notClicked_font20");
        button.setAlignment(Pos.CENTER);
        button.setOpacity(0.9);

        this.playerCounter = 0;
        this.enemyCounter = 0;
        this.period = 15;
        this.powerHeight = 4;
        this.powerWidth = 2;

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
