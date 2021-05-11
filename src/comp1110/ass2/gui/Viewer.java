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
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Viewer extends Application implements Constants {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 600;
    private final Group matrixBoard = new Group();

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField playerTextField;
    private TextField boardTextField;

    public int PLAYER_NUMBER = Game.PLAYER_NUMBER;
    public CenterCoordinates CENTER_COORDINATES = Game.CENTER_COORDINATES;
    public FactoriesCoordinates FACTORIES_COORDINATES = Game.FACTORIES_COORDINATES;
    public StorageCoordinates STORAGE_COORDINATES = Game.STORAGE_COORDINATES;
    public MosaicCoordinates MOSAIC_COORDINATES = Game.MOSAIC_COORDINATES;
    public FloorCoordinates FLOOR_COORDINATES = Game.FLOOR_COORDINATES;

    public ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();
    public ArrayList<DraggableTiles> draggableFactoriesTiles = new ArrayList<DraggableTiles>();
    public ArrayList<DraggableTiles> draggableCenterTiles = new ArrayList<DraggableTiles>();

    public ArrayList<Rectangle> emptyCenterList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyStorageList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyMosaicList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyFloorList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyFactoriesList = new ArrayList<Rectangle>();

    public ArrayList<Rectangle> snappable_Tile = new ArrayList<>();
    public ArrayList<Rectangle> non_snappable_Tile = new ArrayList<>();

    Rectangle highlighted = null;

    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * //@param state an array of two strings, representing the current game state
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
        state[0] = "AF0cdde2abde3cdee4bcceCaaaabbbbccccdddeefB1915161614D1618152019";
        state[1] = "A07Me01b04a11d20b30b41e44S0a11b22c13c14d2FabeeB08Md03b13e23c32b41S0b11c12a33d24e4FabccC12Mb00e11e12a21d20b30b41b42S1b22c13c14d1FeD05Me11e12e13e14a22a23c32b41b42b44b43S2a33d24e4Fabcc";

        SharedState ss = new SharedState(state[0], PLAYER_NUMBER);
        PlayerState ps = new PlayerState(state[1], PLAYER_NUMBER);

        String current_player_turn = ss.getTurnState();
        nPlayer current_player = ps.getnPlayer(current_player_turn.charAt(0));

        //display empty board
        display_empty_Center();
        display_empty_Factories();
        display_empty_Storage();
        display_empty_Mosaic();
        display_empty_Floor();
        non_snappable_Tile.clear();
        non_snappable_Tile.addAll(emptyCenterList);
        non_snappable_Tile.addAll(emptyFactoriesList);
        non_snappable_Tile.addAll(emptyMosaicList);
        matrixBoard.getChildren().addAll(non_snappable_Tile);

        snappable_Tile.clear();
        snappable_Tile.addAll(emptyStorageList);
        snappable_Tile.addAll(emptyFloorList);
        matrixBoard.getChildren().addAll(snappable_Tile);

        //CENTER
        displayCenter(ss);

        //BAG
        int[] bag_tiles = new int[COLORS.length];
        char color = BLUE;
        for (int i = 0; i < COLORS.length; i++) {
            bag_tiles[i] = ss.bag.getTilesNumber(color);
            color++;
        }
        displayBag(bag_tiles);

        //DISCARD
        int[] discard_tiles = new int[COLORS.length];
        color = BLUE;
        for (int i = 0; i < COLORS.length; i++) {
            discard_tiles[i] = ss.discard.getTilesNumber(color);
            color++;
        }
        displayDiscard(discard_tiles);

        //FACTORIES
        displayFactories(ss);

        //STORAGE
        int[] storage_row_Tiles = new int[MAX_STORAGE_ROW];
        char[] storage_row_Colors = new char[MAX_STORAGE_ROW];
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            storage_row_Tiles[storage_row] = current_player.storage.getStorageRow(storage_row).getTotalTilesNumber();
            storage_row_Colors[storage_row] = current_player.storage.getStorageRow(storage_row).getRowTilesColor();
        }

        displayStorage(storage_row_Tiles, storage_row_Colors);

        //MOSAIC
        displayMosaic(current_player);

        //FLOOR
        String floorStateString = current_player.floor.getStateString();
        displayFloor(floorStateString);

        //SCORE
        HBox scoreBox = new HBox();
        for (int player = 0; player < PLAYER_NUMBER; player++) {
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


    public void display_empty_Center() {
        emptyCenterList.clear();
        for (int tiles = 0; tiles < CENTER_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; tiles++) {
            double x = CENTER_COORDINATES.getPos_x(tiles);
            double y = CENTER_COORDINATES.getPos_y(tiles);
            Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
            r.setFill(Color.GREY);
            emptyCenterList.add(r);
        }
    }

    public void display_empty_Factories() {
        emptyFactoriesList.clear();
        for (int factory = 0; factory < FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; factory++) {
            for (int tiles = 0; tiles < FACTORY_SIZE; tiles++) {
                double x = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_x(tiles);
                double y = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_y(tiles);
                Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
                r.setFill(Color.GREY);
                emptyFactoriesList.add(r);
            }
        }
    }

    private void display_empty_Storage() {
        emptyStorageList.clear();
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            for (int tiles = 0; tiles < storage_row + 1; tiles++) {
                double x = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_x(tiles);
                double y = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_y(tiles);
                Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
                r.setFill(Color.GREY);
                emptyStorageList.add(r);
            }
        }

    }

    private void display_empty_Floor() {
        emptyFloorList.clear();
        for (int tiles = 0; tiles < MAX_FLOOR_TILES_COL_IMAGE; tiles++) {
            double x = FLOOR_COORDINATES.getPos_x(tiles);
            double y = FLOOR_COORDINATES.getPos_y(tiles);
            Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
            r.setFill(Color.GREY);
            emptyFloorList.add(r);
        }
    }

    private void display_empty_Mosaic() {
        emptyMosaicList.clear();
        for (int row = 0; row < MAX_MOSAIC_ROW; row++) {
            for (int tiles = 0; tiles < MAX_MOSAIC_COL; tiles++) {
                double x = MOSAIC_COORDINATES.getMosaicRowCoordinates(row).getPos_x(tiles);
                double y = MOSAIC_COORDINATES.getMosaicRowCoordinates(row).getPos_y(tiles);
                Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
                r.setFill(Color.GREY);
                emptyMosaicList.add(r);
            }
        }
    }


    private void displayCenter(SharedState ss) {
        draggableCenterTiles.clear();
        for (int tiles = 0; tiles < CENTER_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; tiles++) {
            double x = CENTER_COORDINATES.getPos_x(tiles);
            double y = CENTER_COORDINATES.getPos_y(tiles);
            if (ss.center.getTileColor(tiles) == NO_COLOR) {

            }
            else{
                DraggableTiles draggableTile = new DraggableTiles(x,y,COLORS_WITH_FIRST_PLAYER_IMAGE[ss.center.getTileColor(tiles) - BLUE],this);
                draggableTile.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                draggableTile.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                draggableTile.setLayoutY(y);
                draggableTile.setLayoutX(x);
                draggableCenterTiles.add(draggableTile);
            }
        }
        matrixBoard.getChildren().addAll(draggableCenterTiles);
    }

    private void displayBag(int[] bag_tiles) {
        for (int tiles = 0; tiles < bag_tiles.length; tiles++) {
            for (int j = 0; j < bag_tiles[tiles]; j++) {
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[tiles]));
                Tile_View.setFitWidth(SMALL_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(SMALL_TILE_IMAGE_SIZE_Y);
                int row = (int) j / MAX_BAG_TILES_COL_IMAGE;
                row = row + 2 * tiles;
                int col = j % MAX_BAG_TILES_COL_IMAGE;
                double x = INITIAL_BAG_IMAGE_POS_X + (SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP) * col;
                double y = INITIAL_BAG_IMAGE_POS_Y + (SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP) * row;
                Tile_View.setLayoutX(x);
                Tile_View.setLayoutY(y);
                matrixBoard.getChildren().add(Tile_View);
            }
        }
    }

    private void displayDiscard(int[] discard_tiles) {
        for (int tiles = 0; tiles < discard_tiles.length; tiles++) {
            for (int j = 0; j < discard_tiles[tiles]; j++) {
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[tiles]));
                Tile_View.setFitWidth(SMALL_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(SMALL_TILE_IMAGE_SIZE_Y);
                int row = (int) j / MAX_DISCARD_TILES_COL_IMAGE;
                row = row + 2 * tiles;
                int col = j % MAX_DISCARD_TILES_COL_IMAGE;
                double x = INITIAL_DISCARD_IMAGE_POS_X + (SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP) * col;
                double y = INITIAL_DISCARD_IMAGE_POS_Y + (SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP) * row;
                Tile_View.setLayoutX(x);
                Tile_View.setLayoutY(y);
                matrixBoard.getChildren().add(Tile_View);
            }
        }
    }


    private void displayFactories(SharedState ss) {
        draggableFactoriesTiles.clear();
        for (int factory = 0; factory < FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; factory++) {
            for (int tiles = 0; tiles < FACTORY_SIZE; tiles++) {
                double x = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_x(tiles);
                double y = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_y(tiles);
                if (ss.factories.getFactory(factory).getTileColor(tiles) != NO_COLOR) {
                    DraggableTiles draggableTile = new DraggableTiles(x, y, COLORS_WITH_FIRST_PLAYER_IMAGE[ss.factories.getFactory(factory).getTileColor(tiles) - BLUE], this);
                    draggableTile.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    draggableTile.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    draggableTile.setLayoutY(y);
                    draggableTile.setLayoutX(x);
                    draggableFactoriesTiles.add(draggableTile);
                }
            }
        }
        matrixBoard.getChildren().addAll(draggableFactoriesTiles);
    }

    private void displayStorage(int[] storage_row_tiles, char[] storage_row_colors) {
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            for (int tiles = 0; tiles < storage_row_tiles[storage_row]; tiles++) {
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[storage_row_colors[storage_row] - BLUE]));
                Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                int storage_col = MAX_STORAGE_ROW - tiles - 1;
                double x = INITIAL_STORAGE_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * storage_col;
                double y = INITIAL_STORAGE_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * storage_row;
                Tile_View.setLayoutY(y);
                Tile_View.setLayoutX(x);
                matrixBoard.getChildren().add(Tile_View);
            }
        }
    }

    private void displayMosaic(nPlayer current_player) {
        for (int mosaic_row = 0; mosaic_row < MAX_MOSAIC_ROW; mosaic_row++) {
            for (int mosaic_col = 0; mosaic_col < MAX_MOSAIC_COL; mosaic_col++) {
                boolean mosaic_tile_exists = current_player.mosaic.getMosaicRow(mosaic_row).existsTile(mosaic_col);
                if (mosaic_tile_exists) {
                    char mosaic_tile_color = current_player.mosaic.getMosaicRow(mosaic_row).getTileColor(mosaic_col);
                    ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[mosaic_tile_color - BLUE]));
                    Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    double x = INITIAL_MOSAIC_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * mosaic_col;
                    double y = INITIAL_MOSAIC_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * mosaic_row;
                    Tile_View.setLayoutY(y);
                    Tile_View.setLayoutX(x);
                    matrixBoard.getChildren().add(Tile_View);
                }
            }
        }
    }

    private void displayFloor(String floorState) {
        char[] floor_chars = floorState.toCharArray();
        for (int tiles = 0; tiles < floor_chars.length; tiles++) {
            ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[floor_chars[tiles] - BLUE]));
            Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
            Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
            int row = (int) tiles / MAX_FLOOR_TILES_COL_IMAGE;
            int col = tiles % MAX_FLOOR_TILES_COL_IMAGE;
            double x = INITIAL_FLOOR_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * col;
            double y = INITIAL_FLOOR_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * row;
            Tile_View.setLayoutY(y);
            Tile_View.setLayoutX(x);
            matrixBoard.getChildren().add(Tile_View);
        }
    }

    private void displayScore(String scoreState) {

    }


    /**
     * Create a basic text field for input and a refresh button.
     */

    // setupViewer() is to start the Viewer, get the state and refresh it as the image shows
    private void setupViewer() {
        Label playerLabel = new Label("Player State:");
        playerTextField = new TextField();
        playerTextField.setPrefWidth(100);
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(100);
        Button RefreshButton = new Button("Refresh");

        // Use lambda expression for button
        RefreshButton.setOnAction(ae -> {
            Rectangle r = new Rectangle(510, 550, 300, 30);
            r.setFill(Color.WHITE);
            controls.getChildren().add(r);
            //add backboard each time to empty the tiles which has been displayed
            ImageView boardA = new ImageView(new Image("file:src/comp1110/ass2/img/empty-board.png"));
            boardA.setFitWidth(1200);
            boardA.setFitHeight(500);
            boardA.setLayoutX(0);
            boardA.setLayoutY(15);
            boardA.setOpacity(0.2);
            matrixBoard.getChildren().add(boardA);

            displayState(new String[]{playerTextField.getText(), boardTextField.getText()});

        });
        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, playerTextField, boardLabel,
                boardTextField, RefreshButton);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

    }
    // animateTile() is to show the animate of Tile.
    private void animateTile() {
        /*
        String[] _gameState = Azul.gameState;
        String _move = Azul.move;
        displayState(_gameState);
        moveTile();

         */
    }
    // displayEnd() is to show the End of the game.
    private void displayEnd() {
        /*
        String[] _gameState = Azul.gameState;
        moveTile();

         */
    }
    // displayBoard() is to show the Center Board and Player Board of the Game class.
    private void displayBoard() {

    }
    // displayError() is to show error.
    private void displayError() {

    }
    // moveTile() is to show the move of the Tile in animate Board, Center Board and Player Board.
    private void moveTile() {

    }
    // displayScore() is to show the calculated Score.
    private void displayScore(int score) {

    }
    public static void main(String[] args) {
        launch(args);
    }
    private void start_page() {

        Rectangle r1 = new Rectangle(50, 548, 1200, 30);
        r1.setFill(Color.WHITE);
        controls.getChildren().add(r1);

        Button WelcomeStartButton = new Button("Start");
        Button WelcomeExitButton = new Button("Exit ");

        ImageView boardA = new ImageView(new Image("file:src/comp1110/ass2/img/Welcome.png"));
        boardA.setFitWidth(1200);
        boardA.setFitHeight(500);
        boardA.setLayoutX(0);
        boardA.setLayoutY(15);
        matrixBoard.getChildren().add(boardA);

        WelcomeStartButton.setOnAction(ae -> {

            //boardA.setOpacity(0);
            matrixBoard.getChildren().clear();
            controls.getChildren().clear();
            setupViewer();

            Rectangle r = new Rectangle(510, 548, 300, 30);
            r.setFill(Color.WHITE);
            controls.getChildren().add(r);

            Button GameExitButton = new Button("Exit ");
            Button GameNextRoundButton = new Button("Next round");

            GameExitButton.setOnAction(ae1 -> {
                System.exit(0);
            });
            GameNextRoundButton.setOnAction(ae1 -> {
                start_page();
            });

            HBox hb1 = new HBox(GameExitButton, GameNextRoundButton);
            hb1.setSpacing(10);
            hb1.setLayoutX(1000);
            hb1.setLayoutY(VIEWER_HEIGHT - 50);
            controls.getChildren().add(hb1);
        });

        WelcomeExitButton.setOnAction(ae -> {
            System.exit(0);
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(WelcomeStartButton, WelcomeExitButton);
        hb.setSpacing(10);
        hb.setLayoutX(530);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    // makeControls() is to make control of the Viewer.
    private void makeControls() {
        start_page();
    }

    // start() is to show the Game start.
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Azul Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        root.getChildren().add(matrixBoard);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Rectangle extends Polygon {
        double x;
        double y;
        double size_x;
        double size_y;
        public Rectangle(double x, double y, double size_x, double size_y) {
            this.x =x;
            this.y =y;
            this.size_x =size_x;
            this.size_y =size_y;
        }
        public double distance(double x,double y){
            return Math.sqrt((x-this.x)*(x-this.x) + (y-this.y)*(y-this.y));
        }
    }

    public Rectangle findNearestRectangle(double x, double y){
        int i = 0;
        double distance = 0;
        int closestID = -1;
        for (Rectangle t : snappable_Tile){
            if (i == 0){
                distance = t.distance(x,y);
                closestID = i;
            }else if(t.distance(x,y) < distance){
                closestID = i;
                distance = t.distance(x,y);
            }
            i++;
        }
        return snappable_Tile.get(closestID);
    }

    public void highlightNearestRectangle(double x, double y){
        int i=0;
        highlighted = findNearestRectangle(x,y);
        ArrayList<Rectangle> ts = this.snappable_Tile;
        for (Rectangle t : ts){
            if (t == highlighted){
                t.setFill(Color.GREEN);
                this.snappable_Tile.set(i,t);
            }else {
                t.setFill(Color.GREY);
                this.snappable_Tile.set(i,t);
            }
            i++;
        }
    }

    public class DraggableTiles extends ImageView {
        double x;
        double y;
        private double mousex;
        private double mousey;
        public DraggableTiles (double x, double y, String image_link, Viewer viewer) {
            super(new Image(image_link));
            this.toFront();

            this.setOnMousePressed(event ->{
                mousex = event.getSceneX();
                mousey = event.getSceneY();
            });

            this.setOnMouseDragged(event ->{
                mousex = event.getSceneX();
                mousey = event.getSceneY();

                this.setLayoutX(mousex);
                this.setLayoutY(mousey);

                viewer.highlightNearestRectangle(mousex,mousey);

            });
            this.setOnMouseReleased(event ->{
                this.x = event.getSceneX();
                this.y = event.getSceneY();
                setLayoutX(highlighted.x);
                setLayoutY(highlighted.y);
            });
        }

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


