package comp1110.ass2.Custom_tests;

import comp1110.ass2.PlayerState;
import comp1110.ass2.SharedState;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GetPlayerStateStringTest implements TestCases{

    private void PlayerTurntest(String in, String expected, int i) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        char out = ps.getnPlayer(ALL_PLAYERS[i]).getnPlayerChar();
        assertEquals(expected, String.valueOf(out), "Input state in PlayerTurnTest " + ALL_PLAYERS[i] + " : \"" + in + "\"");
    }

    private void PlayerStatetest(String in, String expected, int i) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        String out = ps.getnPlayer(ALL_PLAYERS[i]).getStateString();
        assertEquals(expected, out, "Input state in PlayerStateTest " + ALL_PLAYERS[i] + " : \"" + in + "\"");
    }

    // TODO Complete Test for Mosaic, MosaicRow, MosaicCol, Storage, StorageRow, Floor

    @Test
    public void testTurnState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                PlayerTurntest(VALID_STATES[i][1], VALID_STATES_TURNS[i][j+1], j);
            }
        }
    }

    @Test
    public void testPlayerState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                PlayerStatetest(VALID_STATES[i][1], VALID_PLAYER_STATES[i][j], j);
            }
        }
    }

}
