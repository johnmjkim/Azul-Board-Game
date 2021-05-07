package comp1110.ass2;

import comp1110.ass2.State;

import java.util.ArrayList;

public class Mosaic implements Tiles {

    String mosaicState = EMPTY_STATE;

    public ArrayList<MosaicRow> mosaic_rows = new ArrayList<MosaicRow>();
    public ArrayList<MosaicCol> mosaic_cols = new ArrayList<MosaicCol>();

    int[] letters = new int[128];

    boolean mosaicvalid = true;

    /**
     * @author Min Jae, Kim
     * Constructor method for Mosaic class
     * Mosaic class has two inner classes : MosaicRow, MosaicCol
     * Each class checks validity of state, counts number of tiles, calculate score
     * @param mosaicState
     */
    public Mosaic(String mosaicState){
        this.mosaicState = mosaicState;
        addMosaicRowCol(mosaicState);
        countTilesNumber(mosaicState);
        this.mosaicvalid = isMosaicStateValid();
    }

    public void addMosaicRowCol(String mosaicState){
        ArrayList<Character> tile_color = new ArrayList<Character>();
        ArrayList<Integer> mosaic_row_idx = new ArrayList<Integer>();
        ArrayList<Integer> mosaic_col_idx = new ArrayList<Integer>();

        char[] mosaicState_char_array = mosaicState.toCharArray();
        int len = 0;
        int div = 3;
        int i = 0;
        for( char c : mosaicState_char_array ){
            if(i % div == 0){
                tile_color.add(c);
                len++;
            }
            else if(i % div == 1){
                mosaic_row_idx.add(Character.getNumericValue(c));
            }
            else {
                mosaic_col_idx.add(Character.getNumericValue(c));
            }
            i++;
        }

        StringBuilder SB = new StringBuilder();
        SB.append("");
        this.mosaic_rows.clear();
        for(int row=0; row < MAX_MOSAIC_ROW; row++){
            for(int j=0; j < len; j++){
                if(mosaic_row_idx.get(j) == row){
                    SB.append(tile_color.get(j));
                    SB.append(mosaic_row_idx.get(j));
                    SB.append(mosaic_col_idx.get(j));
                }
            }
            if(SB.length() > 0){
                this.mosaic_rows.add(new MosaicRow(String.valueOf(SB), row));
            }
            else {
                SB.append("");
                this.mosaic_rows.add(new MosaicRow(String.valueOf(SB), row));
            }
            SB.delete(0,SB.length());
        }

        SB.append("");
        this.mosaic_cols.clear();
        for(int col=0; col < MAX_MOSAIC_COL; col++){
            for(int j=0; j < len; j++){
                if(mosaic_col_idx.get(j) == col){
                    SB.append(tile_color.get(j));
                    SB.append(mosaic_row_idx.get(j));
                    SB.append(mosaic_col_idx.get(j));
                }
            }
            if(SB.length() > 0){
                this.mosaic_cols.add(new MosaicCol(String.valueOf(SB), col));
            }
            else {
                SB.append("");
                this.mosaic_cols.add(new MosaicCol(String.valueOf(SB), col));
            }
            SB.delete(0,SB.length());
        }

    }

    public boolean isMosaicStateValid(){
        StringBuilder SB = new StringBuilder();
        for( MosaicRow mr : this.mosaic_rows ){
            SB.append(mr.getStateString());
        }
        /*
        System.out.println(this.mosaicState);
        System.out.println(String.valueOf(SB));

         */
        return (this.mosaicState.equals(String.valueOf(SB)));
    }

    public MosaicRow getMosaicRow(int row){
        return this.mosaic_rows.get(row);
    }

    public MosaicCol getMosaicCol(int col){
        return this.mosaic_cols.get(col);
    }

    public int scoreTotalMosaic(){
        int adjacent_score = 0;
        for(int row=0; row < MAX_MOSAIC_ROW; row++){
            adjacent_score += this.getMosaicRow(row).scoreTotalMosaicRow();
        }
        for(int col=0; col < MAX_MOSAIC_COL; col++){
            adjacent_score += this.getMosaicCol(col).scoreTotalMosaicCol();
        }
        return adjacent_score;
    }

    public int scoreMosaic(int row, int col){
        int adjacent_score = 0;

        adjacent_score += this.getMosaicRow(row).scoreMosaicRow(col);
        adjacent_score += this.getMosaicCol(col).scoreMosaicCol(row);

        return adjacent_score;
    }

