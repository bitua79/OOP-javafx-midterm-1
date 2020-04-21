package com.example.chessbattle;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bloc extends Rectangle{

    private int x, y;
    private Piece piece = null;
    private boolean dead = false;
    private Board board;

    //sounds
    private AudioClip destroyBloc = new AudioClip("file:src/main/resources/com/example/chessbattle/media/destroyBloc.wav");
    private AudioClip destroyPiece = new AudioClip("file:src/main/resources/com/example/chessbattle/media/destroyPiece.wav");


    public Bloc(int x, int y, Board board) {
        //make a square with "300/size" side (our board is 300 * 300)
        super((int)(400/Controller.size), (int)(400/Controller.size));
        super.setStyle("-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
        setFill(Color.BLACK);
        setStroke(Color.BEIGE);
        setOpacity(0.5);
        this.x = x;
        this.y = y;
        this.board = board;
    }

    //it set the died cells and return if this cell is a ship cell or not
    public boolean shoot() {
        dead = true;
        if(piece == null){
            FillTransition fillTransition = new FillTransition(new Duration(3000), this);
            fillTransition.setFromValue(Color.BLACK);
            fillTransition.setToValue(Color.DARKCYAN);
            fillTransition.setInterpolator(Interpolator.LINEAR);
            fillTransition.play();
        }
        setOpacity(0.7);
        destroyBloc.play();

        if (piece != null ) {

            piece.hit();
            setFill(Paint.valueOf("#f8c202"));
            setOpacity(0.9);
            Image image = new Image(Controller.photo+"w.gif");
            setFill(new ImagePattern(image));

            if (!piece.isAlive()) {
                destroyPiece.play();
                //player gets some money after shoot to enemy piece
                if(piece.isEnemy()) {
                    if (piece.getType() == 11){
                        Controller.playerMoney += 1000;
                        board.kingCount--;
                    }
                    else if (piece.getType() == 10 || piece.getType() == 9) {
                        Controller.playerMoney += 500;
                        board.rookCount--;
                    }
                    else if (piece.getType() > 5 && piece.getType() <= 8) {
                        Controller.playerMoney += 300;
                        board.knightCount--;
                    }
                    else if (piece.getType() > 0 && piece.getType() <= 5) {
                        Controller.playerMoney += 100;
                        board.pawnCount--;
                    }
                }
                else if(!piece.isEnemy()){
                    if (piece.getType() == 11){
                        board.kingCount--;
                    }
                    else if (piece.getType() == 10 || piece.getType() == 9) {
                        board.rookCount--;
                    }
                    else if (piece.getType() > 5 && piece.getType() <= 8) {
                        board.knightCount--;
                    }
                    else if (piece.getType() > 0 && piece.getType() <= 5) {
                        board.pawnCount--;
                    }
                }
                board.pieceCount--;
            }
            return true;
        }
        return false;
    }

    //getter and setter
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getBlocX() {
        return x;
    }
    public void setBlocX(int x) {
        this.x = x;
    }

    public int getBlocY() {
        return y;
    }
    public void setBlocY(int y) {
        this.y = y;
    }

    //play sound
    public void playSound(MediaPlayer mediaPlayer) {
        mediaPlayer.setCycleCount(1000);
        mediaPlayer.play();
    }

}

