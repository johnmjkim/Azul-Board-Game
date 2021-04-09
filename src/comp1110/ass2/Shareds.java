package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    private Center center;
    private Bag bag;
    private Discard discard;

    public Shareds(int max_player_number){
        this.max_player_number = max_player_number;
    }

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
            if(c >= 'A' && c <= 'D' || c == 'F'){
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

        for(int i=0; i < len; i++){
            System.out.println(sharedState_name_arr.get(i) + ", " + sharedState_content_arr.get(i));
        }

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
        System.out.println(this.factoryState);
        for( eachFactory factory : this.factories){
            System.out.println(factory.toString());
        }
    }

    @Override
    public void addCenter(String centerState) {
        this.centerState = centerState;
        this.center = new Shareds.Center(centerState);
    }

    @Override
    public void printCenter() {
        System.out.println(this.centerState);
        char color = 'a';
        for(int i=0; i <= 'f' - 'a'; i++){
            System.out.print(" " + color + " : " + getCenterTilesNumber(color));
            color++;
        }
        System.out.println();
    }

    @Override
    public void addBag(String bagState) {
        this.bagState = bagState;
        this.bag = new Bag(bagState);
    }

    @Override
    public void printBag() {
        System.out.println(this.bagState);
        char color = 'a';
        for(int i=0; i <= 'e' - 'a'; i++){
            System.out.print(" " + color + " : " + getBagTilesNumber(color));
            color++;
        }
        System.out.println();
    }

    @Override
    public void addDiscard(String discardState) {
        this.discardState = discardState;
        this.discard = new Discard(discardState);
    }

    @Override
    public void printDiscard() {
        System.out.println(this.discardState);
        char color = 'a';
        for(int i=0; i <= 'e' - 'a'; i++){
            System.out.print(" " + color + " : " + getDiscardTilesNumber(color));
            color++;
        }
        System.out.println();
    }

    @Override
    public String getSharedState() {
        return this.sharedState;
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

    public class eachFactory implements Comparable<eachFactory>{
        int number;
        String factory = "";

        eachFactory(String factory, int number) {
            this.factory = factory;
            this.number = number;
        }

        public String toString() {
            return " Number : " + this.number + " Factory : " + this.factory;
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

    public class Center{
        String centerState;
        int[] letters = new int[128];
        final char blue = 'a';
        final char green = 'b';
        final char orange = 'c';
        final char purple = 'd';
        final char red = 'e';
        final char first_player = 'f';

        public Center(String centerState){
            this.centerState = centerState;
            countLetters(centerState);
        }

        public void countLetters(String centerState){
            int[] letters_array = new int[128];
            char[] centerState_char_array = centerState.toCharArray();
            for(char c : centerState_char_array){
                letters_array[c]++;
            }
            // 'a'~'f'
            this.letters[blue] = letters_array[blue];
            this.letters[green] = letters_array[green];
            this.letters[orange] = letters_array[orange];
            this.letters[purple] = letters_array[purple];
            this.letters[red] = letters_array[red];
            this.letters[first_player] = letters_array[first_player];
        }

        public int getTilesNumber(char color){
            return this.letters[color];
        }
    }

    public class Bag{
        String bagState;

        int[] letters = new int[128];
        final char blue = 'a';
        final char green = 'b';
        final char orange = 'c';
        final char purple = 'd';
        final char red = 'e';

        public Bag(String bagState){
            this.bagState = bagState;
            countLetters(bagState);
        }

        public void countLetters(String bagState){
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
            this.letters[blue] = bags_counts.get(0);
            this.letters[green] = bags_counts.get(1);
            this.letters[orange] = bags_counts.get(2);
            this.letters[purple] = bags_counts.get(3);
            this.letters[red] = bags_counts.get(4);
        }

        public int getTilesNumber(char color){
            return this.letters[color];
        }
    }

    public class Discard{
        String discardState;

        int[] letters = new int[128];
        final char blue = 'a';
        final char green = 'b';
        final char orange = 'c';
        final char purple = 'd';
        final char red = 'e';

        public Discard(String discardState){
            this.discardState = discardState;
            countLetters(discardState);
        }

        public void countLetters(String discardState){
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
            this.letters[blue] = discards_counts.get(0);
            this.letters[green] = discards_counts.get(1);
            this.letters[orange] = discards_counts.get(2);
            this.letters[purple] = discards_counts.get(3);
            this.letters[red] = discards_counts.get(4);
        }

        public int getTilesNumber(char color){
            return this.letters[color];
        }
    }
}
