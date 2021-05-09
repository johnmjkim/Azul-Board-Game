package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Floor extends OrderTypedObject{

    // tiles occupy, color
    boolean[] tiles_occupy = new boolean[MAX_FLOOR_STRING_SIZE];
    char[] tiles_color = new char[MAX_FLOOR_STRING_SIZE];

    /**
     * @author Min Jae, Kim
     * Constructor method for Floor class
     * @param orderTypedObjectState
     */
    public Floor(String orderTypedObjectState) {
        super(orderTypedObjectState);
        storeTilesColor();
    }

    private void storeTilesColor(){
        if(super.isStateEmpty()){
            for(int col=0; col < MAX_FLOOR_STRING_SIZE; col++){
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
            for(int rest_col = col; rest_col < MAX_FLOOR_STRING_SIZE; rest_col++){
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