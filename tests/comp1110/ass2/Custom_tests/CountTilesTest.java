package comp1110.ass2.Custom_tests;

import comp1110.ass2.SharedState;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class CountTilesTest implements TestCountCases{

    // TODO Complete Test for Center, Bag, Discard

    private void CountFactoriestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        int out = ss.factories.getFactoryTotalTiles();
        assertEquals(expected[0], out, "Input state in Factoriestest: \"" + in + "\"");
    }

    private void CountFactoriesByTilestest(String in, int[] expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            int out = ss.factories.getFactoryTilesNumber(color);
            assertEquals(expected[j+1], out, "Input state in Factoriestest: \"" + in + "\"");
            color++;
        }
    }

    private void CountCentertest(String in, int[] expected) {
    }

    @Test
    public void testFactoriesCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountFactoriestest(VALID_STATES[i][0], TILES_FACTORIES[i]);
        }
    }

    @Test
    public void testFactoriesCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            CountFactoriesByTilestest(VALID_STATES[i][0], TILES_FACTORIES[i]);
        }
    }

    @Test
    public void testCenterCount(){

    }

}
