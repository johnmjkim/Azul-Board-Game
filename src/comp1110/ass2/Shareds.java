package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Shareds implements Shared{
    // Input String sharedState is stored in this field
    String sharedState;

    // Fields
    char turn;
    String factoryState;
    String centerState;
    String bagState;
    String discardState;

    // All information about factory, Center, Bad, Discard
    ArrayList<eachFactory> factories = new ArrayList<eachFactory>();

    // Maximum player numbers
    int max_player_number;

    // Players substring
    public final char PLAYER_A = 'A';
    public final char PLAYER_B = 'B';
    public final char PLAYER_C = 'C';
    public final char PLAYER_D = 'D';

    // Shared state substring
    public final char FACTORY = 'F';
    public final char CENTER = 'C';
    public final char BAG = 'B';
    public final char DISCARD = 'D';

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';
    public final char FIRST_PLAYER = 'f';

    // Size, Numbers of all components
    public final int FACTORY_MAX_NUMBER = 2 * max_player_number + 1;
    public final int FACTORY_SIZE = 4;
    public final String EMPTY_TILES = "0000000000";

    // Inner class fields
    public Center center;
    public Bag bag;
    public Discard discard;

    /**
     * Shareds constructor method puts number of maximum players
     * @param max_player_number
     */
    public Shareds(int max_player_number){
        this.max_player_number = max_player_number;
    }

    /**
     * Take sharedState as input
     * Automatically sorts information
     * @param sharedState
     */
    @Override
    public void SharedState(String sharedState) {
        this.sharedState = sharedState;
        // Initialize Arraylists
        this.factories.clear();
        char [] sharedState_array = sharedState.toCharArray();
        ArrayList<Character> sharedState_name_arr = new ArrayList<Character>();
        ArrayList<String> sharedState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters
        for( char c : sharedState_array ){
            //System.out.println(c);
            if(c >= PLAYER_A && c <= PLAYER_D || c == FACTORY || c == CENTER || c == BAG || c == DISCARD){
                //System.out.println(String.valueOf(c));
                sharedState_name_arr.add(c);
                sharedState_content_arr.add(String.valueOf(SB));
                SB.delete(0,SB.length());
                len++;
            }
            else{
                SB.append(c);
            }
        }
        sharedState_content_arr.add(String.valueOf(SB));
        SB.delete(0,SB.length());
        sharedState_content_arr.remove(0);
        /*
        for(int i=0; i < len; i++){
            System.out.println(sharedState_name_arr.get(i) + ", " + sharedState_content_arr.get(i));
        }

         */

        for(int i=0; i < len; i++){
            if(i == 0){
                this.addTurn(sharedState_name_arr.get(i));
            }
            else if(i == 1){
                this.addFactory(sharedState_content_arr.get(i));
            }
            else if(i == 2){
                this.addCenter(sharedState_content_arr.get(i));
            }
            else if(i == 3){
                this.addBag(sharedState_content_arr.get(i));
            }
            else if(i == 4){
                this.addDiscard(sharedState_content_arr.get(i));
            }
        }
    }

    @Override
    public void printSharedState() {
        System.out.println(this.sharedState);
    }

    @Override
    public void addTurn(Character turnState) {
        this.turn = turnState;
    }

    @Override
    public void printTurn() {
        System.out.println(this.turn);
    }

    @Override
    public void addFactory(String factoryState) {
        this.factoryState = factoryState;
        ArrayList<String> factory = new ArrayList<String>();
        int max_number = 2 * this.max_player_number + 1;
        int len = 0;
        int fac_num = 0;
        StringBuilder SB = new StringBuilder();
        for(char c : factoryState.toCharArray()){
            if( len % 5 == 0){
                if(fac_num == Character.getNumericValue(c)){
                    factory.add(String.valueOf(SB));
                }
                else{
                    factory.add(String.valueOf(SB));
                    while(fac_num != Character.getNumericValue(c)){
                        factory.add("");
                        fac_num++;
                    }
                }
                SB.delete(0,SB.length());
                fac_num++;
            }
            else{
                SB.append(c);
            }
            len++;
        }
        factory.add(String.valueOf(SB));
        while(fac_num < max_number){
            factory.add("");
            fac_num++;
        }
        SB.delete(0,SB.length());
        /*
        for(String s : factory){
            System.out.println(" -> " + s);
        }

         */
        for(int i=0; i < max_number; i++){
            this.factories.add(new Shareds.eachFactory(factory.get(i+1),i));
        }
        Collections.sort(this.factories);
    }

    @Override
    public void printFactory() {
        System.out.println(" Factory State : " + this.factoryState);
        for( eachFactory factory : this.factories){
            System.out.println(factory.toString());
        }
        System.out.print(" Factory Total : ");
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            System.out.print(" " + color + " : " + getFactoryTilesNumber(color));
            color++;
        }
        System.out.println();
        System.out.println(" Factory tiles total : " + getFactoryTotalTiles());
    }

    @Override
    public void addCenter(String centerState) {
        this.centerState = centerState;
        this.center = new Center(centerState);
    }

    @Override
    public void printCenter() {
        System.out.println(" Center State : " + this.centerState);
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            System.out.print(" " + color + " : " + getCenterTilesNumber(color));
            color++;
        }
        System.out.println();
        System.out.println(" Center tiles total : " + getCenterTotalTiles());
    }

    @Override
    public void addBag(String bagState) {
        this.bagState = bagState;
        this.bag = new Bag(bagState);
    }

    @Override
    public void printBag() {
        System.out.println(" Bag State : " + this.bagState);
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            System.out.print(" " + color + " : " + getBagTilesNumber(color));
            color++;
        }
        System.out.println();
        System.out.println(" Bag tiles total : " + getBagTotalTiles());
    }

    @Override
    public void addDiscard(String discardState) {
        this.discardState = discardState;
        this.discard = new Discard(discardState);
    }

    @Override
    public void printDiscard() {
        System.out.println(" Discard State : " + this.discardState);
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            System.out.print(" " + color + " : " + getDiscardTilesNumber(color));
            color++;
        }
        System.out.println();
        System.out.println(" Discard tiles total : " + getDiscardTotalTiles());
    }

    /**
     * Get values from factory/center/bag/discard
     */

    @Override
    public String getSharedState() {
        return this.sharedState;
    }

    @Override
    public String getFactoryState() {
        return this.factoryState;
    }

    @Override
    public String getCenterState() {
        return this.centerState;
    }

    @Override
    public String getBagState() {
        return this.bagState;
    }

    @Override
    public String getDiscardState() {
        return this.discardState;
    }

    @Override
    public int getFactoryTilesNumber(char color) {
        int tot_tiles = 0;
        for( eachFactory f : this.factories){
            tot_tiles += f.getTilesNumber(color);
        }
        return tot_tiles;
    }

    @Override
    public int getCenterTilesNumber(char color) {
        return this.center.getTilesNumber(color);
    }

    @Override
    public int getBagTilesNumber(char color) {
        return this.bag.getTilesNumber(color);
    }

    @Override
    public int getDiscardTilesNumber(char color) {
        return this.discard.getTilesNumber(color);
    }

    @Override
    public int getFactoryTotalTiles() {
        int tot_tiles = 0;
        for( eachFactory f : this.factories){
            tot_tiles += f.getTotalTilesNumber();
        }
        return tot_tiles;
    }

    @Override
    public int getCenterTotalTiles() {
        return this.center.getTotalTilesNumber();
    }

    @Override
    public int getBagTotalTiles() {
        return this.bag.getTotalTilesNumber();
    }

    @Override
    public int getDiscardTotalTiles() {
        return this.discard.getTotalTilesNumber();
    }

    /**
     * Methods for Factory
     */

    @Override
    public boolean isFactoryFull() {
        int max_factory = 2 * this.max_player_number + 1;
        return ( FACTORY_SIZE * max_factory == getFactoryTotalTiles());
    }

    @Override
    public void refillFactory() {
        char[] factory_tiles = new char[FACTORY_SIZE];
        int factory_num = this.factories.size();
        for(int i=0; i < factory_num; i++){
            if(this.factories.get(i).is_eachFactoryStateEmpty()){
                for(int j=0; j < FACTORY_SIZE; j++){
                    factory_tiles[j] = getRandomTileBag();
                }
                this.factories.get(i).refill_eachFactory(factory_tiles);
            }
        }
        updateSharedState();
    }

    /**
     * Methods for Bag
     */

    @Override
    public void refillBag() {
        if(this.discard.getTotalTilesNumber() > 0){
            String discard_tiles = this.discard.D_discardState;
            this.bag.refillB_Bag(discard_tiles);
            clearDiscard();
            updateSharedState();
        }
    }

    @Override
    public char getRandomTileBag() {
        //printBag();
        if(this.bag.getTotalTilesNumber() > 0){
            char tile_drawn = this.bag.getRandomTile();
            this.bag.removeTile(tile_drawn);
            updateSharedState();
            return tile_drawn;
        }
        else if(this.discard.getTotalTilesNumber() > 0){
            refillBag();
            char tile_drawn = this.bag.getRandomTile();
            this.bag.removeTile(tile_drawn);
            updateSharedState();
            return tile_drawn;
        }
        else{
            return 'Z';
        }
    }

    /**
     * Methods for Discard
     */

    @Override
    public void clearDiscard() {
        this.discard.clearD_Discard();
    }

    /**
     * Private methods for Shareds
     */

    private void updateSharedState(){
        StringBuilder SB = new StringBuilder();
        updateFactoryState();
        updateCenterState();
        updateBagState();
        updateDiscardState();
        SB.append(turn);
        SB.append(FACTORY);
        SB.append(this.factoryState);
        SB.append(CENTER);
        SB.append(this.centerState);
        SB.append(BAG);
        SB.append(this.bagState);
        SB.append(DISCARD);
        SB.append(this.discardState);
        SharedState(String.valueOf(SB));
    }

    private void updateFactoryState(){
        StringBuilder SB = new StringBuilder();
        SB.append("");
        for( eachFactory f : this.factories ){
            if(!f.is_eachFactoryStateEmpty()){
                SB.append(f.number);
                SB.append(f.F_eachFactoryState);
            }
        }
        this.factoryState = String.valueOf(SB);
    }

    private void updateCenterState(){
        StringBuilder SB = new StringBuilder();
        SB.append("");
        int[] center_letters = this.center.letters;
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            while(center_letters[color + i] > 0){
                SB.append((char)(color + i));
                //System.out.println( "char : " + (char)(color + i) + " added : " + SB);
                center_letters[color + i]--;
            }
        }
        this.centerState = String.valueOf(SB);
    }

    private void updateBagState(){
        StringBuilder SB = new StringBuilder();
        SB.append("");
        int[] bag_letters = this.bag.letters;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            if(bag_letters[color + i] < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(bag_letters[color + i]));
        }
        this.bagState = String.valueOf(SB);
    }

    private void updateDiscardState(){
        StringBuilder SB = new StringBuilder();
        SB.append("");
        int[] discard_letters = this.discard.letters;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            if(discard_letters[color + i] < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(discard_letters[color + i]));
        }
        this.discardState = String.valueOf(SB);
    }

    /**
     * Inner class eachFactory of Shareds class
     * All eachFactory state stored here
     */
    public class eachFactory implements Comparable<eachFactory>{
        int number;
        int[] letters = new int[128];
        String F_eachFactoryState = "";

        public eachFactory(String eachFactoryState, int number) {
            this.F_eachFactoryState = eachFactoryState;
            this.number = number;
            count_eachFactoryTilesNumber();
        }

        public void count_eachFactoryTilesNumber(){
            int[] letters_array = new int[128];
            char[] eachFactoryState_char_array = F_eachFactoryState.toCharArray();
            for(char c : eachFactoryState_char_array){
                letters_array[c]++;
            }
            // 'a'~'f'
            this.letters[BLUE] = letters_array[BLUE];
            this.letters[GREEN] = letters_array[GREEN];
            this.letters[ORANGE] = letters_array[ORANGE];
            this.letters[PURPLE] = letters_array[PURPLE];
            this.letters[RED] = letters_array[RED];
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

        boolean is_eachFactoryStateEmpty(){
            return this.F_eachFactoryState.isEmpty();
        }

        void refill_eachFactory(char[] factoryTiles ){
            StringBuilder SB = new StringBuilder();
            for( char c : factoryTiles ){
                SB.append(c);
                this.letters[c]++;
            }
            this.F_eachFactoryState = String.valueOf(SB);
        }

        public String toString() {
            String each_factory_str = " Number : " + this.number + " Factory : " + this.F_eachFactoryState + "\n";
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                each_factory_str += " " + color + " : " + this.getTilesNumber(color);
                color++;
            }
            return each_factory_str;
        }

        @Override
        public int compareTo(eachFactory factory) {
            if(number== factory.number){
                return 0;
            }
            else if(number > factory.number){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    /**
     * Inner class Center of Shareds class
     * All Center state stored here
     */
    public class Center{
        String C_centerState = "";
        int[] letters = new int[128];

        public Center(String centerState){
            this.C_centerState = centerState;
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

        boolean isCenterStateEmpty(){
            return this.C_centerState.isEmpty();
        }
    }

    /**
     * Inner class Bag of Shareds class
     * All Bag state stored here
     */
    public class Bag{
        String B_bagState ="";
        int[] letters = new int[128];

        public Bag(String bagState){
            this.B_bagState = bagState;
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
            // 'a'~'f'
            this.letters[BLUE] = bags_counts.get(0);
            this.letters[GREEN] = bags_counts.get(1);
            this.letters[ORANGE] = bags_counts.get(2);
            this.letters[PURPLE] = bags_counts.get(3);
            this.letters[RED] = bags_counts.get(4);
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

        public boolean isBagStateEmpty(){
            return this.B_bagState.isEmpty();
        }

        public char getRandomTile(){
            char picked_tile = 'Z';
            int prob_blue = getTilesNumber(BLUE);
            int prob_green = prob_blue + getBagTilesNumber(GREEN);
            int prob_orange = prob_green + getBagTilesNumber(ORANGE);
            int prob_purple = prob_orange + getBagTilesNumber(PURPLE);
            int prob_red = prob_purple + getBagTilesNumber(RED);

            Random r = new Random();
            int r_range = getTotalTilesNumber() - 1;
            int r_output = 0;
            if(r_range > 0){
                r_output = r.nextInt(getTotalTilesNumber() - 1) + 1;
            }
            else{
                r_output = 1;
            }
            //System.out.println(prob_blue + ", " + prob_green + ", " + prob_orange + ", " + prob_purple + ", " + prob_red + " : " + r_output);

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

        public void removeTile(char color){
            this.letters[color]--;
        }

        public void refillB_Bag(String refill_bagState){
            this.B_bagState = refill_bagState;
            countBagTilesNumber(refill_bagState);
        }
    }

    /**
     * Inner class Discard of Shareds class
     * All Discard state stored here
     */
    public class Discard{
        String D_discardState = "";

        int[] letters = new int[128];

        public Discard(String discardState){
            this.D_discardState = discardState;
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

        boolean isDiscardStateEmpty(){
            return this.D_discardState.isEmpty();
        }

        public void clearD_Discard(){
            countDiscardTilesNumber(EMPTY_TILES);
        }
    }
}
