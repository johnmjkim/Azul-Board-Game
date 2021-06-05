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
        gameState[0] = "BFCcccB1716171416D0000000000";
        gameState[1] = "A0MS1b12d14d3FbbbeeB0MS2e24a2Faddf";
        all_moves = multiazul.generateAllActions(gameState);
        for(int i=0; i < all_moves.size(); i++){
            System.out.println(all_moves.get(i));
        }
    }
}
