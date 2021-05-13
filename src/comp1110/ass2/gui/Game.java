package comp1110.ass2.gui;

import comp1110.ass2.backend.Constants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application implements Constants {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    public static int PLAYER_NUMBER = 2;
    public static int FACTORY_MAX_NUMBER = FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER];
    public static StorageCoordinates STORAGE_COORDINATES;
    public static FloorCoordinates FLOOR_COORDINATES;
    public static MosaicCoordinates MOSAIC_COORDINATES;
    public static BagCoordinates BAG_COORDINATES;
    public static DiscardCoordinates DISCARD_COORDINATES;
    public static CenterCoordinates CENTER_COORDINATES;
    public static FactoriesCoordinates FACTORIES_COORDINATES;

    public static void main(String[] args) {
        launch(args);
    }

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

        makeCoordinates();

        Rectangle r = new Rectangle(50, 50, 100, 100);
        r.setFill(Color.RED);
        root.getChildren().add(r);

        stage.setScene(scene);
        stage.show();

        Viewer viewer = new Viewer();
        viewer.start(stage);

        //Create draggable tiles

    }



    private void makeCoordinates(){
        // Coordinates
        STORAGE_COORDINATES = new StorageCoordinates();
        FLOOR_COORDINATES = new FloorCoordinates();
        MOSAIC_COORDINATES = new MosaicCoordinates();
        BAG_COORDINATES = new BagCoordinates();
        DISCARD_COORDINATES = new DiscardCoordinates();
        CENTER_COORDINATES = new CenterCoordinates(PLAYER_NUMBER);
        FACTORIES_COORDINATES = new FactoriesCoordinates(FACTORY_MAX_NUMBER);

        //System.out.println(STORAGE_COORDINATES);
        //System.out.println(FLOOR_COORDINATES);
        //System.out.println(MOSAIC_COORDINATES);
        //System.out.println(BAG_COORDINATES);
        //System.out.println(DISCARD_COORDINATES);
        //System.out.println(CENTER_COORDINATES);
        //System.out.println(FACTORIES_COORDINATES);

    }

}