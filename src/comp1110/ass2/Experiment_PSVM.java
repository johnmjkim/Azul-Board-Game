package comp1110.ass2;

public class Experiment_PSVM implements Constants{

    public static void main(String[] args) {
        int MAX_PLAYER_NUMBER = 2;

        //String input_sharedState_1 = "AF0cdde1bbbe2abde3cdee4bcceCfB1915161614D0000000000";
        String input_sharedState_2 = "BF1cbde3dcceCaaabcddeB0807121119D1216150803";
        //String input_sharedState_3 = "BFCB0505040402D0609040610";
        String input_playerState_1 = "A07Me01c02a11d20b30b41S0a11b22c13c44d1FeefB08Mb13e23c32b41S0b11c12a33d24e4Fabcd";
        //String input_playerState_2 = "A75Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3FB60Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SF";


        // Shared State Experiment

        SharedState ss = new SharedState(input_sharedState_2, DEFAULT_MAX_PLAYER);
        PlayerState ps = new PlayerState(input_playerState_1, DEFAULT_MAX_PLAYER);

        System.out.println( " SharedState : " + ss );
        System.out.println( "   TurnState : " + ss.turnState );
        System.out.println( "   FactoryState : " + ss.factories );
        for(int i=0; i < DEFAULT_FACTORY_MAX_NUMBER; i++){
            System.out.println( "     Factory " + i + " : " + ss.factories.getFactory(i));
        }
        System.out.println( "   CenterState : " + ss.center );
        System.out.println( "   BagState : " + ss.bag );
        System.out.println( "   DiscardState : " + ss.discard );
        System.out.println();


        System.out.println( " PLayerState : " + ps );
        for(int i=0; i < DEFAULT_MAX_PLAYER; i++){
            System.out.println( " PLayerState " + ALL_PLAYERS[i] + " : " + ps.getUpdatedPlayerState(ALL_PLAYERS[i]));
            System.out.println( "   PLayer " + ALL_PLAYERS[i] + " MosaicState : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic);
            for(int j=0; j < MAX_MOSAIC_ROW; j++){
                System.out.println( "     PLayer " + ALL_PLAYERS[i] + " MosaicRowState : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j));
            }
            for(int j=0; j < MAX_MOSAIC_COL; j++){
                System.out.println( "     PLayer " + ALL_PLAYERS[i] + " MosaicColState : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j));
            }
            System.out.println( "   PLayer " + ALL_PLAYERS[i] + " StorageState : " + ps.getnPlayer(ALL_PLAYERS[i]).storage);
            for(int j=0; j < MAX_STORAGE_ROW; j++){
                System.out.println( "     PLayer " + ALL_PLAYERS[i] + " StorageRowState : " + ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j));
            }
            System.out.println( "   PLayer " + ALL_PLAYERS[i] + " FloorState : " + ps.getnPlayer(ALL_PLAYERS[i]).floor);
            System.out.println();
        }

    }
}
