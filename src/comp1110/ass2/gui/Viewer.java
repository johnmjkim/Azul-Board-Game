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

        /*
        // VALID STATES : Drafting Stage
        state[0] = "BF0cdee1bdde4aaaeCbbbdeB1616181614D0000000000";
        state[1] = "A0MS2c14a1FB0MS2e1Ff";

        // VALID STATES : Tiling Stage to Mosaic
        state[0]="AFCB1616181614D0000000000";
        state[1]="A0MS0e11b22c13a34a1FbeeeeB0MS0c11b12e13d4Ff";

         */

        // VALID STATES : Tiling Stage to Floor
        //state[0]="AFCB1616181614D0000000000";
        //state[1]="A0MS0e11b22c13a34a1FbeeeeB0MS0c11b12e13d4Ff";

        /*
        // VALID STATES : Next Round Stage
        state[0]="AFCB1616181614D0001000300";
        state[1]="A2Me04b11S2c13a34a1FbeeeeB2Mc02d33S1b12e1Ff";

        // VALID STATES : No More Next Round End of Game
        state[0]="BFCB0609090913D0003010402";
        state[1]="A29Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FabdfB12Mb00a01c02e03d04c10d12a13b14c21b23e24b31d33S2a13c44a4Fa";

        // VALID STATES : End of Game
        state[0]="AFCfB0609090913D0204040502";
        state[1]="A35Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FB19Mb00a01c02e03d04c10d12a13b14c21b23e24b31d33c34S2a14a4F";
         */

        // Four players example
        //state[0] = "AF0bcce1abcd2adde3bbce4aabe5abde6acde7aadd8bdddCfB1213151014D0000000000";
        //state[1] = "A0MSFB0MSFC0MSFD0MSF";

        System.out.println(" valid : " + multiazul.isStateValid(state) + " stage : " + multiazul.findCurrentStage(state));

        this.currentState = state;
        this.current_stage = multiazul.findCurrentStage(currentState);

        ss = new SharedState(state[0], PLAYER_NUMBER);
        ps = new PlayerState(state[1], PLAYER_NUMBER);

        String current_player_turn = ss.getTurnState();
        this.current_turn = current_player_turn.charAt(0);
        nPlayer current_player = ps.getnPlayer(current_turn);

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
    }

    private void display_empty_Board(char current_stage){
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
        storageTiles.clear();
        draggableStorageTiles.clear();
        for (int storage_row = 0; storage_row < MAX_STORAGE_ROW; storage_row++) {
            for (int tiles = 0; tiles < storage_row_tiles[storage_row]; tiles++) {
                int storage_col = storage_row - tiles;
                double x = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_x(storage_col);
                double y = STORAGE_COORDINATES.getStorageRowCoordinates(storage_row).getPos_y(storage_col);
                if(current_player.storage.getStorageRow(storage_row).isTilesFull()){
                    DraggableTiles draggableTile = new DraggableTiles(x,y,COLORS_IMAGE[storage_row_colors[storage_row] - BLUE],this, STORAGE ,storage_row , tiles);
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
    }

    private void displayFloor(String floorState) {
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
            refreshDisplay();
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(playerLabel, playerTextField, boardLabel,
                boardTextField, RefreshButton);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

    }

    public void refreshDisplay(){
        matrixBoard.getChildren().clear();
        //Rectangle r = new Rectangle(510, 550, 300, 30);
        //r.setFill(Color.WHITE);
        //controls.getChildren().add(r);
        //add backboard each time to empty the tiles which has been displayed
        ImageView boardA = new ImageView(new Image(EMPTY_BOARD_IMAGE));
        boardA.setFitWidth(1200);
        boardA.setFitHeight(500);
        boardA.setLayoutX(0);
        boardA.setLayoutY(15);
        //boardA.setOpacity(0.2);
        matrixBoard.getChildren().add(boardA);

        displayState(currentState);
        //displayState(new String[]{playerTextField.getText(), boardTextField.getText()});
    }

    private void ChoosePlayers() {
        Label playerNumber = new Label("Choose Number of Players: ");
        Button TwoPlayer = new Button("2 Players");
        Button ThreePlayer = new Button("3 Players");
        Button FourPlayer = new Button("4 Players");

        HBox hb1 = new HBox(playerNumber,TwoPlayer,ThreePlayer,FourPlayer);
        hb1.setSpacing(15);
        hb1.setLayoutX(400);
        hb1.setLayoutY(226);
        controls.getChildren().add(hb1);

        TwoPlayer.setOnAction(ae -> {
            CoverButtonsInChoosePlayer();
            LabelPlayerOne();
            PlayerTwo();
            PlayWithComputer();
        });
        ThreePlayer.setOnAction(ae -> {
            CoverButtonsInChoosePlayer();
            LabelPlayerOne();
            PlayerTwo();
            PlayerThree();
            PlayWithComputer();
        });
        FourPlayer.setOnAction(ae -> {
            CoverButtonsInChoosePlayer();
            LabelPlayerOne();
            PlayerTwo();
            PlayerThree();
            PlayerFour();
            PlayWithComputer();
        });
    }

    public void StartAzulGame() {
        Button StartGame = new Button("Start Azul Game");
        StartGame.setLayoutX(632);
        StartGame.setLayoutY(470);
        controls.getChildren().add(StartGame);
        StartGame.setOnAction(ae -> {
            setupViewer();
            CoverChoosePlayerPage();
        });
    }

    public void StartAzulGameWithComputer() {
        Button StartGame = new Button("Start Azul Game with computer");
        StartGame.setLayoutX(400);
        StartGame.setLayoutY(470);
        controls.getChildren().add(StartGame);
        StartGame.setOnAction(ae -> {
            setupViewer();
            CoverChoosePlayerPage();
        });
    }

    public void CoverChoosePlayerPage() {
        ImageView white = new ImageView(new Image("file:src/comp1110/ass2/img/White.png"));
        white.setLayoutX(400);
        white.setLayoutY(220);
        white.setFitHeight(300);
        white.setFitWidth(500);
        controls.getChildren().add(white);
    }

    public void CoverButtonsInChoosePlayer() {
        ImageView white = new ImageView(new Image("file:src/comp1110/ass2/img/White.png"));
        white.setLayoutX(400);
        white.setLayoutY(270);
        white.setFitHeight(300);
        white.setFitWidth(500);
        controls.getChildren().add(white);
    }

    public void CoverStartButton() {
        ImageView white = new ImageView(new Image("file:src/comp1110/ass2/img/White.png"));
        white.setLayoutX(390);
        white.setLayoutY(470);
        white.setFitHeight(30);
        white.setFitWidth(500);
        controls.getChildren().add(white);
    }

    public void CoverPlayerLabel() {
        ImageView white = new ImageView(new Image("file:src/comp1110/ass2/img/White.png"));
        white.setLayoutX(555);
        white.setLayoutY(269);
        white.setFitHeight(30);
        white.setFitWidth(160);
        controls.getChildren().add(white);
    }

    public void PlayWithComputer() {
        Label FirstPlayer = new Label("Do you want to set Player 1 as a computer player?");
        FirstPlayer.setLayoutX(400);
        FirstPlayer.setLayoutY(400);
        controls.getChildren().add(FirstPlayer);
        Button NeedComputerPlayer = new Button("Yes ( Player 1 don't need a name )");
        Button DoNotNeedComputerPlayer = new Button("No ( Player 1 need a name )");
        NeedComputerPlayer.setOnAction(ae -> {
            CoverPlayerLabel();
            CoverStartButton();
            StartAzulGameWithComputer();
        });
        DoNotNeedComputerPlayer.setOnAction(ae -> {
            CoverStartButton();
            TextFieldPlayerOne();
            StartAzulGame();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(NeedComputerPlayer,DoNotNeedComputerPlayer);
        hb.setSpacing(20);
        hb.setLayoutX(400);
        hb.setLayoutY(430);
        controls.getChildren().add(hb);
    }

    public void LabelPlayerOne() {
        Label FirstPlayer = new Label("Name of Player 1: ");
        FirstPlayer.setLayoutX(400);
        FirstPlayer.setLayoutY(270);
        controls.getChildren().add(FirstPlayer);
    }

    public void TextFieldPlayerOne() {
        TextField PlayerTextField1;
        PlayerTextField1 = new TextField();
        PlayerTextField1.setPrefWidth(150);
        PlayerTextField1.setLayoutX(556);
        PlayerTextField1.setLayoutY(270);
        controls.getChildren().add(PlayerTextField1);
        PlayerTextField1.getText();
    }

    public void PlayerTwo() {
        TextField PlayerTextField2;
        Label SecondPlayer = new Label("Name of Player 2: ");
        PlayerTextField2 = new TextField();
        PlayerTextField2.setPrefWidth(150);
        HBox hb = new HBox();
        hb.getChildren().addAll(SecondPlayer, PlayerTextField2);
        hb.setSpacing(50);
        hb.setLayoutX(400);
        hb.setLayoutY(300);
        controls.getChildren().add(hb);
    }

    public void PlayerThree() {
        TextField PlayerTextField3;
        Label ThirdPlayer = new Label("Name of Player 3: ");
        PlayerTextField3 = new TextField();
        PlayerTextField3.setPrefWidth(150);
        HBox hb = new HBox();
        hb.getChildren().addAll(ThirdPlayer, PlayerTextField3);
        hb.setSpacing(50);
        hb.setLayoutX(400);
        hb.setLayoutY(330);
        controls.getChildren().add(hb);
    }

    public void PlayerFour() {
        TextField PlayerTextField4;
        Label FourthPlayer = new Label("Name of Player 4: ");
        PlayerTextField4 = new TextField();
        PlayerTextField4.setPrefWidth(150);
        HBox hb = new HBox();
        hb.getChildren().addAll(FourthPlayer, PlayerTextField4);
        hb.setSpacing(50);
        hb.setLayoutX(400);
        hb.setLayoutY(360);
        controls.getChildren().add(hb);
    }

    public static void main(String[] args) {
        launch(args);
    }
    private void start_page() {
        matrixBoard.getChildren().clear();
        controls.getChildren().clear();

        Rectangle r1 = new Rectangle(50, 548, 1200, 30);
        r1.setFill(Color.WHITE);
        controls.getChildren().add(r1);

        Button WelcomeStartButton = new Button("Start");
        Button WelcomeExitButton = new Button("Exit ");

        ImageView boardA = new ImageView(new Image(WELCOME_PAGE_IMAGE));
        boardA.setFitWidth(1200);
        boardA.setFitHeight(600);
        boardA.setLayoutX(0);
        boardA.setLayoutY(15);
        boardA.setOpacity(1.0);
        matrixBoard.getChildren().add(boardA);

        WelcomeStartButton.setLayoutX(430);
        WelcomeStartButton.setLayoutY(360);
        WelcomeStartButton.setPrefSize(120,50);
        matrixBoard.getChildren().add(WelcomeStartButton);

        WelcomeExitButton.setLayoutX(630);
        WelcomeExitButton.setLayoutY(360);
        WelcomeExitButton.setPrefSize(120,50);
        matrixBoard.getChildren().add(WelcomeExitButton);

        WelcomeStartButton.setOnAction(ae -> {

            //boardA.setOpacity(0);
            matrixBoard.getChildren().clear();
            controls.getChildren().clear();
            /*setupViewer();*/

            ChoosePlayers();

            Button GameExitButton = new Button("Exit ");
            Button GameNextRoundButton = new Button("Next round");

            GameExitButton.setOnAction(ae1 -> {
                System.exit(0);
            });
            GameNextRoundButton.setOnAction(ae1 -> {
                //refreshDisplay();
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

    }

    // makeControls() is to make control of the Viewer.
    private void makeControls() {
        start_page();
    }

    // start() is to show the Game start.
    @Override
    public void start(Stage primaryStage) throws Exception {
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
        this.currentState = currentState;
    }

    public void setMove(String move){
        this.move = move;
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
        System.out.print("closestID : " + closestID + " distance : " + distance);
        System.out.println(" snappableTiles x, y : " + snappableTiles.get(closestID).x + ", " + snappableTiles.get(closestID).y);
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
                    System.out.println(" Snap at : " + new_t.index);
                    this.snappableTiles.set(i,new_t);
                }
                else if(t.toFloor()){
                    SnappableTiles new_t = new SnappableTiles(t.x, t.y, HIGHLIGHT_IMAGE, this, FLOOR, t.tile_num);
                    System.out.println(" Snap at : " + "F");
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


