package comp1110.ass2.Custom_tests;

import comp1110.ass2.SharedState;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GetSharedStateStringsTest implements TestCases {

    private void SharedTurntest(String in, String expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.getTurnState();
        assertEquals(expected, out, "Input state in SharedTurnTest: \"" + in + "\"");
    }

    private void SharedFactoriestest(String in, String expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.factories.getStateString();
        assertEquals(expected, out, "Input state in SharedFactoriestest: \"" + in + "\"");
    }

    private void SharedFactorytest(String in, String expected, int i) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.factories.getFactory(i).getStateString();
        assertEquals(expected, out, "Input state in SharedFactorytest, Factroy " + i + " : \"" + in + "\"");
    }

    @Test
    public void testTurnState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            SharedTurntest(VALID_STATES[i][0], VALID_STATES_TURNS[i][0]);
        }
    }

    @Test
    public void testFactoriesState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            SharedFactoriestest(VALID_STATES[i][0], VALID_STATES_FACTORIES[i][0]);
        }
    }

    @Test
    public void testFactoryState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_FACTORY_MAX_NUMBER; j++){
                SharedFactorytest(VALID_STATES[i][0], VALID_STATES_FACTORIES[i][j+1], j);
            }
        }
    }
}
