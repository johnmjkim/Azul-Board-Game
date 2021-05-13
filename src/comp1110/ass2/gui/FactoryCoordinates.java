package comp1110.ass2.gui;

import comp1110.ass2.backend.Constants;

public class FactoryCoordinates implements Constants {
    int number;
    private double[] pos_x = new double[FACTORY_SIZE];
    private double[] pos_y = new double[FACTORY_SIZE];
    public FactoryCoordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy, int number){
        // Factory Coordinates
        Coordinates factory_Coordinates_Class = new Coordinates(max_row_col, initial_xy, gap_xy);
        this.number = number;
        for(int tiles=0; tiles< FACTORY_SIZE; tiles++){
            this.pos_x[tiles] = factory_Coordinates_Class.getCoordinates(tiles)[0];
            this.pos_y[tiles] = factory_Coordinates_Class.getCoordinates(tiles)[1];
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
        SB.append("Factory " + String.valueOf(number));
        for(int tiles=0; tiles < FACTORY_SIZE; tiles++){
            if(tiles % MAX_FACTORY_TILES_COL_IMAGE == 0){
                SB.append("\n");
            }
            SB.append(" (x, y) = (" + String.valueOf(this.getPos_x(tiles))+ ", " + String.valueOf(this.getPos_y(tiles)) + ")");
        }
        return String.valueOf(SB);
    }
}
