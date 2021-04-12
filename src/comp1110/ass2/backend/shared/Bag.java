package comp1110.ass2.backend.shared;

import comp1110.ass2.Metadata;

import java.util.ArrayList;
import java.util.Random;

public class Bag implements Metadata {

    String bagState = EMPTY_STATE;
    int[] letters = new int[128];

    public Bag(String bagState){
        this.bagState = bagState;
        countBagTilesNumber(bagState);
    }

    public void countBagTilesNumber(String bagState){
        ArrayList<Integer> bags_counts = new ArrayList<Integer>();
        char[] bagState_char_array = bagState.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : bagState_char_array){
            if( len % 2 == 0 && len != 0){
                bags_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        bags_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'r'
        this.letters[BLUE] = bags_counts.get(0);
        this.letters[GREEN] = bags_counts.get(1);
        this.letters[ORANGE] = bags_counts.get(2);
        this.letters[PURPLE] = bags_counts.get(3);
        this.letters[RED] = bags_counts.get(4);
    }

    public String getBagState(){
        return this.bagState;
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
        updatebagState();
    }

    public void removeAllTiles(){
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            while(this.letters[color] > 0){
                this.letters[color]--;
            }
            color++;
        }
        updatebagState();
    }

    public void addTile(char color){
        this.letters[color]++;
        updatebagState();
    }

    public void refillTilesBag(String refill){
        ArrayList<Integer> bags_counts = new ArrayList<Integer>();
        char[] refill_char_array = refill.toCharArray();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for(char c : refill_char_array){
            if( len % 2 == 0 && len != 0){
                bags_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
            }
            SB.append(c);
            len++;
        }
        bags_counts.add(Integer.valueOf(String.valueOf(SB)));
        SB.delete(0,SB.length());
        // 'a'~'f'
        this.letters[BLUE] += bags_counts.get(0);
        this.letters[GREEN] += bags_counts.get(1);
        this.letters[ORANGE] += bags_counts.get(2);
        this.letters[PURPLE] += bags_counts.get(3);
        this.letters[RED] += bags_counts.get(4);
        updatebagState();
    }

    public void updatebagState(){
        StringBuilder SB = new StringBuilder();
        SB.append(EMPTY_STATE);
        int[] bag_letters = this.letters;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            if(bag_letters[color] < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(bag_letters[color]));
            color++;
        }
        this.bagState = String.valueOf(SB);
    }

    /**
     * Get random tiles based on number of tiles in bag
     * r_output range is 1 (inclusive) ~ getTotalTilesNumber() (inclusive)
     * @return
     */
    public char getRandomTile(){
        char picked_tile = 'Z';
        int prob_blue = getTilesNumber(BLUE);
        int prob_green = prob_blue + getTilesNumber(GREEN);
        int prob_orange = prob_green + getTilesNumber(ORANGE);
        int prob_purple = prob_orange + getTilesNumber(PURPLE);
        int prob_red = prob_purple + getTilesNumber(RED);

        Random r = new Random();
        int r_range = getTotalTilesNumber();
        int r_output = 0;
        if(r_range > 0){
            r_output = r.nextInt(getTotalTilesNumber()) + 1;
        }
        else{
            prob_blue = 1;
            prob_green = 2;
            prob_orange = 3;
            prob_purple = 4;
            prob_red = 5;
            r_output = r.nextInt(5) + 1;
        }
        //System.out.println(r_range + " : " + prob_blue + ", " + prob_green + ", " + prob_orange + ", " + prob_purple + ", " + prob_red + " : " + r_output);

        if (r_output > 0 && r_output <= prob_blue) {
            picked_tile = BLUE;
        }
        else if (r_output > prob_blue && r_output <= prob_green) {
            picked_tile = GREEN;
        }
        else if (r_output > prob_green && r_output <= prob_orange) {
            picked_tile = ORANGE;
        }
        else if (r_output > prob_orange && r_output <= prob_purple) {
            picked_tile = PURPLE;
        }
        else if (r_output > prob_purple && r_output <= prob_red) {
            picked_tile = RED;
        }
        return picked_tile;
    }

    public boolean isBagStateEmpty(){
        return this.bagState.isEmpty();
    }

    public void printState(){
        System.out.println(this.bagState);
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
