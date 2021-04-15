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
    /*
    public void countFactoryTilesNumber() {
        int[] letters_array = new int[128];
        char[] eachFactoryState_char_array = factoryState.toCharArray();
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

    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        char color = BLUE;
        for (int i = 0; i <= RED - BLUE; i++) {
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

     */

    /**
     * Each factory refills with given factoryTiles
     * Update letters and F_eachFactoryState
     *
     * @param factoryTiles
     */
    void refill_eachFactory(char[] factoryTiles) {
        StringBuilder SB = new StringBuilder();
        Arrays.sort(factoryTiles);
        for (char c : factoryTiles) {
            SB.append(c);
            this.letters[c]++;
        }
        this.factoryState = String.valueOf(SB);
    }

    public String toString() {
        String each_factory_str = " Number : " + this.number + " Factory : " + this.factoryState + "\n";
        char color = BLUE;
        for (int i = 0; i <= RED - BLUE; i++) {
            each_factory_str += " " + color + " : " + this.getTilesNumber(color);
            color++;
        }
        return each_factory_str;
    }

    @Override
    public boolean isStateEmpty() {
        return this.factoryState.isEmpty();
    }

    @Override
    public String getStateString() {
        return this.factoryState;
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

    }

    @Override
    public void removeAllTiles() {

    }

    @Override
    public void addTile(char color) {

    }
}
