package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SnappableTiles extends ImageView implements Constants {
    double x, y;
    int tile_num, index;
    char type;
    private Viewer viewer;

    public SnappableTiles(double x, double y, String image_link, Viewer viewer, char type, int tile_num) {
        super(new Image(image_link));
        this.toFront();
        this.x = x;
        this.y = y;
        this.type = type;
        this.tile_num = tile_num;
        this.viewer = viewer;
        setFitWidth(BIG_TILE_IMAGE_SIZE_X);
        setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
        setLayoutY(y);
        setLayoutX(x);
    }

    public SnappableTiles(double x, double y, String image_link, Viewer viewer, char type, int index, int tile_num) {
        super(new Image(image_link));
        this.toFront();
        this.x = x;
        this.y = y;
        this.type = type;
        this.index = index;
        this.tile_num = tile_num;
        this.viewer = viewer;
        setFitWidth(BIG_TILE_IMAGE_SIZE_X);
        setFitHeight(BIG_TILE_IMAGE_SIZE_Y);
        setLayoutY(y);
        setLayoutX(x);
    }

    public double distance(double x,double y){
        return Math.sqrt((x-this.x)*(x-this.x) + (y-this.y)*(y-this.y));
    }

    public int getIndex() {
        return this.index;
    }

    public int getTileNum() {
        return this.tile_num;
    }

    public boolean toStorage(){
        return type == STORAGE;
    }
    public boolean toFloor(){
        return type == FLOOR;
    }
}
/*
    public Viewer.Rectangle findNearestRectangle(double x, double y){
        int i = 0;
        double distance = 0;
        int closestID = -1;
        for (Viewer.Rectangle t : snappable_Tile){
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
        System.out.println(" snappable_Tile x, y : " + snappable_Tile.get(closestID).x + ", " + snappable_Tile.get(closestID).y);
        return snappable_Tile.get(closestID);
    }

    public void highlightNearestRectangle(double x, double y){
        int i=0;
        highlighted = findNearestRectangle(x,y);
        ArrayList<Viewer.Rectangle> ts = this.snappable_Tile;
        for (Viewer.Rectangle t : ts){
            if (t.equals(this.highlighted)){
                //this.snappable_Tile.get(i).setFill(Color.GREEN);
                t.setFill(Color.GREEN);
                this.snappable_Tile.set(i,t);
            }
            else {
                //this.snappable_Tile.get(i).setFill(Color.GREY);
                t.setFill(Color.GREY);
                this.snappable_Tile.set(i,t);
            }
            i++;
        }
    }
}

 */
