package comp1110.ass2.gui;

import comp1110.ass2.Constants;

import java.util.ArrayList;

public class OtherStorageCoordinates implements Constants {
    ArrayList<OtherStorageRowCoordinates> otherstoragerowcoordinates = new ArrayList<>();

    public OtherStorageCoordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy){
        // OtherStorage
        for(int storage_row=0; storage_row < MAX_STORAGE_ROW; storage_row++){
            int storage_col = MAX_STORAGE_ROW - storage_row - 1;
            double[] storage_row_initial_xy = new double[]{initial_xy[0] + gap_xy[0] * storage_col, initial_xy[1] + gap_xy[1] * storage_row};
            OtherStorageRowCoordinates STORAGE_ROW_COORDINATES = new OtherStorageRowCoordinates(max_row_col, storage_row_initial_xy, gap_xy, storage_row, storage_col);
            otherstoragerowcoordinates.add(STORAGE_ROW_COORDINATES);
        }
    }

    public OtherStorageRowCoordinates getStorageRowCoordinates(int storage_row){
        return this.otherstoragerowcoordinates.get(storage_row);
    }

    public String toString(){
        StringBuilder SB = new StringBuilder();
        SB.append("Storage\n");
        for(int storage_row=0; storage_row < MAX_STORAGE_ROW; storage_row++){
            SB.append(String.valueOf(this.getStorageRowCoordinates(storage_row)));
            if(storage_row < MAX_STORAGE_ROW - 1){
                SB.append("\n");
            }
        }
        return String.valueOf(SB);
    }

    public class OtherStorageRowCoordinates{
        int storage_row;
        private double[] pos_x;
        private double[] pos_y;

        public OtherStorageRowCoordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy, int storage_row, int storage_col){
            this.storage_row = storage_row;
            this.pos_x = new double[storage_row + 1];
            this.pos_y = new double[storage_row + 1];
            for(int tiles=0; tiles < storage_row + 1; tiles++) {
                Coordinates storage_row_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
                this.pos_x[tiles] = storage_row_Coordinates_Class.getCoordinates(tiles)[0];
                this.pos_y[tiles] = storage_row_Coordinates_Class.getCoordinates(tiles)[1];
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
            SB.append("Storage Row " + String.valueOf(storage_row));
            for(int tiles=0; tiles < storage_row + 1; tiles++){
                SB.append(" (x, y) = (" + String.valueOf(this.getPos_x(tiles))+ ", " + String.valueOf(this.getPos_y(tiles)) + ")");
            }
            return String.valueOf(SB);
        }
    }
}
