import java.util.ArrayList;
/**
 * A collection of tiles.
 * @author Kirby Gordon
 */
class TileCollection
{
    public static int[] distribution = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    public ArrayList<Tile> tiles;
    /**
     * Creates a new empty tile collection
     */
    public TileCollection()
    {
        tiles = new ArrayList<Tile>();
    }
    /**
     * Adds a tile to the collection.
     * @param tile 
     */
    public void addTile(Tile tile)
    {
        tiles.add(tile);
    }
    /**
     * Gets the number of tiles in the collection.
     * @return the number of tiles
     */
    public int getNumTiles()
    {
        return tiles.size();
    }
    /**
     * Gets a tile at a given index
     * @param i the index
     * @return a Tile
     */
    public Tile getTile(int i)
    {
        return tiles.get(i);
    }
    /**
     * Deletes a tile at an index
     * @param i the index
     */
    public void delete(int i)
    {
        tiles.remove(i);
    }
    /**
     * Selects a random tile
     * @return a tile
     */
    public Tile pick()
    {
        int i = Constants.random(0, tiles.size() - 1);
        Tile temp = tiles.get(i);
        tiles.remove(i);
        return temp;
    }
    /**
     * Clears the collection
     */
    public void clear()
    {
        tiles.clear();
    }
    /**
     * Gets a tile at the specified position
     * @param p the tile position
     * @return the tile at the position, or null
     */
    public Tile getTile(Position p)
    {
        for (Tile t: tiles)
        {
            if (t.getBoardPosition().equals(p))
            {
                return t;
            }
        }
        return null;
    }
}