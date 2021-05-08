package comp1110.ass2.gui;

import comp1110.ass2.*;
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

public class Viewer extends Application implements Constants {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 600;
    private final Group matrixBoard = new Group();

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField playerTextField;
    private TextField boardTextField;

    public static int PLAYER_NUMBER = 4;

    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     *              TASK 4
     */


    void displayState(String[] state) {
        // FIXME Task 4: implement the simple state viewer

        //state[0]="AF0cdde2abde3cdee4bcceCaaabbbccdddeeefB1915161614D1618152019";
        //state[1]="A07Me01b04a11d20b30b41e44S0a11b22c13c44d5FabeeB08Md03b13e23c32b41S0b11c12a33d24e4Fabcc";

        //state[0]=AF0cdde1bbbe2abde3cdee4bcceCaabbcaabbcaacbbefB1915161614D0000000000
        //state[1]=A07Me01a11d20b30b41S0a11b22c13c44d1FeeabB08Md03b13e23c32b41S0b11c12a33d24e4Fabcc

        //state[0]=AF0abde1bbbe2abde4bcceCaabbcebbfB1915161614D0020000019
        //state[1]=A07Mb00e11e12a21d20b30b41b42S1b22c13c14d1FeB08Me11e12e13e14a22a23c32b41b42b44b43S2a33d24e4Fabcc

        // Four players example
        state[0]="AF0cdde2abde3cdee4bcceCaaabbbccdddeeefB1915161614D1618152019";
        state[1]="A07Me01b04a11d20b30b41e44S0a11b22c13c44d5FabeeB08Md03b13e23c32b41S0b11c12a33d24e4FabccC12Mb00e11e12a21d20b30b41b42S1b22c13c14d1FeD05Me11e12e13e14a22a23c32b41b42b44b43S2a33d24e4Fabcc";

        SharedState ss = new SharedState(state[0], PLAYER_NUMBER);
        PlayerState ps = new PlayerState(state[1], PLAYER_NUMBER);

        String current_player_turn = ss.getTurnState();
        nPlayer current_player = ps.getnPlayer(current_player_turn.charAt(0));

        //CENTER
        String centerStateString = ss.center.getStateString();
        displayCenter(centerStateString);

        //BAG
        int[] bag_tiles = new int[COLORS.length];
        char color = BLUE;
        for(int i=0; i < COLORS.length; i++){
            bag_tiles[i] = ss.bag.getTilesNumber(color);
            color++;
        }
        displayBag(bag_tiles);

        //DISCARD
        int[] discard_tiles = new int[COLORS.length];
        color = BLUE;
        for(int i=0; i < COLORS.length; i++){
            discard_tiles[i] = ss.discard.getTilesNumber(color);
            color++;
        }
        displayDiscard(discard_tiles);

        //FACTORIES
        int max_factory_number = FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER];
        String[] factoryStates = new String[max_factory_number];
        for(int factory=0; factory < max_factory_number; factory++) {
            factoryStates[factory] = ss.factories.getFactory(factory).getStateString();
        }

        displayFactories(factoryStates);

        //STORAGE
        int[] storage_row_Tiles = new int[MAX_STORAGE_ROW];
        char[] storage_row_Colors = new char[MAX_STORAGE_ROW];
        for(int storage_row=0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            storage_row_Tiles[storage_row] = current_player.storage.getStorageRow(storage_row).getTotalTilesNumber();
            storage_row_Colors[storage_row] = current_player.storage.getStorageRow(storage_row).getTilesColor();
        }

        displayStorage(storage_row_Tiles, storage_row_Colors);

        //MOSAIC
        displayMosaic(current_player);

        //FLOOR
        String floorStateString = current_player.floor.getStateString();
        displayFloor(floorStateString);

        //SCORE
        HBox scoreBox = new HBox();
        for(int player=0; player < PLAYER_NUMBER; player++) {
            int score = ps.getnPlayer(ALL_PLAYERS[player]).score.getScore();
            Label score_label = new Label("Score of Player " + ALL_PLAYERS[player] + ": " + score);
            scoreBox.getChildren().add(score_label);
        }
        scoreBox.setSpacing(SCORE_IMAGE_GAP);
        scoreBox.setLayoutX(INITIAL_SCORE_IMAGE_POS_X);
        scoreBox.setLayoutY(INITIAL_SCORE_IMAGE_POS_Y);
        controls.getChildren().add(scoreBox);

