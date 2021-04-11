package comp1110.ass2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    public final char[] ALL_PLAYERS = {PLAYER_A, PLAYER_B, PLAYER_C, PLAYER_D};

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

    // Size, Numbers of all components
    public final String EMPTY_STATE = "";

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
        /*
        for(int i=0; i < len; i++){
            System.out.println(playerState_name_arr.get(i) + ", " + playerState_content_arr.get(i));
        }

         */

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

    @Override
    public void printPlayerState() {
        System.out.println(this.playerState);
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
        System.out.println(" Mosaic tiles total : " + getMosaicTotalTiles());
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
        System.out.println(" Storage tiles total : " + getStorageTotalTiles());
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
        System.out.println(" Floor tiles total : " + getFloorTotalTiles());
    }

    @Override
    public String getPlayerState() {
        return this.playerState;
    }

    @Override
    public String[] getMosaicState() {
        String[] str_return = new String[this.players.size()];
        int i=0;
        for( eachPlayer p : this.players ){
            str_return[i] = p.getM_MosaicState();
            i++;
        }
        return str_return;
    }

    @Override
    public String[] getStorageState() {
        String[] str_return = new String[this.players.size()];
        int i=0;
        for( eachPlayer p : this.players ){
            str_return[i] = p.getS_StorageState();
            i++;
        }
        return str_return;
    }

    @Override
    public String[] getFloorState() {
        String[] str_return = new String[this.players.size()];
        int i=0;
        for( eachPlayer p : this.players ){
            str_return[i] = p.getF_FloorState();
            i++;
        }
        return str_return;
    }

    @Override
    public int getMosaicTotalTiles() {
        int tot_tiles = 0;
        for( eachPlayer p : this.players){
            tot_tiles += p.getMosaicTotalTilesNumber();
        }
        return tot_tiles;
    }

    @Override
    public int getStorageTotalTiles() {
        int tot_tiles = 0;
        for( eachPlayer p : this.players){
            tot_tiles += p.getStorageTotalTilesNumber();
        }
        return tot_tiles;
    }

    @Override
    public int getFloorTotalTiles() {
        int tot_tiles = 0;
        for( eachPlayer p : this.players){
            tot_tiles += p.getFloorTotalTilesNumber();
        }
        return tot_tiles;
    }

    /**
     * Private methods for PLayers
     * updatePlayerState() : updates update_eachPlayerState()
     * update_eachPlayerState() : updates updateMosaicState(), updateStorageState(), updateFloorState()
     * updateMosaicState() : factory number and string
     * updateStorageState() : all tile characters
     * updateFloorState() : all tile characters
     */

    private void updatePlayerState(){
        StringBuilder SB = new StringBuilder();

        for(int i=0; i < max_player_number; i++){
            // Update all strings of each player, mosaic, storage, floor
            update_eachPlayerState(ALL_PLAYERS[i]);

            int score = this.players.get(i).score;

            // Form sharedState
            SB.append(ALL_PLAYERS[i]);
            if(score < 10){
                SB.append("0");
            }
            SB.append(String.valueOf(score));
            SB.append(MOSAIC);
            SB.append(this.players.get(i).mosaicState);
            SB.append(STORAGE);
            SB.append(this.players.get(i).storageState);
            SB.append(FLOOR);
            SB.append(this.players.get(i).floorState);
        }

        PlayerState(String.valueOf(SB));
    }

    private void update_eachPlayerState(Character name){
        updateMosaicState(name);
        updateStorageState(name);
        updateFloorState(name);
    }

    private void updateMosaicState(Character name){
        String mosaicState = getPlayer(name).getM_MosaicState();
        getPlayer(name).eachMosaic(mosaicState);
    }

    private void updateStorageState(Character name){
        String storageState = getPlayer(name).getS_StorageState();
        getPlayer(name).eachStorage(storageState);
    }

    private void updateFloorState(Character name){
        String floorState = getPlayer(name).getF_FloorState();
        getPlayer(name).eachFloor(floorState);
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

    /**
     * Get player data by player name
     * @param player_name
     * @return
     */
    public eachPlayer getPlayer(Character player_name){
        return players_map.get(player_name);
    }

    /**
     * Inner class eachPlayer of Players class
     * Each player stored in order by turn
     */
    public class eachPlayer implements Comparable<eachPlayer>{
        Character name;
        int score;
        int num_turn;
        String mosaicState = EMPTY_STATE;
        String storageState = EMPTY_STATE;
        String floorState = EMPTY_STATE;

        // Inner class fields
        private Mosaic mosaic;
        private Storage storage;
        private Floor floor;

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

        public char getName(){
            return this.name;
        }

        public int getScore(){
            return this.score;
        }

        public int getNum_turn(){
            return this.num_turn;
        }

        public String getM_MosaicState(){
            return this.mosaicState;
        }

        public String getS_StorageState(){
            return this.storageState;
        }

        public String getF_FloorState(){
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

        public int getMosaicTotalTilesNumber() {
            return this.mosaic.getTotalTilesNumber();
        }

        public int getStorageTotalTilesNumber() {
            return this.storage.getTotalTilesNumber();
        }

        public int getFloorTotalTilesNumber() {
            return this.floor.getTotalTilesNumber();
        }

        public void clearFloorScoring(){
            int score_lost = this.floor.scoreLoseFloor();
            int adjusted_score = this.score;
            if(adjusted_score + score_lost > 0){
                this.score = adjusted_score + score_lost;
            }
            else{
                this.score = 0;
            }
        }

        public void clearFloor(){
            System.out.println(this.floorState);
            this.floor.clearFloor();
            System.out.println(this.floorState);
        }

        public int[] getClearedTilesFloor(){
            return this.floor.getClearedTilesFloor();
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

        /**
         * Inner class Mosaic of eachPlayer class
         * Each player has Mosaic state stored here
         */
        public class Mosaic {
            String M_mosaicState;
            ArrayList<String> mosaic_cols = new ArrayList<String>();
            ArrayList<eachMosaicRow> mosaic_rows = new ArrayList<eachMosaicRow>();

            int[] letters = new int[128];

            public Mosaic(String mosaicState){
                this.M_mosaicState = mosaicState;
                addMosaicRow(mosaicState);
                countMosaicTilesNumber();
            }

            public void addMosaicRow(String mosaicState){
                // TODO finish addMosaicRow and addMosaicCol
                ArrayList<String> mosaic_row = new ArrayList<String>();
                int max_number = 5;
                int len = 0;
                int msc_row_num = 0;
                StringBuilder SB = new StringBuilder();
                /*
                for(char c : mosaicState.toCharArray()){
                    if( len % 3 == 0){
                        if(msc_row_num == Character.getNumericValue(c)){
                            mosaic_row.add(String.valueOf(SB));
                        }
                        else{
                            mosaic_row.add(String.valueOf(SB));
                            while(msc_row_num != Character.getNumericValue(c)){
                                mosaic_row.add(EMPTY_STATE);
                                msc_row_num++;
                            }
                        }
                        SB.delete(0,SB.length());
                        msc_row_num++;
                    }
                    else{
                        SB.append(c);
                    }
                    len++;
                }
                mosaic_row.add(String.valueOf(SB));
                while(msc_row_num < max_number){
                    mosaic_row.add(EMPTY_STATE);
                    msc_row_num++;
                }
                SB.delete(0,SB.length());

                 */
                /*
                for(String s : storage_row){
                    System.out.println(" -> " + s);
                }

                 */
                /*
                for(int i=0; i < max_number; i++){
                    this.mosaic_rows.add(new eachMosaicRow(mosaic_row.get(i+1),i));
                }
                Collections.sort(this.mosaic_rows);

                 */
            }

            private void countMosaicTilesNumber(){

                int[] letters_array = new int[128];
                char[] mosaicState_char_array = this.M_mosaicState.toCharArray();
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

            public int getTotalTilesNumber(){
                int tot_tiles = 0;
                char color = BLUE;
                for(int i=0; i <= RED - BLUE; i++){
                    tot_tiles += this.letters[color];
                    color++;
                }
                return tot_tiles;
            }

            boolean isMosaicStateEmpty(){
                return this.M_mosaicState.isEmpty();
            }

            /**
             * Inner class eachMosaicRow of Mosaic class
             * Each player has eachMosaicRow state stored here
             */
            public class eachMosaicRow implements Comparable<eachMosaicRow>{
                // TODO finish eachMosaicRow and eachMosaicCol
                String mosaic_rowState = EMPTY_STATE;
                int row;

                public eachMosaicRow (String mosaic_rowState, int row){
                    this.mosaic_rowState = mosaic_rowState;
                    this.row = row;
                }

                public int getTilesNumber(){
                    if(this.isMosaicRowStateEmpty()){
                        return 0;
                    }
                    else{
                        int mosaic_row_count = 0;
                        return mosaic_row_count;
                    }
                }

                public char getTilesColor(){
                    if(this.isMosaicRowStateEmpty()){
                        return ' ';
                    }
                    else{
                        char mosaic_row_color = ' ';
                        return mosaic_row_color;
                    }
                }

                boolean isMosaicRowStateEmpty(){
                    return this.mosaic_rowState.isEmpty();
                }

                @Override
                public int compareTo(eachMosaicRow mosaic_row) {
                    if(row == mosaic_row.row){
                        return 0;
                    }
                    else if(row > mosaic_row.row){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
            }
        }

        /**
         * Inner class Storage of eachPlayer class
         * Each player has Storage state stored here
         */
        public class Storage{
            String S_storageState;
            ArrayList<eachStorageRow> storage_rows = new ArrayList<eachStorageRow>();

            int[] letters = new int[128];

            public Storage(String storageState){
                this.storage_rows.clear();
                this.S_storageState = storageState;
                addStorageRow(storageState);
                countStorageTilesNumber();
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
                                storage_row.add(EMPTY_STATE);
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
                    storage_row.add(EMPTY_STATE);
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

            private void countStorageTilesNumber(){
                for( eachStorageRow sr : this.storage_rows){
                    if(!sr.isStorageRowStateEmpty()){
                        //System.out.println(" color : " + sr.getTilesColor() + " number : " + sr.getTilesNumber());
                        this.letters[sr.getTilesColor()] += sr.getTilesNumber();
                    }
                }
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

            boolean isStorageStateEmpty(){
                return this.S_storageState.isEmpty();
            }

            /**
             * Inner class eachStorageRow of Storage class
             * Each player has eachStorageRow state stored here
             */
            public class eachStorageRow implements Comparable<eachStorageRow>{
                String storage_rowState = EMPTY_STATE;
                int row;

                public eachStorageRow (String storage_rowState, int row){
                    this.storage_rowState = storage_rowState;
                    this.row = row;
                }

                public int getTilesNumber(){
                    if(this.isStorageRowStateEmpty()){
                        return 0;
                    }
                    else{
                        int storage_row_count = Character.getNumericValue(this.storage_rowState.charAt(1));
                        return storage_row_count;
                    }
                }

                public char getTilesColor(){
                    if(this.isStorageRowStateEmpty()){
                        return ' ';
                    }
                    else{
                        char storage_row_color = this.storage_rowState.charAt(0);
                        return storage_row_color;
                    }
                }

                boolean isStorageRowStateEmpty(){
                    return this.storage_rowState.isEmpty();
                }

                @Override
                public int compareTo(eachStorageRow storage_row) {
                    if(row == storage_row.row){
                        return 0;
                    }
                    else if(row > storage_row.row){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
            }
        }

        /**
         * Inner class Floor of eachPlayer class
         * Each player has Floor state stored here
         */
        public class Floor {
            String F_floorState;
            int[] letters = new int[128];

            public Floor(String floorState){
                this.F_floorState = floorState;
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

            public int getTotalTilesNumber(){
                int tot_tiles = 0;
                char color = BLUE;
                for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
                    tot_tiles += this.letters[color];
                    color++;
                }
                return tot_tiles;
            }

            public int scoreLoseFloor(){
                int floor_length = this.F_floorState.length();
                if(floor_length == 0){
                    return 0;
                }
                else if(floor_length == 1){
                    return -1;
                }
                else if(floor_length == 2){
                    return -2;
                }
                else if(floor_length == 3){
                    return -4;
                }
                else if(floor_length == 4){
                    return -6;
                }
                else if(floor_length == 5){
                    return -8;
                }
                else if(floor_length == 6){
                    return -11;
                }
                else{
                    return -14;
                }
            }

            public void clearFloor(){
                this.F_floorState = EMPTY_STATE;
                int[] tiles_to_discard = new int[128];
                char color = BLUE;
                for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
                    while(this.letters[color] > 0){
                        this.letters[color]--;
                        tiles_to_discard[color]++;
                    }
                    color++;
                }
            }

            public int[] getClearedTilesFloor(){
                return this.letters;
            }

            public boolean isFloorStateEmpty(){
                return this.F_floorState.isEmpty();
            }
        }
    }
}
