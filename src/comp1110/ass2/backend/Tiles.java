package comp1110.ass2.backend;

import comp1110.ass2.backend.State;

/**
 * @author Min Jae, Kim
 * Tiles interface counts all numbers of tiles contains in the class
 * It also counts number of tiles by color
 * Used for all classes
 * SharedState : factories, center, bag, discard
 * PlayerState : mosaic, storage, floor
 */
public interface Tiles extends State {
    public void countTilesNumber(String State);

    public int getTilesNumber(char color);

    public int getTotalTilesNumber();

}
