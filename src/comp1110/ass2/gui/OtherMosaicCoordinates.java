package comp1110.ass2.gui;

import comp1110.ass2.Constants;

import java.util.ArrayList;

public class OtherMosaicCoordinates implements Constants {
    ArrayList<OtherMosaicRowCoordinates> othermosaicrowcoordinates = new ArrayList<>();

    public OtherMosaicCoordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy){
        // Mosaic

        for(int mosaic_row=0; mosaic_row < MAX_MOSAIC_ROW; mosaic_row++){
            double[] mosaic_row_initial_xy = new double[]{initial_xy[0], initial_xy[1] + gap_xy[1] * mosaic_row};
            OtherMosaicRowCoordinates MOSAIC_ROW_COORDINATES = new OtherMosaicRowCoordinates(max_row_col, mosaic_row_initial_xy, gap_xy, mosaic_row);
            othermosaicrowcoordinates.add(MOSAIC_ROW_COORDINATES);
        }
    }

    public OtherMosaicRowCoordinates getMosaicRowCoordinates(int mosaic_row){
        return this.othermosaicrowcoordinates.get(mosaic_row);
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

    public class OtherMosaicRowCoordinates{
        int mosaic_row;
        private double[] pos_x;
        private double[] pos_y;

        public OtherMosaicRowCoordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy, int mosaic_row) {
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
