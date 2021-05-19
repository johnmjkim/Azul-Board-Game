package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import comp1110.ass2.backend.MultiAzul;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Si Bo, Hu
 * We show all the game pages in this class, include Playsetting, welcomePage, Viewer
 * We can play the Game here
 */
public class Game extends Application implements Constants {

    Group controls = new Group();
    Group matrixBoard = new Group();

    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 600;

    public static int PLAYER_NUMBER;
    public static int FACTORY_MAX_NUMBER;

    public static String[] currentState;
    public static boolean end_stage;
    public Viewer viewer;

    public static ArrayList<Integer> playerOrders = new ArrayList<Integer>();
    public static ArrayList<String> playerNames = new ArrayList<String>();
    public static ArrayList<Character> playerTypes = new ArrayList<Character>();
    public static ArrayList<PlayersInformation.PlayerInfo> playerInfos = new ArrayList<PlayersInformation.PlayerInfo>();
    public static HashMap<Character, PlayersInformation.PlayerInfo> playerMap = new HashMap<Character, PlayersInformation.PlayerInfo>();

    public static StorageCoordinates STORAGE_COORDINATES;
    public static FloorCoordinates FLOOR_COORDINATES;
    public static MosaicCoordinates MOSAIC_COORDINATES;
    public static BagCoordinates BAG_COORDINATES;
    public static DiscardCoordinates DISCARD_COORDINATES;
    public static CenterCoordinates CENTER_COORDINATES;
    public static FactoriesCoordinates FACTORIES_COORDINATES;
    public static ArrayList<OtherStorageCoordinates> OTHER_STORAGE_COORDINATES_GROUP = new ArrayList<>();
    public static ArrayList<OtherFloorCoordinates> OTHER_FLOOR_COORDINATES_GROUP = new ArrayList<>();
    public static ArrayList<OtherMosaicCoordinates> OTHER_MOSAIC_COORDINATES_GROUP = new ArrayList<>();

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

        end_stage = false;

        root.getChildren().add(controls);
        root.getChildren().add(matrixBoard);

        //start_page();
        //PlayerSetting playerSetting = new PlayerSetting();
        //playerSetting.main();

        start_page();

        stage.setScene(scene);
        stage.show();

        //Viewer viewer = new Viewer();
        //viewer.start(stage);

        //Create draggable tiles

