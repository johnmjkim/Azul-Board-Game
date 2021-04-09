package comp1110.ass2;

public interface Shared {
    // Interface for Shared class

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

    String getSharedState();
    int getCenterTilesNumber(char color);
    int getBagTilesNumber(char color);
    int getDiscardTilesNumber(char color);
}
