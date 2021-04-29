package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Factory extends OrderTypedObject{
    int number;

    public Factory(String orderTypedObjectState, int number ) {
        super(orderTypedObjectState);
        this.number = number;
    }
}

/*
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
        updateState();
        return this.factoryState;
    }

    @Override
    public String toString() {
        return getStateString();
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] factory_letters = new int[128];
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            factory_letters[color] = this.letters[color];
            color++;
        }
        color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            while(factory_letters[color] > 0){
                SB.append(color);
                factory_letters[color]--;
            }
            color++;
        }
        this.factoryState = String.valueOf(SB);

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
    public void removeTiles(char color, int n){
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
}

 */
