package comp1110.ass2;

public interface Shared {
    // Interface for Shared class

    // Input sharedState, construct factory/center/bag/discard and print information

    /**
     * Input of PlayerState is String playerState
     * The method automatically sort shared information as below :
     * addTurn : String name, Integer score, int num_turn
     * addFactory : String factoryState
     * addCenter : String storageState
     * addBag : String bagState
     * @param sharedState
     */
    void SharedState(String sharedState);
    void printSharedState();
    void addTurn(Character turnState);
    void printTurn();
    void addFactory(String factoryState);
    void printFactory();
    void addCenter(String centerState);
    void printCenter();
    void addBag(String bagState);
    void printBag();
    void addDiscard(String discardState);
    void printDiscard();

    // Get values from factory/center/bag/discard
    String getSharedState();
    String getFactoryState();
    String getCenterState();
    String getBagState();
    String getDiscardState();
    int getFactoryTilesNumber(char color);
    int getCenterTilesNumber(char color);
    int getBagTilesNumber(char color);
    int getDiscardTilesNumber(char color);

    int getFactoryTotalTiles();
    int getCenterTotalTiles();
    int getBagTotalTiles();
    int getDiscardTotalTiles();

    // Methods for Factory
    boolean isFactoryFull();

    // Methods for Center

    // Methods for Bag
    char getRandomTileBag();
    void refillBag();

    // Methods for Discard
    void clearDiscard();

}
