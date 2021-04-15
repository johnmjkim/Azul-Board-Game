package comp1110.ass2;

public interface Tiles extends State{
    public void countTilesNumber(String State);

    public int getTilesNumber(char color);

    public int getTotalTilesNumber();

    public void removeTile(char color);

    public void removeAllTiles();

    public void addTile(char color);

}
