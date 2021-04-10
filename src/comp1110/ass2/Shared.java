package comp1110.ass2;

public interface Shared {
    // Interface for Shared class

    // Input sharedState, construct factory/center/bag/discard and print information
    void SharedState(String sharedState);
    void addTurn(Character turnState);
    void printTurn();
    void addFactory(String factoryState);
    void printFactory();
    void addCenter(String centerState);
    void printCenter();
    void addBag(String BagState);
    void printBag();
    void addDiscard(String discardState);
    void printDiscard();

    // Get values from factory/center/bag/discard
    String getSharedState();
    int getFactoryTilesNumber(char color);
    int getCenterTilesNumber(char color);
    int getBagTilesNumber(char color);
    int getDiscardTilesNumber(char color);

    int getFactoryTotalTiles();
    int getCenterTotalTiles();
    int getBagTotalTiles();
    int getDiscardTotalTiles();
}
