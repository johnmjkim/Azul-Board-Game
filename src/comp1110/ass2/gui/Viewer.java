package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import comp1110.ass2.backend.*;
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
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Viewer extends Application implements Constants {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 600;

    Stage window;
    Scene scene;
    Group root = new Group();
    Group controls = new Group();
    Group matrixBoard = new Group();

    SnappableTiles highlighted = null;

    private TextField playerTextField;
    private TextField boardTextField;

    SharedState ss;
    PlayerState ps;
    String[] currentState;
    String move;
    public char current_turn;
    public char current_stage;

    public int PLAYER_NUMBER = Game.PLAYER_NUMBER;
    //public int PLAYER_NUMBER = 4;
    public static HashMap<Character, PlayersInformation.PlayerInfo> playerMap = Game.playerMap;
    MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);

    public StorageCoordinates STORAGE_COORDINATES = Game.STORAGE_COORDINATES;
    public FloorCoordinates FLOOR_COORDINATES = Game.FLOOR_COORDINATES;
    public MosaicCoordinates MOSAIC_COORDINATES = Game.MOSAIC_COORDINATES;
    public BagCoordinates BAG_COORDINATES = Game.BAG_COORDINATES;
    public DiscardCoordinates DISCARD_COORDINATES = Game.DISCARD_COORDINATES;
    public CenterCoordinates CENTER_COORDINATES = Game.CENTER_COORDINATES;
    public FactoriesCoordinates FACTORIES_COORDINATES = Game.FACTORIES_COORDINATES;

    public ArrayList<ImageView> storageTiles = new ArrayList<ImageView>();
    public ArrayList<ImageView> floorTiles = new ArrayList<ImageView>();
    public ArrayList<ImageView> mosaicTiles = new ArrayList<ImageView>();
    public ArrayList<ImageView> bagTiles = new ArrayList<ImageView>();
    public ArrayList<ImageView> discardTiles = new ArrayList<ImageView>();
    public ArrayList<DraggableTiles> draggableCenterTiles = new ArrayList<DraggableTiles>();
    public ArrayList<ImageView> firstplayerCenterTiles = new ArrayList<ImageView>();
    public ArrayList<DraggableTiles> draggableFactoriesTiles = new ArrayList<DraggableTiles>();
    public ArrayList<DraggableTiles> draggableStorageTiles = new ArrayList<DraggableTiles>();
    public ArrayList<ImageView> undraggableTiles = new ArrayList<ImageView>();
    public ArrayList<ImageView> draggableTiles = new ArrayList<ImageView>();

    public ArrayList<Rectangle> emptyStorageList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyMosaicList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyFloorList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyCenterList = new ArrayList<Rectangle>();
    public ArrayList<Rectangle> emptyFactoriesList = new ArrayList<Rectangle>();
    public ArrayList<SnappableTiles> snappableStorageTiles = new ArrayList<SnappableTiles>();
    public ArrayList<SnappableTiles> snappableFloorTiles = new ArrayList<SnappableTiles>();
    public ArrayList<SnappableTiles> snappableMosaicTiles = new ArrayList<SnappableTiles>();

    public ArrayList<Rectangle> non_snappable_Tile = new ArrayList<>();
    public ArrayList<Rectangle> snappable_Tile = new ArrayList<Rectangle>();
    public ArrayList<SnappableTiles> snappableTiles = new ArrayList<SnappableTiles>();

    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * //@param state an array of two strings, representing the current game state
     *              TASK 4
     */

    void displayState(String[] state) {
        // FIXME Task 4: implement the simple state viewer

        setState(state);
        //this.currentState = state;
        this.current_stage = multiazul.findCurrentStage(state);

        ss = new SharedState(state[0], PLAYER_NUMBER);
        ps = new PlayerState(state[1], PLAYER_NUMBER);

        String current_player_turn = ss.getTurnState();
        this.current_turn = current_player_turn.charAt(0);
        nPlayer current_player = ps.getnPlayer(current_turn);

        System.out.println(" turn : " + this.current_turn + " valid : " + multiazul.isStateValid(state) + " stage : " + multiazul.findCurrentStage(state) + " previous move : " + this.move);
        System.out.println(playerMap.get(current_turn));
        if(playerMap.get(current_turn).getType() == COMPUTER_PLAYER){
            this.move = multiazul.generateSmartAction(currentState);
            playerMap.get(current_turn).setMove(move);
            System.out.println(" move : " + move + " From : " + currentState[0] + ", " + currentState[1]);
            this.currentState = multiazul.applyMove(currentState, move);
            System.out.println(" To : " + currentState[0] + ", " + currentState[1]);
            refreshDisplay();
        }
        else{
            // Display empty, snappable board
            display_empty_Board(current_stage);

            // Display undraggable board :
            // Drafting Stage : Storage, Mosaic, Floor, Bag, Discard
            // Tiling Stage : Factories, Center, Mosaic, Floor, Bag, Discard
            displayUndraggableBoard(ss, current_player);

            // Display draggable board :
            // Drafting Stage : Factories, Center
            // Tiling Stage : Storage
            displayDraggableBoard(ss);

            //SCORE
            controls.getChildren().clear();
            HBox scoreBox = new HBox();
            for (int player = 0; player < PLAYER_NUMBER; player++) {
                int score = ps.getnPlayer(ALL_PLAYERS[player]).score.getScore();
                playerMap.get(ALL_PLAYERS[player]).setScore(score);
                Label score_label = new Label("Score of Player " + ALL_PLAYERS[player] + ": " + score);
                scoreBox.getChildren().add(score_label);
            }
            setRanks(playerMap);
            scoreBox.setSpacing(SCORE_IMAGE_GAP);
            scoreBox.setLayoutX(INITIAL_SCORE_IMAGE_POS_X);
            scoreBox.setLayoutY(INITIAL_SCORE_IMAGE_POS_Y);
            controls.getChildren().add(scoreBox);
        }
    }

    private void display_empty_Board(char current_stage){
        //System.out.println("display_empty_Board");
        display_empty_Center();
        display_empty_Factories();

        non_snappable_Tile.clear();
        snappableTiles.clear();

        non_snappable_Tile.addAll(emptyCenterList);
        non_snappable_Tile.addAll(emptyFactoriesList);

        if(current_stage == DRAFTING_STAGE){
            display_empty_Mosaic();
            display_empty_Storage();
            display_empty_Floor();
            non_snappable_Tile.addAll(emptyMosaicList);
            snappableTiles.addAll(snappableStorageTiles);
            snappableTiles.addAll(snappableFloorTiles);
        }
        else if(current_stage == TILING_STAGE){
            display_empty_Mosaic();
            display_empty_Storage();
            display_empty_Floor();
            non_snappable_Tile.addAll(emptyStorageList);
            snappableTiles.addAll(snappableMosaicTiles);
            snappableTiles.addAll(snappableFloorTiles);
        }
        else{
            display_empty_Mosaic();
            display_empty_Storage();
            display_empty_Floor();
            non_snappable_Tile.addAll(emptyStorageList);
            non_snappable_Tile.addAll(emptyMosaicList);
            non_snappable_Tile.addAll(emptyFloorList);
        }
        matrixBoard.getChildren().addAll(non_snappable_Tile);
        matrixBoard.getChildren().addAll(snappableTiles);
    }

    private void display_empty_Center() {
        //System.out.println("  display_empty_Center");
        emptyCenterList.clear();
        for (int tiles = 0; tiles < CENTER_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; tiles++) {
            double x = CENTER_COORDINATES.getPos_x(tiles);
            double y = CENTER_COORDINATES.getPos_y(tiles);
            Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
            //r.setFill(Color.GREY);
            emptyCenterList.add(r);
        }
    }

    private void display_empty_Storage() {
        //System.out.println("  display_empty_Storage");
        emptyStorageList.clear();
        snappableStorageTiles.clear();
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            for (int tiles = 0; tiles < storage_row + 1; tiles++) {
                double x = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_x(tiles);
                double y = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_y(tiles);
                Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
                r.setFill(Color.GREY);
                emptyStorageList.add(r);
                SnappableTiles snapTile = new SnappableTiles(x, y, NO_TILE_IMAGE, this, STORAGE, storage_row, tiles);
                snappableStorageTiles.add(snapTile);
            }
        }
    }

    private void display_empty_Floor() {
        //System.out.println("  display_empty_Floor");
        emptyFloorList.clear();
        snappableFloorTiles.clear();
        for (int tiles = 0; tiles < MAX_FLOOR_TILES_COL_IMAGE; tiles++) {
            double x = FLOOR_COORDINATES.getPos_x(tiles);
            double y = FLOOR_COORDINATES.getPos_y(tiles);
            Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
            //r.setFill(Color.GREY);
            emptyFloorList.add(r);
            SnappableTiles snapTile = new SnappableTiles(x, y, NO_TILE_IMAGE, this, FLOOR, tiles);
            snappableFloorTiles.add(snapTile);
        }
    }

    private void display_empty_Mosaic() {
        //System.out.println("  display_empty_Mosaic");
        emptyMosaicList.clear();
        snappableMosaicTiles.clear();
        for (int row = 0; row < MAX_MOSAIC_ROW; row++) {
            for (int tiles = 0; tiles < MAX_MOSAIC_COL; tiles++) {
                double x = MOSAIC_COORDINATES.getMosaicRowCoordinates(row).getPos_x(tiles);
                double y = MOSAIC_COORDINATES.getMosaicRowCoordinates(row).getPos_y(tiles);
                Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
                //r.setFill(Color.GREY);
                emptyMosaicList.add(r);
                SnappableTiles snapTile = new SnappableTiles(x, y, NO_TILE_IMAGE, this, MOSAIC, row, tiles);
                snappableMosaicTiles.add(snapTile);
            }
        }
    }

    private void display_empty_Factories() {
        //System.out.println("  display_empty_Factories");
        emptyFactoriesList.clear();
        for (int factory = 0; factory < FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; factory++) {
            for (int tiles = 0; tiles < FACTORY_SIZE; tiles++) {
                double x = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_x(tiles);
                double y = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_y(tiles);
                Rectangle r = new Rectangle(x, y, BIG_TILE_IMAGE_SIZE_X, BIG_TILE_IMAGE_SIZE_Y);
                //r.setFill(Color.GREY);
                emptyFactoriesList.add(r);
            }
        }
    }

    private void displayUndraggableBoard(SharedState ss, nPlayer current_player){
        //System.out.println("displayUndraggableBoard");
        //STORAGE
        int[] storage_row_Tiles = new int[MAX_STORAGE_ROW];
        char[] storage_row_Colors = new char[MAX_STORAGE_ROW];
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            storage_row_Tiles[storage_row] = current_player.storage.getStorageRow(storage_row).getTotalTilesNumber();
            storage_row_Colors[storage_row] = current_player.storage.getStorageRow(storage_row).getRowTilesColor();
        }

        displayStorage(current_player, storage_row_Tiles, storage_row_Colors);

        //MOSAIC
        displayMosaic(current_player);

        //FLOOR
        String floorStateString = current_player.floor.getStateString();
        displayFloor(floorStateString);

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

        undraggableTiles.clear();
        undraggableTiles.addAll(storageTiles);
        undraggableTiles.addAll(mosaicTiles);
        undraggableTiles.addAll(floorTiles);
        undraggableTiles.addAll(bagTiles);
        undraggableTiles.addAll(discardTiles);
        matrixBoard.getChildren().addAll(undraggableTiles);
    }


    private void displayDraggableBoard(SharedState ss){
        //System.out.println("displayUndraggableBoard");
        //FACTORIES
        displayFactories(ss);
        //CENTER
        displayCenter(ss);

        draggableTiles.clear();
        if(current_stage == DRAFTING_STAGE){

        }
        else if(current_stage == TILING_STAGE){
            draggableTiles.addAll(draggableStorageTiles);
        }
        else {

        }
        draggableTiles.addAll(draggableFactoriesTiles);
        draggableTiles.addAll(draggableCenterTiles);
        matrixBoard.getChildren().addAll(draggableTiles);
        matrixBoard.getChildren().addAll(firstplayerCenterTiles);
    }

    private void displayFactories(SharedState ss) {
        //System.out.println("  displayFactories");
        draggableFactoriesTiles.clear();
        for (int factory = 0; factory < FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; factory++) {
            for (int tiles = 0; tiles < FACTORY_SIZE; tiles++) {
                double x = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_x(tiles);
                double y = FACTORIES_COORDINATES.getFactoryCoordinates(factory).getPos_y(tiles);
                if (ss.factories.getFactory(factory).getTileColor(tiles) != NO_COLOR) {
                    DraggableTiles draggableTile = new DraggableTiles(x, y, COLORS_WITH_FIRST_PLAYER_IMAGE[ss.factories.getFactory(factory).getTileColor(tiles) - BLUE], this, FACTORY, factory, tiles);
                    draggableTile.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    draggableTile.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    draggableTile.setLayoutY(y);
                    draggableTile.setLayoutX(x);
                    draggableFactoriesTiles.add(draggableTile);
                }
            }
        }
    }

    private void displayCenter(SharedState ss) {
        //System.out.println("  displayCenter");
        draggableCenterTiles.clear();
        firstplayerCenterTiles.clear();
        for (int tiles = 0; tiles < CENTER_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER]; tiles++) {
            double x = CENTER_COORDINATES.getPos_x(tiles);
            double y = CENTER_COORDINATES.getPos_y(tiles);
            if (ss.center.getTileColor(tiles) == NO_COLOR) {

            }
            else if(ss.center.getTileColor(tiles) == FIRST_PLAYER){
                ImageView firstplayerTile = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[ss.center.getTileColor(tiles) - BLUE]));
                firstplayerTile.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                firstplayerTile.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                firstplayerTile.setLayoutY(y);
                firstplayerTile.setLayoutX(x);
                firstplayerCenterTiles.add(firstplayerTile);
            }
            else{
                DraggableTiles draggableTile = new DraggableTiles(x,y,COLORS_WITH_FIRST_PLAYER_IMAGE[ss.center.getTileColor(tiles) - BLUE],this,CENTER ,tiles);
                draggableTile.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                draggableTile.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                draggableTile.setLayoutY(y);
                draggableTile.setLayoutX(x);
                draggableCenterTiles.add(draggableTile);
            }
        }
    }

    private void displayStorage(nPlayer current_player, int[] storage_row_tiles, char[] storage_row_colors) {
        //System.out.println("  displayStorage");
        storageTiles.clear();
        draggableStorageTiles.clear();
        //System.out.println("    draggableStorageTiles size : " + draggableStorageTiles.size() + "  storageTiles size : " + storageTiles.size());
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            for (int tiles = 0; tiles < storage_row_tiles[storage_row]; tiles++) {
                int storage_col = storage_row - tiles;
                double x = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_x(storage_col);
                double y = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_y(storage_col);
                if(current_player.storage.getStorageRow(storage_row).isTilesFull() && (current_stage == TILING_STAGE)){
                    DraggableTiles draggableTile = new DraggableTiles(x,y,COLORS_IMAGE[storage_row_colors[storage_row] - BLUE],this, STORAGE ,storage_row , storage_col);
                    draggableTile.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    draggableTile.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    draggableTile.setLayoutY(y);
                    draggableTile.setLayoutX(x);
                    draggableStorageTiles.add(draggableTile);
                }
                else{
                    ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[storage_row_colors[storage_row] - BLUE]));
                    Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    Tile_View.setLayoutY(y);
                    Tile_View.setLayoutX(x);
                    storageTiles.add(Tile_View);
                }
            }
        }
        //System.out.println("    draggableStorageTiles size : " + draggableStorageTiles.size() + "  storageTiles size : " + storageTiles.size());
    }

    private void displayFloor(String floorState) {
        //ystem.out.println("  displayFloor");
        floorTiles.clear();
        char[] floor_chars = floorState.toCharArray();
        for (int tiles = 0; tiles < floor_chars.length; tiles++) {
            ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[floor_chars[tiles] - BLUE]));
            Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
            Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
            double x = FLOOR_COORDINATES.getPos_x(tiles);
            double y = FLOOR_COORDINATES.getPos_y(tiles);
            Tile_View.setLayoutX(x);
            Tile_View.setLayoutY(y);
            floorTiles.add(Tile_View);
        }
    }

    private void displayMosaic(nPlayer current_player) {
        //System.out.println("  displayMosaic");
        mosaicTiles.clear();
        for (int mosaic_row = 0; mosaic_row < MAX_MOSAIC_ROW; mosaic_row++) {
            for (int mosaic_col = 0; mosaic_col < MAX_MOSAIC_COL; mosaic_col++) {
                boolean mosaic_tile_exists = current_player.mosaic.getMosaicRow(mosaic_row).existsTile(mosaic_col);
                double x = MOSAIC_COORDINATES.getMosaicRowCoordinates(mosaic_row).getPos_x(mosaic_col);
                double y = MOSAIC_COORDINATES.getMosaicRowCoordinates(mosaic_row).getPos_y(mosaic_col);
                if (mosaic_tile_exists) {
                    char mosaic_tile_color = current_player.mosaic.getMosaicRow(mosaic_row).getTileColor(mosaic_col);
                    ImageView Tile_View = new ImageView(new Image(COLORS_WITH_FIRST_PLAYER_IMAGE[mosaic_tile_color - BLUE]));
                    Tile_View.setFitWidth(BIG_TILE_IMAGE_SIZE_X);
                    Tile_View.setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
                    Tile_View.setLayoutY(y);
                    Tile_View.setLayoutX(x);
                    mosaicTiles.add(Tile_View);
                }
            }
        }
    }

    private void displayBag(int[] bag_tiles) {
        //System.out.println("  displayBag");
        bagTiles.clear();
        for (int tile_color = 0; tile_color < bag_tiles.length; tile_color++){
            for(int tiles = 0; tiles < bag_tiles[tile_color]; tiles++){
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[tile_color]));
                Tile_View.setFitWidth(SMALL_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(SMALL_TILE_IMAGE_SIZE_Y);
                double x = BAG_COORDINATES.getPos_x(tiles + tile_color * MAX_BAG_EACH_COLOR_SIZE);
                double y = BAG_COORDINATES.getPos_y(tiles + tile_color * MAX_BAG_EACH_COLOR_SIZE);
                Tile_View.setLayoutX(x);
                Tile_View.setLayoutY(y);
                bagTiles.add(Tile_View);
            }
        }
    }

    private void displayDiscard(int[] discard_tiles) {
        //System.out.println("  displayDiscard");
        discardTiles.clear();
        for (int tile_color = 0; tile_color < discard_tiles.length; tile_color++){
            for(int tiles = 0; tiles < discard_tiles[tile_color]; tiles++){
                ImageView Tile_View = new ImageView(new Image(COLORS_IMAGE[tile_color]));
                Tile_View.setFitWidth(SMALL_TILE_IMAGE_SIZE_X);
                Tile_View.setFitHeight(SMALL_TILE_IMAGE_SIZE_Y);
                double x = DISCARD_COORDINATES.getPos_x(tiles + tile_color * MAX_DISCARD_EACH_COLOR_SIZE);
                double y = DISCARD_COORDINATES.getPos_y(tiles + tile_color * MAX_DISCARD_EACH_COLOR_SIZE);
                Tile_View.setLayoutX(x);
                Tile_View.setLayoutY(y);
                discardTiles.add(Tile_View);
            }
        }
    }

    private void displayScore(String scoreState) {

    }

    /**
     * Create a basic text field for input and a refresh button.
     */

    // setupViewer() is to start the Viewer, get the state and refresh it as the image shows
    public void setupViewer() {
        refreshDisplay();
    }

    public void refreshDisplay(){
        this.current_stage = multiazul.findCurrentStage(currentState);
        if(this.current_stage == NEXT_ROUND_STAGE){
            System.out.print(" Next Round : ");
            System.out.print(currentState[0]);
            System.out.print(" , ");
            System.out.println(currentState[1]);
            this.currentState = multiazul.nextRound(currentState);
            System.out.print(" Set to : ");
            System.out.print(currentState[0]);
            System.out.print(" , ");
            System.out.println(currentState[1]);
        }
        else if(this.current_stage == END_OF_GAME){
            this.currentState = multiazul.nextRound(currentState);
        }
        matrixBoard.getChildren().clear();
        //add backboard each time to empty the tiles which has been displayed
        ImageView boardA = new ImageView(new Image(EMPTY_BOARD_IMAGE));
        boardA.setFitWidth(1200);
        boardA.setFitHeight(500);
        boardA.setLayoutX(0);
        boardA.setLayoutY(15);
        matrixBoard.getChildren().add(boardA);
        displayState(currentState);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // makeControls() is to make control of the Viewer.
    private void makeControls(){
        setupViewer();
    }

    // start() is to show the Game start.
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        String[] state = new String[NUMBER_OF_STATES];
        // Empty bag, Discard 4 Players
        state[0] = "AF0abdd1bdee2bcce3aaac4bccc5adee6bbdd7aaee8cdnnCfB0000000000D0000000000";
        state[1] = "A0Md00c02e03b04b11e12d14d22c23e30S2a33a24e1FB0Ma01b02c03d04d10c12d23b34e40S1a12a13d14b5FC0Mc02d03e04b12a23a32S1d22c23e34c3FD0Md00c01e02b03a11c13d14b24d41b42c44S1e12e23b14a2F";

        this.currentState = state;

         */
        this.currentState = Game.currentState;

        window = primaryStage;
        window.setTitle("Azul Viewer");
        scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        window.setScene(scene);

        /*
        javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(50, 50, 100, 100);
        r.setFill(Color.RED);
        root.getChildren().add(r);

         */

        /*
        Rectangle r = new Rectangle(50, 50, 100, 100);
        r.setFill(Color.RED);
        root.getChildren().add(r);

         */
        root.getChildren().add(controls);
        root.getChildren().add(matrixBoard);

        makeControls();
        window.show();
    }

    public void setState(String[] currentstate){
        this.currentState = currentstate;
    }

    public void setMove(String move){
        this.move = move;
    }

    public void setRanks(HashMap<Character, PlayersInformation.PlayerInfo> playerMap){
        int[] score_array = new int[PLAYER_NUMBER];

        for(int i=0; i < PLAYER_NUMBER; i++){
            score_array[i] = playerMap.get(ALL_PLAYERS[i]).getScore();
        }
        Arrays.sort(score_array);

        for(int rank = PLAYER_NUMBER; rank >= 1; rank--){
            for( char player : ALL_PLAYERS){
                if(playerMap.get(player).getScore() == score_array[PLAYER_NUMBER - rank]){
                    playerMap.get(player).setRank(rank);
                }
            }
        }
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

    public SnappableTiles findNearestRectangle(double x, double y){
        int i = 0;
        double distance = 0;
        int closestID = -1;
        for (SnappableTiles t : snappableTiles){
            if (i == 0){
                distance = t.distance(x,y);
                closestID = i;
            }else if(t.distance(x,y) < distance){
                closestID = i;
                distance = t.distance(x,y);
            }
            i++;
        }
        //System.out.print("closestID : " + closestID + " distance : " + distance);
        //System.out.println(" snappableTiles x, y : " + snappableTiles.get(closestID).x + ", " + snappableTiles.get(closestID).y);
        return snappableTiles.get(closestID);
    }

    public void highlightNearestRectangle(double x, double y){
        int i=0;
        highlighted = findNearestRectangle(x,y);
        ArrayList<SnappableTiles> ts = this.snappableTiles;
        for (SnappableTiles t : ts){
            if (t.equals(this.highlighted)){
                if(t.toStorage()){
                    SnappableTiles new_t = new SnappableTiles(t.x, t.y, HIGHLIGHT_IMAGE, this, STORAGE, t.index, t.tile_num);
                    //System.out.println(" Snap at : " + new_t.index);
                    this.snappableTiles.set(i,new_t);
                }
                else if(t.toFloor()){
                    SnappableTiles new_t = new SnappableTiles(t.x, t.y, HIGHLIGHT_IMAGE, this, FLOOR, t.tile_num);
                    //System.out.println(" Snap at : " + "F");
                    this.snappableTiles.set(i,new_t);
                }

            }
            else {
                if(t.toStorage()){
                    SnappableTiles new_t = new SnappableTiles(t.x, t.y, NO_TILE_IMAGE, this, STORAGE, t.index, t.tile_num);
                    this.snappableTiles.set(i,new_t);
                }
                else if(t.toFloor()){
                    SnappableTiles new_t = new SnappableTiles(t.x, t.y, NO_TILE_IMAGE, this, FLOOR, t.tile_num);
                    this.snappableTiles.set(i,new_t);
                }
            }
            i++;
        }
    }

}


