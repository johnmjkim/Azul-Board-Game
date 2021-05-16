package comp1110.ass2;

import comp1110.ass2.backend.PlayerState;
import comp1110.ass2.backend.SharedState;

public class Experiment_PSVM implements TestCountCases {

    public static void main(String[] args) {
        int MAX_PLAYER_NUMBER = 4;

        String input_sharedState_1 = "CFCB0108040603D0401050100";
        String input_playerState_1 = "A0MS0a11c22e23e44a5FaabbbbB4Mc03c34a44S1c12b2FcccddddC0Md02b23S1b12c13d24e3FaaaaabfD0Md03a13S1c22d13d34e5Fbbdeee";
        //String input_move = "A30";

        //int input_row = Character.getNumericValue(input_move.charAt(1));
        //int input_col = Character.getNumericValue(input_move.charAt(2));

        // Shared State Experiment
        SharedState ss = new SharedState(input_sharedState_1, MAX_PLAYER_NUMBER);
        PlayerState ps = new PlayerState(input_playerState_1, MAX_PLAYER_NUMBER);

        System.out.println(" SharedState : " + ss);
        System.out.println("   TurnState : " + ss.getTurnState());
        System.out.println("   FactoryState : " + ss.factories);
        for (int i = 0; i < FACTORY_MAX_NUMBERS[MAX_PLAYER_NUMBER-DEFAULT_MAX_PLAYER]; i++) {
            System.out.println("     Factory " + i + " : " + ss.factories.getFactory(i));
        }
        System.out.println("   CenterState : " + ss.center);
        System.out.println("   BagState : " + ss.bag);
        System.out.println("   DiscardState : " + ss.discard);
        System.out.println();


        System.out.println(" PlayerState : " + ps);
        for (int i = 0; i < MAX_PLAYER_NUMBER; i++) {
            System.out.println(" PlayerState " + ALL_PLAYERS[i] + " : " + ps.getUpdatedPlayerState(ALL_PLAYERS[i]));
            System.out.println("   Player " + ALL_PLAYERS[i] + " MosaicState : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic + ", Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.mosaicvalid);
            for (int j = 0; j < MAX_MOSAIC_ROW; j++) {
                System.out.print("     Player " + ALL_PLAYERS[i] + " MosaicRowState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j) + ", Row Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).tiles_row_position_valid);
                System.out.println();
            }
            for (int j = 0; j < MAX_MOSAIC_COL; j++) {
                System.out.print("     Player " + ALL_PLAYERS[i] + " MosaicColState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j) + ", Col Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j).tiles_col_position_valid);
                System.out.println();
            }
            System.out.println("   Player " + ALL_PLAYERS[i] + " StorageState : " + ps.getnPlayer(ALL_PLAYERS[i]).storage + ", Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.storagevalid);
            for (int j = 0; j < MAX_STORAGE_ROW; j++) {
                System.out.print("     Player " + ALL_PLAYERS[i] + " StorageRowState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j) + ", Max Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j).isStorageRowTilesValid());
                System.out.println();
            }
            System.out.println("   Player " + ALL_PLAYERS[i] + " FloorState : " + ps.getnPlayer(ALL_PLAYERS[i]).floor);
            System.out.println();
        }

        for (int i = 0; i < MAX_PLAYER_NUMBER; i++) {
            System.out.println("Player " + ALL_PLAYERS[i] + " MosaicState : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic);
            //System.out.println(" Adjacent Total Score : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.scoreTotalMosaic() + ", Adjacent New Score at (" + input_row + ", " + input_col + ") : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.scoreMosaic(input_row, input_col));
            for (int j = 0; j < MAX_MOSAIC_ROW; j++) {
                for (int k = 0; k < MAX_MOSAIC_COL; k++) {
                    char color = ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).mosaicrow_tiles_color[k];
                    if (!(color >= BLUE && color <= RED)) {
                        color = '-';
                    }
                    System.out.print(color);
                    if (k < MAX_MOSAIC_COL - 1) {
                        System.out.print(" | ");
                    }
                }
                System.out.print(" " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).scoreTotalMosaicRow());
                System.out.println();
            }
            for (int j = 0; j < MAX_MOSAIC_COL; j++) {
                System.out.print(ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j).scoreTotalMosaicCol());
                if (j < MAX_MOSAIC_COL - 1) {
                    if (ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j).scoreTotalMosaicCol() < 10) {
                        System.out.print("   ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
/*

        int len = 0;
        System.out.println("Factories Tile Numbers ");
        System.out.println("Factories Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makefactoriesints(VALID_STATES[i][0]);
        }
        System.out.println("Factories Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makefactoriesints(FULL_GAME_WITH_MOVES[i][0]);
        }

        len = 0;
        System.out.println("Center Tile Numbers ");
        System.out.println("Center Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makecenterints(VALID_STATES[i][0]);
        }
        System.out.println("Center Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makecenterints(FULL_GAME_WITH_MOVES[i][0]);
        }

        len = 0;
        System.out.println("Bag Tile Numbers ");
        System.out.println("Bag Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makebagints(VALID_STATES[i][0]);
        }
        System.out.println("Bag Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makebagints(FULL_GAME_WITH_MOVES[i][0]);
        }

        len = 0;
        System.out.println("Discard Tile Numbers ");
        System.out.println("Discard Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makediscardints(VALID_STATES[i][0]);
        }
        System.out.println("Discard Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makediscardints(FULL_GAME_WITH_MOVES[i][0]);
        }

        len = 0;
        System.out.println("Mosaic Tile Numbers ");
        System.out.println("Mosaic Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makemosaicints(VALID_STATES[i][1]);
        }
        System.out.println("Mosaic Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makemosaicints(FULL_GAME_WITH_MOVES[i][1]);
        }

        len = 0;
        System.out.println("Mosaic Row Tile Numbers ");
        System.out.println("Mosaic Row Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makemosaicrowints(VALID_STATES[i][1]);
        }
        System.out.println("Mosaic Row Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makemosaicrowints(FULL_GAME_WITH_MOVES[i][1]);
        }

        len = 0;
        System.out.println("Mosaic Col Tile Numbers ");
        System.out.println("Mosaic Col Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makemosaiccolints(VALID_STATES[i][1]);
        }
        System.out.println("Mosaic Col Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makemosaiccolints(FULL_GAME_WITH_MOVES[i][1]);
        }

        len = 0;
        System.out.println("Storage Tile Numbers ");
        System.out.println("Storage Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makestorageints(VALID_STATES[i][1]);
        }
        System.out.println("Storage Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makestorageints(FULL_GAME_WITH_MOVES[i][1]);
        }

        len = 0;
        System.out.println("Storage Row Tile Numbers ");
        System.out.println("Storage Row Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makestoragerowints(VALID_STATES[i][1]);
        }
        System.out.println("Storage Row Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makestoragerowints(FULL_GAME_WITH_MOVES[i][1]);
        }

        len = 0;
        System.out.println("Floor Tile Numbers ");
        System.out.println("Floor Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if(i % 3 == 0){
                if( len < 6 ){
                    if( len == 0){
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                }
                else{
                    System.out.println("// BONUS SCORING");
                }
            }
            makefloorints(VALID_STATES[i][1]);
        }
        System.out.println("Floor Test Game With Moves");
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            makefloorints(FULL_GAME_WITH_MOVES[i][1]);
        }

 */
        int len = 0;
        System.out.println("Mosaic Tile Numbers ");
        System.out.println("Mosaic Test Valid States");
        for (int i = 0; i < VALID_STATES.length; i++) {
            if (i % 3 == 0) {
                if (len < 6) {
                    if (len == 0) {
                        System.out.println("// full game, automatic scoring");
                    }
                    System.out.println("// ROUND " + (len + 1));
                    len++;
                } else {
                    System.out.println("// BONUS SCORING");
                }
            }
            makemosaicstrings(VALID_STATES[i][1]);
        }

    }

    public static void makemosaicstrings(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new String[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            String out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getStateString();
            if(player==0){System.out.print("\""+out+"\""+", ");}
            if(player==1){System.out.print("\""+out+"\"");}
        }
        System.out.println("},");
    }


    public static void makefactoriesints(String in) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.factories.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for (int j = 0; j <= RED - BLUE; j++) {
            out = ss.factories.getTilesNumber(color);
            if (j < RED - BLUE) {
                System.out.print(out + ", ");
            } else {
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makecenterints(String in) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.center.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for (int j = 0; j <= FIRST_PLAYER - BLUE; j++) {
            out = ss.center.getTilesNumber(color);
            if (j < FIRST_PLAYER - BLUE) {
                System.out.print(out + ", ");
            } else {
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makebagints(String in) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.bag.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for (int j = 0; j <= RED - BLUE; j++) {
            out = ss.bag.getTilesNumber(color);
            if (j < RED - BLUE) {
                System.out.print(out + ", ");
            } else {
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makediscardints(String in) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.discard.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for (int j = 0; j <= RED - BLUE; j++) {
            out = ss.discard.getTilesNumber(color);
            if (j < RED - BLUE) {
                System.out.print(out + ", ");
            } else {
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makemosaicints(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getTotalTilesNumber();
            System.out.print(out + ", ");
            char color = BLUE;
            for (int j = 0; j <= RED - BLUE; j++) {
                out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getTilesNumber(color);
                if (j < RED - BLUE) {
                    System.out.print(out + ", ");
                } else {
                    if (player == DEFAULT_MAX_PLAYER - 1) {
                        System.out.println(out + "},");
                    } else {
                        System.out.print(out + ", ");
                    }
                }
                color++;
            }
        }
    }

    public static void makemosaicrowints(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            for (int row = 0; row < MAX_MOSAIC_ROW; row++) {
                int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getMosaicRow(row).getTotalTilesNumber();
                if (row < MAX_MOSAIC_ROW - 1) {
                    System.out.print(out + ", ");
                } else {
                    if (player == DEFAULT_MAX_PLAYER - 1) {
                        System.out.println(out + "},");
                    } else {
                        System.out.print(out + ", ");
                    }
                }
            }
        }
    }

    public static void makemosaiccolints(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            for (int col = 0; col < MAX_MOSAIC_COL; col++) {
                int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getMosaicCol(col).getTotalTilesNumber();
                if (col < MAX_MOSAIC_COL - 1) {
                    System.out.print(out + ", ");
                } else {
                    if (player == DEFAULT_MAX_PLAYER - 1) {
                        System.out.println(out + "},");
                    } else {
                        System.out.print(out + ", ");
                    }
                }
            }
        }
    }

    public static void makestorageints(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getTotalTilesNumber();
            System.out.print(out + ", ");
            char color = BLUE;
            for (int j = 0; j <= RED - BLUE; j++) {
                out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getTilesNumber(color);
                if (j < RED - BLUE) {
                    System.out.print(out + ", ");
                } else {
                    if (player == DEFAULT_MAX_PLAYER - 1) {
                        System.out.println(out + "},");
                    } else {
                        System.out.print(out + ", ");
                    }
                }
                color++;
            }
        }
    }

    public static void makestoragerowints(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            for (int row = 0; row < MAX_MOSAIC_ROW; row++) {
                int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getStorageRow(row).getTotalTilesNumber();
                if (row < MAX_MOSAIC_ROW - 1) {
                    System.out.print(out + ", ");
                } else {
                    if (player == DEFAULT_MAX_PLAYER - 1) {
                        System.out.println(out + "},");
                    } else {
                        System.out.print(out + ", ");
                    }
                }
            }
        }
    }

    public static void makefloorints(String in) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for (int player = 0; player < DEFAULT_MAX_PLAYER; player++) {
            int out = ps.getnPlayer(ALL_PLAYERS[player]).floor.getTotalTilesNumber();
            System.out.print(out + ", ");
            char color = BLUE;
            for (int j = 0; j <= FIRST_PLAYER - BLUE; j++) {
                out = ps.getnPlayer(ALL_PLAYERS[player]).floor.getTilesNumber(color);
                if (j < FIRST_PLAYER - BLUE) {
                    System.out.print(out + ", ");
                } else {
                    if (player == DEFAULT_MAX_PLAYER - 1) {
                        System.out.println(out + "},");
                    } else {
                        System.out.print(out + ", ");
                    }
                }
                color++;
            }
        }
    }
}
