package comp1110.ass2;

public interface OrderTyped extends Tiles{

    public void refillTiles(char[] refill);

    public void removeTile(char color);

    public void removeAllTiles();

    public void addTile(char color);

}
