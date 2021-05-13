package comp1110.ass2.backend;

/**
 * @author Min Jae, Kim
 * CoordinateTyped interface is all object that stores tiles and information about position:
 * For mosaic : e00c01d02b03a04d10c12b14a20b21c23e24e32d43
 * For storage : 3a34c3
 * Mosaic, Storage class stores tiles like above format
 */
public interface CoordinateTyped extends Tiles {

    public boolean isTilesFull();

    public char getTileColor(int index);

}
