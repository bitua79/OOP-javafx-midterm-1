package com.example.chessbattle;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn {
    public Button button;
    private final int powerHeight;
    private final int powerWidth;



    public Pawn() {
        this.button = new Button("pawn");
        Image image = new Image(Controller.photo+"pawn.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        button.setGraphic(imageView);
        button.setId("orange_notClicked_font20");
        button.setAlignment(Pos.CENTER);
        button.setOpacity(0.9);

        this.powerHeight = 1;
        this.powerWidth = 1;
    }

    public int getPowerHeight() {
        return powerHeight;
    }

    public int getPowerWidth() {
        return powerWidth;
    }


}