    @Override
    public void countTilesNumber(String mosaicState){

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

    @Override
    public int getTilesNumber(char color){
        return this.letters[color];
    }

    @Override
    public int getTotalTilesNumber(){
        int tot_tiles = 0;
        char color = BLUE;
        for(int i=0; i <= RED - BLUE; i++){
            tot_tiles += this.letters[color];
            color++;
        }
        return tot_tiles;
    }

    @Override
    public boolean isStateEmpty() {
        return this.mosaicState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.mosaicState;
    }

    @Override
    public String toString(){
        return getStateString();
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        for( MosaicRow mr : this.mosaic_rows ){
            SB.append(mr.getStateString());
        }
        this.mosaicState = String.valueOf(SB);
    }

    /**
     * @author Min Jae, Kim
     * Inner class MosaicRow of Mosaic class
     * Each player has MosaicRow state stored here
     */
    public class MosaicRow implements CoordinateTyped{
        String mosaic_rowState = EMPTY_STATE;
        int[] letters = new int[128];
        int MAX_TILES_LIMIT = MAX_MOSAIC_COL;
        int row;

        boolean[] mosaicrow_tiles_occupy = new boolean[MAX_MOSAIC_COL];
        char[] mosaicrow_tiles_color = new char[MAX_MOSAIC_COL];
        boolean tiles_row_position_valid = true;

        public MosaicRow (String mosaic_rowState, int row){
            //System.out.println(" row " + row + " : " + mosaic_rowState);
            this.mosaic_rowState = mosaic_rowState;
            this.row = row;
            countTilesNumber(mosaic_rowState);
            storeMosaicPosition(mosaic_rowState);
            boolean valid_mosaic_position = checkTilesPosition();
            boolean valid_mosaic_color = checkTilesColor();
            this.tiles_row_position_valid = valid_mosaic_position && valid_mosaic_color;
        }

        public void storeMosaicPosition(String mosaic_rowState){
            ArrayList<Character> tile_color = new ArrayList<Character>();
            ArrayList<Integer> mosaic_col_idx = new ArrayList<Integer>();

            char[] mosaic_rowState_char_array = mosaic_rowState.toCharArray();
            int len = 0;
            int div = 3;
            int i = 0;
            for( char c : mosaic_rowState_char_array ){
                if(i % div == 0){
                    tile_color.add(c);
                    len++;
                }
                else if(i % div == 2){
                    mosaic_col_idx.add(Character.getNumericValue(c));
                }
                i++;
            }
            for(int j=0; j < len; j++){
                mosaicrow_tiles_occupy[mosaic_col_idx.get(j)] = true;
                mosaicrow_tiles_color[mosaic_col_idx.get(j)] = tile_color.get(j);
            }
        }

        public boolean checkTilesPosition(){
            StringBuilder SB = new StringBuilder();
            for(int i=0; i < MAX_MOSAIC_COL; i++){
                if(mosaicrow_tiles_occupy[i]){
                    SB.append(mosaicrow_tiles_color[i]);
                    SB.append(row);
                    SB.append(i);
                }
                else{
                    SB.append("");
                }
            }
            return this.mosaic_rowState.equals(String.valueOf(SB));
        }

        public boolean checkTilesColor(){
            StringBuilder SB = new StringBuilder();
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                if(getTilesNumber(color) > 1){
                    return false;
                }
                color++;
            }
            return true;
        }

        public boolean existsTileColor(char color){
            return (getTilesNumber(color) > 0);
        }

        public boolean existsTile(int column){
            return this.mosaicrow_tiles_occupy[column];
        }

        public void addTile(char color, int column) {
            mosaicrow_tiles_occupy[column] = true;
            mosaicrow_tiles_color[column] = color;
            updateState();
        }

        public char getTileColor(int column) {
            if(mosaicrow_tiles_occupy[column]){
                return mosaicrow_tiles_color[column];
            }
            else{
                return ' ';
            }
        }

        public int scoreTotalMosaicRow(){
            int adjacent_score = 0;
            for(int i=1; i < MAX_MOSAIC_COL; i++){
                if(mosaicrow_tiles_occupy[i] && mosaicrow_tiles_occupy[i-1]){
                    adjacent_score++;
                }
            }
            if(adjacent_score > 0){
                adjacent_score += 1;
            }
            return adjacent_score;
        }

        public int scoreMosaicRow(int col){
            int adjacent_score = 0;
            boolean right_end = true;
            boolean left_end = true;
            for(int left=col; left > 0; left--){
                if(!(mosaicrow_tiles_occupy[left] && mosaicrow_tiles_occupy[left-1])){
                    left_end = false;
                }
                else{
                    if(left_end){
                        adjacent_score++;
                    }
                }
            }
            for(int right=col; right < MAX_MOSAIC_COL - 1; right++){
                if(!(mosaicrow_tiles_occupy[right] && mosaicrow_tiles_occupy[right+1])){
                    right_end = false;
                }
                else{
                    if(right_end){
                        adjacent_score++;
                    }
                }
            }
            if(adjacent_score > 0){
                adjacent_score += 1;
            }
            return adjacent_score;
        }

        @Override
        public void countTilesNumber(String State) {
            int[] letters_array = new int[128];
            char[] mosaic_rowState_char_array = State.toCharArray();
            for(char c : mosaic_rowState_char_array){
                letters_array[c]++;
            }
            // 'a'~'f'
            this.letters[BLUE] = letters_array[BLUE];
            this.letters[GREEN] = letters_array[GREEN];
            this.letters[ORANGE] = letters_array[ORANGE];
            this.letters[PURPLE] = letters_array[PURPLE];
            this.letters[RED] = letters_array[RED];
        }

        @Override
        public int getTilesNumber(char color){
            return this.letters[color];
        }

        @Override
        public int getTotalTilesNumber(){
            int tot_tiles = 0;
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                tot_tiles += this.letters[color];
                color++;
            }
            return tot_tiles;
        }

