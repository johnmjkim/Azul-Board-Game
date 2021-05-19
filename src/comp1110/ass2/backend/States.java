package comp1110.ass2.backend;

/**
 * @author Min Jae, Kim
 * States class implements interface State
 * SharedState, PlayerState extends this class
 */
public class States implements State {
    String State;

    // Maximum player numbers
    public int MAX_PLAYER_NUMBER;
    //public final int FACTORY_MAX_NUMBER = 2 * MAX_PLAYER_NUMBER + 1;

    // Shared fields Strings
    public String sharedState = EMPTY_STATE;

    // Player fields strings
    public String playerState = EMPTY_STATE;

    /**
     * @author Min Jae, Kim
     * Constructor method for States class
     * SharedState, PlayerState extends this class
     * @param State
     * @param max_player_number
     */
    public States( String State , int max_player_number ){
        this.State = State;
        this.MAX_PLAYER_NUMBER = max_player_number;
    }

    public String getState() {
        return State;
    }

    public void setState( String setState) {
        this.State = setState;
    }

    @Override
    public boolean isStateEmpty() {
        return this.State.isEmpty();
    }

    @Override
    public String getStateString() {
        return this.State;
    }

    @Override
    public String toString(){
        return getStateString();
    }

    @Override
    public void updateState() {

    }
}
