package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Si Bo, Hu
 * This part is setting Snappable Tiles(we show them as images) from Storage, Mosaic and Floor
 * Snappable means tiles can be snapped in Stroage, Mosaic and Floor
 * Snap is the process after nothing can be drag from Factories and center, before next round
 */
public class SnappableTiles extends ImageView implements Constants {
    double x, y;
    int tile_num, index;
    char type;
    private Viewer viewer;

    /**
     *
     * @param x
     * @param y
     * @param image_link
     * @param viewer
     * @param type
     * @param tile_num
     */
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

    public boolean toMosaic() {
        return type == MOSAIC;
    }

    public boolean toFloor(){
        return type == FLOOR;
    }
}