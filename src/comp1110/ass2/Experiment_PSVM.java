package comp1110.ass2;

import java.util.ArrayList;

public class Experiment_PSVM {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        String input_sharedState = "AF0cdde1bbbe2abde3cdee4bcceCfB1915161614D0000000000";
        String input_sharedState_another = "BF1cbde3dcceCaaabcddeB0807121119D1216150803";
        String input_playerState = "A07Me01a11d20b30b41S0a11b22c13c44d1FeeB08Md03b13e23c32b41S0b11c12a33d24e4Fab";
        String input_playerState_another = "A75Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3FB60Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SF";

        Shareds azulShareds = new Shareds(2);
        azulShareds.SharedState(input_sharedState);
        System.out.println(azulShareds.sharedState);
        //azulShareds.printFactory();
        //azulShareds.printCenter();
        //azulShareds.printBag();
        //azulShareds.printDiscard();
        //azulShareds.printTurn();

        azulShareds.SharedState(input_sharedState_another);
        System.out.println(azulShareds.sharedState);
        //azulShareds.printFactory();
        //azulShareds.printCenter();
        //azulShareds.printBag();
        //azulShareds.printDiscard();
        //azulShareds.printTurn();

        Players azulPlayers = new Players(2);
        azulPlayers.PlayerState(input_playerState);
        System.out.println(azulPlayers.playerState);
        //azulPlayers.printPlayer();
        //azulPlayers.printMosaic();
        //azulPlayers.printStorage();
        //azulPlayers.printFloor();
        //System.out.println(azulPlayers.getPlayer('A').getName());

        azulPlayers.PlayerState(input_playerState_another);
        System.out.println(azulPlayers.playerState);
        //azulPlayers.printPlayer();
        //azulPlayers.printMosaic();
        //azulPlayers.printStorage();
        //azulPlayers.printFloor();

        azulShareds.printBag();
        System.out.println(azulShareds.getRandomTileBag());

    }
}
