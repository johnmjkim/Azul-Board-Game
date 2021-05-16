package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;
import comp1110.ass2.backend.SharedState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Experiment_PSVM3 implements Constants{
    public static void main(String[] args) {

        int PLAYER_NUMBER = 4;
        String[] gameState = new String[NUMBER_OF_STATES];
        String move;
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);

        /*
        gameState[0] = "CF7abdn8nnnnCaacceeeeB0000000000D000000000";
        gameState[1] = "A0Ma02d03c04e20b30S1d22d13d14c5FaaabeefB0Mc01d04b11a13e14d33b42e43S0a11d22b23b14c5FadddddC0Mb01e03a22d23c30a34S0d11e22c33b34a5FceD0Ma00e02d03d12d34e41a43c44S1a12b33e34b4Fb";
        move = "C7bF";
        gameState = multiazul.applyMove(gameState, move);

        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }

        gameState = multiazul.nextRound(gameState);

        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }

         */

        /*
        int TEST_TIMES = 1;
        for(int test=1; test <= TEST_TIMES; test++){
            gameState = multiazul.setInitalStates(PLAYER_NUMBER);
            for(int i=0; i < NUMBER_OF_STATES; i++){
                System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
            }

            gameState = multiazul.setStartingState(gameState);
            for(int i=0; i < NUMBER_OF_STATES; i++){
                System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
            }

            while(!(multiazul.findCurrentStage(gameState) == END_OF_GAME) && multiazul.isStateValid(gameState)){
                while(!(multiazul.findCurrentStage(gameState) == NEXT_ROUND_STAGE || multiazul.findCurrentStage(gameState) == END_OF_GAME) && multiazul.isStateValid(gameState)){
                    move = multiazul.generateSmartAction(gameState);
                    gameState = multiazul.applyMove(gameState, move);
                    for(int i=0; i < NUMBER_OF_STATES; i++){
                        System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i] + " Move : " + move);
                    }
                }
                gameState = multiazul.nextRound(gameState);
                for(int i=0; i < NUMBER_OF_STATES; i++){
                    System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
                }
            }
        }
        System.out.println(" tests finished");

         */

        ArrayList<Integer> scores = new ArrayList<>();

        scores.add(8);
        scores.add(25);
        scores.add(14);
        scores.add(7);

        int[] score_array = new int[scores.size()];
        int[] ranks = new int[score_array.length];

        for(int i=0; i < score_array.length; i++){
            score_array[i] = scores.get(i);
        }

        Arrays.sort(score_array);

        for(int i=0; i < scores.size(); i++){
            System.out.println(score_array[i]);
        }

        for(int rank = ranks.length; rank >= 1; rank--){
            for(int idx = 0; idx < scores.size(); idx++){
                if(scores.get(idx) == score_array[ranks.length - rank]){
                    ranks[idx] = rank;
                }
            }
        }

        for(int i=0; i < ranks.length; i++){
            System.out.println(ranks[i]);
        }

    }
}
