package comp1110.ass2;

public interface Player {

    void PlayerState(String playerState);
    void addPlayer(String name, Integer score, int turn);
    void printPlayer();
    void addMosaic(String mosaicState, int turn);
    void printMosaic();
    void addStorage(String storageState, int turn);
    void printStorage();
    void addFloor(String floorState, int turn);
    void printFloor();
    void Move(String applyMove);
    void Turn(int turn);
}

