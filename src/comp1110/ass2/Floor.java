package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Floor extends OrderTypedObject{

    // maximum player number, floor tiles
    int max_player_number;
    int max_floor_tiles_before_adjust;

    // tiles occupy, color
    boolean[] tiles_occupy;
    char[] tiles_color;

    /**
     * @author Min Jae, Kim
     * Constructor method for Floor class
     * @param orderTypedObjectState
     */
    public Floor(String orderTypedObjectState, int max_player_number) {
        super(orderTypedObjectState);
        this.max_player_number = max_player_number;
        this.max_floor_tiles_before_adjust = MAX_FLOOR_STRING_SIZE + CENTER_MAX_NUMBERS[max_player_number - DEFAULT_MAX_PLAYER];
        this.tiles_occupy = new boolean[max_floor_tiles_before_adjust];
        this.tiles_color = new char[max_floor_tiles_before_adjust];
        storeTilesColor();
    }

    private void storeTilesColor(){
        if(super.isStateEmpty()){
            for(int col=0; col < max_floor_tiles_before_adjust; col++){
                tiles_occupy[col] = false;
                tiles_color[col] = NO_COLOR;
            }
        }
        else{
            char[] colors = super.orderTypedObjectState.toCharArray();
            int col=0;
            for(char color : colors){
                tiles_occupy[col] = true;
                tiles_color[col] = color;
                col++;
            }
            for(int rest_col = col; rest_col < max_floor_tiles_before_adjust; rest_col++){
                tiles_occupy[col] = false;
                tiles_color[col] = NO_COLOR;
            }
        }
    }

    public char getTileColor(int index){
        return tiles_color[index];
    }

    /**
     * @author Min Jae, Kim
     * Fill discard by tiles from floor
     * refillDiscardtoBag() method in SharedState
     * @return
     */
    public String getFloorTilesString(){
        StringBuilder SB = new StringBuilder();
        char color = BLUE;
        for (int i = 0; i <= FIRST_PLAYER - BLUE; i++) {
            if(this.letters[color] < 10){
                SB.append("0");
            }
            SB.append(this.letters[color]);
            color++;
        }
        return String.valueOf(SB);
    }

    boolean hasOnlyOneFirstPlayerToken() {
        return (this.letters[FIRST_PLAYER] == 1) && (getTotalTilesNumber() == 1);
    }

    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }
}