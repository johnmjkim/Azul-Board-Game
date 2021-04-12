package comp1110.ass2;

import java.util.ArrayList;

public class Experiment_PSVM {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        //String input_sharedState_1 = "AF0cdde1bbbe2abde3cdee4bcceCfB1915161614D0000000000";
        String input_sharedState_2 = "BF1cbde3dcceCaaabcddeB0807121119D1216150803";
        //String input_sharedState_3 = "BFCB0505040402D0609040610";
        String input_playerState_1 = "A07Me01a11d20b30b41S0a11b22c13c44d1FeefB08Md03b13e23c32b41S0b11c12a33d24e4Fabcd";
        //String input_playerState_2 = "A75Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3FB60Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SF";

        // Shared State Experiment
        /*
        SharedState ss = new SharedState(input_sharedState_2, 2);

        System.out.println(ss.getState());

        System.out.println(ss.getSharedState());
        ss.bag.removeTile('a');
        ss.bag.removeTile('b');
        ss.discard.removeTile('b');
        ss.discard.removeTile('b');
        ss.center.removeTile('c');
        ss.center.removeTile('d');
        System.out.println(ss.getSharedState());
        ss.bag.refillTilesBag("0304000000");
        ss.discard.refillTilesDiscard("0204000405");
        System.out.println(ss.getSharedState());
        ss.center.addTile('c');
        ss.center.addTile('b');
        System.out.println(ss.getSharedState());
        ss.center.removeAllTiles();
        System.out.println(ss.getSharedState());
        System.out.println(ss.factories.getFactoriesState());
        System.out.println(ss.factories.factory.get(1));
        System.out.println(ss.factories.getFactoryTotalTiles());
        System.out.println(ss.factories.getFactoryTilesNumber('c'));
        ss.bag.removeAllTiles();
        ss.refillFactory();
        System.out.println(ss.getSharedState());

        System.out.println(ss.factories.factory.get(0));

        // Player State Experiment
        PlayerState ps = new PlayerState(input_playerState_1, 2);
        System.out.println(ps.getState());
        System.out.println(ps.getPlayerState());
        System.out.println(ps.nplayers.get(0).getnplayerState());
        System.out.println(ps.nplayers.get(0).score.getScoreState());
        System.out.println(ps.nplayers.get(0).mosaice.getMosaicState());
        System.out.println(ps.nplayers.get(0).storage.getStorageState());
        System.out.println(ps.nplayers.get(0).floor.getFloorState());

        int tot_tiles = 0;
        String tiles_to_discard;
        System.out.println(ss.getSharedState());
        System.out.println(ps.getPlayerState());
        tot_tiles = ps.nplayers.get(0).floor.getTotalTilesNumber();
        tiles_to_discard = ps.nplayers.get(0).floor.getFloorTilesString();
        ps.nplayers.get(0).score.clearFloorScore(tot_tiles);
        ps.nplayers.get(0).floor.removeAllTiles();
        ss.discard.refillTilesDiscard(tiles_to_discard);
        System.out.println(ss.getSharedState());
        System.out.println(ps.getPlayerState());

         */

        // NextRound experiment
        String[] gameState = new String[2];
        gameState[0] = input_sharedState_2;
        gameState[1] = input_playerState_1;

        gameState = Azul.nextRound(gameState);

        System.out.println("After all preparation for next round");
        System.out.println(gameState[0]);
        System.out.println(gameState[1]);

    }
}
