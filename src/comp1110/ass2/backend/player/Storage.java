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
    public final int MAX_MOSAIC_ROW = 5;
    public final int MAX_MOSAIC_COL = 5;
    public final String EMPTY_TILES = "0000000000";
    public final String EMPTY_STATE = "";

    String storageState = EMPTY_STATE;
    // TODO State Mosaic Row ArrayList needed
    ArrayList<StorageRow> storage_rows = new ArrayList<StorageRow>();

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
                for(String s : storage_row){
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

        public StorageRow (String storage_rowState, int row){
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
