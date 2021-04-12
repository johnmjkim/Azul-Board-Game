package comp1110.ass2.backend.player;

import java.util.ArrayList;
import java.util.Collections;

public class Storage {

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';
    public final char FIRST_PLAYER = 'f';

    // Size, Numbers of all components
    public final int MAX_STORAGE_ROW = 5;
    public final String EMPTY_TILES = "0000000000";
    public final String EMPTY_STATE = "";

    String storageState = EMPTY_STATE;
    // TODO State Mosaic Row ArrayList needed
    public ArrayList<StorageRow> storage_rows = new ArrayList<StorageRow>();

    int[] letters = new int[128];

    public Storage(String storageState){
        this.storage_rows.clear();
        this.storageState = storageState;
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
        for (String s : storage_row) {
            System.out.println(" -> " + s);
        }

         */

        for(int i=0; i < max_number; i++){
            this.storage_rows.add(new StorageRow(storage_row.get(i+1),i));
        }
        Collections.sort(this.storage_rows);

    }

    private void countStorageTilesNumber(){
        for( StorageRow sr : this.storage_rows){
            if(!sr.isStorageRowStateEmpty()){
                //System.out.println(" color : " + sr.getTilesColor() + " number : " + sr.getTilesNumber());
                this.letters[sr.getTilesColor()] += sr.getTilesNumber();
            }
        }
    }

    public String getStorageState(){
        return this.storageState;
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

    boolean isStorageStateEmpty(){
        return this.storageState.isEmpty();
    }

    /**
     * Inner class eachStorageRow of Storage class
     * Each player has eachStorageRow state stored here
     */
    public class StorageRow implements Comparable<StorageRow>{
        String storage_rowState = EMPTY_STATE;
        int row;
        final int MAX_TILES_LIMIT = row + 1;
        int[] letters = new int[128];

        public StorageRow (String storage_rowState, int row){
            this.storage_rowState = storage_rowState;
            this.row = row;
            this.letters[getTilesColor()] = getTilesNumber();
        }

        public String getStorage_rowState(){
            return this.storage_rowState;
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

        public void removeTile(char color) {
            if(isStorageRowTileColorValid(color)){
                this.letters[color]--;
            }
            updateStorageRowState();
        }

        public void removeAllTiles(){
            char color = BLUE;
            for(int i=0; i <= FIRST_PLAYER - BLUE; i++){
                while(this.letters[color] > 0){
                    this.letters[color]--;
                }
                color++;
            }
            updateStorageRowState();
        }

        public void addTile(char color){
            if(isStorageRowTileColorValid(color) || (getTilesNumber() == 0)){
                if(this.MAX_TILES_LIMIT > getTilesNumber()) {
                    this.letters[color]++;
                    updateStorageRowState();
                }
                else{
                    System.out.println("Max storage row tiles reached");
                }
                System.out.println("Max incompatible tile adding");
            }
        }

        public void updateStorageRowState(){
            StringBuilder SB = new StringBuilder();
            if(getTilesColor() >= BLUE && getTilesColor() <= RED){
                this.letters[getTilesColor()] = getTilesNumber();
                SB.append(getTilesColor());
                SB.append(getTilesNumber());
            }
            else{
                SB.append("");
            }
            this.storage_rowState = String.valueOf(SB);
        }

        boolean isStorageRowTileColorValid(char color){
            return (getTilesColor() == color);
        }

        boolean isStorageRowTilesValid(){
            return (this.MAX_TILES_LIMIT >= getTilesNumber());
        }

        boolean isStorageRowStateEmpty(){
            return this.storage_rowState.isEmpty();
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
    }
}
