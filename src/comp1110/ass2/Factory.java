package comp1110.ass2;

public class Factory {

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';

    // Size, Numbers of all components
    public final String EMPTY_STATE = "";

    // Size, Numbers of all components
    public final int FACTORY_SIZE = 4;

    int number;
    int[] letters = new int[128];
    String factoryState = EMPTY_STATE;

    public Factory(String factoryState, int number) {
        this.factoryState = factoryState;
        this.number = number;
        countFactoryTilesNumber();
    }

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

    public String getFactoryState() {
        return this.factoryState;
    }

    /**
     * Get number of specific color of tiles in EACH factory
     *
     * @param color
     * @return
     */
    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    /**
     * Get number ALL of tiles in EACH factory
     *
     * @return
     */
    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        char color = BLUE;
        for (int i = 0; i <= RED - BLUE; i++) {
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

    /**
     * Each factory refills with given factoryTiles
     * Update letters and F_eachFactoryState
     *
     * @param factoryTiles
     */
    void refill_eachFactory(char[] factoryTiles) {
        StringBuilder SB = new StringBuilder();
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

    boolean isFactoryStateEmpty() {
        return this.factoryState.isEmpty();
    }
}
