package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application implements Constants {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    public static int PLAYER_NUMBER = 4;
    public static int FACTORY_MAX_NUMBER = FACTORY_MAX_NUMBERS[PLAYER_NUMBER - DEFAULT_MAX_PLAYER];
    public static CenterCoordinates CENTER_COORDINATES;
    public static FactoriesCoordinates FACTORIES_COORDINATES;
    public static StorageCoordinates STORAGE_COORDINATES;
    public static MosaicCoordinates MOSAIC_COORDINATES;
    public static FloorCoordinates FLOOR_COORDINATES;

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

        stage.setScene(scene);
        stage.show();

        Viewer viewer = new Viewer();
        viewer.start(stage);

        //Create draggable tiles

    }



    private void makeCoordinates(){
        // Center Coordinates
        CENTER_COORDINATES = new CenterCoordinates(PLAYER_NUMBER);
        FACTORIES_COORDINATES = new FactoriesCoordinates(FACTORY_MAX_NUMBER);
        STORAGE_COORDINATES = new StorageCoordinates();
        MOSAIC_COORDINATES = new MosaicCoordinates();
        FLOOR_COORDINATES = new FloorCoordinates();

        System.out.println(CENTER_COORDINATES);
        System.out.println(FACTORIES_COORDINATES);
        System.out.println(STORAGE_COORDINATES);
        System.out.println(MOSAIC_COORDINATES);
        System.out.println(FLOOR_COORDINATES);

    }

    public class GameTile extends Polygon {

    }

    public class GameTileDrag extends GameTile{

    }

    public class GameBoard extends Polygon{

    }

}

