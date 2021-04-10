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

    // Players substring
    public final char PLAYER_A = 'A';
    public final char PLAYER_B = 'B';
    public final char PLAYER_C = 'C';
    public final char PLAYER_D = 'D';

    // Players state substring
    public final char MOSAIC = 'M';
    public final char STORAGE = 'S';
    public final char FLOOR = 'F';

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';
    public final char FIRST_PLAYER = 'f';

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
            if( (c >= PLAYER_A && c <= PLAYER_D) || c == MOSAIC || c == STORAGE || c == FLOOR){
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
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
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
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                System.out.print(" " + color + " : " + player.getStorageTilesNumber(color));
                color++;
            }
            System.out.println();
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
            char color = BLUE;
            for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
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
        private Storage storage;
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
            this.storage = new Storage(storageState);
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

        public int getStorageTilesNumber(char color) {
            return this.storage.getTilesNumber(color);
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
                this.letters[BLUE] = letters_array[BLUE];
                this.letters[GREEN] = letters_array[GREEN];
                this.letters[ORANGE] = letters_array[ORANGE];
                this.letters[PURPLE] = letters_array[PURPLE];
                this.letters[RED] = letters_array[RED];
            }

            public int getTilesNumber(char color){
                return this.letters[color];
            }
        }

        public class Storage{
            String storageState;
            ArrayList<eachStorageRow> storage_rows = new ArrayList<eachStorageRow>();

            int[] letters = new int[128];

            public Storage(String storageState){
                this.storage_rows.clear();
                this.storageState = storageState;
                addStorageRow(storageState);
                countTilesNumber();
            }

            public void addStorageRow(String storageState){
                ArrayList<String> storage_row = new ArrayList<String>();
                int max_number = 5;
                int len = 0;
                int str_row_num = 0;
                StringBuilder SB = new StringBuilder();
                for(char c : storageState.toCharArray()){
                    if( len % 3 == 0){
                        if(str_row_num == Character.getNumericValue(c)){
                            storage_row.add(String.valueOf(SB));
                        }
                        else{
                            storage_row.add(String.valueOf(SB));
                            while(str_row_num != Character.getNumericValue(c)){
                                storage_row.add("");
                                str_row_num++;
                            }
                        }
                        SB.delete(0,SB.length());
                        str_row_num++;
                    }
                    else{
                        SB.append(c);
                    }
                    len++;
                }
                storage_row.add(String.valueOf(SB));
                while(str_row_num < max_number){
                    storage_row.add("");
                    str_row_num++;
                }
                SB.delete(0,SB.length());
                /*
                for(String s : storage_row){
                    System.out.println(" -> " + s);
                }

                 */

                for(int i=0; i < max_number; i++){
                    this.storage_rows.add(new eachStorageRow(storage_row.get(i+1),i));
                }
                Collections.sort(this.storage_rows);

            }

            public void countTilesNumber(){
                for( eachStorageRow sr : this.storage_rows){
                    if(!sr.isStorageRowEmpty()){
                        //System.out.println(" color : " + sr.getTilesColor() + " number : " + sr.getTilesNumber());
                        this.letters[sr.getTilesColor()] += sr.getTilesNumber();
                    }
                }
            }

            public int getTilesNumber(char color){
                return this.letters[color];
            }

            public class eachStorageRow implements Comparable<eachStorageRow>{
                String storagerowState = "";
                int row;

                public eachStorageRow (String storagerowState, int row){
                    this.storagerowState = storagerowState;
                    this.row = row;
                }

                public int getTilesNumber(){
                    if(this.isStorageRowEmpty()){
                        return 0;
                    }
                    else{
                        int storage_row_count = Character.getNumericValue(this.storagerowState.charAt(1));
                        return storage_row_count;
                    }
                }

                public char getTilesColor(){
                    if(this.isStorageRowEmpty()){
                        return ' ';
                    }
                    else{
                        char storage_row_color = this.storagerowState.charAt(0);
                        return storage_row_color;
                    }
                }

                boolean isStorageRowEmpty(){
                    return this.storagerowState.isEmpty();
                }

                @Override
                public int compareTo(eachStorageRow storagerow) {
                    if(row == storagerow.row){
                        return 0;
                    }
                    else if(row > storagerow.row){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
            }
        }

        public class Floor {
            String floorState;

            int[] letters = new int[128];

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
        }
    }
}
