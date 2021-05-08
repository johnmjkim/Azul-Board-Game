package comp1110.ass2;

import comp1110.ass2.gui.Coordinates;

public class Experiment_PSVM2 implements Constants {
    public static void main(String[] args) {
        int[] max_row_col = new int[]{MAX_CENTER_STRING_SIZE/MAX_CENTER_TILES_COL_IMAGE, MAX_CENTER_TILES_COL_IMAGE};
        double[] initial_xy = new double[]{(double)INITIAL_CENTER_IMAGE_POS_X,(double)INITIAL_CENTER_IMAGE_POS_Y};
        double[] gap_xy = new double[]{(double)(BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP),(double)(BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP)};

        Coordinates center_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);

        for (int tiles = 0; tiles < MAX_CENTER_STRING_SIZE; tiles++) {
            int row = (int) tiles / MAX_CENTER_TILES_COL_IMAGE;
            int col = tiles % MAX_CENTER_TILES_COL_IMAGE;
            double x = INITIAL_CENTER_IMAGE_POS_X + (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * col;
            double y = INITIAL_CENTER_IMAGE_POS_Y + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * row;
            System.out.println(" x : " + x + " y : " + y);
        }

        for(int i=0; i< MAX_CENTER_STRING_SIZE; i++){
            System.out.println(" x : " + center_Coordinates_Class.getCoordinates(i)[0] + " y : " + center_Coordinates_Class.getCoordinates(i)[1]);
        }

    }

}
