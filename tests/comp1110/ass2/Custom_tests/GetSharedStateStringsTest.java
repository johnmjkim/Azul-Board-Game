package comp1110.ass2.Custom_tests;

import comp1110.ass2.SharedState;
import comp1110.ass2.TestStateCases;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GetSharedStateStringsTest implements TestStateCases {

    // TODO Complete Test for Center, Bag, Discard

    private void Turntest(String in, String expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.getTurnState();
        assertEquals(expected, out, "Input state in TurnTest: \"" + in + "\"");
    }

    private void Factoriestest(String in, String expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.factories.getStateString();
        assertEquals(expected, out, "Input state in Factoriestest: \"" + in + "\"");
    }

    private void Factorytest(String in, String expected, int factory_number) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.factories.getFactory(factory_number).getStateString();
        assertEquals(expected, out, "Input state in Factorytest, Factory " + factory_number + " : \"" + in + "\"");
    }

    private void Centertest(String in, String expected) {
        // TODO Center test
    }

    private void Bagtest(String in, String expected) {
        // TODO Bag test
    }

    private void Discardtest(String in, String expected) {
        // TODO Discard test
    }

    @Test
    public void testTurnState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            Turntest(VALID_STATES[i][0], VALID_STATES_TURNS[i][0]);
        }
    }

    @Test
    public void testFactoriesState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            Factoriestest(VALID_STATES[i][0], VALID_STATES_FACTORIES[i][0]);
        }
    }

    @Test
    public void testFactoryState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_FACTORY_MAX_NUMBER; j++){
                Factorytest(VALID_STATES[i][0], VALID_STATES_FACTORIES[i][j+1], j);
            }
        }
    }

    @Test
    public void testCenterState(){
        // TODO use Center test
    }

    @Test
    public void testBagState(){
        // TODO use Bag test
    }

    @Test
    public void testDiscadState(){
        // TODO use Discard test
    }
}