        //new PlayerSetting();
    }

    private void decidePlayers(){
        //PLAYER_NUMBER = DEFAULT_MAX_PLAYER;
        //PLAYER_NUMBER = 4;
        PLAYER_NUMBER = PlayerSetting.PLAYER_NUMBER;
        FACTORY_MAX_NUMBER = FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER];
        /*
        String[] temporary_names = new String[] {"Player 1", "Player 2", "Player 3", "Player 4"};
        char[] temporary_types = new char[] {HUMAN_PLAYER, COMPUTER_PLAYER , COMPUTER_PLAYER, COMPUTER_PLAYER};
        for(int i=0; i < PLAYER_NUMBER; i++){
            playerNames.add(temporary_names[i]);
            playerTypes.add(temporary_types[i]);
        }

         */

        playerTypes = PlayerSetting.playerTypesSetting;
        playerNames = PlayerSetting.playerNamesSetting;
        /*
        for(int i=0; i < PLAYER_NUMBER; i++){
            System.out.println(playerTypes.get(i));
        }
        for(int i=0; i < PLAYER_NUMBER; i++){
            System.out.println(playerNames.get(i));
        }

         */

        randomizeOrders();
        /*
        for(int i=0; i < PLAYER_NUMBER; i++){
            System.out.println(playerOrders.get(i));
        }

         */

        PlayersInformation playersinformation = new PlayersInformation(playerOrders, playerNames, playerTypes);
        playerInfos = playersinformation.getPlayerInfos();
        playerMap = playersinformation.getPlayerMap();
        //System.out.println(playersinformation);

        /*
        for( char player_turn : ALL_PLAYERS ){
            System.out.print(" Turn : " + player_turn + playerMap.get(player_turn));
        }

         */
    }

    private void randomizeOrders(){
        Random r = new Random();
        int bound = PLAYER_NUMBER;
        for(int i=0; i < bound; i++){
            int rand_num = r.nextInt(bound);
            if(i == 0){
                playerOrders.add(rand_num);
            }
            else{
                boolean repeated = false;
                for(int j=0; j < playerOrders.size(); j++){
                    if(playerOrders.get(j) == rand_num){
                        repeated = true;
                    }
                }
                if(repeated){
                    i--;
                }
                else{
                    playerOrders.add(rand_num);
                }
            }
        }
    }

    private void initializeStates() {
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);
        currentState = multiazul.setInitalStates(PLAYER_NUMBER);
        currentState = multiazul.setStartingState(currentState);
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

        // Coordinates for other storages
        int[] max_row_col = new int[]{MAX_STORAGE_ROW, MAX_STORAGE_COL};
        double[] initial_xy = new double[]{INITIAL_OTHER_STORAGE_IMAGE_POS_X,INITIAL_OTHER_STORAGE_IMAGE_POS_Y};
        double[] gap_xy = new double[]{SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP,SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP};
        for(int i=0; i < PLAYER_NUMBER - 1; i++){
            OTHER_STORAGE_COORDINATES_GROUP.add(new OtherStorageCoordinates(max_row_col, initial_xy, gap_xy));
            initial_xy[0] += OTHER_BOARD_GAP_X;
        }

        // Coordinates for other floors
        max_row_col = new int[]{1, MAX_FLOOR_TILES_COL_IMAGE};
        initial_xy = new double[]{INITIAL_OTHER_FLOOR_IMAGE_POS_X,INITIAL_OTHER_FLOOR_IMAGE_POS_Y};
        gap_xy = new double[]{SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP,SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP};
        for(int i=0; i < PLAYER_NUMBER - 1; i++){
            OTHER_FLOOR_COORDINATES_GROUP.add(new OtherFloorCoordinates(max_row_col, initial_xy, gap_xy));
            initial_xy[0] += OTHER_BOARD_GAP_X;
        }

        // Coordinates for other mosaics
        max_row_col = new int[]{MAX_MOSAIC_ROW, MAX_MOSAIC_COL};
        initial_xy = new double[]{INITIAL_OTHER_MOSAIC_IMAGE_POS_X,INITIAL_OTHER_MOSAIC_IMAGE_POS_Y};
        gap_xy = new double[]{SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP,SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP};
        for(int i=0; i < PLAYER_NUMBER - 1; i++){
            OTHER_MOSAIC_COORDINATES_GROUP.add(new OtherMosaicCoordinates(max_row_col, initial_xy, gap_xy));
            initial_xy[0] += OTHER_BOARD_GAP_X;
        }
        /*
        for (int i=0; i < PLAYER_NUMBER - 1; i++){
            System.out.print("Other PLayer " + i + " ");
            System.out.println(OTHER_STORAGE_COORDINATES_GROUP.get(i));
        }

        for (int i=0; i < PLAYER_NUMBER - 1; i++){
            System.out.print("Other PLayer " + i + " ");
            System.out.println(OTHER_FLOOR_COORDINATES_GROUP.get(i));
        }

        for (int i=0; i < PLAYER_NUMBER - 1; i++){
            System.out.print("Other PLayer " + i + " ");
            System.out.println(OTHER_MOSAIC_COORDINATES_GROUP.get(i));
        }

         */
    }

    private void start_page() {
        matrixBoard.getChildren().clear();
        controls.getChildren().clear();

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

        //String mes = String.valueOf(PlayerSetting.comboBoxP5.getSelectedItem());
        //System.out.println(mes);

        WelcomeStartButton.setOnAction(ae -> {
                if(PlayerSetting.game_starts){
                    decidePlayers();
                    initializeStates();
                    makeCoordinates();

                    try {
                        new Viewer().start(new Stage());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    new PlayerSetting();
                }
        }
        );

        WelcomeExitButton.setOnAction(ae -> {
            System.exit(0);
        });
    }



/*
        WelcomeStartButton.setOnAction(ae -> {

            //boardA.setOpacity(0);
            matrixBoard.getChildren().clear();
            controls.getChildren().clear();
            //setupViewer();

            //ChoosePlayers();
            //setupViewer();

            Button GameExitButton = new Button("Exit ");
            //Button GameNextRoundButton = new Button("Next round");

            GameExitButton.setOnAction(ae1 -> {
                System.exit(0);
            });
            //GameNextRoundButton.setOnAction(ae1 -> {

                //if(current_stage == NEXT_ROUND_STAGE){
                //    currentState = multiazul.nextRound(currentState);
                //}
                //setupViewer();
            //});

            HBox hb1 = new HBox(GameExitButton);
            hb1.setSpacing(10);
            hb1.setLayoutX(1000);
            hb1.setLayoutY(550);
            controls.getChildren().add(hb1);
        });

        WelcomeExitButton.setOnAction(ae -> {
            System.exit(0);
        });*/

    }
