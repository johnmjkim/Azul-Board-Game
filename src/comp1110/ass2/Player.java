package comp1110.ass2;

public interface Player {
    // Interface for Player class

    // Input playerState, construct player/mosaic/storage/floor and print information
    /**
     * Input of PlayerState is String playerState
     * The method automatically sort players information as below :
     * addPlayer : String name, Integer score, int num_turn
     * addMosaic : String mosaicState
     * addStorage : String storageState
     * addFloor : String floorState
     * @param playerState the state of player
     */
    void PlayerState(String playerState);
    void addPlayer(Character name, Integer score, int num_turn);
    void printPlayer();
    void addMosaic(String mosaicState, int num_turn);
    void printMosaic();
    void addStorage(String storageState, int num_turn);
    void printStorage();
    void addFloor(String floorState, int num_turn);
    void printFloor();

    // Get values from player/mosaic/storage/floor and print information
    String getPlayerState();
    int getMosaicTotalTiles();
    int getStorageTotalTiles();
    int getFloorTotalTiles();

}

