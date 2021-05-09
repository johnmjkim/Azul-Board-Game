package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Center extends OrderTypedObject{

    // tiles occupy, color
    boolean[] tiles_occupy = new boolean[MAX_CENTER_STRING_SIZE];
    char[] tiles_color = new char[MAX_CENTER_STRING_SIZE];

    /**
     * @author Min Jae, Kim
     * Constructor method for Center class
     * @param orderTypedObjectState
     */
    public Center(String orderTypedObjectState) {
        super(orderTypedObjectState);
        storeTilesColor();
    }

    private void storeTilesColor(){
        if(super.isStateEmpty()){
            for(int col=0; col < MAX_CENTER_STRING_SIZE; col++){
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
            for(int rest_col = col; rest_col < MAX_CENTER_STRING_SIZE; rest_col++){
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
     * Check if there is only one first player token if center has first player token
     * @return
     */
    boolean hasOnlyOneFirstPlayerToken() {
        return (this.letters[FIRST_PLAYER] == 1) && (getTotalTilesNumber() == 1);
    }

    /**
     * @author Min Jae, Kim
     * Check if the center has first player token
     * @return
     */
    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }
}
