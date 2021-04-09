package comp1110.ass2;

public interface Player {
    // Interface for Player class

    /**
     * Input of constructor PlayerState is String playerState
     * Constructor method automatically sort players information as below :
     * addPlayer : String name, Integer score, int turn
     * addMosaic : String mosaicState
     * addStorage : String storageState
     * addFloor : String floorState
     * @param playerState the state of player
     */
    void PlayerState(String playerState);
    void addPlayer(Character name, Integer score, int turn);
    void printPlayer();
    void addMosaic(String mosaicState, int turn);
    void printMosaic();
    void addStorage(String storageState, int turn);
    void printStorage();
    void addFloor(String floorState, int turn);
    void printFloor();
}

