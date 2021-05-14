package comp1110.ass2.backend;

public class Center extends OrderTypedObject {

    // maximum player number, center tiles
    int max_player_number;
    int max_center_tiles;

    // tiles occupy, color
    boolean[] tiles_occupy;
    char[] tiles_color;

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
                tiles_occupy[rest_col] = false;
                tiles_color[rest_col] = NO_COLOR;
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
    public boolean hasOnlyOneFirstPlayerToken() {
        return (super.letters[FIRST_PLAYER] == 1) && (getTotalTilesNumber() == 1);
    }

    /**
     * @author Min Jae, Kim
     * Check if the center has first player token
     * @return
     */
    public boolean hasFirstPlayerToken() {return (super.letters[FIRST_PLAYER] != 0); }
}
