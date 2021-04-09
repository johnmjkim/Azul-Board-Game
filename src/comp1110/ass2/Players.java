package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Players implements Player{
    // All information about players are stored in ArrayList players
    ArrayList<eachPlayer> players = new ArrayList<eachPlayer>();

    // All HashMap of String and eachPlayer are stored
    HashMap<Character, eachPlayer> players_map = new HashMap<>();

    // Input String playerState is stored in this field
    String playerState;

    /**
     * Take playerState as input
     * Automatically sorts information
     * @param playerState the state of player
     */
    @Override
    public void PlayerState(String playerState) {
        this.playerState = playerState;
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
        int turn = 0;
        for(int i=0; i < len; i++){
            if(i % 4 == 0){
                turn++;
                this.addPlayer(playerState_name_arr.get(i), Integer.valueOf(playerState_content_arr.get(i)), turn);
            }
            else if(i % 4 == 1){
                this.addMosaic(playerState_content_arr.get(i), turn);
            }
            else if(i % 4 == 2){
                this.addStorage(playerState_content_arr.get(i), turn);
            }
            else if(i % 4 == 3){
                this.addFloor(playerState_content_arr.get(i), turn);
            }
        }
        // Produce HashMap<String, eachPlayer>
        playerHashMap();
    }

    /**
     * Automatically stores below information
     * @param name
     * @param score
     * @param turn
     */
    @Override
    public void addPlayer(Character name, Integer score, int turn){
        this.players.add(new eachPlayer(name, score, turn));
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
     * @param turn
     */
    @Override
    public void addMosaic(String mosaicState, int turn) {
        this.players.get(turn - 1).eachMosaic(mosaicState);
    }

    @Override
    public void printMosaic() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Mosaic : " + player.mosaic);
        }
    }

    /**
     * Automatically stores below information
     * @param storageState
     * @param turn
     */
    @Override
    public void addStorage(String storageState, int turn) {
        this.players.get(turn - 1).eachStorage(storageState);
    }

    @Override
    public void printStorage() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Storage : " + player.storage);
        }
    }

    /**
     * Automatically stores below information
     * @param floorState
     * @param turn
     */
    @Override
    public void addFloor(String floorState, int turn) {
        this.players.get(turn - 1).eachFloor(floorState);
    }

    @Override
    public void printFloor() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Floor : " + player.floor);
        }
    }

    /**
     * Stores name of each player and each player to HashMap
     * Use getPlayer method to get each player data
     */
    public void playerHashMap(){
        for( eachPlayer eachplayer : players){
            this.players_map.put(eachplayer.name, eachplayer);
        }
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
        int turn;
        String mosaic = "";
        String storage = "";
        String floor = "";

        /**
         * Each player has its name, score and turn
         * @param n
         * @param s
         * @param c
         */
        eachPlayer(Character n, int s, int c) {
            this.name = n;
            this.score = s;
            this.turn = c;
        }

        public void eachMosaic(String mosaic){
            this.mosaic = mosaic;
        }

        public void eachStorage(String storage){
            this.storage = storage;
        }

        public void eachFloor(String floor){
            this.floor = floor;
        }

        public String toString() {
            return " Name : " + this.name + " Turn : " + this.turn + " Score : " + this.score;
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

        public int getTurn(){
            return this.turn;
        }

        public String getMosaic(){
            return this.mosaic;
        }

        public String getStorage(){
            return this.storage;
        }

        public String getFloor(){
            return this.floor;
        }

        @Override
        public int compareTo(eachPlayer player) {
            if(turn == player.turn){
                return 0;
            }
            else if(turn > player.turn){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
}
