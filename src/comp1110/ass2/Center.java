package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Center extends OrderTypedObject{

    // maximum player number, center tiles
    int max_player_number;
    int max_center_tiles;

    // tiles occupy, color
    boolean[] tiles_occupy = new boolean[MAX_CENTER_WITH_FIRST_PLAYER_TOKEN_STRING_SIZE];
    char[] tiles_color = new char[MAX_CENTER_WITH_FIRST_PLAYER_TOKEN_STRING_SIZE];

    /**
     * @author Min Jae, Kim
     * Constructor method for Center class
     * @param orderTypedObjectState
     */
    public Center(String orderTypedObjectState, int max_player_number) {
        super(orderTypedObjectState);
        this.max_player_number = max_player_number;
        this.max_center_tiles = CENTER_MAX_NUMBERS[max_player_number - DEFAULT_MAX_PLAYER];
        this.tiles_occupy = new boolean[max_center_tiles];
        this.tiles_color = new char[max_center_tiles];
        storeTilesColor();
    }

    private void storeTilesColor(){
        if(super.isStateEmpty()){
            for(int col=0; col < max_center_tiles; col++){
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
            for(int rest_col = col; rest_col < max_center_tiles; rest_col++){
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
