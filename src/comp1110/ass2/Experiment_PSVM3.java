package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;
import comp1110.ass2.backend.SharedState;

import java.util.ArrayList;
import java.util.Random;

public class Experiment_PSVM3 implements Constants{
    public static void main(String[] args) {
        int PLAYER_NUMBER = 2;
        String[] gameState = new String[NUMBER_OF_STATES];
        String move;
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);

        /*
        gameState[0] = "AF0cdee1bdde2abbe3bcde4aaaeCfB1616181614D0000000000";
        gameState[1] = "A0MSFB0MSF";

        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }
         */

        gameState = multiazul.setInitalStates(PLAYER_NUMBER);
        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }

        gameState = multiazul.setStartingState(gameState);
        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }

        while(multiazul.findCurrentStage(gameState) != END_OF_GAME){
            while(multiazul.findCurrentStage(gameState) != NEXT_ROUND_STAGE){
                move = multiazul.generateAction(gameState);
                gameState = multiazul.applyMove(gameState, move);
                for(int i=0; i < NUMBER_OF_STATES; i++){
                    System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i] + " Move : " + move);
                }
            }
            gameState = multiazul.nextRound(gameState);
            for(int i=0; i < NUMBER_OF_STATES; i++){
                System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
            }
        }

        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }
    }
}
