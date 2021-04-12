package comp1110.ass2.gui;

import comp1110.ass2.Azul;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1000;
    private static final int VIEWER_HEIGHT = 500;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField playerTextField;    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     *              TASK 4
     */


    void displayState(String[] state) {
        // FIXME Task 4: implement the simple state viewer

        //get State
        String playerState = state[0];
        String boardState = state[1];

        //creat 2 labels and a button
        Label playerLabel1 = new Label("Player State: "+"\n\n"+playerState);
        Label boardLabel1 = new Label("Board State: "+"\n\n"+boardState);

        //add the labels and button in different lines
        HBox pl = new HBox();
        pl.getChildren().addAll(playerLabel1);
        pl.setLayoutX(50);
        pl.setLayoutY(VIEWER_HEIGHT - 300);
        controls.getChildren().add(pl);

        HBox bl = new HBox();
        bl.getChildren().addAll(boardLabel1);
        bl.setLayoutX(50);
        bl.setLayoutY(VIEWER_HEIGHT - 240);
        controls.getChildren().add(bl);



        //use "A07Me01a11d20b30b41S0a11b22c13c44d1FeeB08Md03b13e23c32b41S0b11c12a33d24e4Fab" to text in
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
            javafx.scene.shape.Rectangle r = new Rectangle(50, 200, 3000, 115);
            r.setFill(Color.WHITE);
            controls.getChildren().add(r);
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

        /*
        animateTile();
        displayBoard();
        displayScore(Azul.gameScore);
        if(Azul.isEndGameValid(Azul.gameState)){
            displayEnd();
        }

         */
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


