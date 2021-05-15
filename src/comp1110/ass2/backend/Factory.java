package comp1110.ass2.backend;

public class Factory extends OrderTypedObject{
    // number of Factory 0~4 for two players
    int number;

    // tiles occupy, color
    boolean[] tiles_occupy = new boolean[FACTORY_SIZE];
    char[] tiles_color = new char[FACTORY_SIZE];

    /**
     * @author Min Jae, Kim
     * Constructor method for Factory class with factory number
     * @param orderTypedObjectState
     * @param number
     */
    public Factory(String orderTypedObjectState, int number ) {
        super(orderTypedObjectState);
        this.number = number;
        storeTilesColor();
    }

    private void storeTilesColor(){
        if(super.isStateEmpty()){
            for(int col = 0; col < FACTORY_SIZE; col++){
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
            for(int rest_col = col; rest_col < FACTORY_SIZE; rest_col++){
                tiles_occupy[rest_col] = false;
                tiles_color[rest_col] = NO_COLOR;
            }
        }
    }

    public char getTileColor(int index){
        return tiles_color[index];
    }
}