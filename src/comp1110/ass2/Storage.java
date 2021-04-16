package comp1110.ass2;

import comp1110.ass2.State;

import java.util.ArrayList;
import java.util.Collections;

public class Storage implements Tiles {

    String storageState = EMPTY_STATE;
    public ArrayList<StorageRow> storage_rows = new ArrayList<StorageRow>();

    int[] letters = new int[128];

    public Storage(String storageState){
        this.storage_rows.clear();
        this.storageState = storageState;
        addStorageRow(storageState);
        countTilesNumber(storageState);
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
        for (String s : storage_row) {
            System.out.println(" -> " + s);
        }

         */

        for(int i=0; i < max_number; i++){
            this.storage_rows.add(new StorageRow(storage_row.get(i+1),i));
        }
        Collections.sort(this.storage_rows);

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

    @Override
    public boolean isStateEmpty() {
        return this.storageState.isEmpty();
    }

    @Override
    public String getStateString() {
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
            }
            i++;
        }
        this.storageState = String.valueOf(SB);
    }

    /**
     * Inner class eachStorageRow of Storage class
     * Each player has eachStorageRow state stored here
     */
    public class StorageRow implements CoordinateTyped, Comparable<StorageRow>{
        String storage_rowState = EMPTY_STATE;
        char storage_row_color;
        int row;
        int MAX_TILES_LIMIT;
        int[] letters = new int[128];

        public StorageRow (String storage_rowState, int row){
            this.storage_rowState = storage_rowState;
            this.row = row;
            this.MAX_TILES_LIMIT = row + 1;
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
                while(this.letters[color] > 0){
                    this.letters[color]--;
                }
                color++;
            }
            updateState();
        }

        public void addTile(char color) {
            if (isStorageRowTileColorValid(color) || (getTilesNumber(color) == 0)) {
                if (this.MAX_TILES_LIMIT > getTilesNumber(color)) {
                    this.letters[color]++;
                    updateState();
                } else {
                    System.out.println("Max storage row tiles reached");
                }
            } else {
                System.out.println("Max incompatible tile adding");
            }
        }

        @Override
        public int compareTo(StorageRow storage_row) {
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

        @Override
        public boolean isStateEmpty() {
            return this.storage_rowState.isEmpty();
        }

        @Override
        public String getStateString() {
            return this.storage_rowState;
        }

        @Override
        public String toString(){
            return getStateString();
        }

        @Override
        public void updateState() {
            StringBuilder SB = new StringBuilder();
            if(getTilesColor() >= BLUE && getTilesColor() <= RED){
                SB.append(getTilesColor());
                SB.append(this.letters[getTilesColor()]);
            }
            else{
                SB.append("");
            }
            this.storage_rowState = String.valueOf(SB);
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
