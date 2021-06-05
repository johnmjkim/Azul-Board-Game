package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;

public class Experiment_PSVM5 implements Constants{
    public static void main(String[] args) {
        int PLAYER_NUMBER = 2;
        String[] gameState = new String[NUMBER_OF_STATES];
        String move;
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);

        gameState = multiazul.setInitalStates(PLAYER_NUMBER);
        gameState = multiazul.setStartingState(gameState);
        move = multiazul.generateSmartAction(gameState);
    }
}
