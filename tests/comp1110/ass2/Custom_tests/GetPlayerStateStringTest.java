package comp1110.ass2.Custom_tests;

import comp1110.ass2.PlayerState;
import comp1110.ass2.TestStateCases;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GetPlayerStateStringTest implements TestStateCases {

    // TODO Complete Test for Mosaic, MosaicRow, MosaicCol, Storage, StorageRow, Floor

    private void Turntest(String in, String expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        char out = ps.getnPlayer(ALL_PLAYERS[player]).getnPlayerChar();
        assertEquals(expected, String.valueOf(out), "Input state in TurnTest " + ALL_PLAYERS[player] + " : \"" + in + "\"");
    }

    private void PlayerStatetest(String in, String expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        String out = ps.getnPlayer(ALL_PLAYERS[player]).getStateString();
        assertEquals(expected, out, "Input state in PlayerStateTest " + ALL_PLAYERS[player] + " : \"" + in + "\"");
    }

    private void Mosaictest(String in, String expected, int player) {
        // TODO Mosaic test
    }

    private void MosaicRowtest(String in, String expected, int row) {
        // TODO Mosaic Row test
    }

    private void MosaicColtest(String in, String expected, int col) {
        // TODO Mosaic Col test
    }

    private void Storagetest(String in, String expected, int player) {
        // TODO Storage test
    }

    private void StorageRowtest(String in, String expected, int row) {
        // TODO StorageRow test
    }

    private void Floortest(String in, String expected, int player) {
        // TODO Floor test
    }

    @Test
    public void testTurnState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                Turntest(VALID_STATES[i][1], VALID_STATES_TURNS[i][j+1], j);
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

    @Test
    public void testMosaicState(){
        // TODO use Mosaic test
    }

    @Test
    public void testMosaicRowState(){
        // TODO use Mosaic Row test
    }

    @Test
    public void testMosaicColState(){
        // TODO use Mosaic Column test
    }

    @Test
    public void testStorageState(){
        // TODO use Storage test
    }

    @Test
    public void testStorageRowState(){
        // TODO use Storage Row test
    }

    @Test
    public void testFloorState(){
        // TODO use Floor test
    }
}
