package comp1110.ass2;

/**
 * @author Min Jae, Kim
 * OrderTyped interface is all object that stores tiles like below format:
 * aabbbccdef
 * Floor, Factory, Center class stores tiles like above format
 */
public interface OrderTyped extends Tiles{

    public void refillTiles(char[] refill);

    public void removeTile(char color);

    public void removeTiles(char color, int n);

    public void removeAllTiles();

    public void addTile(char color);

    public void addTiles(char color, int n);

}
