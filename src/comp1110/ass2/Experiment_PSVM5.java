package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;

import java.util.ArrayList;

public class Experiment_PSVM5 implements Constants{
    public static void main(String[] args) {
        int PLAYER_NUMBER = 2;
        String[] gameState = new String[NUMBER_OF_STATES];
        String move;
        ArrayList<String> all_moves;
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);

        gameState = multiazul.setInitalStates(PLAYER_NUMBER);
        gameState = multiazul.setStartingState(gameState);

        gameState[0] = "BFCceB1713191615D0000000000";
        gameState[1] = "A0MS0b11d22b33d24e1FbbbB0MS0a11e22a13e1Faf";
        /*
        all_moves = multiazul.generateAllActions(gameState);
        for(int i=0; i < all_moves.size(); i++){
            System.out.println(all_moves.get(i));
        }
         */
        move = multiazul.generateSmartAction(gameState);
    }
}
