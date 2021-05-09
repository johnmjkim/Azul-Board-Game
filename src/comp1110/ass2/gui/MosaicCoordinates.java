package comp1110.ass2.gui;

import comp1110.ass2.Constants;

import java.util.ArrayList;

public class MosaicCoordinates implements Constants {

    ArrayList<MosaicRowCoordinates> mosaicrowcoordinates = new ArrayList<MosaicRowCoordinates>();

    public MosaicCoordinates(){
        // Mosaic
        int[] max_row_col = new int[]{MAX_MOSAIC_ROW, MAX_MOSAIC_COL};
        double[] initial_xy = new double[]{INITIAL_MOSAIC_IMAGE_POS_X,INITIAL_MOSAIC_IMAGE_POS_Y};
        double[] gap_xy = new double[]{BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP,BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP};

        for(int mosaic_row=0; mosaic_row < MAX_MOSAIC_ROW; mosaic_row++){
            double[] mosaic_row_initial_xy = new double[]{initial_xy[0], initial_xy[1] + gap_xy[1] * mosaic_row};
            MosaicRowCoordinates MOSAIC_ROW_COORDINATES = new MosaicRowCoordinates(max_row_col, mosaic_row_initial_xy, gap_xy, mosaic_row);
            mosaicrowcoordinates.add(MOSAIC_ROW_COORDINATES);
        }
    }

    public MosaicRowCoordinates getMosaicRowCoordinates(int mosaic_row){
        return this.mosaicrowcoordinates.get(mosaic_row);
    }

    public String toString(){
        StringBuilder SB = new StringBuilder();
        SB.append("Mosaic\n");
        for(int mosaic_row=0; mosaic_row < MAX_MOSAIC_ROW; mosaic_row++){
            SB.append(String.valueOf(this.getMosaicRowCoordinates(mosaic_row)));
            if(mosaic_row < MAX_STORAGE_ROW - 1){
                SB.append("\n");
            }
        }
        return String.valueOf(SB);
    }

    public class MosaicRowCoordinates{
        int mosaic_row;
        private double[] pos_x;
        private double[] pos_y;

        public MosaicRowCoordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy, int mosaic_row) {
            this.mosaic_row = mosaic_row;
            this.pos_x = new double[MAX_MOSAIC_COL];
            this.pos_y = new double[MAX_MOSAIC_COL];
            for(int tiles=0; tiles < MAX_MOSAIC_COL; tiles++) {
                Coordinates mosaic_row_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
                this.pos_x[tiles] = mosaic_row_Coordinates_Class.getCoordinates(tiles)[0];
                this.pos_y[tiles] = mosaic_row_Coordinates_Class.getCoordinates(tiles)[1];
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
            SB.append("Mosaic Row " + String.valueOf(mosaic_row));
            for(int tiles=0; tiles < MAX_MOSAIC_COL; tiles++){
                SB.append(" (x, y) = (" + String.valueOf(this.getPos_x(tiles))+ ", " + String.valueOf(this.getPos_y(tiles)) + ")");
            }
            return String.valueOf(SB);
        }
    }
}
