package comp1110.ass2.gui;
import comp1110.ass2.Constants;

/**
 * @author Si Bo, Hu
 * this is a method to get the coordinates of tiles in Center
 */
public class CenterCoordinates implements Constants {
    int max_player_number;
    int max_center_number;
    private double[] pos_x;
    private double[] pos_y;

    public CenterCoordinates(int max_player_number){
        // Center Coordinates
        this.max_player_number = max_player_number;
        this.max_center_number = CENTER_MAX_NUMBERS[max_player_number - DEFAULT_MAX_PLAYER];
        pos_x = new double[max_center_number];
        pos_y = new double[max_center_number];
        int[] max_row_col = new int[]{(int)max_center_number/MAX_CENTER_TILES_COL_IMAGE + 1, MAX_CENTER_TILES_COL_IMAGE};
        double[] initial_xy = new double[]{INITIAL_CENTER_IMAGE_POS_X,INITIAL_CENTER_IMAGE_POS_Y};
        double[] gap_xy = new double[]{BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP,BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP};

        Coordinates center_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
        for(int tiles=0; tiles< max_center_number; tiles++){
            this.pos_x[tiles] = center_Coordinates_Class.getCoordinates(tiles)[0];
            this.pos_y[tiles] = center_Coordinates_Class.getCoordinates(tiles)[1];
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
        SB.append("Center\n");
        for(int tiles=0; tiles< max_center_number; tiles++){
            if(tiles % MAX_CENTER_TILES_COL_IMAGE == 0 && tiles != 0){
                SB.append("\n");
            }
            SB.append(" (x, y) = (" + this.getPos_x(tiles)+ ", " + this.getPos_y(tiles) + ")");
        }
        return String.valueOf(SB);
    }
}
