package comp1110.ass2;

import java.util.ArrayList;

public class BagTypedBoard implements BagTyped, Tiles{

    String State = EMPTY_STATE;
    int[] letters = new int[128];

    public BagTypedBoard(String State){
        this.State = State;
        countTilesNumber(State);
    }

    @Override
    public void countTilesNumber(String State) {
        ArrayList<Integer> tiles_counts = new ArrayList<Integer>();
        char[] State_char_array = State.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : State_char_array){
            if( len % 2 == 0 && len != 0){
                tiles_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        tiles_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'r'
        this.letters[BLUE] = tiles_counts.get(0);
        this.letters[GREEN] = tiles_counts.get(1);
        this.letters[ORANGE] = tiles_counts.get(2);
        this.letters[PURPLE] = tiles_counts.get(3);
        this.letters[RED] = tiles_counts.get(4);
    }

    @Override
    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    @Override
    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
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
        for(int i=0; i <= RED - BLUE; i++){
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
    public void refillTiles(String refill) {
        ArrayList<Integer> tiles_counts = new ArrayList<Integer>();
        char[] refill_char_array = refill.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : refill_char_array){
            if( len % 2 == 0 && len != 0){
                tiles_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        tiles_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'e'
        this.letters[BLUE] += tiles_counts.get(0);
        this.letters[GREEN] += tiles_counts.get(1);
        this.letters[ORANGE] += tiles_counts.get(2);
        this.letters[PURPLE] += tiles_counts.get(3);
        this.letters[RED] += tiles_counts.get(4);

        updateState();
    }

    @Override
    public boolean isStateEmpty() {
        return false;
    }

    @Override
    public String getStateString() {
        return null;
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] tiles_letters = this.letters;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            if(tiles_letters[color] < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(tiles_letters[color]));
            color++;
        }
        this.State = String.valueOf(SB);
    }
}
