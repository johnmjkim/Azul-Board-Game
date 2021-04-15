package comp1110.ass2;

import comp1110.ass2.State;

public class Center implements State {

    String centerState = EMPTY_STATE;
    int[] letters = new int[128];

    public Center(String centerState){
        this.centerState = centerState;
        countCenterTilesNumber(centerState);
    }

    public void countCenterTilesNumber(String centerState){
        int[] letters_array = new int[128];
        char[] centerState_char_array = centerState.toCharArray();
        for(char c : centerState_char_array){
            letters_array[c]++;
        }
        // 'a'~'f'
        this.letters[BLUE] = letters_array[BLUE];
        this.letters[GREEN] = letters_array[GREEN];
        this.letters[ORANGE] = letters_array[ORANGE];
        this.letters[PURPLE] = letters_array[PURPLE];
        this.letters[RED] = letters_array[RED];
        this.letters[FIRST_PLAYER] = letters_array[FIRST_PLAYER];
    }

    public int getTilesNumber(char color){
        return this.letters[color];
    }

    public int getTotalTilesNumber(){
        int tot_tiles = 0;
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

    public void removeTile(char color) {
        this.letters[color]--;
        updateState();
    }

    public void removeAllTiles(){
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            while(this.letters[color] > 0){
                this.letters[color]--;
            }
            color++;
        }
        updateState();
    }

    public void addTile(char color){
        this.letters[color]++;
        updateState();
    }

    boolean hasOnlyOneFirstPlayerToken() {
        return (this.letters[FIRST_PLAYER] == 1) && (getTotalTilesNumber() == 1);
    }

    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }

    @Override
    public boolean isStateEmpty() {
        updateState();
        return this.centerState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.centerState;
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
        color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            while(center_letters[color] > 0){
                SB.append(color);
                center_letters[color]--;
            }
            color++;
        }
        this.centerState = String.valueOf(SB);
    }
}
