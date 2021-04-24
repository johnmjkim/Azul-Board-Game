package comp1110.ass2.Custom_tests;

import comp1110.ass2.SharedState;
import comp1110.ass2.TestCountCases;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class CountTilesSharedStateTest implements TestCountCases {

    private void CountFactoriestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.factories.getFactoryTotalTiles();
        assertEquals(expected[0], out, "Input state in Factories test: \"" + in + "\"");
    }

    private void CountFactoriesByTilestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            int out = ss.factories.getFactoryTilesNumber(color);
            assertEquals(expected[j+1], out, "Input state in Factories test with color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    private void CountCentertest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.center.getTotalTilesNumber();
        assertEquals(expected[0], out, "Input state in Center test: \"" + in + "\"");
    }

    private void CountCenterByTilestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= FIRST_PLAYER - BLUE; j++){
            int out = ss.center.getTilesNumber(color);
            assertEquals(expected[j+1], out, "Input state in Center test with color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    private void CountBagtest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.bag.getTotalTilesNumber();
        assertEquals(expected[0], out, "Input state in Bag test: \"" + in + "\"");
    }

    private void CountBagByTilestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            int out = ss.bag.getTilesNumber(color);
            assertEquals(expected[j+1], out, "Input state in Bag test with color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    private void CountDiscardtest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.discard.getTotalTilesNumber();
        assertEquals(expected[0], out, "Input state in Discard test: \"" + in + "\"");
    }

    private void CountDiscardByTilestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            int out = ss.discard.getTilesNumber(color);
            assertEquals(expected[j+1], out, "Input state in Discard test with color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    @Test
    public void testFactoriesCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountFactoriestest(VALID_STATES[i][0], VALID_TILES_FACTORIES[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountFactoriestest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_FACTORIES[i]);
        }
    }

    @Test
    public void testFactoriesCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountFactoriesByTilestest(VALID_STATES[i][0], VALID_TILES_FACTORIES[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountFactoriesByTilestest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_FACTORIES[i]);
        }
    }

    @Test
    public void testCenterCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountCentertest(VALID_STATES[i][0], VALID_TILES_CENTER[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountCentertest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_CENTER[i]);
        }
    }

    @Test
    public void testCenterCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountCenterByTilestest(VALID_STATES[i][0], VALID_TILES_CENTER[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountCenterByTilestest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_CENTER[i]);
        }
    }

    @Test
    public void testBagCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountBagtest(VALID_STATES[i][0], VALID_TILES_BAG[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountBagtest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_BAG[i]);
        }
    }

    @Test
    public void testBagCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountBagByTilestest(VALID_STATES[i][0], VALID_TILES_BAG[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountBagByTilestest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_BAG[i]);
        }
    }

    @Test
    public void testDiscardCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountDiscardtest(VALID_STATES[i][0], VALID_TILES_DISCARD[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountDiscardtest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_DISCARD[i]);
        }
    }

    @Test
    public void testDiscardCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountDiscardByTilestest(VALID_STATES[i][0], VALID_TILES_DISCARD[i]);
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            CountDiscardByTilestest(FULL_GAME_WITH_MOVES[i][0], GAME_WITH_MOVE_TILES_DISCARD[i]);
        }
    }

}
