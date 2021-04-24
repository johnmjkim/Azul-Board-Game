package comp1110.ass2;

import comp1110.ass2.State;

import java.util.ArrayList;
import java.util.Collections;

public class Storage implements Tiles {

    String storageState = EMPTY_STATE;
    public ArrayList<StorageRow> storage_rows = new ArrayList<StorageRow>();

    int[] letters = new int[128];

    boolean storagevalid = true;

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

    boolean existsStorageRowTilesFull() {
        boolean existsRowTilesFull = false;
        for( StorageRow sr : this.storage_rows){
            if(sr.isTilesFull()){
                existsRowTilesFull = true;
            }
        }
        return existsRowTilesFull;
    }

    boolean isStorageRowTilesFull( int row ){
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

    @Override
    public void countTilesNumber(String storageState){
        for( StorageRow sr : this.storage_rows){
            if(!sr.isStateEmpty()){
                //System.out.println(" color : " + sr.getTilesColor() + " number : " + sr.getTilesNumber());
                this.letters[sr.getTilesColor()] += sr.getTotalTilesNumber();
            }
        }
    }

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
     * Inner class eachStorageRow of Storage class
     * Each player has eachStorageRow state stored here
     */
    public class StorageRow implements CoordinateTyped{
        String storage_rowState = EMPTY_STATE;
        char storage_row_color = ' ';
        int row;
        int MAX_TILES_LIMIT;
        int[] letters = new int[128];

        public StorageRow (String storage_rowState, int row){
            this.storage_rowState = storage_rowState;
            this.row = row;
            this.MAX_TILES_LIMIT = row + 1;
            this.storage_row_color = getTilesColor();
            countTilesNumber(storage_rowState);
        }

        public char getTilesColor(){
            if(this.isStateEmpty()){
                return ' ';
            }
            else{
                char storage_row_color = this.storage_rowState.charAt(0);
                return storage_row_color;
            }
        }

        boolean isStorageRowTileColorValid(char color){
            return (getTilesColor() == color);
        }

        boolean isStorageRowTilesValid(){
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
            /*
            StringBuilder SB = new StringBuilder();
            if(getTilesColor() >= BLUE && getTilesColor() <= RED){
                SB.append(getTilesColor());
                SB.append(this.letters[getTilesColor()]);
            }
            else{
                SB.append("");
            }
            //System.out.println(" Update with : " + SB);
            this.storage_rowState = String.valueOf(SB);
             */
        }

        @Override
        public void countTilesNumber(String State) {
            this.storage_row_color = getTilesColor();
            if(this.isStateEmpty()){

            }
            else{
                int storage_row_count = Character.getNumericValue(State.charAt(1));
                this.letters[storage_row_color] = storage_row_count;
            }
        }

        @Override
        public int getTilesNumber(char color) {
            if(this.isStateEmpty()){
                return 0;
            }
            else {
                return this.letters[color];
            }
        }

        @Override
        public int getTotalTilesNumber() {
            return getTilesNumber(this.storage_row_color);
        }

        @Override
        public boolean isTilesFull() {
            return (this.MAX_TILES_LIMIT == getTotalTilesNumber());
        }
    }
}
