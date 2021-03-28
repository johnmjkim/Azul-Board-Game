package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    @Override
    public void start(Stage stage) throws Exception {
        //  showCenterBoard();
        //  showFactory();
        //  showCenter();
        //  showBag();
        //  showDiscard();
        //  showPlayerBoard();
        //  showStorage();
        //  showMosaic();
        //  showFloor();
        //  animateBoard();
        //  showCenterBoard() contains four parts: showFactory(), showCenter(), showBag() and showDiscard().
        //  showPlayerBoard() contains three parts: showStorage(), showMosaic(), showFloor().
        //  All this methods are going to show the different parts.
        //  FIXME Task 12: Implement a basic playable Azul game in JavaFX that only allows tiles to be placed in valid places
        //  FIXME Task 14: Implement a computer opponent so that a human can play your game against the computer.
        stage.setTitle("Azul");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}
