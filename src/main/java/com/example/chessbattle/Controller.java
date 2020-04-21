package com.example.chessbattle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Controller extends Stage {

    public static int size;
    private int countVolume = 0;
    private int gameCountVolume = 0;
    private String logo = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/")
            + "\\src\\main\\resources\\com\\example\\chessbattle\\photos\\Logo.png";
    public static String photo = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/")
            + "\\src\\main\\resources\\com\\example\\chessbattle\\photos\\";
    public String music = System.getProperty("user.dir").replaceAll("\\\\", "/") +
            "\\src\\main\\resources\\com\\example\\chessbattle\\media\\";


    //first part of menu which get dimension and player name
    @FXML
    private AnchorPane beginningPane;
    @FXML
    private ImageView firstImage;
    @FXML
    private TextField nameField;
    @FXML
    private Label labelName;
    @FXML
    private TextField dimensionField;
    @FXML
    private Label labelDimension;
    @FXML
    private Button start;
    @FXML
    private Button mute1;
    public Stage stage = new Stage();

    //second part of menu which show the name of player
    @FXML
    private ImageView secondImage;
    @FXML
    private Label labelWelcome;
    @FXML
    private Label nameLabel;
    @FXML
    private Button goToMenu;
    @FXML
    private Button exit;
    @FXML
    private Button mute2;

    //third part of menu which contains plat button and guide button
    @FXML
    private Button play;
    @FXML
    private Button guide;
    @FXML
    private Button mute3;
    @FXML
    private ImageView firstImage2;
    @FXML
    private Label GameInformation;
    @FXML
    private Label GameName;

    /************************************************menu pages********************************************************/
    //the initialization of menu which handle start button
    @FXML
    public void initialize() {
        String dir0 = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\main\\resources\\com\\example\\chessbattle\\photos\\3.jpg";
        Image image = new Image(dir0);
        nameField.setOpacity(0.5);
        dimensionField.setOpacity(0.5);


        System.out.println("Hello!!!\nDimension fixes the size of board! Please enter an integer in range 12 and 22!\n");

        start.setDisable(true);
        playSound(menuMediaPlayer);
    }

    //play game sound

    public Media menuMedia = new Media(new File(music + "menu.mp3").toURI().toString());
    public MediaPlayer menuMediaPlayer = new MediaPlayer(menuMedia);
    //.................................................................................................................//
    public Media gameMedia = new Media(new File(music + "game.mp3").toURI().toString());
    public MediaPlayer gameMediaPlayer = new MediaPlayer(gameMedia);
    //.................................................................................................................//
    public Media winMedia = new Media(new File(music + "win.mp3").toURI().toString());
    public MediaPlayer winMediaPlayer = new MediaPlayer(winMedia);
    //.................................................................................................................//
    public Media loseMedia = new Media(new File(music + "lose.mp3").toURI().toString());
    public MediaPlayer loseMediaPlayer = new MediaPlayer(loseMedia);


    //to get sure that none of nameField and dimensionField is empty
    public boolean flag;

    @FXML
    public void handle() {
        try {
            size = Integer.parseInt(dimensionField.getText());
            flag = true;
        } catch (NumberFormatException e) {
            size = 12;
            flag = false;
        }
        //minimum size of board is 8*8 and maximum is 15*15
        if (size < 12 || size > 22) flag = false;
        boolean isEmpty = nameField.getText().isEmpty() || nameField.getText().trim().isEmpty() ||
                dimensionField.getText().isEmpty() || dimensionField.getText().trim().isEmpty();

        start.setDisable(isEmpty);
    }

    public Rectangle rectangle1;
    public Rectangle rectangle2;
    public Rectangle rectangle3;
    public Rectangle rectangle4;

    //set first and second part of menu after click on start button
    @FXML
    public void beginningGame() throws IOException, InterruptedException {
        {
            //this part is setting a label to say hi to player
            String playerName = "Hello " + nameField.getText() + "!";
            nameLabel.setText(playerName);
            nameLabel.prefWidth(100);
            nameLabel.setAlignment(Pos.CENTER);
            nameLabel.setFont(Font.font("Chiller", 54));
            nameLabel.setTextFill(Paint.valueOf("#3e2b61"));
        }

        //change the menu and goes to second part
        if (!flag) {
            System.out.println("You enter dimension wrong!automatically dimension was set to 12!\n");
            size = 12;
        }
        beginningPane.getChildren().removeAll(nameField, dimensionField, labelName, labelDimension, start, firstImage);
        mute1.setVisible(false);

        {
            //this part design 4 squares rotating and moving around nameLabel
            rectangle1 = new Rectangle(10, 10, Paint.valueOf("#91aeab"));
            rectangle1.setStyle("-fx-effect: innershadow( three-pass-box , #27304f , 6, 0.0 , 0 , 2 );");

            rectangle2 = new Rectangle(10, 10, Paint.valueOf("#27304f"));
            rectangle2.setStyle("-fx-effect: innershadow( three-pass-box , #425080 , 6, 0.0 , 0 , 2 );");

            rectangle4 = new Rectangle(10, 10, Paint.valueOf("#91aeab"));
            rectangle4.setStyle("-fx-effect: innershadow( three-pass-box , #27304f , 6, 0.0 , 0 , 2 );");

            rectangle3 = new Rectangle(10, 10, Paint.valueOf("#27304f"));
            rectangle3.setStyle("-fx-effect: innershadow( three-pass-box , #425080 , 6, 0.0 , 0 , 2 );");
            //.................................................................//
            TranslateTransition translateTransition1 = new TranslateTransition();
            setDuration_node_reverse_cycle(translateTransition1, rectangle1);
            TranslateTransition translateTransition2 = new TranslateTransition();
            setDuration_node_reverse_cycle(translateTransition2, rectangle2);
            TranslateTransition translateTransition3 = new TranslateTransition();
            setDuration_node_reverse_cycle(translateTransition3, rectangle3);
            TranslateTransition translateTransition4 = new TranslateTransition();
            setDuration_node_reverse_cycle(translateTransition4, rectangle4);
            //.................................................................//
            RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(1), rectangle1);
            setAngle_cycle_reverse_node(rotateTransition1, rectangle1);
            RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(1), rectangle2);
            setAngle_cycle_reverse_node(rotateTransition2, rectangle2);
            RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(1), rectangle3);
            setAngle_cycle_reverse_node(rotateTransition3, rectangle3);
            RotateTransition rotateTransition4 = new RotateTransition(Duration.seconds(1), rectangle4);
            setAngle_cycle_reverse_node(rotateTransition4, rectangle4);
            //..........................................................................................//
            //set the location and relocation of squares
            rectangle1.setX(300);
            translateTransition1.setByX(20);
            rectangle1.setY(166);
            translateTransition1.setByY(20);

            rectangle2.setX(550);
            translateTransition2.setByX(-20);
            rectangle2.setY(166);
            translateTransition2.setByY(20);

            rectangle3.setX(300);
            translateTransition3.setByX(20);
            rectangle3.setY(286);
            translateTransition3.setByY(-20);

            rectangle4.setX(550);
            translateTransition4.setByX(-20);
            rectangle4.setY(286);
            translateTransition4.setByY(-20);
            //....................................................//
            //Playing the animation
            translateTransition1.play();
            rotateTransition1.play();
            translateTransition2.play();
            rotateTransition2.play();
            translateTransition3.play();
            rotateTransition3.play();
            translateTransition4.play();
            rotateTransition4.play();
            //.................................................//
            //show the animation
            beginningPane.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4);
            System.out.println("welcome lord " + nameField.getText() + " !");
            System.out.println("I hope you enjoy the game!\n");
        }
    }

    //to pause and resume the sound
    @FXML
    public void muteButton() {
        if (countVolume % 2 == 0) {
            menuMediaPlayer.setMute(true);
            mouseEnteredPattern(mute1);
            mouseEnteredPattern_(mute2);
            mouseEnteredPattern(mute3);
            changeMuteButton(mute1, 2);
            changeMuteButton(mute2, 2);
            changeMuteButton(mute3, 2);

        } else {
            menuMediaPlayer.setMute(false);
            mouseExitPattern(mute1);
            mouseExitPattern_(mute2);
            mouseExitPattern(mute3);
            changeMuteButton(mute1, 1);
            changeMuteButton(mute2, 1);
            changeMuteButton(mute3, 1);
        }
        countVolume++;
    }

    @FXML
    public void muteGameButton() {
        if (gameCountVolume % 2 == 0) {
            gameMediaPlayer.setMute(true);
            mouseEnteredPattern(mute);
            changeMuteButton(mute, 2);

        } else {
            gameMediaPlayer.setMute(false);
            changeMuteButton(mute, 1);
            mouseExitPattern(mute);
        }
        gameCountVolume++;
    }


    //when mouse perches on the button, change the color of button
    @FXML
    public void mouseEnteredStart() {
        fadeButton(start);
        mouseEnteredPattern(start);
    }

    @FXML
    public void mouseEnteredGuide() {
        fadeButton(guide);
        mouseEnteredPattern(guide);
    }

    @FXML
    public void mouseEnteredPlay() {
        fadeButton(play);
        mouseEnteredPattern(play);
    }

    @FXML
    public void mouseEnteredExit() {
        fadeButton(exit);
        mouseEnteredPattern_(exit);
    }

    @FXML
    public void mouseEnteredGoToMenu() {
        fadeButton(goToMenu);
        mouseEnteredPattern_(goToMenu);
    }


    //reset to normal form when mouse exit from button
    @FXML
    public void mouseExitStart() {
        mouseExitPattern(start);
    }

    @FXML
    public void mouseExitGuide() {
        mouseExitPattern(guide);
    }

    @FXML
    public void mouseExitPlay() {
        mouseExitPattern(play);
    }

    @FXML
    public void mouseExitExit() {
        mouseExitPattern_(exit);
    }

    @FXML
    public void mouseExitGoToMenu() {
        mouseExitPattern_(goToMenu);
    }


    //quit program after click exit button
    @FXML
    public void quitProgram() throws IOException, InterruptedException {
        System.exit(0);
    }

    //show the third part of menu after click on menu button
    @FXML
    public void menuOpen() {
        beginningPane.getChildren().removeAll(rectangle1, rectangle2, rectangle3, rectangle4);
        mute2.setVisible(false);
        beginningPane.getChildren().removeAll(nameLabel, labelWelcome, secondImage, goToMenu, exit);

    }

    //some information about game
    private Button next = new Button("Next");
    private Button back = new Button("back");
    private Image guideImage = new Image(photo + "guide1.png");

    @FXML
    public void guide() {
        AnchorPane guideRoot = new AnchorPane();
        ImageView guideImageView = new ImageView(guideImage);
        guideImageView.setFitHeight(700);
        guideImageView.setFitWidth(610);
        next.setId("orange_notClicked_font20");
        next.setLayoutX(470);
        next.setLayoutY(620);

        next.setOnAction(event -> {
            guideImage = new Image(photo + "guide2.png");
            guideImageView.setImage(guideImage);
            guideRoot.getChildren().remove(next);
        });
        next.setOnMouseEntered(event -> {
            mouseEnteredPattern(next);
        });
        next.setOnMouseExited(event -> {
            mouseExitPattern(next);
        });

        back.setId("orange_notClicked_font20");
        back.setLayoutX(470);
        back.setLayoutY(620);

        back.setOnAction(event -> {
            back.getScene().getWindow().hide();
        });
        back.setOnMouseEntered(event -> {
            mouseEnteredPattern(back);
        });
        back.setOnMouseExited(event -> {
            mouseExitPattern(back);
        });

        guideRoot.getChildren().addAll(guideImageView, back, next);
        guideRoot.getStylesheets().add(Controller.class.getResource("styles.css").toExternalForm());

        Stage stage = new Stage();
        stage.getIcons().add(new Image(logo));
        stage.setTitle("ChessBattle");
        stage.setScene(new Scene(guideRoot, 600, 700));
        stage.setResizable(false);
        stage.show();
    }

    /************************************************game play*********************************************************/
    private String[] randomSentences = {"Good shot!", "Nice!", "My grandma plays better than you!", "Not a chance!",
            "Wow!", "Good job!", "Call your mom for help!", "You're the best!", "Ha-ha-ha!", "You'll win!", "You hit it!Yeah!",
            "Missed it!", "You are a great player!", "Never give up!", "Let me win!", "Here we go again!", "OMG!",
            "Go play with your dolls!", "Who is the best?", "Defeat me if you can!", "Shame on you!", "That's not your job!",
            "Lucky!", "You are crazy!", "Do you have brain?!", "What a Noob player!", "There is nobody can defeat you!",
            "I can see your weakness!", "Are you scared?",
    };

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    private int piecesToPlace = 11;
    private boolean enemyTurn = false;
    private Random random = new Random();
    private boolean pieceBought = false;
    private Goal goal = null;
    private boolean randomArrange = false;

    //pieces information for attack
    private King king = new King();
    private Rook rook = new Rook();
    private Knight knight = new Knight();
    private Pawn pawn = new Pawn();


    private int playerAttackHeight = pawn.getPowerHeight();
    private int playerAttackWidth = pawn.getPowerWidth();
    public static int playerMoney = 0;
    public int playerTurnCounter = 0;
    private Label report = new Label();
    private Label money = new Label();
    private Button mute;

    //choose a bloc and shoot it
    private void enemyMove() {
        int x;
        int y;
        while (enemyTurn) {
            int enemyAttackWidth;
            int enemyAttackHeight;

            //choose attacker piece
            if ((king.enemyCounter == 0 || king.enemyCounter >= king.getPeriod()) && enemyBoard.kingCount > 0) {
                enemyAttackHeight = king.getPowerHeight();
                enemyAttackWidth = king.getPowerWidth();
                king.enemyCounter = 0;
            } else if ((rook.enemyCounter == 0 || rook.enemyCounter >= rook.getPeriod()) && enemyBoard.rookCount > 0) {
                enemyAttackWidth = rook.getPowerWidth();
                enemyAttackHeight = rook.getPowerHeight();
                rook.enemyCounter = 0;
            } else if ((knight.enemyCounter == 0 || knight.enemyCounter >= knight.getPeriod()) && enemyBoard.knightCount > 0) {
                enemyAttackHeight = knight.getPowerHeight();
                enemyAttackWidth = knight.getPowerWidth();
                knight.enemyCounter = 0;
            } else if (enemyBoard.pawnCount < 0 && enemyBoard.knightCount < 0 &&
                    enemyBoard.rookCount < 0 && enemyBoard.kingCount < 0) {
                return;
            } else {
                enemyAttackHeight = 1;
                enemyAttackWidth = 1;
            }

            //shoot the blocs around the bloc which belonged to a piece
//            if (!goal.getPiece().isAlive())goal=null;
            if (goal != null) {
                if (goal.getRight() != null && !goal.getRight().isDead()) {
                    x = goal.getRight().getBlocX();
                    y = goal.getRight().getBlocY();

                } else if (goal.getLeft() != null && !goal.getLeft().isDead()) {
                    x = goal.getLeft().getBlocX();
                    y = goal.getLeft().getBlocY();

                } else if (goal.getUp() != null && !goal.getUp().isDead()) {
                    x = goal.getUp().getBlocX();
                    y = goal.getUp().getBlocY();

                } else if (goal.getDown() != null && !goal.getDown().isDead()) {
                    x = goal.getDown().getBlocX();
                    y = goal.getDown().getBlocY();

                } else if (goal.getRight_up() != null && !goal.getRight_up().isDead()) {
                    x = goal.getRight_up().getBlocX();
                    y = goal.getRight_up().getBlocY();

                } else if (goal.getRight_down() != null && !goal.getRight_down().isDead()) {
                    x = goal.getRight_down().getBlocX();
                    y = goal.getRight_down().getBlocY();

                } else if (goal.getLeft_up() != null && !goal.getLeft_up().isDead()) {
                    x = goal.getLeft_up().getBlocX();
                    y = goal.getLeft_up().getBlocY();

                } else if (goal.getLeft_down() != null && !goal.getLeft_down().isDead()) {
                    x = goal.getLeft_down().getBlocX();
                    y = goal.getLeft_down().getBlocY();

                } else {
                    goal = null;
                    x = random.nextInt(size - enemyAttackHeight + 1);
                    y = random.nextInt(size - enemyAttackWidth + 1);
                }
            } else {
                x = random.nextInt(size - enemyAttackHeight + 1);
                y = random.nextInt(size - enemyAttackWidth + 1);
            }

            Bloc[][] blocs = new Bloc[enemyAttackHeight][enemyAttackWidth];
            blocs[0][0] = playerBoard.getBloc(x, y);

            if (blocs[0][0].isDead())
                continue;

            //in case bloc belongs to a ship, there is another turn
            enemyTurn = blocs[0][0].shoot();

            if (enemyTurn) {
                goal = new Goal(x, y, playerBoard);
            }

            //shoot a rectangle of blocs with enemyWidth and enemyHeight sides
            for (int i = 0; i < enemyAttackHeight; i++) {
                for (int j = 0; j < enemyAttackWidth; j++) {
                    if (i + j == 0 || i + x >= size || j + y >= size) continue;
                    blocs[i][j] = playerBoard.getBloc(x + i, y + j);
                    if (blocs[i][j].isDead()) continue;
                    if (blocs[i][j].shoot() && goal == null)
                        goal = new Goal(x, y, playerBoard);
                }
            }
            if (playerBoard.pieceCount == 0) report.setText("YOU LOSE! click any bloc in enemy board to exit!");
            knight.enemyCounter++;
            rook.enemyCounter++;
            king.enemyCounter++;

            System.out.println("\n# enemy information:\n" + "king count = " + enemyBoard.kingCount);
            System.out.println("rook count = " + enemyBoard.rookCount);
            System.out.println("knight count = " + enemyBoard.knightCount);
            System.out.println("pawn count = " + enemyBoard.pawnCount);
            System.out.println("piece count = " + enemyBoard.pieceCount);
            System.out.println("enemy shot bloc in row " + x + " and column " + y);
        }
    }

    //place enemy ships
    private void startGame() {
        report.setText("wait for your opponent...");
        pawn.button.setDisable(false);
        pawn.button.setOpacity(0.9);
        knight.button.setDisable(false);
        knight.button.setOpacity(0.9);
        rook.button.setDisable(false);
        rook.button.setOpacity(0.9);
        king.button.setDisable(false);
        king.button.setOpacity(0.9);

        int pieceCount = 11;

        while (pieceCount > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placePiece(new Piece(pieceCount, Math.random() < 0.5, true), x, y)) {
                pieceCount--;
            }
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), ae -> {
            //start the game
            running = true;
            report.setText("start the game!");
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    //after start the game
    private Parent gameDisplay() {
        BorderPane root = new BorderPane();

        //set root background
        {
            root.setPrefSize(1500, 800);
            Image GameImage = new Image(photo + "38.jpg");
            ImageView imageViewGame = new ImageView(GameImage);
            imageViewGame.setFitWidth(1500);
            imageViewGame.setFitHeight(810);
            root.getChildren().add(imageViewGame);
        }

        //mute button
        {
            mute = new Button("Mute");
            mute.setAlignment(Pos.CENTER_RIGHT);
            mute.setId("orange_notClicked_font20");
            changeMuteButton(mute, 1);
            mute.setOnAction(event -> muteGameButton());
        }

        //random arrange
        Button arrange = new Button("random arrange");
        ImageView imageViewArrange = new ImageView(new Image(photo + "RandomArrange.png"));
        imageViewArrange.setFitHeight(30);
        imageViewArrange.setFitWidth(30);
        arrange.setGraphic(imageViewArrange);
        arrange.setId("orange_notClicked_font15");
        arrange.setOnAction(event -> {
            arrange.setDisable(true);
            randomArrange = true;

            int pieceCount = 11;
            while (pieceCount > 0) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);

                if (playerBoard.placePiece(new Piece(pieceCount, Math.random() < 0.5, false), x, y)) {
                    if (--pieceCount == 0) {
                        startGame();
                    }
                }
            }
        });

        //attack column in right
        VBox attackColumn = new VBox();
        {
            Label attack = new Label();
            attackColumn.setSpacing(20);
            attackColumn.setPadding(new Insets(10, 20, 20, 0));
            attack.setText("Choose a piece\n to attack:");
            attack.setAlignment(Pos.CENTER);
            attack.setPadding(new Insets(0, 0, 0, 0));
            attack.setFont(Font.font("chiller", 30));
            attack.setTextAlignment(TextAlignment.CENTER);
            attack.setTextFill(Color.BLACK);
            attack.setOpacity(0.5);

            //before start the game, attack buttons are disable
            pawn.button.setDisable(true);
            pawn.button.setOpacity(0.5);
            knight.button.setDisable(true);
            knight.button.setOpacity(0.5);
            rook.button.setDisable(true);
            rook.button.setOpacity(0.5);
            king.button.setDisable(true);
            king.button.setOpacity(0.5);

            //set attack button actions
            pawn.button.setOnAction(event -> {
                playerAttackHeight = 1;
                playerAttackWidth = 1;
                pawn.button.setId("shiny-darkOrange");
                report.setText("Your click destroys 1 bloc");
            });
            knight.button.setOnAction(event -> {
                playerAttackHeight = knight.getPowerHeight();
                playerAttackWidth = knight.getPowerWidth();
                knight.playerCounter = 0;
                knight.button.setDisable(true);
                knight.button.setOpacity(0.5);
                pawn.button.setId("orange_notClicked_font20");
                report.setText("Your click destroys 1*2 blocs");
            });
            rook.button.setOnAction(event -> {
                playerAttackHeight = rook.getPowerHeight();
                playerAttackWidth = rook.getPowerWidth();
                rook.playerCounter = 0;
                rook.button.setDisable(true);
                rook.button.setOpacity(0.5);
                pawn.button.setId("orange_notClicked_font20");
                report.setText("Your click destroys 3*2 blocs");
            });
            king.button.setOnAction(event -> {
                playerAttackHeight = king.getPowerHeight();
                playerAttackWidth = king.getPowerWidth();
                king.playerCounter = 0;
                king.button.setDisable(true);
                king.button.setOpacity(0.5);
                pawn.button.setId("orange_notClicked_font20");
                report.setText("Your click destroys 2*4 blocs");
            });
            attackColumn.getChildren().addAll(arrange, attack, pawn.button, knight.button, rook.button, king.button);
        }

        //bottom part
        HBox bottom = new HBox();
        {
            report.setText("Arrange your pieces!");
            setLabelStyles(report);
            report.setFont(Font.font("chiller", 30));
            report.setPrefWidth(500);
            report.setOpacity(0.3);
            bottom.setPadding(new Insets(10, 300, 10, 300));
            bottom.getChildren().add(report);
        }

        //buy pieces in left
        VBox buyColumn = new VBox();
        Label buy = new Label();
        Button buyPawn = new Button();
        Button buyKnight = new Button();
        Button buyRook = new Button();
        Button buyKing = new Button();
        {
            buyColumn.setSpacing(10);
            buyColumn.setPadding(new Insets(0, 20, 0, 20));
            buy.setPadding(new Insets(0, 0, 20, 0));

            money.setText(Integer.toString(playerMoney));
            setLabelStyles(money);
            money.setFont(Font.font("chiller", 23));
            money.setOpacity(0.5);
            money.setPrefWidth(100);

            buy.setText("You can buy just\n one piece!");
            buy.setFont(Font.font("chiller", 30));
            buy.setTextAlignment(TextAlignment.CENTER);
            buy.setAlignment(Pos.CENTER);
            buy.setTextFill(Color.BLACK);
            buy.setOpacity(0.5);


            TextField row = new TextField();
            row.setPromptText("enter the row");
            row.setStyle("-fx-background-color:black;    " +
                    "    -fx-background-radius: 30;" +
                    "    -fx-background-insets: 0,1,2,3,0;" +
                    "    -fx-padding: 10 20 10 20;");
            row.setPrefWidth(30);
            row.setOpacity(0.5);

            TextField column = new TextField();
            column.setPromptText("enter the column");
            column.setStyle("-fx-background-color:black;    " +
                    "    -fx-background-radius: 30;" +
                    "    -fx-background-insets: 0,1,2,3,0;" +
                    "    -fx-padding: 10 20 10 20;");
            column.setPrefWidth(30);
            column.setOpacity(0.5);

            buyPawn.setText("New pawn");
            setPieceButton("pawn.png", buyPawn);
            buyKnight.setText("New knight");
            setPieceButton("knight.png", buyKnight);
            buyRook.setText("New rook");
            setPieceButton("rook.png", buyRook);
            buyKing.setText("New king");
            setPieceButton("king.png", buyKing);

            buyColumn.getChildren().addAll(buy, row, column, buyPawn, buyKnight, buyRook, buyKing);

            buyPawn.setOnAction(event -> {
                Bloc bloc = (Bloc) playerBoard.getBloc(Integer.parseInt(column.getText()), Integer.parseInt(row.getText()));
                if (playerBoard.rookCount >= 1) {
                    if (500 <= playerMoney) {
                        if (playerBoard.placePiece(new Piece(1, false, false), bloc.getBlocX(), bloc.getBlocY())) {
                            playerBoard.pieceCount++;
                            report.setText("It's done!You can't buy piece anymore!");
                            pieceBought = true;

                            buyPawn.setDisable(true);
                            buyPawn.setOpacity(0.5);

                            buyKnight.setDisable(true);
                            buyKnight.setOpacity(0.5);

                            buyRook.setDisable(true);
                            buyRook.setOpacity(0.5);

                            buyKing.setDisable(true);
                            buyKing.setOpacity(0.5);

                        } else {
                            report.setText("You can't put a pawn here!");
                            return;
                        }
                    } else {
                        report.setText("You don't have enough money!");
                        return;
                    }
                } else {
                    report.setText("You lost your rooks!");
                    return;
                }


                boolean isPossible = false;
                for (int i = 0; i < size; i++)
                    for (int j = 0; j < size; j++) {
                        if (enemyBoard.isValidPoint(i, j) && !enemyBoard.getBloc(i, j).isDead()) isPossible = true;
                    }

                if (!isPossible) {
                    report.setText("Enemy doesn't have enough space!");
                    return;
                }

                boolean done = false;
                do {
                    int x = random.nextInt(size);
                    int y = random.nextInt(size);
                    if (enemyBoard.placePiece(new Piece(1, false, true), x, y)) {
                        enemyBoard.pieceCount++;
                        done = true;
                    }
                } while (!done);
            });
            buyKnight.setOnAction(event -> {
                Bloc bloc = (Bloc) playerBoard.getBloc(Integer.parseInt(column.getText()), Integer.parseInt(row.getText()));
                if (playerBoard.rookCount >= 1) {
                    if (1000 <= playerMoney) {
                        if (playerBoard.placePiece(new Piece(6, false, false), bloc.getBlocX(), bloc.getBlocY())) {
                            playerBoard.pieceCount++;
                            report.setText("It's done!You can't buy piece anymore!");
                            pieceBought = true;

                            buyPawn.setDisable(true);
                            buyPawn.setOpacity(0.5);

                            buyKnight.setDisable(true);
                            buyKnight.setOpacity(0.5);

                            buyRook.setDisable(true);
                            buyRook.setOpacity(0.5);

                            buyKing.setDisable(true);
                            buyKing.setOpacity(0.5);

                        } else {
                            report.setText("You can't put a knight here!");
                            return;
                        }
                    } else {
                        report.setText("You don't have enough money!");
                        return;
                    }
                } else {
                    report.setText("You lost your rooks!");
                    return;
                }

                boolean isPossible = false;
                int counter = 0;
                for (int i = 0; i < size; i++)
                    for (int j = 0; j < size - 1; j++) {
                        if (enemyBoard.isValidPoint(i, j) && !enemyBoard.getBloc(i, j).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i + 1, j) && !enemyBoard.getBloc(i + 1, j).isDead()) counter++;

                        if (counter == 4) isPossible = true;
                    }

                if (!isPossible) {
                    report.setText("Enemy doesn't have enough space!");
                    return;
                }

                boolean done = false;
                do {
                    int x = random.nextInt(size);
                    int y = random.nextInt(size - 1);
                    if (enemyBoard.placePiece(new Piece(6, false, true), x, y)) {
                        enemyBoard.pieceCount++;
                        done = true;
                    }
                } while (!done);
            });
            buyRook.setOnAction(event -> {
                Bloc bloc = (Bloc) playerBoard.getBloc(Integer.parseInt(column.getText()), Integer.parseInt(row.getText()));
                if (playerBoard.rookCount >= 1) {
                    if (2000 <= playerMoney) {
                        if (playerBoard.placePiece(new Piece(9, false, false), bloc.getBlocX(), bloc.getBlocY())) {
                            playerBoard.pieceCount++;
                            report.setText("It's done!You can't buy piece anymore!");
                            pieceBought = true;

                            buyPawn.setDisable(true);
                            buyPawn.setOpacity(0.5);

                            buyKnight.setDisable(true);
                            buyKnight.setOpacity(0.5);

                            buyRook.setDisable(true);
                            buyRook.setOpacity(0.5);

                            buyKing.setDisable(true);
                            buyKing.setOpacity(0.5);

                        } else {
                            report.setText("You can't put a rook here!");
                            return;
                        }
                    } else {
                        report.setText("You don't have enough money!");
                        return;
                    }
                } else {
                    report.setText("You lost your rooks!");
                    return;
                }

                boolean isPossible = false;
                int counter = 0;
                for (int i = 0; i < size - 1; i++)
                    for (int j = 0; j < size - 1; j++) {
                        if (enemyBoard.isValidPoint(i, j) && !enemyBoard.getBloc(i, j).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i, j + 1) && !enemyBoard.getBloc(i, j + 1).isDead()) counter++;

                        if (enemyBoard.isValidPoint(i + 1, j) && !enemyBoard.getBloc(i + 1, j).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i + 1, j + 1) && !enemyBoard.getBloc(i + 1, j + 1).isDead())
                            counter++;

                        if (counter == 4) isPossible = true;
                    }

                if (!isPossible) {
                    report.setText("Enemy doesn't have enough space!");
                    return;
                }

                boolean done = false;
                do {
                    int x = random.nextInt(size - 1);
                    int y = random.nextInt(size - 1);
                    if (enemyBoard.placePiece(new Piece(9, false, true), x, y)) {
                        enemyBoard.pieceCount++;
                        done = true;
                    }
                } while (!done);
            });
            buyKing.setOnAction(event -> {
                Bloc bloc = (Bloc) playerBoard.getBloc(Integer.parseInt(column.getText()) - 1, Integer.parseInt(row.getText()) - 1);
                if (playerBoard.rookCount >= 1) {
                    if (3000 <= playerMoney) {
                        if (playerBoard.placePiece(new Piece(11, false, false), bloc.getBlocX(), bloc.getBlocY())) {
                            playerBoard.pieceCount++;
                            report.setText("It's done!You can't buy piece anymore!");
                            pieceBought = true;

                            buyPawn.setDisable(true);
                            buyPawn.setOpacity(0.5);

                            buyKnight.setDisable(true);
                            buyKnight.setOpacity(0.5);

                            buyRook.setDisable(true);
                            buyRook.setOpacity(0.5);

                            buyKing.setDisable(true);
                            buyKing.setOpacity(0.5);

                        } else {
                            report.setText("You can't put a king here!");
                            return;
                        }
                    } else {
                        report.setText("You don't have enough money!");
                        return;
                    }
                } else {
                    report.setText("You lost your rooks!");
                    return;
                }


                boolean isPossible = false;
                int counter = 0;
                for (int i = 0; i < size - 2; i++)
                    for (int j = 0; j < size - 2; j++) {

                        if (enemyBoard.isValidPoint(i - 1, j - 1) && !enemyBoard.getBloc(i - 1, j - 1).isDead())
                            counter++;
                        if (enemyBoard.isValidPoint(i - 1, j) && !enemyBoard.getBloc(i - 1, j).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i - 1, j + 1) && !enemyBoard.getBloc(i - 1, j + 1).isDead())
                            counter++;

                        if (enemyBoard.isValidPoint(i, j - 1) && !enemyBoard.getBloc(i, j - 1).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i, j) && !enemyBoard.getBloc(i, j).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i, j + 1) && !enemyBoard.getBloc(i, j + 1).isDead()) counter++;

                        if (enemyBoard.isValidPoint(i + 1, j - 1) && !enemyBoard.getBloc(i + 1, j - 1).isDead())
                            counter++;
                        if (enemyBoard.isValidPoint(i + 1, j) && !enemyBoard.getBloc(i + 1, j).isDead()) counter++;
                        if (enemyBoard.isValidPoint(i + 1, j + 1) && !enemyBoard.getBloc(i + 1, j + 1).isDead())
                            counter++;

                        if (counter == 9) isPossible = true;
                    }

                if (!isPossible) {
                    report.setText("Enemy doesn't have enough space!");
                    return;
                }

                boolean done = false;
                do {
                    int x = random.nextInt(size - 2);
                    int y = random.nextInt(size - 2);
                    if (enemyBoard.placePiece(new Piece(11, false, true), x, y)) {
                        enemyBoard.pieceCount++;
                        done = true;
                    }
                } while (!done);
            });
        }

        //top part
        HBox top = new HBox();
        Label sentences = new Label();
        {
            sentences.setFont(Font.font("chiller", 36));
            sentences.setPrefWidth(430);
            sentences.setOpacity(0.5);
            setLabelStyles(sentences);
            top.setSpacing(255);
            top.setPadding(new Insets(10, 40, 0, 60));
            top.getChildren().addAll(money, sentences, mute);
        }

        //boards
        enemyBoard = new Board(true, event -> {

            report.setText("Continue the game!");
            //after start the game, do this
            if (!running) return;

            //losing you and winning enemy
            if (playerBoard.pieceCount == 0) {
                report.setText("You lose!");
                gameMediaPlayer.stop();
                playSound(loseMediaPlayer);
                AnchorPane root1 = new AnchorPane();
                String dir = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\sample\\photos\\lose.gif";
                Image image = new Image(dir);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(510);
                imageView.setFitWidth(810);
                root1.getChildren().add(imageView);

                enemyBoard.getScene().getWindow().hide();
                stage.setTitle("ChessBattle");
                stage.setScene(new Scene(root1, 800, 500));
                stage.getIcons().add(new Image(logo));
                stage.setResizable(false);
                stage.show();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(9300), ae -> {
                    System.out.println("YOU LOSE");
                    System.out.println("GOOD PLAY!");
                    System.exit(0);
                }));
                timeline.play();
            }

            //handle attack buttons
            {
                //check if the attacker piece is alive
                if (playerBoard.kingCount < 0) {
                    king.button.setDisable(true);
                    king.button.setOpacity(0.5);
                }
                if (playerBoard.rookCount < 0) {
                    rook.button.setDisable(true);
                    rook.button.setOpacity(0.5);
                }
                if (playerBoard.knightCount < 0) {
                    knight.button.setDisable(true);
                    knight.button.setOpacity(0.5);
                }
                if (playerBoard.pawnCount < 0) {
                    pawn.button.setId("orange_notClicked_font20");
                    pawn.button.setDisable(true);
                    pawn.button.setOpacity(0.5);
                }

                //if are pieces are not valid, skip a turn
                if (pawn.button.isDisabled() && knight.button.isDisabled()
                        && rook.button.isDisabled() && king.button.isDisabled()) return;

                Bloc[][] blocs = new Bloc[playerAttackHeight][playerAttackWidth];
                blocs[0][0] = (Bloc) event.getSource();
                int x = blocs[0][0].getBlocX();
                int y = blocs[0][0].getBlocY();
                if (blocs[0][0].isDead()) return;

                System.out.println("\n# your information:\n" + "king count = " + playerBoard.kingCount);
                System.out.println("rook count = " + playerBoard.rookCount);
                System.out.println("knight count = " + playerBoard.knightCount);
                System.out.println("pawn count = " + playerBoard.pawnCount);
                System.out.println("piece count = " + playerBoard.pieceCount);
                System.out.println("you shot bloc in row " + x + " and column " + y);

                enemyTurn = !blocs[0][0].shoot();

                //shoot a rectangle of blocs with playerWidth and playerHeight sides
                for (int i = 0; i < playerAttackHeight; i++) {
                    for (int j = 0; j < playerAttackWidth; j++) {
                        if (i + j == 0 || i + x >= size || j + y >= size) continue;
                        blocs[i][j] = enemyBoard.getBloc(i + x, j + y);
                        if (blocs[i][j].isDead()) continue;
                        blocs[i][j].shoot();
                    }
                }

                king.playerCounter++;
                rook.playerCounter++;
                knight.playerCounter++;
                playerAttackHeight = playerAttackWidth = 1;
                pawn.button.setId("shiny-darkOrange");

                //recovery periods
                if ((knight.playerCounter % (knight.getPeriod() + 1) == 0) && playerBoard.knightCount > 0) {
                    knight.button.setDisable(false);
                    knight.button.setOpacity(0.9);
                }
                if ((rook.playerCounter % (rook.getPeriod() + 1) == 0) && playerBoard.rookCount > 0) {
                    rook.button.setDisable(false);
                    rook.button.setOpacity(0.9);
                }
                if ((king.playerCounter % (king.getPeriod() + 1) == 0) && playerBoard.kingCount > 0) {
                    king.button.setDisable(false);
                    king.button.setOpacity(0.9);
                }
            }

            money.setText(Integer.toString(playerMoney) + " $");
            if (pieceBought) {
                buyPawn.setDisable(true);
                buyPawn.setOpacity(0.5);
                buyKnight.setDisable(true);
                buyKnight.setOpacity(0.5);
                buyRook.setDisable(true);
                buyRook.setOpacity(0.5);
                buyKing.setDisable(true);
                buyKing.setOpacity(0.5);
            } else {
                if (playerMoney >= 500) {
                    buyPawn.setDisable(false);
                    buyPawn.setOpacity(0.9);
                }
                if (playerMoney >= 1000) {
                    buyKnight.setDisable(false);
                    buyKnight.setOpacity(0.9);
                }
                if (playerMoney >= 2000) {
                    buyRook.setDisable(false);
                    buyRook.setOpacity(0.9);
                }
                if (playerMoney >= 3000) {
                    buyKing.setDisable(false);
                    buyKing.setOpacity(0.9);
                }
            }

            //random sentences
            int number = random.nextInt(randomSentences.length - 1);
            {
                if (playerTurnCounter % 5 == 0) {
                    sentences.setText(randomSentences[number]);
                }
            }

            //loosing enemy and winning you!
            if (enemyBoard.pieceCount == 0) {
                AnchorPane root1 = new AnchorPane();
                String dir = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/") + "\\src\\sample\\photos\\win.gif";
                Image image = new Image(dir);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(510);
                imageView.setFitWidth(810);

                stage.getIcons().add(new Image(logo));
                stage.setTitle("ChessBattle");
                stage.setScene(new Scene(root1, 800, 500));
                stage.setResizable(false);

                Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(5000), ae -> {
                    menuMediaPlayer.stop();
                    playSound(winMediaPlayer);
                    enemyBoard.getScene().getWindow().hide();
                    stage.show();
                }));
                timeline1.play();

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(9500), ae -> {

                    System.out.println("YOU WIN!");
                    System.out.println("GOOD PLAY!");
                    System.exit(0);
                }));
                timeline.play();
            }
            playerTurnCounter++;

            if (enemyTurn)
                enemyMove();
        });
        playerBoard = new Board(false, event -> {
            //update money label
            money.setText(Integer.toString(playerMoney));

            //before start the game, do this
            if (running)
                return;

            if (randomArrange)
                return;

            Bloc bloc = (Bloc) event.getSource();
            if (playerBoard.placePiece(new Piece(piecesToPlace, event.getButton().equals(MouseButton.PRIMARY), false), bloc.getBlocX(), bloc.getBlocY())) {
                if (--piecesToPlace == 0) {
                    startGame();
                }
            }
        });
        HBox boards = new HBox(30, enemyBoard, playerBoard);
        boards.setPadding(new Insets(10, 10, 10, 10));

        root.getStylesheets().add(Controller.class.getResource("style/styles.css").toExternalForm());
        root.setCenter(boards);
        boards.setAlignment(Pos.CENTER);

        root.setRight(attackColumn);
        attackColumn.setAlignment(Pos.CENTER);

        root.setTop(top);
        top.setAlignment(Pos.CENTER);

        root.setLeft(buyColumn);
        buyColumn.setAlignment(Pos.CENTER);

        root.setBottom(bottom);
        bottom.setAlignment(Pos.CENTER);

        return root;
    }

    //click play button and go to game page
    public void playGame() {
        System.out.println("Please wait...");
        AnchorPane loading = new AnchorPane();
        Image background = new Image(photo + "38.jpg");
        ImageView backgroundView = new ImageView(background);
        backgroundView.setFitHeight(810);
        backgroundView.setFitWidth(1510);

        Image loadingImage = new Image(photo + "2.gif");
        ImageView loadingView = new ImageView(loadingImage);
        loadingView.setFitWidth(1410);
        loadingView.setFitHeight(810);
        loadingView.setOpacity(0.5);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(810);
        rectangle.setHeight(1300);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.5);

        Label label = new Label("L O A D I N G . . .");
        label.setFont(Font.font("chiller", 36));
        label.setTextFill(Color.DARKGRAY);
        label.setOpacity(0.5);
        label.setLayoutX(300);
        label.setLayoutY(330);

        loading.getChildren().addAll(backgroundView, loadingView);
        beginningPane.getChildren().removeAll(firstImage2, GameInformation, play, guide, mute3, GameName);
        beginningPane.getChildren().add(loading);

        menuMediaPlayer.stop();
        playSound(gameMediaPlayer);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), ae -> {

            loading.getScene().getWindow().hide();
            Stage gameStage = new Stage();
            gameStage.setScene(new Scene(gameDisplay(), 1400, 700));
            gameStage.getIcons().add(new Image(logo));
            gameStage.setTitle("ChessBattle");
            gameStage.setResizable(false);
            gameStage.show();

        }));
        timeline.play();

    }

    /**********************************************to summarize the code***********************************************/
    //play the music
    public void playSound(MediaPlayer mediaPlayer) {
        mediaPlayer.setCycleCount(1000);
        mediaPlayer.play();
    }

    //Setting change of angle of squares, count of cycle of rotation and auto reverse value
    public void setAngle_cycle_reverse_node(RotateTransition rotateTransition, Rectangle rectangle) {
        rotateTransition.setNode(rectangle);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(100);
        rotateTransition.setAutoReverse(true);
    }

    //set duration, node, auto reverse and cycle count of square translation
    public void setDuration_node_reverse_cycle(TranslateTransition translateTransition, Rectangle rectangle) {
        translateTransition.setNode(rectangle);
        translateTransition.setCycleCount(100);
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setAutoReverse(true);
    }

    //button effect before click
    @FXML
    public void mouseEnteredPattern(Button button) {
        button.setStyle("    -fx-background-color:\n" +
                "            linear-gradient(#ddba4f, #b08400),\n" +
                "            linear-gradient(#ffe969, #ddba4f),\n" +
                "            linear-gradient(#ddba4f, #e89609),\n" +
                "            linear-gradient(#ddba4f 0%, #e89609 50%, #eea10b 100%),\n" +
                "            linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-padding: 10 20 10 20;");
    }

    @FXML
    public void mouseEnteredPattern_(Button button) {
        button.setStyle("        -fx-background-color:\n" +
                "     linear-gradient(#91aeab, #425080),\n" +
                "     linear-gradient(#425080 0%, #425080 50%, #91aeab 100%)," +
                "            linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #ffff;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-padding: 10 20 10 20;");
    }

    @FXML
    public void mouseExitPattern(Button button) {
        button.setStyle("    -fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #b08400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-padding: 10 20 10 20;");
    }

    @FXML
    public void mouseExitPattern_(Button button) {
        button.setStyle("     -fx-background-color:\n" +
                "            linear-gradient(#6b8b88, #27304f),\n" +
                "            linear-gradient(#27304f 0%, #27304f 50%, #6b8b88 100%)," +
                "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #ffff;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-padding: 10 20 10 20;");
    }

    //change button photo after mute music
    public void changeMuteButton(Button button, int Style) {
        String dir;
        if (Style == 1) {
            dir = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/")
                    + "\\src\\main\\java\\com\\example\\chessbattle\\media\\photos\\muteoff.png";
        } else {
            dir = "file:" + System.getProperty("user.dir").replaceAll("\\\\", "/")
                    + "\\src\\main\\java\\com\\example\\chessbattle\\media\\photos\\muteon.png";
        }
        Image image = new Image(dir);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        button.setGraphic(imageView);
    }

    //fade button after click
    public void fadeButton(Button button) {
        FadeTransition fadeTransition = new FadeTransition(new Duration(300), button);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        fadeTransition.play();
    }

    //set piece buttons graphic
    public void setPieceButton(String photoDir, Button button) {
        Image image = new Image(photo + photoDir);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        button.setGraphic(imageView);
        button.setId("orange_notClicked_font15");
        button.setAlignment(Pos.CENTER);
        button.setOpacity(0.9);
    }

    //set game page label styles
    public void setLabelStyles(Label label) {
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.BOTTOM_CENTER);
        label.setTextFill(Color.BEIGE);
        label.setStyle("-fx-background-color:black;    " +
                "    -fx-background-radius: 30;" +
                "    -fx-background-insets: 0,1,2,3,0;" +
                "    -fx-padding: 10 20 10 20;");
    }
}