        @Override
        public boolean isTilesFull() {
            return (this.MAX_TILES_LIMIT == getTotalTilesNumber());
        }

        @Override
        public boolean isStateEmpty() {
            return this.mosaic_rowState.isEmpty();
        }

        @Override
        public String getStateString() {
            if(checkTilesPosition()){
                updateState();
            }
            return this.mosaic_rowState;
        }

        @Override
        public String toString(){
            return getStateString();
        }

        @Override
        public void updateState() {
            StringBuilder SB = new StringBuilder();
            for(int i=0; i < MAX_MOSAIC_COL; i++){
                if(mosaicrow_tiles_occupy[i]){
                    SB.append(mosaicrow_tiles_color[i]);
                    SB.append(row);
                    SB.append(i);
                }
                else{
                    SB.append("");
                }
            }
            countTilesNumber(String.valueOf(SB));
            this.mosaic_rowState = String.valueOf(SB);
        }
    }

    /**
     * @author Min Jae, Kim
     * Inner class MosaicCol of Mosaic class
     * Each player has MosaicCol state stored here
     */
    public class MosaicCol implements CoordinateTyped {
        String mosaic_colState = EMPTY_STATE;
        char[] MOSAIC_MASK_COL = new char[MAX_MOSAIC_COL];
        int[] letters = new int[128];
        int MAX_TILES_LIMIT = MAX_MOSAIC_COL;
        int col;

        boolean[] mosaiccol_tiles_occupy = new boolean[MAX_MOSAIC_ROW];
        char[] mosaiccol_tiles_color = new char[MAX_MOSAIC_ROW];
        boolean tiles_col_position_valid = true;

        public MosaicCol(String mosaic_colState, int col) {
            //System.out.println(" col " + col + " : " + mosaic_colState);
            this.mosaic_colState = mosaic_colState;
            this.col = col;
            countTilesNumber(mosaic_colState);
            storeMosaicPosition(mosaic_colState);
            boolean valid_mosaic_position = checkTilesPosition();
            boolean valid_mosaic_color = checkTilesColor();
            this.tiles_col_position_valid = valid_mosaic_position && valid_mosaic_color;
        }

        public void storeMosaicPosition(String mosaic_colState){
            ArrayList<Character> tile_color = new ArrayList<Character>();
            ArrayList<Integer> mosaic_row_idx = new ArrayList<Integer>();

            char[] mosaic_colState_char_array = mosaic_colState.toCharArray();
            int len = 0;
            int div = 3;
            int i = 0;
            for( char c : mosaic_colState_char_array ){
                if(i % div == 0){
                    tile_color.add(c);
                    len++;
                }
                else if(i % div == 1){
                    mosaic_row_idx.add(Character.getNumericValue(c));
                }
                i++;
            }
            for(int j=0; j < len; j++){
                mosaiccol_tiles_occupy[mosaic_row_idx.get(j)] = true;
                mosaiccol_tiles_color[mosaic_row_idx.get(j)] = tile_color.get(j);
            }
        }

        public boolean checkTilesPosition(){
            StringBuilder SB = new StringBuilder();
            for(int i=0; i < MAX_MOSAIC_ROW; i++){
                if(mosaiccol_tiles_occupy[i]){
                    SB.append(mosaiccol_tiles_color[i]);
                    SB.append(i);
                    SB.append(col);
                }
                else{
                    SB.append("");
                }
            }
            return this.mosaic_colState.equals(String.valueOf(SB));
        }

        public boolean checkTilesColor(){
            StringBuilder SB = new StringBuilder();
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                if(getTilesNumber(color) > 1){
                    return false;
                }
                color++;
            }
            return true;
        }

        public boolean existsTileColor(char color){
            return (getTilesNumber(color) > 0);
        }

        public boolean existsTile(int row){
            return this.mosaiccol_tiles_occupy[row];
        }

        public void addTile(char color, int row) {
            mosaiccol_tiles_occupy[row] = true;
            mosaiccol_tiles_color[row] = color;
            updateState();
        }

        public char getTileColor(int row) {
            if(mosaiccol_tiles_occupy[row]){
                return mosaiccol_tiles_color[row];
            }
            else{
                return ' ';
            }
        }

        public int scoreTotalMosaicCol(){
            int adjacent_score = 0;
            for(int i=1; i < MAX_MOSAIC_ROW; i++){
                if(mosaiccol_tiles_occupy[i] && mosaiccol_tiles_occupy[i-1]){
                    adjacent_score++;
                }
            }
            if(adjacent_score > 0){
                adjacent_score += 1;
            }
            return adjacent_score;
        }

        public int scoreMosaicCol(int row){
            int adjacent_score = 0;
            boolean top_end = true;
            boolean bottom_end = true;
            for(int top=row; top > 0; top--){
                if(!(mosaiccol_tiles_occupy[top] && mosaiccol_tiles_occupy[top-1])){
                    top_end = false;
                }
                else{
                    if(top_end){
                        adjacent_score++;
                    }
                }
            }
            for(int bottom=row; bottom < MAX_MOSAIC_ROW - 1; bottom++){
                if(!(mosaiccol_tiles_occupy[bottom] && mosaiccol_tiles_occupy[bottom+1])){
                    bottom_end = false;
                }
                else{
                    if(bottom_end){
                        adjacent_score++;
                    }
                }
            }
            if(adjacent_score > 0){
                adjacent_score += 1;
            }
            return adjacent_score;
        }

        @Override
        public void countTilesNumber(String mosaic_colState){
            int[] letters_array = new int[128];
            char[] mosaic_colState_char_array = mosaic_colState.toCharArray();
            for(char c : mosaic_colState_char_array){
                letters_array[c]++;
            }
            // 'a'~'f'
            this.letters[BLUE] = letters_array[BLUE];
            this.letters[GREEN] = letters_array[GREEN];
            this.letters[ORANGE] = letters_array[ORANGE];
            this.letters[PURPLE] = letters_array[PURPLE];
            this.letters[RED] = letters_array[RED];
        }

        @Override
        public int getTilesNumber(char color){
            return this.letters[color];
        }

        @Override
        public int getTotalTilesNumber() {
            int tot_tiles = 0;
            char color = BLUE;
            for (int i = 0; i <= RED - BLUE; i++) {
                tot_tiles += this.letters[color];
                color++;
            }
            return tot_tiles;
        }

        @Override
        public boolean isTilesFull() {
            return (this.MAX_TILES_LIMIT == getTotalTilesNumber());
        }

        @Override
        public boolean isStateEmpty() {
            return this.mosaic_colState.isEmpty();
        }

        @Override
        public String getStateString() {
            if(checkTilesPosition()){
                updateState();
            }
            return this.mosaic_colState;
        }

        @Override
        public String toString(){
            return getStateString();
        }

        @Override
        public void updateState() {
            StringBuilder SB = new StringBuilder();
            for(int i=0; i < MAX_MOSAIC_ROW; i++){
                if(mosaiccol_tiles_occupy[i]){
                    SB.append(mosaiccol_tiles_color[i]);
                    SB.append(i);
                    SB.append(col);
                }
                else{
                    SB.append("");
                }
            }
            countTilesNumber(String.valueOf(SB));
            this.mosaic_colState = String.valueOf(SB);
        }
    }

}
