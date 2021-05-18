package comp1110.ass2.gui;

import comp1110.ass2.Constants;

/**
 * @author Si Bo, Hu
 * this is a method to get the coordinates of tiles in Discard
 */
public class DiscardCoordinates implements Constants{
    private double[] pos_x = new double[MAX_DISCARD_SIZE];
    private double[] pos_y = new double[MAX_DISCARD_SIZE];

    public DiscardCoordinates(){
        // Discard Coordinates
        int[] max_row_col = new int[]{MAX_DISCARD_SIZE/MAX_DISCARD_TILES_COL_IMAGE, MAX_DISCARD_TILES_COL_IMAGE};
        double[] initial_xy = new double[]{INITIAL_DISCARD_IMAGE_POS_X,INITIAL_DISCARD_IMAGE_POS_Y};
        double[] gap_xy = new double[]{SMALL_TILE_IMAGE_SIZE_X + SMALL_TILE_IMAGE_SIZE_X_GAP,SMALL_TILE_IMAGE_SIZE_Y + SMALL_TILE_IMAGE_SIZE_Y_GAP};

        Coordinates discard_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
        for(int tiles=0; tiles< MAX_DISCARD_SIZE; tiles++){
            this.pos_x[tiles] = discard_Coordinates_Class.getCoordinates(tiles)[0];
            this.pos_y[tiles] = discard_Coordinates_Class.getCoordinates(tiles)[1];
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
        SB.append("Discard\n");
        for(int tiles=0; tiles< MAX_DISCARD_SIZE; tiles++){
            if(tiles % MAX_DISCARD_TILES_COL_IMAGE == 0 && tiles != 0){
                SB.append("\n");
            }
            SB.append(" (x, y) = (" + this.getPos_x(tiles)+ ", " + this.getPos_y(tiles) + ")");
        }
        return String.valueOf(SB);
    }
}