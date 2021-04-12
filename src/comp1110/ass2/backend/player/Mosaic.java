package comp1110.ass2.backend.player;

import java.util.ArrayList;

public class Mosaic {

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

    String mosaicState = EMPTY_STATE;

    // TODO State Mosaic Row Col ArrayList needed
    ArrayList<MosaicRow> mosaic_rows = new ArrayList<MosaicRow>();
    ArrayList<String> mosaic_cols = new ArrayList<String>();

    int[] letters = new int[128];

    public Mosaic(String mosaicState){
        this.mosaicState = mosaicState;
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
        char[] mosaicState_char_array = this.mosaicState.toCharArray();
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

    public String getMosaicState(){
        return this.mosaicState;
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
        return this.mosaicState.isEmpty();
    }

    /**
     * Inner class eachMosaicRow of Mosaic class
     * Each player has eachMosaicRow state stored here
     */
    public class MosaicRow implements Comparable<MosaicRow>{
        // TODO finish eachMosaicRow and eachMosaicCol
        String mosaic_rowState = EMPTY_STATE;
        int row;

        public MosaicRow (String mosaic_rowState, int row){
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
        public int compareTo(MosaicRow mosaic_row) {
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
