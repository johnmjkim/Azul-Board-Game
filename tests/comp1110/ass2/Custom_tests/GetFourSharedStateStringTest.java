package comp1110.ass2.Custom_tests;

import comp1110.ass2.TestFourPlayerStateCases;
import comp1110.ass2.backend.SharedState;
import comp1110.ass2.TestStateCases;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GetFourSharedStateStringTest implements TestFourPlayerStateCases {

    // TODO 1 : Change all below code to test 4 Players version of game
    // TODO 2 : Use PLAYER_NUMBER (= 4), FULL_FOUR_PLAYER_GAME_WITH_MOVES to finish test
    // TODO 3 : Add valid states in interface TestFourPlayerStateCases
    // TODO 4 : Finish Turntest, Factoriestest, Factorytest, Centertest, Bagtest, Discardtest
    // TODO 5 : Finish testTurnState, testFactoriesState, testFactoryState, testCenterState, testBagState, testDiscardState
    // TODO 6 : Once done, get checked by Min Jae (John) Kim

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
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.center.getStateString();
        assertEquals(expected, out, "Input state in Centertest: \"" + in + "\"");
    }

    private void Bagtest(String in, String expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.bag.getStateString();
        assertEquals(expected, out, "Input state in Bagtest: \"" + in + "\"");
    }

    private void Discardtest(String in, String expected) {
        SharedState ss = new SharedState(in, DEFAULT_MAX_PLAYER);
        String out = ss.discard.getStateString();
        assertEquals(expected, out, "Input state in Discardtest: \"" + in + "\"");
    }

    /*
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
        for (int i = 0; i < VALID_STATES.length; i++) {
            Centertest(VALID_STATES[i][0], VALID_STATES_CENTER[i]);
        }
    }

    @Test
    public void testBagState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            Bagtest(VALID_STATES[i][0], VALID_STATES_BAG[i]);
        }
    }

    @Test
    public void testDiscardState(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            Discardtest(VALID_STATES[i][0], VALID_STATES_DISCARD[i]);
        }
    }

     */
}
