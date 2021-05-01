package comp1110.ass2.gui;

import comp1110.ass2.Azul;
import comp1110.ass2.Constants;
import comp1110.ass2.PlayerState;
import comp1110.ass2.SharedState;
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


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     *              TASK 4
     */


    void displayState(String[] state) {
        // FIXME Task 4: implement the simple state viewer

        state[0]="AF0cdde1bbbe2abde3cdee4bcceCaabbcebbeecddaafB1915161614D2020202019";
        state[1]="A07Me01a11d20b30b41S0a11b22c13c44d1FeeabB08Md03b13e23c32b41S0b11c12a33d24e4Fabcc";

        SharedState ss = new SharedState(state[0], DEFAULT_MAX_PLAYER);
        PlayerState ps = new PlayerState(state[1], DEFAULT_MAX_PLAYER);

        String player_state_of_A = state[1].substring(state[1].indexOf("A"), state[1].indexOf("B"));
        String player_state_of_B = state[1].substring(state[1].indexOf("B"));

        //center
        String center = state[0].substring(state[0].indexOf("C") + 1, state[0].indexOf("B"));

        String[] Center = center.split("");
        for (int i = 0; i < Center.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Center[i] + ".png"));
            A.setFitWidth(16);
            A.setFitHeight(18);
            if (i < 23) {
                int x = 420 + 17 * i;
                int y = 48;
                A.setLayoutY(y);
                A.setLayoutX(x);
            }
            if (i >= 23 & i < 46) {
                int x = 420 + 17 * (i - 23);
                int y = 48 + 19;
                A.setLayoutY(y);
                A.setLayoutX(x);
            }
            if (i >= 46 & i < 69) {
                int x = 420 + 17 * (i - 46);
                int y = 48 + 19 * 2;
                A.setLayoutY(y);
                A.setLayoutX(x);
            }
            if (i >= 69 & i < 92) {
                int x = 420 + 17 * (i - 69);
                int y = 48 + 19 * 3;
                A.setLayoutY(y);
                A.setLayoutX(x);
            }
            matrixBoard.getChildren().add(A);
        }

        //BAG
        String bag = state[0].substring(state[0].indexOf("B") + 1, state[0].indexOf("D"));
        String[] Bag = bag.split("");
        String[] newS = {"a", "", "b", "", "c", "", "d", "", "e", ""};
        String alphabet_Bag = "";
        for (int i = 0; i < Bag.length; i += 2) {
            for (int j = 0; j < Integer.parseInt(Bag[i]) * 10 + Integer.parseInt(Bag[i + 1]); j++) {
                alphabet_Bag += newS[i];
            }
        }
        String[] Alphabet_Bag = alphabet_Bag.split("");

        for (int i = 0; i < alphabet_Bag.length(); i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_Bag[i] + ".png"));
            A.setFitWidth(16);
            A.setFitHeight(17);
            if (i < 21) {
                double x = 826 + 17 * i;
                A.setLayoutX(x);
                double y = 227;
                A.setLayoutY(y);
            }
            if (i >= 21 & i < 42) {
                double x = 826 + 17 * (i - 21);
                A.setLayoutX(x);
                double y = 227 + 18;
                A.setLayoutY(y);
            }
            if (i >= 42 & i < 63) {
                double x = 826 + 17 * (i - 42);
                A.setLayoutX(x);
                double y = 227 + 18 * 2;
                A.setLayoutY(y);
            }
            if (i >= 63 & i < 84) {
                double x = 826 + 17 * (i - 63);
                A.setLayoutX(x);
                double y = 227 + 18 * 3;
                A.setLayoutY(y);
            }
            if (i >= 84 & i <= 100) {
                double x = 826 + 17 * (i - 84);
                A.setLayoutX(x);
                double y = 227 + 18 * 4;
                A.setLayoutY(y);
            }
            matrixBoard.getChildren().add(A);
        }

        //DISCARD
        String discard = state[0].substring(state[0].indexOf("D") + 1);
        String[] Discard = discard.split("");
        String[] newD = {"a", "", "b", "", "c", "", "d", "", "e", ""};
        String alphabet_discard = "";
        for (int i = 0; i < Discard.length; i += 2) {
            for (int j = 0; j < Integer.parseInt(Discard[i]) * 10 + Integer.parseInt(Discard[i + 1]); j++) {
                alphabet_discard += newD[i];
            }
        }
        String[] Alphabet_Discard = alphabet_discard.split("");
        for (int i = 0; i < alphabet_discard.length(); i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_Discard[i] + ".png"));
            A.setFitWidth(16);
            A.setFitHeight(17);
            if (i < 21) {
                double x = 826 + 17 * i;
                A.setLayoutX(x);
                double y = 48;
                A.setLayoutY(y);
            }
            if (i >= 21 & i < 42) {
                double x = 826 + 17 * (i - 21);
                A.setLayoutX(x);
                double y = 48 + 18;
                A.setLayoutY(y);
            }
            if (i >= 42 & i < 63) {
                double x = 826 + 17 * (i - 42);
                A.setLayoutX(x);
                double y = 48 + 18 * 2;
                A.setLayoutY(y);
            }
            if (i >= 63 & i < 84) {
                double x = 826 + 17 * (i - 63);
                A.setLayoutX(x);
                double y = 48 + 18 * 3;
                A.setLayoutY(y);
            }
            if (i >= 84 & i <= 100) {
                double x = 826 + 17 * (i - 84);
                A.setLayoutX(x);
                double y = 48 + 18 * 4;
                A.setLayoutY(y);
            }
            matrixBoard.getChildren().add(A);
        }

        //FACTORIES
        {String factories = state[0].substring(state[0].indexOf("F") + 1, state[0].indexOf("C")+1);
        String[] Factories = factories.split("");
        //0cdde1bbbe2abde3cdee4bcceC

        String num_of_factory = "";
        String num_of_factory_withoutC = "";
            for (int i = 0; i < factories.length(); i++) {
                if (Factories[i].equals("0") || Factories[i].equals("1") || Factories[i].equals("2") || Factories[i].equals("3") || Factories[i].equals("4") || Factories[i].equals("C")) {
                    num_of_factory += Factories[i];
                }
                if (Factories[i].equals("0") || Factories[i].equals("1") || Factories[i].equals("2") || Factories[i].equals("3") || Factories[i].equals("4")) {
                    num_of_factory_withoutC += Factories[i];
                }
            }
            String[] Num_of_factory = {};
        Num_of_factory = num_of_factory.split("");
            String[] Num_of_factory_withoutC = {};
        Num_of_factory_withoutC = num_of_factory_withoutC.split("");
        //0,1,2,3,4,c,&0,1,2,3,4

        String[] tiles_in_factories = {"nnnn", "nnnn", "nnnn", "nnnn", "nnnn", "", ""};
        for (int i = 0; i < Num_of_factory_withoutC.length; i++) {
            String start ="";
                    start = Num_of_factory[i];
            String end ="";
                    end = Num_of_factory[i + 1];
            tiles_in_factories[Integer.parseInt(Num_of_factory[i])] = factories.substring(factories.indexOf(start)+1, factories.indexOf(end));
        }
        //AF0cdde1bbbe2abde3cdee4bcceCaabbcebbeecddaafB1915161614D2020202019,cdde,bbbe,abde,cdee

        for (int i = 0; i < tiles_in_factories.length; i++) {
            if (4 - tiles_in_factories[i].length() == 0) {
                tiles_in_factories[i] = tiles_in_factories[i];
            }
            if (4 - tiles_in_factories[i].length() == 1) {
                tiles_in_factories[i] = tiles_in_factories[i] + "n";
            }
            if (4 - tiles_in_factories[i].length() == 2) {
                tiles_in_factories[i] = tiles_in_factories[i] + "n" + "n";
            }
            if (4 - tiles_in_factories[i].length() == 3) {
                tiles_in_factories[i] = tiles_in_factories[i] + "n" + "n" + "n";
            }
        }

        String full_srting_of_factories = "";
        int i = 0;
        do {
            full_srting_of_factories += tiles_in_factories[i];
            i++;
        } while (i < 5);
        String[] Full_srting_of_factories=full_srting_of_factories.split("");
            System.out.println(full_srting_of_factories);

        String alphabet_1 ="";
        for (int j=0; j<full_srting_of_factories.length(); j+=4){
            alphabet_1 += Full_srting_of_factories[j];
        }
        String[] Alphabet_1 = alphabet_1.split("");
        System.out.println(Alphabet_1[0]+"+"+Alphabet_1[1]+"+"+Alphabet_1[2]+"+"+Alphabet_1[3]+"+"+Alphabet_1[4]);

        String alphabet_2 ="";
        for (int j=1; j<full_srting_of_factories.length(); j+=4){
            alphabet_2 += Full_srting_of_factories[j];
        }
        String[] Alphabet_2 = alphabet_2.split("");

        String alphabet_3 ="";
        for (int j=2; j<full_srting_of_factories.length(); j+=4){
            alphabet_3 += Full_srting_of_factories[j];
        }
        String[] Alphabet_3 = alphabet_3.split("");
        String alphabet_4 ="";
        for (int j=3; j<full_srting_of_factories.length(); j+=4){
            alphabet_4 += Full_srting_of_factories[j];
        }
        String[] Alphabet_4 = alphabet_4.split("");

        int[] all_numbers = {0,1,2,3,4};
        for (i = 0; i < all_numbers.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_1[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            double x = 5 + 41.5 * 2* (all_numbers[i]);
            A.setLayoutX(x);
            double y = 49;
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
            ImageView B = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_2[i] + ".png"));
            B.setFitWidth(35);
            B.setFitHeight(39);
            double x1 = 42.2 + 41.5 * 2* (all_numbers[i]);
            B.setLayoutX(x1);
            double y1 = 49;
            B.setLayoutY(y1);
            matrixBoard.getChildren().add(B);
            ImageView C = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_3[i] + ".png"));
            C.setFitWidth(35);
            C.setFitHeight(39);
            double x2 = 5 + 41.5 * 2* (all_numbers[i]);
            C.setLayoutX(x2);
            double y2 = 91;
            C.setLayoutY(y2);
            matrixBoard.getChildren().add(C);
            ImageView D = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_4[i] + ".png"));
            D.setFitWidth(35);
            D.setFitHeight(39);
            double x3 = 42.2 + 41.5 * 2* (all_numbers[i]);
            D.setLayoutX(x3);
            double y3 = 91;
            D.setLayoutY(y3);
            matrixBoard.getChildren().add(D);
        }}

        //floor
        String floor_A = player_state_of_A.substring(player_state_of_A.indexOf("F") + 1);
        String[] Floor_A = floor_A.split("");
        for (int i = 0; i < Floor_A.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Floor_A[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            int x = 5 + 39 * i;
            int y = 468;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }

        String floor_B = player_state_of_B.substring(player_state_of_B.indexOf("F") + 1);
        String[] Floor_B = floor_B.split("");
        for (int i = 0; i < Floor_B.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Floor_B[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            Double x = 415 + 38.5 * i;
            int y = 467;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }


        //STORAGE
        String storage_A = player_state_of_A.substring(player_state_of_A.indexOf("S") + 1, player_state_of_A.indexOf("F"));
        String[] Storage_A = storage_A.split("");

        String row_A1 = "";
        for (int i = 0; i < Storage_A.length; i += 3) {
            row_A1 += Storage_A[i];
        }
        String[] Row_A1 = row_A1.split("");

        String alphabet_A1 = "";
        for (int i = 1; i < Storage_A.length; i += 3) {
            alphabet_A1 += Storage_A[i];
        }
        String[] Alphabet_A1 = alphabet_A1.split("");

        String number_A1 = "";
        for (int i = 2; i < Storage_A.length; i += 3) {
            number_A1 += Storage_A[i];
        }
        String[] Number_A1 = number_A1.split("");

        for (int i = 0; i < number_A1.length(); i++) {
            for (int j = 0; j < Integer.parseInt(Number_A1[i]); j++){
                ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_A1[i] + ".png"));
                A.setFitWidth(35);
                A.setFitHeight(39);
                double Y1 = Double.parseDouble(Row_A1[i]);
                Double x = 6 + 38.0 * (4 - j);
                Double y = 227 + 42.9 * Y1;
                A.setLayoutX(x);
                A.setLayoutY(y);
                matrixBoard.getChildren().add(A);
            }
        }


        String storage_B = player_state_of_B.substring(player_state_of_B.indexOf("S") + 1, player_state_of_B.indexOf("F"));
        String[] Storage_B= storage_B.split("");

        String row_B1 = "";
        for (int i = 0; i < Storage_B.length; i += 3) {
            row_B1 += Storage_B[i];
        }
        String[] Row_B1 = row_B1.split("");

        String alphabet_B1 = "";
        for (int i = 1; i < Storage_B.length; i += 3) {
            alphabet_B1 += Storage_B[i];
        }
        String[] Alphabet_B1 = alphabet_B1.split("");

        String number_B1 = "";
        for (int i = 2; i < Storage_B.length; i += 3) {
            number_B1 += Storage_B[i];
        }
        String[] Number_B1 = number_B1.split("");

        for (int i = 0; i < number_B1.length(); i++) {
            for (int j = 0; j < Integer.parseInt(Number_B1[i]); j++){
                ImageView B = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_B1[i] + ".png"));
                B.setFitWidth(35);
                B.setFitHeight(39);
                double Y1 = Double.parseDouble(Row_B1[i]);
                double x = 417 + 38.1 * (4 - j);;
                double y = 226 + 42.9 * Y1;
                B.setLayoutX(x);
                B.setLayoutY(y);
                matrixBoard.getChildren().add(B);
            }
        }

        //mosaic
        String mosaic_A = player_state_of_A.substring(player_state_of_A.indexOf("M") + 1, player_state_of_A.indexOf("S"));
        String[] Mosaic_A = mosaic_A.split("");

        String alphabet_A = "";
        for (int i = 0; i < Mosaic_A.length; i += 3) {
            alphabet_A += Mosaic_A[i];
        }
        String[] Alphabet_A = alphabet_A.split("");

        String row_A = "";
        for (int i = 1; i < Mosaic_A.length; i += 3) {
            row_A += Mosaic_A[i];
        }
        String[] Row_A = row_A.split("");

        String column_A = "";
        for (int i = 2; i < Mosaic_A.length; i += 3) {
            column_A += Mosaic_A[i];
        }
        String[] Column_A = column_A.split("");

        for (int i = 0; i < Column_A.length; i++) {
            ImageView A = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_A[i] + ".png"));
            A.setFitWidth(35);
            A.setFitHeight(39);
            double X1 = Double.parseDouble(Row_A[i]);
            double Y1 = Double.parseDouble(Column_A[i]);
            Double x = 218 + 38.1 * Y1;
            Double y = 227 + 42.5 * X1;
            A.setLayoutX(x);
            A.setLayoutY(y);
            matrixBoard.getChildren().add(A);
        }


        //MOSAIC B
        String mosaic_B = player_state_of_B.substring(player_state_of_B.indexOf("M") + 1, player_state_of_B.indexOf("S"));
        String[] Mosaic_B = mosaic_B.split("");

        String alphabet_B = "";
        for (int i = 0; i < Mosaic_B.length; i += 3) {
            alphabet_B += Mosaic_B[i];
        }
        String[] Alphabet_B = alphabet_B.split("");

        String row_B = "";
        for (int i = 1; i < Mosaic_B.length; i += 3) {
            row_B += Mosaic_B[i];
        }
        String[] Row_B = row_B.split("");

        String column_B = "";
        for (int i = 2; i < Mosaic_B.length; i += 3) {
            column_B += Mosaic_B[i];
        }
        String[] Column_B = column_B.split("");

        for (int j = 0; j < alphabet_B.length(); j++) {
            ImageView B = new ImageView(new Image("file:src/comp1110/ass2/img/" + Alphabet_B[j] + ".png"));
            B.setFitWidth(35);
            B.setFitHeight(39);
            double X2 = Double.parseDouble(Row_B[j]);
            double Y2 = Double.parseDouble(Column_B[j]);
            double x = 630 + 38.1 * Y2;
            double y = 226 + 42.7 * X2;
            B.setLayoutX(x);
            B.setLayoutY(y);
            matrixBoard.getChildren().add(B);
        }

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

        //state[0]=AF0cdde1bbbe2abde3cdee4bcceCaabbcaabbcaacbbefB1915161614D0000000000
        //state[1]=A07Me01a11d20b30b41S0a11b22c13c44d1FeeabB08Md03b13e23c32b41S0b11c12a33d24e4Fabcc

        //state[0]=AF0abde1bbbe2abde4bcceCaabbcebbfB1915161614D0020000019
        //state[1]=A07Mb00e11e12a21d20b30b41b42S1b22c13c14d1FeB08Me11e12e13e14a22a23c32b41b42b44b43S2a33d24e4Fabcc

    }


    /**
     * Create a basic text field for input and a refresh button.
     */

    // makeControls() is to make control of the Viewer.
    private void makeControls() {
        setupViewer();
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

    ;

    // animateTile() is to show the animate of Tile.
    private void animateTile() {
        String[] _gameState = Azul.gameState;
        String _move = Azul.move;
        displayState(_gameState);
        moveTile();
    }

    ;

    // displayEnd() is to show the End of the game.
    private void displayEnd() {
        String[] _gameState = Azul.gameState;
        moveTile();
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