        /*
        //SCORE
        String score_A = player_state_of_A.substring(player_state_of_A.indexOf("A") + 1, player_state_of_A.indexOf("M"));
        String[] Score_A = score_A.split("");
        int a = Integer.parseInt(Score_A[0])*10+Integer.parseInt(Score_A[1]);
        Label Ascore = new Label("Score of Player A: "+a);
        String score_B = player_state_of_B.substring(player_state_of_B.indexOf("B") + 1, player_state_of_B.indexOf("M"));
        String[] Score_B = score_B.split("");
        int b = Integer.parseInt(Score_B[0])*10+Integer.parseInt(Score_B[1]);
        Label Bscore = new Label("Score of Player B: "+b);
        HBox Sc = new HBox();
        Sc.getChildren().addAll(Ascore, Bscore);
        Sc.setSpacing(10);
        Sc.setLayoutX(550);
        Sc.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(Sc);
         */

    }

    private void displayCenter(String centerState){
        char[] center_chars = centerState.toCharArray();
        for (int tiles = 0; tiles < center_chars.length; tiles++){
            ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[center_chars[tiles]-BLUE]));
            Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
            Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
            int row = (int) tiles/MAX_CENTER_TILES_COL_IMAGE;
            int col = tiles % MAX_CENTER_TILES_COL_IMAGE;
            int x = INITIAL_CENTER_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * col;
            int y = INITIAL_CENTER_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * row;
            Tile_View.setLayoutY(y);
            Tile_View.setLayoutX(x);
            matrixBoard.getChildren().add(Tile_View);
        }
    }

    private void displayBag(int[] bag_tiles){
        for(int tiles=0; tiles < bag_tiles.length; tiles++){
            for(int j=0; j < bag_tiles[tiles]; j++){
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[tiles]));
                Tile_View.setFitWidth(SMALL_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(SMALL_TILE_IMAGE_SIZE_Y);
                int row = (int) j/MAX_BAG_TILES_COL_IMAGE;
                row = row + 2 * tiles;
                int col = j % MAX_BAG_TILES_COL_IMAGE;
                int x = INITIAL_BAG_IMAGE_POS_X + (SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP) * col;
                int y = INITIAL_BAG_IMAGE_POS_Y + (SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP) * row;
                Tile_View.setLayoutX(x);
                Tile_View.setLayoutY(y);
                matrixBoard.getChildren().add(Tile_View);
            }
        }
    }

    private void displayDiscard(int[] discard_tiles){
        for(int tiles=0; tiles < discard_tiles.length; tiles++){
            for(int j=0; j < discard_tiles[tiles]; j++){
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[tiles]));
                Tile_View.setFitWidth(SMALL_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(SMALL_TILE_IMAGE_SIZE_Y);
                int row = (int) j/MAX_DISCARD_TILES_COL_IMAGE;
                row = row + 2 * tiles;
                int col = j % MAX_DISCARD_TILES_COL_IMAGE;
                int x = INITIAL_DISCARD_IMAGE_POS_X + (SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP) * col;
                int y = INITIAL_DISCARD_IMAGE_POS_Y + (SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP) * row;
                Tile_View.setLayoutX(x);
                Tile_View.setLayoutY(y);
                matrixBoard.getChildren().add(Tile_View);
            }
        }
    }

    private void displayFactories(String[] factoryStates){
        for(int factory=0; factory < factoryStates.length; factory++){
            String factoryState = factoryStates[factory];
            char[] factory_chars = factoryState.toCharArray();
            int factories_row = (int) factory/MAX_FACTORIES_TILES_COL_IMAGE;
            int factories_col = factory % MAX_FACTORIES_TILES_COL_IMAGE + factories_row;
            for (int tiles = 0; tiles < factory_chars.length; tiles++){
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[factory_chars[tiles]-BLUE]));
                Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                int factory_row = (int) tiles/MAX_FACTORY_TILES_ROW_IMAGE;
                int factory_col = tiles % MAX_FACTORY_TILES_ROW_IMAGE;
                int x = INITIAL_FACTORIES_IMAGE_POS_X + FACTORIES_IMAGE_SIZE_X_GAP * factories_col + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * factory_col;
                int y = INITIAL_FACTORIES_IMAGE_POS_Y + FACTORIES_IMAGE_SIZE_Y_GAP * factories_row + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * factory_row;
                Tile_View.setLayoutY(y);
                Tile_View.setLayoutX(x);
                matrixBoard.getChildren().add(Tile_View);
            }
        }
    }

    private void displayStorage(int[] storage_row_tiles, char[] storage_row_colors){
        for(int storage_row=0; storage_row < MAX_STORAGE_ROW; storage_row++){
            for(int tiles=0; tiles < storage_row_tiles[storage_row]; tiles++){
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[storage_row_colors[storage_row]-BLUE]));
                Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                int storage_col = MAX_STORAGE_ROW - tiles - 1;
                int x = INITIAL_STORAGE_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * storage_col;
                int y = INITIAL_STORAGE_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * storage_row;
                Tile_View.setLayoutY(y);
                Tile_View.setLayoutX(x);
                matrixBoard.getChildren().add(Tile_View);

            }
        }
    }

    private void displayMosaic(nPlayer current_player){
        for(int mosaic_row=0; mosaic_row < MAX_MOSAIC_ROW; mosaic_row++){
            for(int mosaic_col=0; mosaic_col < MAX_MOSAIC_COL; mosaic_col++){
                boolean mosaic_tile_exists = current_player.mosaic.getMosaicRow(mosaic_row).existsTile(mosaic_col);
                if(mosaic_tile_exists){
                    char mosaic_tile_color = current_player.mosaic.getMosaicRow(mosaic_row).getTileColor(mosaic_col);
                    ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[mosaic_tile_color-BLUE]));
                    Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    int x = INITIAL_MOSAIC_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * mosaic_col;
                    int y = INITIAL_MOSAIC_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * mosaic_row;
                    Tile_View.setLayoutY(y);
                    Tile_View.setLayoutX(x);
                    matrixBoard.getChildren().add(Tile_View);
                }
            }
        }
    }

    private void displayFloor(String floorState){
        char[] floor_chars = floorState.toCharArray();
        for (int tiles = 0; tiles < floor_chars.length; tiles++){
            ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[floor_chars[tiles]-BLUE]));
            Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
            Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
            int row = (int) tiles/MAX_FLOOR_TILES_COL_IMAGE;
            int col = tiles % MAX_FLOOR_TILES_COL_IMAGE;
            int x = INITIAL_FLOOR_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * col;
            int y = INITIAL_FLOOR_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * row;
            Tile_View.setLayoutY(y);
            Tile_View.setLayoutX(x);
            matrixBoard.getChildren().add(Tile_View);
        }
    }

    private void displayScore(String scoreState){

    }

    /**
     * Create a basic text field for input and a refresh button.
     */

    // makeControls() is to make control of the Viewer.
    private void makeControls() {
        start_page();
        //setupViewer();
    }

    // setupViewer() is to start the Viewer, get the state and refresh it as the image shows
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
            javafx.scene.shape.Rectangle r = new Rectangle(510, 550, 300, 30);
            r.setFill(Color.WHITE);
            controls.getChildren().add(r);
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

    private void start_page(){
        Button button_1 = new Button("Start");
        Button button_2 = new Button("Exit");
        ImageView boardA = new ImageView(new Image("file:src/comp1110/ass2/img/Welcome.png"));
        boardA.setFitWidth(1200);
        boardA.setFitHeight(500);
        boardA.setLayoutX(0);
        boardA.setLayoutY(15);
        matrixBoard.getChildren().add(boardA);
        button_1.setOnAction(ae -> {
            setupViewer();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(button_1,button_2);
        hb.setSpacing(10);
        hb.setLayoutX(530);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }
    ;

    // animateTile() is to show the animate of Tile.
    private void animateTile() {
        /*
        String[] _gameState = Azul.gameState;
        String _move = Azul.move;
        displayState(_gameState);
        moveTile();

         */
    }

    ;

    // displayEnd() is to show the End of the game.
    private void displayEnd() {
        /*
        String[] _gameState = Azul.gameState;
        moveTile();

         */
    }

    ;

    // displayBoard() is to show the Center Board and Player Board of the Game class.
    private void displayBoard() {

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
    public static void main(String[] args) {
        launch(args);
    }

    // start() is to show the Game start.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Azul Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        root.getChildren().add(matrixBoard);

        makeControls();

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


