package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Players implements Player{
    ArrayList<eachPlayer> players = new ArrayList<eachPlayer>();
    String playerState;
    @Override
    public void PlayerState(String playerState) {
        this.playerState = playerState;

        char [] playerState_array = playerState.toCharArray();
        ArrayList<Character> playerState_name_arr = new ArrayList<Character>();
        ArrayList<String> playerState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters
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
        /*
        for(int i=0; i < len; i++){
            System.out.println(playerState_name_arr.get(i) + ", " + playerState_content_arr.get(i));
        }

         */
        int turn = 0;
        for(int i=0; i < len; i++){
            if(i % 4 == 0){
                turn++;
                this.addPlayer(String.valueOf(playerState_name_arr.get(i)), Integer.valueOf(playerState_content_arr.get(i)), turn);
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
    }

    @Override
    public void addPlayer(String name, Integer score, int turn){
        this.players.add(new eachPlayer(name, turn, score));
        Collections.sort(this.players);
    }

    @Override
    public void printPlayer(){
        for( eachPlayer player : this.players){
            System.out.println(player.toString());
        }
    }

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

    @Override
    public void addFloor(String floorState, int turn) {
        this.players.get(turn - 1).eachFloor(floorState);
    }

    @Override
    public void printFloor() {
        for( eachPlayer player : this.players){
            System.out.println(" Name : " + player.name + " Floor : " + player.storage);
        }
    }

    @Override
    public void Move(String applyMove) {

    }

    @Override
    public void Turn(int turn) {

    }

    private class eachPlayer implements Comparable<eachPlayer>{
        String name = "";
        int turn;
        Integer score;
        String mosaic = "";
        String storage = "";
        String floor = "";

        eachPlayer(String n, Integer s, int c) {
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
