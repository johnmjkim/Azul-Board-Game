package comp1110.ass2.backend;

import java.util.ArrayList;

/**
 * @author Min Jae, Kim
 * Stores all information of storage
 */
public class Storage implements Tiles {

    String storageState = EMPTY_STATE;
    public ArrayList<StorageRow> storage_rows = new ArrayList<StorageRow>();

    int[] letters = new int[128];

    public boolean storagevalid = true;

    /**
     * @author Min Jae, Kim
     * Constructor method for Storage class
     * Storage class has one inner class : StorageRow
     * Each class checks validity of state, counts number of tiles, calculate score
     * @param storageState
     */
    public Storage(String storageState){
        this.storage_rows.clear();
        this.storageState = storageState;
        addStorageRow(storageState);
        countTilesNumber(storageState);
        this.storagevalid = isStorageStateValid();
    }

    public void addStorageRow(String storageState){
        ArrayList<Character> tile_color = new ArrayList<Character>();
        ArrayList<Integer> storage_row_idx = new ArrayList<Integer>();
        ArrayList<Integer> storage_row_tiles = new ArrayList<Integer>();

        char[] storageState_char_array = storageState.toCharArray();
        int len = 0;
        int div = 3;
        int i = 0;
        for( char c : storageState_char_array ){
            if(i % div == 0){
                storage_row_idx.add(Character.getNumericValue(c));
                len++;
            }
            else if(i % div == 1){
                tile_color.add(c);
            }
            else {
                storage_row_tiles.add(Character.getNumericValue(c));
            }
            i++;
        }

        StringBuilder SB = new StringBuilder();
        SB.append("");
        this.storage_rows.clear();
        for(int row=0; row < MAX_STORAGE_ROW; row++){
            for(int j=0; j < len; j++){
                if(storage_row_idx.get(j) == row){
                    SB.append(tile_color.get(j));
                    SB.append(storage_row_tiles.get(j));
                }
            }
            if(SB.length() > 0){
                this.storage_rows.add(new StorageRow(String.valueOf(SB), row));
            }
            else {
                SB.append("");
                this.storage_rows.add(new StorageRow(String.valueOf(SB), row));
            }
            SB.delete(0,SB.length());
        }
    }

    boolean isStorageTilesValid(){
        boolean[] isrowstilesvalid = new boolean[MAX_STORAGE_ROW];
        boolean isStorageTilesValid = true;

        for(int i=0; i < MAX_STORAGE_ROW; i++){
            isrowstilesvalid[i] = storage_rows.get(i).isStorageRowTilesValid();
        }
        for(int i=0; i < MAX_STORAGE_ROW; i++){
            if(isrowstilesvalid[i] == false){
                isStorageTilesValid = false;
            }
        }
        return isStorageTilesValid;
    }

    public int findHighestStorageRowFull(){
        int highest_row = 0;
        if(existsStorageRowTilesFull()){
            for(int row=0; row < MAX_STORAGE_ROW; row++){
                if(this.getStorageRow(row).isTilesFull()){
                    highest_row = row;
                }
            }
        }
        return highest_row;
    }

    public int findMinimumEmptyStorageTiles(){
        int minimum_empty_tiles = MAX_STORAGE_COL;
        for(int row=0; row < MAX_STORAGE_ROW; row++){
            int minimum_empty_row_tiles = this.getStorageRow(row).findMinimumEmptyStorageRowTiles();
            if(minimum_empty_tiles > minimum_empty_row_tiles){
                minimum_empty_tiles = minimum_empty_row_tiles;
            }
        }
        return minimum_empty_tiles;
    }

    public boolean existsStorageRowTilesFull() {
        boolean existsRowTilesFull = false;
        for(int row=0; row < MAX_STORAGE_ROW; row++){
            if(isStorageRowTilesFull(row)){
                existsRowTilesFull = true;
            }
        }
        return existsRowTilesFull;
    }

    public boolean isStorageRowTilesFull( int row ){
        return this.storage_rows.get(row).isTilesFull();
    }

    public StorageRow getStorageRow(int row){
        return this.storage_rows.get(row);
    }

    public boolean isStorageStateValid(){
        StringBuilder SB = new StringBuilder();
        int i=0;
        for( StorageRow sr : storage_rows ){
            if(!sr.isStateEmpty()){
                SB.append(i);
                SB.append(sr.getStateString());
            }
            i++;
        }
        /*
        System.out.println(this.storageState);
        System.out.println(String.valueOf(SB));

         */
        return (this.storageState.equals(String.valueOf(SB)));
    }

    /**
     * @author Min Jae, Kim
     * Counts all number of tiles and saves by color of tiles
     * @param storageState
     */
    @Override
    public void countTilesNumber(String storageState){
        for( StorageRow sr : this.storage_rows){
            if(!sr.isStateEmpty()){
                //System.out.println(" color : " + sr.getTilesColor() + " number : " + sr.getTilesNumber());
                this.letters[sr.getRowTilesColor()] += sr.getTotalTilesNumber();
            }
        }
    }

    /**
     * @author Min Jae, Kim
     * Gets number of tiles by color
     * @param color
     * @return
     */
    @Override
    public int getTilesNumber(char color) {
        return this.letters[color];
    }

