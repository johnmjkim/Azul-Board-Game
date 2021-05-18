package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;

public class Experiment_PSVM4 implements TestFourPlayerStateCases{
    public static void main(String[] args) {
        String[] gameState = new String[NUMBER_OF_STATES];
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);
        for(int i=0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length - 1; i++){
            boolean has_move = (FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i].length == 3);
            if(has_move){
                System.out.print("new String[]{\"");
                System.out.print(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0]);
                System.out.print("\", \"");
                System.out.print(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][1]);
                System.out.print("\", \"");
                System.out.print(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][2]);
                System.out.print("\", \"");
                System.out.print(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i+1][0]);
                System.out.print("\", \"");
                System.out.print(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i+1][1]);
                System.out.println("\"},");
            }
        }
    }
}
