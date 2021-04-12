package comp1110.ass2;

public class States {
    String State;
    // Maximum player numbers
    int MAX_PLAYER_NUMBER;

    // Players substring
    public final char PLAYER_A = 'A';
    public final char PLAYER_B = 'B';
    public final char PLAYER_C = 'C';
    public final char PLAYER_D = 'D';
    public final char[] ALL_PLAYERS = {PLAYER_A, PLAYER_B, PLAYER_C, PLAYER_D};

    // Shared state substring
    public final char FACTORY = 'F';
    public final char CENTER = 'C';
    public final char BAG = 'B';
    public final char DISCARD = 'D';

    // Players state substring
    public final char MOSAIC = 'M';
    public final char STORAGE = 'S';
    public final char FLOOR = 'F';

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';
    public final char FIRST_PLAYER = 'f';

    // Size, Numbers of all components
    public final int FACTORY_MAX_NUMBER = 2 * MAX_PLAYER_NUMBER + 1;
    public final int FACTORY_SIZE = 4;
    public final String EMPTY_TILES = "0000000000";
    public final String EMPTY_STATE = "";

    // Shared fields Strings
    String sharedState = EMPTY_STATE;

    // Player fields strings
    String playerState = EMPTY_STATE;

    // Class fields
    /*
    private Factory factory;
    private Center center;
    private Bag bag;
    private Discard discard;
    private Score score;
    private Mosaic mosaic;
    private Storage storage;
    private Floor floor;
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

    public void printState() {
        System.out.println(State);
    }
}
