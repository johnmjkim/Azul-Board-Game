package comp1110.ass2.gui;

import comp1110.ass2.Azul;
//After I got the history, the two backend become red, and the statement are not used, so I use this//
//import comp1110.ass2.backend.Azul;
//import comp1110.ass2.backend.player.Floor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 600;
    private final Group matrixBoard = new Group();

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField playerTextField;
    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     *              TASK 4
     */


    void displayState(String[] state) {
        // FIXME Task 4: implement the simple state viewer

        //get the text in State
        String playerState = state[0];
        String boardState = state[1];
        String player_state_of_A = state[1].substring(state[1].indexOf("A"), state[1].indexOf("B"));
        String player_state_of_B = state[1].substring(state[1].indexOf("B"));

        //floor Done
        String floor_A = player_state_of_A.substring(player_state_of_A.indexOf("F") + 1);
        String[] Floor_A = floor_A.split("");
        for (int i = 0; i < Floor_A.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Floor_A[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            int x = 5 + 39 * i;
            int y = 468;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }

        String floor_B = player_state_of_B.substring(player_state_of_B.indexOf("F") + 1);
        String[] Floor_B = floor_B.split("");
        for (int i = 0; i < Floor_B.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Floor_B[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            Double x = 415 + 38.5 * i;
            int y = 467;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }

        //mosaic
        String mosaic_A = player_state_of_A.substring(state[1].indexOf("M") + 1, state[1].indexOf("S"));
        String[] Mosaic_A = mosaic_A.split("");

        String alphabet_A = "";
        for (int i = 0; i < Mosaic_A.length; i += 3) {
            alphabet_A += Mosaic_A[i];
        }
        String[] Alphabet_A = alphabet_A.split("");

        String row_A = "";
        for (int i = 1; i < Mosaic_A.length; i += 3) {
            row_A += Mosaic_A[i];
        }
        String[] Row_A = row_A.split("");

        String column_A = "";
        for (int i = 2; i < Mosaic_A.length; i += 3) {
            column_A += Mosaic_A[i];
        }
        String[] Column_A = column_A.split("");

        for (int i = 0; i < Column_A.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_A[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            double X1 = Double.parseDouble(Row_A[i]);
            double Y1 = Double.parseDouble(Column_A[i]);
            Double x = 218 + 38.1 * Y1;
            Double y = 227 + 42.5 * X1;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }

        String mosaic_B = player_state_of_B.substring(state[1].indexOf("M")+ 1, state[1].indexOf("S"));
        String[] Mosaic_B = mosaic_B.split("");

        String alphabet_B = "";
        for (int i = 0; i < Mosaic_B.length; i += 3) {
            alphabet_B += Mosaic_B[i];
        }
        String[] Alphabet_B = alphabet_B.split("");

        String row_B = "";
        for (int i = 1; i < Mosaic_B.length; i += 3) {
            row_B += Mosaic_B[i];
        }
        String[] Row_B = row_B.split("");

        String column_B = "";
        for (int i = 2; i < Mosaic_B.length; i += 3) {
            column_B += Mosaic_B[i];
        }
        String[] Column_B = column_B.split("");

        for (int i = 0; i < Column_B.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_B[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            double X1 = Double.parseDouble(Row_B[i]);
            double Y1 = Double.parseDouble(Column_B[i]);
            Double x = 630 + 38.1 * Y1;
            Double y = 226 + 42.7 * X1;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }

        //the size of the tile is (35,39). x+42 Y+48(floor) one of the 7 positions
        //make position each time after the backboard made,so that we can see the tiles.

        //use "A07Me01a11d20b30b41S0a11b22c13c44d1FeeabfB08Md03b13e23c32b41S0b11c12a33d24e4Fabcced" to text in
    }


    /**
     * Create a basic text field for input and a refresh button.
     */

    // makeControls() is to make control of the Viewer.
    private void makeControls() {
        setupViewer();
        displayBoard();
    }

    // setupViewer() is to set up the Viewer and execute all "1. Game Setup" phase
    private void setupViewer() {
        Label playerLabel = new Label("Player State:");
        playerTextField = new TextField();
        playerTextField.setPrefWidth(100);
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(100);
        Button button = new Button("Refresh");
        /*
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(new String[]{playerTextField.getText(),
                        boardTextField.getText()});
            }
        });
         */
        // Use lambda expression for button
        button.setOnAction(ae -> {
            //add backboard each time to empty the tiles which has been displayed
            ImageView boardA = new ImageView(new Image("file:src/comp1110/ass2/img/empty-board.png"));
            boardA.setFitWidth(1200);
            boardA.setFitHeight(500);
            boardA.setLayoutX(0);
            boardA.setLayoutY(15);
            matrixBoard.getChildren().add(boardA);
            displayState(new String[]{playerTextField.getText(), boardTextField.getText()});
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, playerTextField, boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

    }

    ;

    // animateTile() is to show the animate of Tile.
    private void animateTile() {
        String[] _gameState = Azul.gameState;
        String _move = Azul.move;
        displayState(_gameState);
        moveTile();
    }

    ;

    // displayEnd() is to show the End of the game.
    private void displayEnd() {
        String[] _gameState = Azul.gameState;
        moveTile();
    }

    ;

    // displayBoard() is to show the Center Board and Player Board of the Game class.
    private void displayBoard() {
        Game.animateBoard();
    }

    ;

    // displayError() is to show error.
    private void displayError() {

    }

    ;

    // moveTile() is to show the move of the Tile in animate Board, Center Board and Player Board.
    private void moveTile() {

    }

    ;

    // displayScore() is to show the calculated Score.
    private void displayScore(int score) {

    }

    ;

    // start() is to show the Game start.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Azul Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        root.getChildren().add(matrixBoard);

        makeControls();

        displayBoard();

        /*
        animateTile();
        displayBoard();
        displayScore(Azul.gameScore);
        if(Azul.isEndGameValid(Azul.gameState)){
            displayEnd();
        }*/


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class ViewerControlButtons extends Viewer {

    }

    public class ViewerDisplay extends Viewer {


    }

    public class ViewerDisplayButtons extends ViewerDisplay {

    }

    public class ViewerDisplayBoard extends ViewerDisplay {

    }

}


