package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Players implements Player{
    // All information about players are stored in ArrayList players
    ArrayList<eachPlayer> players = new ArrayList<eachPlayer>();

    // All HashMap of Character and eachPlayer are stored
    HashMap<Character, eachPlayer> players_map = new HashMap<>();

    // Input String playerState is stored in this field
    String playerState;

    // Maximum player numbers
    int max_player_number;

    /**
     * Players constructor method puts number of maximum players
     * @param max_player_number
     */
    public Players(int max_player_number){
        this.max_player_number = max_player_number;
    }
    /**
     * Take playerState as input
     * Automatically sorts information
     * @param playerState the state of player
     */
    @Override
    public void PlayerState(String playerState) {
        this.playerState = playerState;
        // Initialize Arraylists
        this.players.clear();
        this.players_map.clear();
        // Split String into character array
        char [] playerState_array = playerState.toCharArray();
        ArrayList<Character> playerState_name_arr = new ArrayList<Character>();
        ArrayList<String> playerState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters player 'A' ~ 'D' || mosaic 'M || storage 'S' || floor 'F
        for( char c : playerState_array ){
            //System.out.println(c);
            if( (c >= 'A' && c <= 'D') || c =='M' || c =='S' || c == 'F'){
                //System.out.println(String.valueOf(c));
                playerState_name_arr.add(c);
                playerState_content_arr.add(String.valueOf(SB));
                SB.delete(0,SB.length());
                len++;
            }
            else{
                SB.append(c);
            }
        }
        playerState_content_arr.add(String.valueOf(SB));
        SB.delete(0,SB.length());
        playerState_content_arr.remove(0);

        for(int i=0; i < len; i++){
            System.out.println(playerState_name_arr.get(i) + ", " + playerState_content_arr.get(i));
        }

        // Store information of each player into this.players
        int num_turn = 0;
        for(int i=0; i < len; i++){
            if(i % 4 == 0){
                num_turn++;
                this.addPlayer(playerState_name_arr.get(i), Integer.valueOf(playerState_content_arr.get(i)), num_turn);
            }
            else if(i % 4 == 1){
                this.addMosaic(playerState_content_arr.get(i), num_turn);
            }
            else if(i % 4 == 2){
                this.addStorage(playerState_content_arr.get(i), num_turn);
            }
            else if(i % 4 == 3){
                this.addFloor(playerState_content_arr.get(i), num_turn);
            }
        }
        // Produce HashMap<String, eachPlayer>
        playerHashMap();
    }

    /**
     * Automatically stores below information
     * @param name
     * @param score
     * @param num_turn
     */
    @Override
    public void addPlayer(Character name, Integer score, int num_turn){
        this.players.add(new eachPlayer(name, score, num_turn));
        Collections.sort(this.players);
    }

    @Override
    public void printPlayer(){
        for( eachPlayer player : this.players){
            System.out.println(player.toString());
        }
    }

    /**
     * Automatically stores below information
     * @param mosaicState
     * @param num_turn
     */
    @Override
    public void addMosaic(String mosaicState, int num_turn) {
        this.players.get(num_turn - 1).eachMosaic(mosaicState);
    }

    @Override
    public void printMosaic() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Mosaic State : " + player.mosaicState);
            char color = 'a';
            for(int i=0; i <= 'e' - 'a'; i++){
                System.out.print(" " + color + " : " + player.getMosaicTilesNumber(color));
                color++;
            }
            System.out.println();
        }
    }

    /**
     * Automatically stores below information
     * @param storageState
     * @param num_turn
     */
    @Override
    public void addStorage(String storageState, int num_turn) {
        this.players.get(num_turn - 1).eachStorage(storageState);
    }

    @Override
    public void printStorage() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Storage State : " + player.storageState);
        }
    }

    /**
     * Automatically stores below information
     * @param floorState
     * @param num_turn
     */
    @Override
    public void addFloor(String floorState, int num_turn) {
        this.players.get(num_turn - 1).eachFloor(floorState);
    }

    @Override
    public void printFloor() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Floor State : " + player.floorState);
            char color = 'a';
            for(int i=0; i <= 'f' - 'a'; i++){
                System.out.print(" " + color + " : " + player.getFloorTilesNumber(color));
                color++;
            }
            System.out.println();
        }
    }

    /**
     * Stores name of each player and each player to HashMap
     * Use getPlayer method to get each player data
     */
    public void playerHashMap(){
        for( eachPlayer eachplayer : players ){
            this.players_map.put(eachplayer.name, eachplayer);
        }
    }

    @Override
    public String getPlayerState() {
        return this.playerState;
    }

    /**
     * Get player data by player name
     * @param player_name
     * @return
     */
    public eachPlayer getPlayer(Character player_name){
        return players_map.get(player_name);
    }

    public class eachPlayer implements Comparable<eachPlayer>{
        Character name;
        int score;
        int num_turn;
        String mosaicState = "";
        String storageState = "";
        String floorState = "";

        // Inner class fields
        private Mosaic mosaic;
        //private Storage storage;
        private Floor floor;
        /**
         * Each player has its name, score and turn
         * @param n
         * @param s
         * @param c
         */
        public eachPlayer(Character n, int s, int c) {
            this.name = n;
            this.score = s;
            this.num_turn = c;
        }

        public void eachMosaic(String mosaicState){
            this.mosaicState = mosaicState;
            this.mosaic = new Mosaic(mosaicState);
        }

        public void eachStorage(String storageState){
            this.storageState = storageState;
        }

        public void eachFloor(String floorState){
            this.floorState = floorState;
            this.floor = new Floor(floorState);
        }

        public String toString() {
            return " Name : " + this.name + " Turn : " + this.num_turn + " Score : " + this.score;
        }

        /**
         * Type of this.name is Character
         * @return
         */
        public char getName(){
            return this.name;
        }

        /**
         * Type of this.score is int
         * @return
         */
        public int getScore(){
            return this.score;
        }

        public int getNum_turn(){
            return this.num_turn;
        }

        public String getMosaic(){
            return this.mosaicState;
        }

        public String getStorage(){
            return this.storageState;
        }

        public String getFloor(){
            return this.floorState;
        }

        public int getMosaicTilesNumber(char color) {
            return this.mosaic.getTilesNumber(color);
        }

        public int getFloorTilesNumber(char color) {
            return this.floor.getTilesNumber(color);
        }


        @Override
        public int compareTo(eachPlayer player) {
            if(num_turn == player.num_turn){
                return 0;
            }
            else if(num_turn > player.num_turn){
                return 1;
            }
            else{
                return -1;
            }
        }

        public class Mosaic {
            String mosaicState;

            int[] letters = new int[128];
            final char blue = 'a';
            final char green = 'b';
            final char orange = 'c';
            final char purple = 'd';
            final char red = 'e';

            public Mosaic(String mosaicState){
                this.mosaicState = mosaicState;
                countLetters(mosaicState);
            }

            public void countLetters(String mosaicState){

                int[] letters_array = new int[128];
                char[] mosaicState_char_array = mosaicState.toCharArray();
                for(char c : mosaicState_char_array){
                    letters_array[c]++;
                }
                // 'a'~'f'
                this.letters[blue] = letters_array[blue];
                this.letters[green] = letters_array[green];
                this.letters[orange] = letters_array[orange];
                this.letters[purple] = letters_array[purple];
                this.letters[red] = letters_array[red];
            }

            public int getTilesNumber(char color){
                return this.letters[color];
            }
        }

        public class Storage{
            String storageState;

            int[] letters = new int[128];
            final char blue = 'a';
            final char green = 'b';
            final char orange = 'c';
            final char purple = 'd';
            final char red = 'e';

            public Storage(String storageState){
                this.storageState = storageState;
                countLetters(storageState);
            }

            public void countLetters(String storageState){
                ArrayList<Integer> storage_counts = new ArrayList<Integer>();
                char[] storageState_char_array = storageState.toCharArray();
                StringBuilder SB = new StringBuilder();
                int len = 0;
                for(char c : storageState_char_array){
                    if( len % 2 == 0 && len != 0){
                        storage_counts.add(Integer.valueOf(String.valueOf(SB)));
                        SB.delete(0,SB.length());
                    }
                    SB.append(c);
                    len++;
                }
                storage_counts.add(Integer.valueOf(String.valueOf(SB)));
                SB.delete(0,SB.length());
                // 'a'~'f'
                this.letters[blue] = storage_counts.get(0);
                this.letters[green] = storage_counts.get(1);
                this.letters[orange] = storage_counts.get(2);
                this.letters[purple] = storage_counts.get(3);
                this.letters[red] = storage_counts.get(4);
            }

            public int getTilesNumber(char color){
                return this.letters[color];
            }
        }

        public class Floor {
            String floorState;

            int[] letters = new int[128];
            final char blue = 'a';
            final char green = 'b';
            final char orange = 'c';
            final char purple = 'd';
            final char red = 'e';
            final char first_player = 'f';

            public Floor(String floorState){
                this.floorState = floorState;
                countLetters(floorState);
            }

            public void countLetters(String floorState){

                int[] letters_array = new int[128];
                char[] floorState_char_array = floorState.toCharArray();
                for(char c : floorState_char_array){
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
    }
}
