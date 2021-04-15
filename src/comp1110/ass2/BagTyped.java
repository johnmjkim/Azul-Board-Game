package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public interface BagTyped extends State{

    public void countTilesNumber(String State);

    public int getTilesNumber(char color);

    public int getTotalTilesNumber();

    public void removeTile(char color);

    public void removeAllTiles();

    public void addTile(char color);

    public void refillTiles(String refill);
}
