package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;
import comp1110.ass2.backend.PlayerState;
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

        //gameState[0] = "AFCB0303050605D0002000004";
        //gameState[1] = "A0Md02c03e13S0a11a12d23c34a2FeeeB0Ma00e03b13S1a12d13a44d3FbbbbbC15Ma00c04c10e12b13d20c21e23b24b30d31a33d42S1d23c44a4FdfD9Mc00e01d04e10c11b12b20e32e43a44SFbbbbcc";

        //gameState[0] = "CF7abdn8nnnnCaacceeeeB0000000000D000000000";
        //gameState[1] = "A0Ma02d03c04e20b30S1d22d13d14c5FaaabeefB0Mc01d04b11a13e14d33b42e43S0a11d22b23b14c5FadddddC0Mb01e03a22d23c30a34S0d11e22c33b34a5FceD0Ma00e02d03d12d34e41a43c44S1a12b33e34b4Fb";
        //move = "C7bF";
        //gameState = multiazul.applyMove(gameState, move);

        //gameState[0] = "CFCB0108040603D0401050100";
        //gameState[1] = "A0MS0a11c22e23e44a5FaabbbbB4Mc03c34a44S1c12b2FcccddddC0Md02b23S1b12c13d24e3FaaaaabfD0Md03a13S1c22d13d34e5Fbbdeee";

        //move = multiazul.generateSmartAction(gameState);
        //System.out.println(move);
        /*
        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }

        gameState = multiazul.nextRound(gameState);

        for(int i=0; i < NUMBER_OF_STATES; i++){
            System.out.println(" Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
        }

         */

        int TEST_TIMES = 1;
        for(int test=1; test <= TEST_TIMES; test++){
            gameState = multiazul.setInitalStates(PLAYER_NUMBER);
            /*
            for(int i=0; i < NUMBER_OF_STATES; i++){
                System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
            }

             */

            gameState = multiazul.setStartingState(gameState);
            /*
            for(int i=0; i < NUMBER_OF_STATES; i++){
                System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
            }

             */

            while(!(multiazul.findCurrentStage(gameState) == END_OF_GAME) && multiazul.isStateValid(gameState)){
                while(!(multiazul.findCurrentStage(gameState) == NEXT_ROUND_STAGE || multiazul.findCurrentStage(gameState) == END_OF_GAME) && multiazul.isStateValid(gameState)){
                    move = multiazul.generateSmartAction(gameState);
                    printStates(gameState[0], gameState[1], move);
                    if(move == EMPTY_STATE){
                        gameState = multiazul.changeTurn(gameState);
                    }
                    else {
                        gameState = multiazul.applyMove(gameState, move);
                    }
                    /*
                    for(int i=0; i < NUMBER_OF_STATES; i++){
                        System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i] + " Move : " + move);
                    }

                     */
                }
                printStates(gameState[0], gameState[1], EMPTY_STATE);
                gameState = multiazul.nextRound(gameState);
                /*
                for(int i=0; i < NUMBER_OF_STATES; i++){
                    System.out.println(" test " + test + " Stage : " + multiazul.findCurrentStage(gameState) + " Valid : " + multiazul.isStateValid(gameState) + " State : " + gameState[i]);
                }

                 */
            }
        }
        printStates(gameState[0], gameState[1], EMPTY_STATE);
        System.out.println(" tests finished");
    }

    public static void printStates( String ss, String ps, String mv){
        System.out.print("new String[]{\"");
        System.out.print(ss);
        System.out.print("\", \"");
        System.out.print(ps);
        if(mv != EMPTY_STATE){
            System.out.print("\", \"");
            System.out.print(mv);
        }
        System.out.println("\"},");
    }
}