// Draggable Tiles Codes
/*
package comp1110.lab5;

        import comp1110.lab2.Triangle;
        import javafx.animation.Timeline;
        import javafx.application.Application;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Polygon;
        import javafx.stage.Stage;

        import java.util.ArrayList;

// 1. Create a new JavaFX class
public class Board extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 519;
    Stage window;
    Group root;
    Scene scene;
    ArrayList<Triangle> Triangles = new ArrayList<Triangle>();
    Triangle highlighted = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primarystage) throws Exception{
        window = primarystage;
        root = new Group();
        // Draws 600 x 519 pixel window
        scene = new Scene(root, WIDTH, HEIGHT);
        window.setScene(scene);

        // Set window title to "Board"
        window.setTitle("Board");

        // 2. Draw a triangle : Set polygon(Hu: already have the images)

        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[]{0.0, -86.6, 100.0, 86.6, -100.0, 86.6});
        p.setFill(Color.LIGHTGREY);
        root.getChildren().add(p);



        // 4. Fill the board with triangles & 5. Create a white border
        (Hu: maybe I can fill the empty-board with some grey squares)
        //ArrayList<Triangle> Triangles = new ArrayList<Triangle>();
        for(int i=-2; i <=2 ; i++){
            for(int j=-1; j <=1 ; j++){
                //System.out.println("(" + 100 * i + ", " + 173.2 * j + ")");
                Triangles.add(new Triangle(100 * i, 173.2 * j, 196));
            }
        }
        boolean isRotate = false;
        int count = 0;
        for(Triangle ts : Triangles){
            if(count % 3 == 0){
                if(count % 6 ==0){
                    isRotate = false;
                }
                else{
                    isRotate = true;
                }
            }
            if(isRotate){
                ts.setRotate(180);
                Triangles.set(count, ts);
            }
            root.getChildren().add(ts);
            count++;
        }

        // Extension 4. Create a draggable triangle
        DraggableTriangle dt = new DraggableTriangle(300, 260, 200, this);
        root.getChildren().add(dt);
        // Show all
        window.show();
    }

    // 7. Find the nearest triangle
    public Triangle findNearestTriangle(double x, double y){
        double nearest_dist = this.Triangles.get(0).distance(x, y);
        for( Triangle t : this.Triangles){
            if( t.distance(x, y) <= nearest_dist){
                nearest_dist = t.distance(x, y);
                this.highlighted = t;
            }
        }

        //System.out.println(index + ", " + nearest_dist);
        return this.highlighted;
    }

    // 8. Highlight the nearest
    public void highlightedNearestTriangle(double x, double y){
        int i=0;
        this.highlighted = findNearestTriangle(x, y);
        ArrayList<Triangle> ts = this.Triangles;
        for( Triangle t : ts){
            if( t.equals(this.highlighted) ){
                //System.out.println(i + ", - > GREEN " + ", Dist " + t.distance(x, y) + ", at (" + x + "," +y +") to (" + t.getLayoutX() + ", " + t.getLayoutY() +")");
                t.setFill(Color.GREEN);
                this.Triangles.set(i, t);
            }
            else if( t.getFill().equals(Color.GREEN) ){
                //System.out.println(i + ", - > LIGHTGREY " + ", Dist " + t.distance(x, y) + ", at (" + x + "," +y +") to (" + t.getLayoutX() + ", " + t.getLayoutY() +")");
                t.setFill(Color.LIGHTGREY);
                this.Triangles.set(i, t);
            }
            else{
                //System.out.println(i + ", LIGHTGREY " + ", Dist " + t.distance(x, y) + ", at (" + x + "," +y +") to (" + t.getLayoutX() + ", " + t.getLayoutY() +")");
            }
            i++;
        }
    }

    // 3. Create an inner class
    public class Triangle extends Polygon {
        double x, y, side;

        public Triangle(double x, double y, double side){
            this.x = x;
            this.y = y;
            this.side = side;
            double half_length = side/2;
            double apex_point = side * 86.6/200;
            getPoints().addAll(new Double[]{0.0, -apex_point, half_length, apex_point, -half_length, apex_point});
            setFill(Color.LIGHTGREY);
            setLayoutX(x + WIDTH/2);
            setLayoutY(y + HEIGHT/2);
            //System.out.println("x (" + x + ") + WIDTH/2 (" + WIDTH/2 +") =" + (x + WIDTH/2) + ", y (" + y + ") + HEIGHT/2 (" + HEIGHT/2 + ") =" + (y + HEIGHT/2));
            //System.out.println(": getLayoutX : " + getLayoutX() + ", getLayoutY :" + getLayoutY());
        }
        // 6. Add a distance calculator
        private double distance(double x, double y){
            return Math.sqrt(Math.pow(getLayoutX() - x, 2) + Math.pow(getLayoutY() - y, 2));
        }
    }

    // Extension 1. Create an inner class
    public class DraggableTriangle extends Triangle {
        private Board board;
        private double mousex, mousey;
        private double posit_x, posit_y;

        // Extension 2 : Add a constructor
        public DraggableTriangle(double x, double y, double size, Board board){
            super( x, y, size);
            setFill(Color.RED);
            // Extension 3 : Add a new field
            this.board = board;
            setLayoutX(super.getLayoutX());
            setLayoutY(super.getLayoutY());

            // Extension 5: Make the inner class draggable
            this.toFront();
            this.setOnMousePressed(event ->{
                this.mousex = event.getSceneX();
                this.mousey = event.getSceneY();
                this.posit_x = this.getLayoutX();
                this.posit_y = this.getLayoutY();
            });
            this.setOnMouseDragged(event ->{
                setLayoutX(event.getSceneX() - mousex + this.posit_x);
                setLayoutY(event.getSceneY() - mousey + this.posit_y);
                board.highlightedNearestTriangle(getLayoutX(), getLayoutY());
            });

            // Extension 9 : Snap to nearest triangle
            this.setOnMouseReleased(event ->{
                setRotate(board.highlighted.getRotate());
                setLayoutX(board.highlighted.getLayoutX());
                setLayoutY(board.highlighted.getLayoutY());
            });
        }
    }
}

 */
