package comp1110.ass2.gui;

/**
 * @author Si Bo, Hu
 * this is an initial setting of coordinates, we used this in different kinds of coordinates
 */
public class Coordinates {
    int max_row, max_col;
    double initial_x, initial_y;
    double gap_x, gap_y;
    double[] coordinates_x;
    double[] coordinates_y;

    public Coordinates(int[] max_row_col, double[] initial_xy, double[] gap_xy){
        this.max_row = max_row_col[0];
        this.max_col = max_row_col[1];
        this.initial_x = initial_xy[0];
        this.initial_y = initial_xy[1];
        this.gap_x = gap_xy[0];
        this.gap_y = gap_xy[1];
        findCoordinates();
    }

    void findCoordinates(){
        this.coordinates_x = new double[max_row * max_col];
        this.coordinates_y = new double[max_row * max_col];
        for(int tiles=0; tiles < max_row * max_col; tiles++){
            int row = (int) tiles/max_col;
            int col = tiles % max_col;
            this.coordinates_x[tiles] = initial_x + (gap_x) * col;
            this.coordinates_y[tiles] = initial_y + (gap_y) * row;
        }
    }

    public double[] getCoordinates(int idx) {
        return new double[]{this.coordinates_x[idx], this.coordinates_y[idx]};
    }
}
