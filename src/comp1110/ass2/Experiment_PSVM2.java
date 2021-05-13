package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;

public class Experiment_PSVM2 implements TestStateCases {
    public static void main(String[] args) {
        MultiAzul multiazul = new MultiAzul(2);
        for(int i=0; i < FULL_GAME_WITH_MOVES.length; i++){
            String[] testState = new String[]{FULL_GAME_WITH_MOVES[i][0], FULL_GAME_WITH_MOVES[i][1]};

            System.out.print(" Stage : " + multiazul.findCurrentStage(testState) + " SS : " + testState[0] + " PS : " + testState[1]);
            if(FULL_GAME_WITH_MOVES[i].length > 2){
                System.out.println(" MV : " + FULL_GAME_WITH_MOVES[i][2]);
            }
            else{
                System.out.println();
            }
        }
    }
}
