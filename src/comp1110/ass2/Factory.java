package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Factory implements OrderTyped {

    int number;
    int[] letters = new int[128];
    String factoryState = EMPTY_STATE;

    public Factory(String factoryState, int number) {
        this.factoryState = factoryState;
        this.number = number;
        //countFactoryTilesNumber();
        countTilesNumber(factoryState);
    }

    @Override
    public boolean isStateEmpty() {
        return this.factoryState.isEmpty();
    }

    @Override
    public String getStateString() {
        return this.factoryState;
    }

    @Override
    public String toString() {
        /*
        String each_factory_str = " Number : " + this.number + " Factory : " + this.factoryState + "\n";
        char color = BLUE;
        for (int i = 0; i <= RED - BLUE; i++) {
            each_factory_str += " " + color + " : " + this.getTilesNumber(color);
            color++;
        }
        return each_factory_str;

         */
        return getStateString();
    }

    // TODO update factory state
    @Override
    public void updateState() {

    }

    @Override
    public void refillTiles(char[] refill) {
        StringBuilder SB = new StringBuilder();
        Arrays.sort(refill);
        for (char c : refill) {
            SB.append(c);
            this.letters[c]++;
        }
        this.factoryState = String.valueOf(SB);
    }

    @Override
    public void countTilesNumber(String State) {
        int[] letters_array = new int[128];
        char[] eachFactoryState_char_array = State.toCharArray();
        for (char c : eachFactoryState_char_array) {
            letters_array[c]++;
        }
        // 'a'~'f'
        this.letters[BLUE] = letters_array[BLUE];
        this.letters[GREEN] = letters_array[GREEN];
        this.letters[ORANGE] = letters_array[ORANGE];
        this.letters[PURPLE] = letters_array[PURPLE];
        this.letters[RED] = letters_array[RED];
    }

    @Override
    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    @Override
    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        char color = BLUE;
        for (int i = 0; i <= RED - BLUE; i++) {
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

    @Override
    public void removeTile(char color) {
        this.letters[color]--;
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
        updateState();
    }

    @Override
    public void addTile(char color) {
        this.letters[color]++;
        updateState();
    }
}
