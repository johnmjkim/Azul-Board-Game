package comp1110.ass2.Custom_tests;

import comp1110.ass2.TestFourPlayerStateCases;
import comp1110.ass2.backend.MultiAzul;
import comp1110.ass2.backend.PlayerState;
import comp1110.ass2.TestStateCases;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import comp1110.ass2.TestFourPlayerStateCases;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class FourPlayerApplyMoveTest implements TestFourPlayerStateCases {

    private void applyFourPlayerMovetest(String[] in, String[] expected, String move){
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);
        String[] out = new String[NUMBER_OF_STATES];
        out = multiazul.applyMove(in, move);
        SharedStatetest(out[0], expected[0]);
        PlayerStatetest(out[1], expected[1]);
    }

    private void SharedStatetest(String in, String expected) {
        assertEquals(expected, in, "Input state in SharedStatetest " + " : \"" + in + "\"");
    }

    private void PlayerStatetest(String in, String expected) {
        assertEquals(expected, in, "Input state in PlayerStatetest " + " : \"" + in + "\"");
    }

    @Test
    public void testSharedState(){
        for (int i = 0; i < FOUR_PLAYER_APPLY_MOVES.length; i++) {
            String[] in = new String[NUMBER_OF_STATES];
            String[] expected = new String[NUMBER_OF_STATES];
            in[0] = FOUR_PLAYER_APPLY_MOVES[i][0];
            in[1] = FOUR_PLAYER_APPLY_MOVES[i][1];
            String move = FOUR_PLAYER_APPLY_MOVES[i][2];
            expected[0] = FOUR_PLAYER_APPLY_MOVES[i][3];
            expected[1] = FOUR_PLAYER_APPLY_MOVES[i][4];
            applyFourPlayerMovetest(in, expected, move);
        }
    }
}
