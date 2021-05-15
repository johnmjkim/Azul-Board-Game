package comp1110.ass2.backend;

import java.util.ArrayList;

public class BagTypedObject implements BagTyped{

    String bagTypedObjectState = EMPTY_STATE;
    int[] letters = new int[128];

    /**
     * @author Min Jae, Kim
     * Constructor method for BagTypedObject class
     * BagTypedObject class is all object that stores tiles like below format:
     * "1315050716"
     * Bag, Discard class stores tiles like above format
     * @param bagTypedObjectState
     */
    public BagTypedObject(String bagTypedObjectState){
        this.bagTypedObjectState = bagTypedObjectState;
        countTilesNumber(bagTypedObjectState);
    }

    @Override
    public void refillTiles(String refill) {
        ArrayList<Integer> bagtypedobject_counts = new ArrayList<Integer>();
        char[] refill_char_array = refill.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : refill_char_array){
            if( len % 2 == 0 && len != 0){
                bagtypedobject_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        bagtypedobject_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'f'
        this.letters[BLUE] += bagtypedobject_counts.get(0);
        this.letters[GREEN] += bagtypedobject_counts.get(1);
        this.letters[ORANGE] += bagtypedobject_counts.get(2);
        this.letters[PURPLE] += bagtypedobject_counts.get(3);
        this.letters[RED] += bagtypedobject_counts.get(4);
        updateState();
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
    public void addTiles(char color, int n) {
        for(int i=0; i < n; i++){
            this.letters[color]++;
        }
        updateState();
    }

    @Override
    public boolean isStateEmpty() {
        return this.bagTypedObjectState.isEmpty();
    }

    @Override
    public String getStateString() {
        return this.bagTypedObjectState;
    }

    @Override
    public String toString(){
        return getStateString();
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] bagTypedObject_letters = this.letters;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            if(bagTypedObject_letters[color] < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(bagTypedObject_letters[color]));
            //System.out.println(" string, value : " + SB + ", " + String.valueOf(bagTypedObject_letters[color]) + " ");
            color++;
        }
        this.bagTypedObjectState = String.valueOf(SB);
    }

    @Override
    public void countTilesNumber(String bagTypedObjectState) {
        ArrayList<Integer> bagtypedobject_counts = new ArrayList<Integer>();
        char[] bagTypedObjectState_char_array = bagTypedObjectState.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : bagTypedObjectState_char_array){
            if( len % 2 == 0 && len != 0){
                //System.out.print(" string, value : " + SB + ", " + String.valueOf(SB) + " ");
                bagtypedobject_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        //System.out.println(" string, value : " + SB + ", " + String.valueOf(SB) + " ");
        bagtypedobject_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'f'
        this.letters[BLUE] = bagtypedobject_counts.get(0);
        this.letters[GREEN] = bagtypedobject_counts.get(1);
        this.letters[ORANGE] = bagtypedobject_counts.get(2);
        this.letters[PURPLE] = bagtypedobject_counts.get(3);
        this.letters[RED] = bagtypedobject_counts.get(4);
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
}
