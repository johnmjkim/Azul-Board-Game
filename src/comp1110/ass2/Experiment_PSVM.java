package comp1110.ass2;

public class Experiment_PSVM implements TestCountCases {

    public static void main(String[] args) {
        int MAX_PLAYER_NUMBER = 2;

        //String input_sharedState_1 = "AF0cdde1bbbe2abde3cdee4bcceCfB1915161614D0000000000";
        String input_sharedState_2 = "BF1bcde3ccdeCaaabcddeB0807121119D1216150803";
        //String input_sharedState_3 = "BFCB0505040402D0609040610";
        String input_sharedState_4 = "AF0cdde1bbbe2abde3cdee4bcceCfB1915161614D1111111111";
        String input_playerState_1 = "A07Me01c02a11d20b30b41S0a11b22c13c44d1FeefB08Mb13e23c32b41S0b11c12a33d24e4Fabcd";
        //String input_playerState_2 = "A75Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3FB60Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SF";
        String input_playerState_4 = "A3Ma00a11b12e00c13e21c24a31c42S1a20e52d13d34a3FB5Mb00c01a03d04d21b31e41S0d11e52b14b1Fbccccdf";

        // Shared State Experiment
        SharedState ss = new SharedState(input_sharedState_4, DEFAULT_MAX_PLAYER);
        PlayerState ps = new PlayerState(input_playerState_1, DEFAULT_MAX_PLAYER);
        System.out.println( " SharedState : " + ss );
        System.out.println( "   TurnState : " + ss.turnState );
        System.out.println( "   FactoryState : " + ss.factories);
        for(int i=0; i < DEFAULT_FACTORY_MAX_NUMBER; i++){
            System.out.println( "     Factory " + i + " : " + ss.factories.getFactory(i));
        }
        System.out.println( "   CenterState : " + ss.center );
        System.out.println( "   BagState : " + ss.bag );
        System.out.println( "   DiscardState : " + ss.discard );
        System.out.println();


        System.out.println( " PlayerState : " + ps );
        for(int i=0; i < DEFAULT_MAX_PLAYER; i++){
            System.out.println( " PlayerState " + ALL_PLAYERS[i] + " : " + ps.getUpdatedPlayerState(ALL_PLAYERS[i]));
            System.out.println( "   Player " + ALL_PLAYERS[i] + " MosaicState : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic + ", Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.mosaicvalid);
            for(int j=0; j < MAX_MOSAIC_ROW; j++){
                System.out.print( "     Player " + ALL_PLAYERS[i] + " MosaicRowState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j) + ", Position Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).isMosaicRowTilePositionsValid());
                System.out.println();
            }
            for(int j=0; j < MAX_MOSAIC_COL; j++){
                System.out.print( "     Player " + ALL_PLAYERS[i] + " MosaicColState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j));
                System.out.println();
            }
            System.out.println( "   Player " + ALL_PLAYERS[i] + " StorageState : " + ps.getnPlayer(ALL_PLAYERS[i]).storage + ", Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.storagevalid);
            for(int j=0; j < MAX_STORAGE_ROW; j++){
                System.out.print( "     Player " + ALL_PLAYERS[i] + " StorageRowState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j)+ ", Max Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j).isStorageRowTilesValid());
                System.out.println();
            }
            System.out.println( "   Player " + ALL_PLAYERS[i] + " FloorState : " + ps.getnPlayer(ALL_PLAYERS[i]).floor);
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

    }

    public static void makefactoriesints(String in){
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.factories.getFactoryTotalTiles();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            out = ss.factories.getFactoryTilesNumber(color);
            if(j < RED - BLUE){
                System.out.print(out + ", ");
            }
            else{
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makecenterints(String in){
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.center.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for(int j=0; j <= FIRST_PLAYER - BLUE; j++){
            out = ss.center.getTilesNumber(color);
            if(j < FIRST_PLAYER - BLUE){
                System.out.print(out + ", ");
            }
            else{
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makebagints(String in){
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.bag.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            out = ss.bag.getTilesNumber(color);
            if(j < RED - BLUE){
                System.out.print(out + ", ");
            }
            else{
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makediscardints(String in){
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.discard.getTotalTilesNumber();
        System.out.print("new int[]{");
        System.out.print(out + ", ");
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            out = ss.discard.getTilesNumber(color);
            if(j < RED - BLUE){
                System.out.print(out + ", ");
            }
            else{
                System.out.println(out + "},");
            }
            color++;
        }
    }

    public static void makemosaicints(String in){
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for(int player=0; player < DEFAULT_MAX_PLAYER; player++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getTotalTilesNumber();
            System.out.print(out + ", ");
            char color = BLUE;
            for(int j=0; j <= RED - BLUE; j++){
                out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getTilesNumber(color);
                if(j < RED - BLUE){
                    System.out.print(out + ", ");
                }
                else{
                    if(player == DEFAULT_MAX_PLAYER - 1){
                        System.out.println(out + "},");
                    }
                    else{
                        System.out.print(out + ", ");
                    }
                }
                color++;
            }
        }
    }

    public static void makemosaicrowints(String in){
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for(int player=0; player < DEFAULT_MAX_PLAYER; player++){
            for(int row=0; row < MAX_MOSAIC_ROW; row++){
                int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getMosaicRow(row).getTotalTilesNumber();
                if(row < MAX_MOSAIC_ROW - 1){
                    System.out.print(out + ", ");
                }
                else{
                    if(player == DEFAULT_MAX_PLAYER - 1){
                        System.out.println(out + "},");
                    }
                    else{
                        System.out.print(out + ", ");
                    }
                }
            }
        }
    }

    public static void makemosaiccolints(String in){
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for(int player=0; player < DEFAULT_MAX_PLAYER; player++){
            for(int col=0; col < MAX_MOSAIC_COL; col++){
                int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getMosaicCol(col).getTotalTilesNumber();
                if(col < MAX_MOSAIC_COL - 1){
                    System.out.print(out + ", ");
                }
                else{
                    if(player == DEFAULT_MAX_PLAYER - 1){
                        System.out.println(out + "},");
                    }
                    else{
                        System.out.print(out + ", ");
                    }
                }
            }
        }
    }

    public static void makestorageints(String in){
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for(int player=0; player < DEFAULT_MAX_PLAYER; player++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getTotalTilesNumber();
            System.out.print(out + ", ");
            char color = BLUE;
            for(int j=0; j <= RED - BLUE; j++){
                out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getTilesNumber(color);
                if(j < RED - BLUE){
                    System.out.print(out + ", ");
                }
                else{
                    if(player == DEFAULT_MAX_PLAYER - 1){
                        System.out.println(out + "},");
                    }
                    else{
                        System.out.print(out + ", ");
                    }
                }
                color++;
            }
        }
    }

    public static void makestoragerowints(String in){
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for(int player=0; player < DEFAULT_MAX_PLAYER; player++){
            for(int row=0; row < MAX_MOSAIC_ROW; row++){
                int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getStorageRow(row).getTotalTilesNumber();
                if(row < MAX_MOSAIC_ROW - 1){
                    System.out.print(out + ", ");
                }
                else{
                    if(player == DEFAULT_MAX_PLAYER - 1){
                        System.out.println(out + "},");
                    }
                    else{
                        System.out.print(out + ", ");
                    }
                }
            }
        }
    }

    public static void makefloorints(String in){
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        System.out.print("new int[]{");
        for(int player=0; player < DEFAULT_MAX_PLAYER; player++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).floor.getTotalTilesNumber();
            System.out.print(out + ", ");
            char color = BLUE;
            for(int j=0; j <= FIRST_PLAYER - BLUE; j++){
                out = ps.getnPlayer(ALL_PLAYERS[player]).floor.getTilesNumber(color);
                if(j < FIRST_PLAYER - BLUE){
                    System.out.print(out + ", ");
                }
                else{
                    if(player == DEFAULT_MAX_PLAYER - 1){
                        System.out.println(out + "},");
                    }
                    else{
                        System.out.print(out + ", ");
                    }
                }
                color++;
            }
        }
    }
}