    @Override
    public int getTotalTilesNumber(){
        int tot_tiles = 0;
        char color = BLUE;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

    @Override
    public boolean isStateEmpty() {
        return this.storageState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.storageState;
    }

    @Override
    public String toString(){
        return getStateString();
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        int i=0;
        for( StorageRow sr : storage_rows ){
            if(!sr.isStateEmpty()){
                SB.append(i);
                SB.append(sr.getStateString());
                //System.out.println(SB);
            }
            i++;
        }
        this.storageState = String.valueOf(SB);
    }

    /**
     * @author Min Jae, Kim
     * Inner class eachStorageRow of Storage class
     * Each player has eachStorageRow state stored here
     */
    public class StorageRow implements CoordinateTyped {
        String storage_rowState = EMPTY_STATE;
        char storage_row_color = NO_COLOR;
        int row;
        int MAX_TILES_LIMIT;
        int[] letters = new int[128];
        boolean[] storagerow_tiles_occupy;
        char[] storagerow_tiles_color;

        /**
         * @author Min Jae, Kim
         * Constructor method for StorageRow class
         * @param storage_rowState
         * @param row
         */
        public StorageRow (String storage_rowState, int row){
            this.storage_rowState = storage_rowState;
            this.row = row;
            this.MAX_TILES_LIMIT = row + 1;
            this.storagerow_tiles_occupy = new boolean[MAX_TILES_LIMIT];
            this.storagerow_tiles_color = new char[MAX_TILES_LIMIT];
            this.storage_row_color = getRowTilesColor();
            countTilesNumber(storage_rowState);
        }

        /**
         * @author Min Jae, Kim
         * Gets tile color of storage row
         * @return
         */
        public char getRowTilesColor(){
            if(this.isStateEmpty()){
                return NO_COLOR;
            }
            else{
                char storage_row_color = this.storage_rowState.charAt(0);
                return storage_row_color;
            }
        }

        /**
         * @author Min Jae, Kim
         * Check if storagae row tile color is valid
         * @param color
         * @return
         */
        public boolean isStorageRowTileColorValid(char color){
            return (getRowTilesColor() == color);
        }

        /**
         * @author Min Jae, Kim
         * Check is storage row do not exceed maximum tile numbers
         * @return
         */
        public boolean isStorageRowTilesValid(){
            return (this.MAX_TILES_LIMIT >= getTotalTilesNumber());
        }

        public void removeTile(char color) {
            if(isStorageRowTileColorValid(color)){
                this.letters[color]--;
            }
            updateState();
        }

        public void removeAllTiles() {
            char color = BLUE;
            for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
                //System.out.print(" Color : " + color + " Before : " + this.letters[color] + " After : ");
                while(this.letters[color] > 0){
                    this.letters[color]--;
                }
                //System.out.println(this.letters[color]);
                color++;
            }
            this.storage_rowState = EMPTY_STATE;
            updateState();
        }

        public void addTiles(char color, int n) {
            for(int i=0; i < n; i++){
                this.letters[color]++;
            }
            this.storage_row_color = color;
            updateState();
        }

        public void addTile(char color) {
            this.letters[color]++;
            this.storage_row_color = color;
            updateState();
        }

        public int getMaxTilesLimit() {
            return this.MAX_TILES_LIMIT;
        }

        public int findMinimumEmptyStorageRowTiles(){
            return getMaxTilesLimit() - getTotalTilesNumber();
        }

        @Override
        public boolean isStateEmpty() {
            return this.storage_rowState.isEmpty();
        }

        @Override
        public String getStateString() {
            updateState();
            return this.storage_rowState;
        }

        @Override
        public String toString(){
            return getStateString();
        }

        @Override
        public void updateState() {
            StringBuilder SB = new StringBuilder();
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                if(this.letters[color] > 0){
                    this.storage_row_color = color;
                    SB.append(this.storage_row_color);
                    SB.append(this.letters[this.storage_row_color]);
                }
                else{
                    SB.append("");
                }
                color++;
            }
            this.storage_rowState = String.valueOf(SB);
        }

        /**
         * @author Min Jae, Kim
         * Counts all number of tiles and saves by color of tiles
         * @param State
         */
        @Override
        public void countTilesNumber(String State) {
            this.storage_row_color = getRowTilesColor();
            if(this.isStateEmpty()){
                for(int col=0; col < MAX_TILES_LIMIT; col++){
                    storagerow_tiles_occupy[col] = false;
                    storagerow_tiles_color[col] = NO_COLOR;
                }
            }
            else{
                int storage_row_count = Character.getNumericValue(State.charAt(1));
                this.letters[storage_row_color] = storage_row_count;
                for(int col=0; col < MAX_TILES_LIMIT; col++){
                    if(col < storage_row_count){
                        storagerow_tiles_occupy[col] = false;
                        storagerow_tiles_color[col] = NO_COLOR;
                    }
                    else{
                        storagerow_tiles_occupy[col] = false;
                        storagerow_tiles_color[col] = this.storage_row_color;
                    }
                }
            }
        }

        /**
         * @author Min Jae, Kim
         * Gets number of tiles by color
         * @param color
         * @return
         */
        @Override
        public int getTilesNumber(char color) {
            if(this.isStateEmpty()){
                return 0;
            }
            else {
                return this.letters[color];
            }
        }

        /**
         * @author Min Jae, Kim
         * Gets number of total tiles regardless of color
         * @return
         */
        @Override
        public int getTotalTilesNumber() {
            return getTilesNumber(this.storage_row_color);
        }

        /**
         * @author Min Jae, Kim
         * Check if tile in the column is full
         * @return
         */
        @Override
        public boolean isTilesFull() {
            return (this.MAX_TILES_LIMIT == getTotalTilesNumber());
        }

        /**
         * @author Min Jae, Kim
         * Gets color of tiles by column
         * @param column
         * @return
         */
        @Override
        public char getTileColor(int column) {
            return storagerow_tiles_color[column];
        }
    }
}
