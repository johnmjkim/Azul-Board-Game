package comp1110.ass2;

import java.util.ArrayList;

public interface Player {

    void Player(String playerState);
    void Name(String nameState);
    void Score(String scoreState);
    void Mosaic(String mosaicState);
    void Storage(String storageState);
    void Floor(String floorState);
    void Move(String applyMove);
    void Turn(int turn);
    int getTurn();
    String getName();
    int getScore();

}

