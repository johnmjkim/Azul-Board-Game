package comp1110.ass2.backend.shared;

import comp1110.ass2.Metadata;

import java.util.ArrayList;

public class Discard implements Metadata {

    String discardState = EMPTY_STATE;
    int[] letters = new int[128];

    public Discard(String discardState){
        this.discardState = discardState;
        countDiscardTilesNumber(discardState);
    }

    public void countDiscardTilesNumber(String discardState){
        ArrayList<Integer> discards_counts = new ArrayList<Integer>();
        char[] discardState_char_array = discardState.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : discardState_char_array){
            if( len % 2 == 0 && len != 0){
                discards_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        discards_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'f'
        this.letters[BLUE] = discards_counts.get(0);
        this.letters[GREEN] = discards_counts.get(1);
        this.letters[ORANGE] = discards_counts.get(2);
        this.letters[PURPLE] = discards_counts.get(3);
        this.letters[RED] = discards_counts.get(4);
    }

    public String getDiscardState(){
        return this.discardState;
    }

    public int getTilesNumber(char color){
        return this.letters[color];
    }

    public int getTotalTilesNumber(){
        int tot_tiles = 0;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

    public void removeTile(char color){
        this.letters[color]--;
        updatediscardState();
    }

    public void removeAllTiles(){
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            while(this.letters[color] > 0){
                this.letters[color]--;
            }
            color++;
        }
        updatediscardState();
    }

    public void addTile(char color){
        this.letters[color]++;
        updatediscardState();
    }

    public void refillTilesDiscard(String refill){
        ArrayList<Integer> discards_counts = new ArrayList<Integer>();
        char[] refill_char_array = refill.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : refill_char_array){
            if( len % 2 == 0 && len != 0){
                discards_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        discards_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'f'
        this.letters[BLUE] += discards_counts.get(0);
        this.letters[GREEN] += discards_counts.get(1);
        this.letters[ORANGE] += discards_counts.get(2);
        this.letters[PURPLE] += discards_counts.get(3);
        this.letters[RED] += discards_counts.get(4);
        if(discards_counts.size() > 5){
            this.letters[FIRST_PLAYER] += discards_counts.get(4);
        }
        updatediscardState();
    }

    public void updatediscardState(){
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] discard_letters = this.letters;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            if(discard_letters[color] < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(discard_letters[color]));
            color++;
        }
        this.discardState = String.valueOf(SB);
    }

    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }

    public boolean isDiscardStateEmpty(){
        return this.discardState.isEmpty();
    }

    public void printState(){
        System.out.println(this.discardState);
    }

    @Override
    public String printBriefMetadata() {
        return null;
    }

    @Override
    public String printDetailMetadata() {
        return null;
    }
}
