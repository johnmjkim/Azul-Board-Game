package comp1110.ass2.gui;

import comp1110.ass2.Azul;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField playerTextField;    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     *              TASK 4
     */

    // displayState() is to show the Game State.
    void displayState(String[] state) {
        // FIXME Task 4: implement the simple state viewer
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
    private void setupViewer(){
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
            displayState(new String[]{playerTextField.getText(),boardTextField.getText()});
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, playerTextField, boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    };

    // animateTile() is to show the animate of Tile.
    private void animateTile(){
        String[] _gameState = Azul.gameState;
        String _move = Azul.move;
        displayState(_gameState);
        moveTile();
    };

    // displayEnd() is to show the End of the game.
    private void displayEnd(){
        String[] _gameState = Azul.gameState;
        int _gameScore = Azul.gameScore;
        displayScore(_gameScore);
        displayState(_gameState);
        moveTile();
    };

    // displayBoard() is to show the Center Board and Player Board of the Game class.
    private void displayBoard(){
        Game.animateBoard();
    };

    // displayError() is to show error.
    private void displayError(){

    };

    // moveTile() is to show the move of the Tile in animate Board, Center Board and Player Board.
    private void moveTile(){

    };

    // displayScore() is to show the calculated Score.
    private void displayScore(int score){

    };

    // start() is to show the Game start.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Azul Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        animateTile();
        displayBoard();
        displayScore(Azul.gameScore);
        if(Azul.isEndGameValid(Azul.gameState)){
            displayEnd();
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class ViewerControlButtons extends Viewer{

    }

    public class ViewerDisplay extends Viewer{


    }
    public class ViewerDisplayButtons extends ViewerDisplay{

    }

    public class ViewerDisplayBoard extends ViewerDisplay{

    }

}


