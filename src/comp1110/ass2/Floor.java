package comp1110.ass2;

import comp1110.ass2.State;

public class Floor implements State {

    String floorState = EMPTY_STATE;
    int[] letters = new int[128];

    public Floor(String floorState) {
        this.floorState = floorState;
        countFloorTilesNumber(floorState);
    }

    public void countFloorTilesNumber(String floorState) {

        int[] letters_array = new int[128];
        char[] floorState_char_array = floorState.toCharArray();
        for (char c : floorState_char_array) {
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

    // Discard
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

    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        char color = BLUE;
        for (int i = 0; i <= FIRST_PLAYER - BLUE; i++) {
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

    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }

    @Override
    public boolean isStateEmpty() {
        updateState();
        return this.floorState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.floorState;
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] floor_letters = new int[128];
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            floor_letters[color] = this.letters[color];
            color++;
        }
        color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            while(floor_letters[color] > 0){
                SB.append(color);
                floor_letters[color]--;
            }
            color++;
        }
        this.floorState = String.valueOf(SB);
    }
}
