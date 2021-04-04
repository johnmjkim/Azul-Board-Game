package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    // animateBoard() contains two parts:
    // showCenterBoard() and showPlayerBoard() of each players
    public static void animateBoard(){
        // Show setup of center of board
        showCenterBoard();
        // Show setup of player of board
        showPlayerBoard();
    };

    // showCenterBoard() contains four parts:
    // showFactory(), showCenter(), showBag() and showDiscard()
    // This is graphic of board states shared with all players
    public static void showCenterBoard(){
        showFactory();
        showCenter();
        showBag();
        showDiscard();
    };
    // showFactory() shows graphics of factories and their tiles
    public static void showFactory(){

    };
    // showCenter() shows graphics of center and their tiles
    public static void showCenter(){

    };
    // showBag() shows graphics of bag and their tiles
    public static void showBag(){

    };
    // showDiscard() shows graphics of discard and their tiles
    public static void showDiscard(){

    };

    // showPlayerBoard() contains three parts:
    // showStorage(), showMosaic(), showFloor().
    // This is graphic of board state of each players
    public static void showPlayerBoard(){
        showStorage();
        showMosaic();
        showFloor();
    };
    // showStorage() shows graphics of player's storage and their tiles
    public static void showStorage(){

    };
    // showMosaic() shows graphics of player's mosaic and their tiles
    public static void showMosaic(){

    };
    // showFloor() shows graphics of player's floor and their tiles
    public static void showFloor(){

    };

    @Override
    public void start(Stage stage) throws Exception {
        //  FIXME Task 12: Implement a basic playable Azul game in JavaFX that only allows tiles to be placed in valid places
        //  FIXME Task 14: Implement a computer opponent so that a human can play your game against the computer.
        stage.setTitle("Azul");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    public class GameTile extends Polygon {

    }

    public class GameTileDrag extends GameTile{

    }

    public class GameBoard extends Polygon{

    }
}
