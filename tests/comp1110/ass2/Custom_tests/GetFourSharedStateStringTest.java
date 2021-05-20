package comp1110.ass2.Custom_tests;

import comp1110.ass2.TestFourSharedStateCases;
import comp1110.ass2.backend.SharedState;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

/**
 * @author Si Bo, Hu
 * Test if four shared state are correct
 */
public class GetFourSharedStateStringTest implements TestFourSharedStateCases {


    private void Turntest(String in, String expected) {
        SharedState ss = new SharedState(in,PLAYER_NUMBER);
        String out = ss.getTurnState();
        assertEquals(expected, out, "Input state in TurnTest: \"" + in + "\"");
    }

    private void Factoriestest(String in, String expected) {
        SharedState ss = new SharedState(in, PLAYER_NUMBER);
        String out = ss.factories.getStateString();
        assertEquals(expected, out, "Input state in Factoriestest: \"" + in + "\"");
    }

    private void Factorytest(String in, String expected, int factory_number) {
        SharedState ss = new SharedState(in, PLAYER_NUMBER);
        String out = ss.factories.getFactory(factory_number).getStateString();
        assertEquals(expected, out, "Input state in Factorytest, Factory " + factory_number + " : \"" + in + "\"");
    }

    private void Centertest(String in, String expected) {
        SharedState ss = new SharedState(in, PLAYER_NUMBER);
        String out = ss.center.getStateString();
        assertEquals(expected, out, "Input state in Centertest: \"" + in + "\"");
    }

    private void Bagtest(String in, String expected) {
        SharedState ss = new SharedState(in, PLAYER_NUMBER);
        String out = ss.bag.getStateString();
        assertEquals(expected, out, "Input state in Bagtest: \"" + in + "\"");
    }

    private void Discardtest(String in, String expected) {
        SharedState ss = new SharedState(in, PLAYER_NUMBER);
        String out = ss.discard.getStateString();
        assertEquals(expected, out, "Input state in Discardtest: \"" + in + "\"");
    }


    @Test
    public void testTurnState(){
        for (int i = 0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length; i++) {
            Turntest(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0], FULL_FOUR_PLAYER_GAME_STATES_TURNS[i][0]);
        }
    }

    @Test
    public void testFactoriesState(){
        for (int i = 0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length; i++) {
            Factoriestest(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0], FULL_FOUR_PLAYER_GAME_STATES_FACTORIES[i][0]);
        }
    }

    @Test
    public void testFactoryState(){
        for (int i = 0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length; i++) {
            for(int j = 0; j < FACTORY_MAX_NUMBER; j++){
                Factorytest(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0], FULL_FOUR_PLAYER_GAME_STATES_FACTORIES[i][j+1], j);
            }
        }
    }

    @Test
    public void testCenterState(){
        for (int i = 0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length; i++) {
            Centertest(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0], FULL_FOUR_PLAYER_GAME_STATES_CENTER[i]);
        }
    }

    @Test
    public void testBagState(){
        for (int i = 0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length; i++) {
            Bagtest(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0], FULL_FOUR_PLAYER_GAME_STATES_BAG[i]);
        }
    }

    @Test
    public void testDiscardState(){
        for (int i = 0; i < FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES.length; i++) {
            Discardtest(FULL_FOUR_PLAYER_GAME_STATES_WITH_MOVES[i][0], FULL_FOUR_PLAYER_GAME_STATES_DISCARD[i]);
        }
    }

}
