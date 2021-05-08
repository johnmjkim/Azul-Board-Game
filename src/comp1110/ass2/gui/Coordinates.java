package comp1110.ass2.gui;

public class Coordinates {
    double[] coordinates;
    int size;
    int max_row, max_col;
    double initial_x, initial_y;
    double gap_x, gap_y;
    void Coordinates(int size, int[] max_row_col, double[] initial_xy, double[] gap_xy){
        this.size = size;
        this.max_row = max_row_col[0];
        this.max_col = max_row_col[1];
    }
}
