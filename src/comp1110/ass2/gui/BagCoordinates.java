package comp1110.ass2.gui;

import comp1110.ass2.Constants;

/**
 * @author Min Jae, Kim
 * this is a method to get the coordinates of tiles in Bag
 */
public class BagCoordinates implements Constants {
    private double[] pos_x = new double[MAX_BAG_SIZE];
    private double[] pos_y = new double[MAX_BAG_SIZE];

    public BagCoordinates(){
        // Discard Coordinates
        int[] max_row_col = new int[]{MAX_BAG_SIZE/MAX_BAG_TILES_COL_IMAGE, MAX_BAG_TILES_COL_IMAGE};
        double[] initial_xy = new double[]{INITIAL_BAG_IMAGE_POS_X,INITIAL_BAG_IMAGE_POS_Y};
        double[] gap_xy = new double[]{SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP,SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP};

        Coordinates bag_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
        for(int tiles=0; tiles< MAX_BAG_SIZE; tiles++){
            this.pos_x[tiles] = bag_Coordinates_Class.getCoordinates(tiles)[0];
            this.pos_y[tiles] = bag_Coordinates_Class.getCoordinates(tiles)[1];
        }
    }

    public double getPos_x(int tiles){
        return this.pos_x[tiles];
    }

    public double getPos_y(int tiles){
        return this.pos_y[tiles];
    }

    public String toString(){
        StringBuilder SB = new StringBuilder();
        SB.append("Bag\n");
        for(int tiles=0; tiles< MAX_BAG_SIZE; tiles++){
            if(tiles % MAX_BAG_TILES_COL_IMAGE == 0 && tiles != 0){
                SB.append("\n");
            }
            SB.append(" (x, y) = (" + this.getPos_x(tiles)+ ", " + this.getPos_y(tiles) + ")");
        }
        return String.valueOf(SB);
    }
}
