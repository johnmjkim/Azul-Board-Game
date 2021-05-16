package comp1110.ass2.gui;

import comp1110.ass2.Constants;

public class FloorCoordinates implements Constants {
    private double[] pos_x = new double[MAX_FLOOR_STRING_SIZE];
    private double[] pos_y = new double[MAX_FLOOR_STRING_SIZE];

    public FloorCoordinates(){
        // Floor Coordinates
        int[] max_row_col = new int[]{1, MAX_FLOOR_TILES_COL_IMAGE};
        double[] initial_xy = new double[]{INITIAL_FLOOR_IMAGE_POS_X,INITIAL_FLOOR_IMAGE_POS_Y};
        double[] gap_xy = new double[]{BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP,BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP};

        Coordinates floor_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
        for(int tiles=0; tiles< MAX_FLOOR_STRING_SIZE; tiles++){
            this.pos_x[tiles] = floor_Coordinates_Class.getCoordinates(tiles)[0];
            this.pos_y[tiles] = floor_Coordinates_Class.getCoordinates(tiles)[1];
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
        SB.append("Floor\n");
        for(int tiles=0; tiles< MAX_FLOOR_STRING_SIZE; tiles++){
            if(tiles % MAX_FLOOR_TILES_COL_IMAGE == 0 && tiles != 0){
                SB.append("\n");
            }
            SB.append(" (x, y) = (" + this.getPos_x(tiles)+ ", " + this.getPos_y(tiles) + ")");
        }
        return String.valueOf(SB);
    }
}
