package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public interface BagTyped extends Tiles{

    public void refillTiles(String refill);

    public void removeTile(char color);

    public void removeAllTiles();

    public void addTile(char color);
}
