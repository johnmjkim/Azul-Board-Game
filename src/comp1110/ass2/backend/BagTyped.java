package comp1110.ass2.backend;

/**
 * @author Min Jae, Kim
 * BagTyped interface is all object that stores tiles like below format:
 * 1315050716
 * Bag, Discard class stores tiles like above format
 */
public interface BagTyped extends Tiles {

    public void refillTiles(String refill);

    public void removeTile(char color);

    public void removeAllTiles();

    public void addTile(char color);

    public void addTiles(char color, int n);
}
