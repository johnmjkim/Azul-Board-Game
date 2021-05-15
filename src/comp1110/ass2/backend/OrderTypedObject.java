package comp1110.ass2.backend;

import java.util.Arrays;

public class OrderTypedObject implements OrderTyped{

    String orderTypedObjectState = EMPTY_STATE;
    int[] letters = new int[128];

    /**
     * @author Min Jae, Kim
     * Constructor method for OrderTypedObject class
     * OrderTypedObject class is all object that stores tiles like below format:
     * "aabcdef"
     * Floor, Factory, Center class stores tiles like above format
     * @param orderTypedObjectState
     */
    public OrderTypedObject(String orderTypedObjectState){
        this.orderTypedObjectState = orderTypedObjectState;
        countTilesNumber(orderTypedObjectState);
    }

    @Override
    public void refillTiles(char[] refill) {
        StringBuilder SB = new StringBuilder();
        Arrays.sort(refill);
        for (char c : refill) {
            SB.append(c);
            this.letters[c]++;
        }
        this.orderTypedObjectState = String.valueOf(SB);
    }

    @Override
    public void removeTile(char color) {
        this.letters[color]--;
        updateState();
    }

    @Override
    public void removeTiles(char color, int n) {
        for(int i=0; i < n; i++){
            this.letters[color]--;
        }
        updateState();
    }

    @Override
    public void removeAllTiles() {
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            while(this.letters[color] > 0){
                this.letters[color]--;
            }
            color++;
        }
        while(this.letters[NO_COLOR] > 0){
            this.letters[NO_COLOR]--;
        }
        updateState();
    }

    @Override
    public void addTile(char color) {
        this.letters[color]++;
        updateState();
    }

    @Override
    public void addTiles(char color, int n) {
        for(int i=0; i < n; i++){
            this.letters[color]++;
        }
        updateState();
    }

    @Override
    public boolean isStateEmpty() {
        updateState();
        return this.orderTypedObjectState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.orderTypedObjectState;
    }

    @Override
    public String toString(){
        return getStateString();
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] center_letters = new int[128];
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            center_letters[color] = this.letters[color];
            color++;
        }
        center_letters[NO_COLOR] = this.letters[NO_COLOR];
        color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            while(center_letters[color] > 0){
                SB.append(color);
                center_letters[color]--;
            }
            color++;
        }
        if(!SB.isEmpty()){
            while(center_letters[NO_COLOR] > 0){
                SB.append(NO_COLOR);
                center_letters[NO_COLOR]--;
            }
        }
        else{
            while(center_letters[NO_COLOR] > 0){
                center_letters[NO_COLOR]--;
            }
        }
        this.orderTypedObjectState = String.valueOf(SB);
    }

    @Override
    public void countTilesNumber(String orderTypedObjectState) {
        int[] letters_array = new int[128];
        char[] orderTypedObjectState_char_array = orderTypedObjectState.toCharArray();
        for(char c : orderTypedObjectState_char_array){
            letters_array[c]++;
        }
        // 'a'~'f'
        this.letters[BLUE] = letters_array[BLUE];
        this.letters[GREEN] = letters_array[GREEN];
        this.letters[ORANGE] = letters_array[ORANGE];
        this.letters[PURPLE] = letters_array[PURPLE];
        this.letters[RED] = letters_array[RED];
        this.letters[FIRST_PLAYER] = letters_array[FIRST_PLAYER];
        this.letters[NO_COLOR] = letters_array[NO_COLOR];
    }

    @Override
    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    @Override
    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }
}
