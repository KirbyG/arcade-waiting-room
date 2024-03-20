/**
 * Contains all letters that have been played in their positions.
 * @author Kirby Gordon
 */
class Board
{
    private Tile[][] tiles;
    /**
     * Constructs a new Board with the correct size.
     */
    public Board()
    {
        tiles = new Tile[15][15];
    }
    /**
     * Places a collection of tiles onto the board at their locations.
     * @param additions the TileCollection whose tiles will be added to the board
     */
    public void add(TileCollection additions)
    {
        for (int i = 0; i < additions.getNumTiles(); i++)
        {
            tiles[additions.getTile(i).getBoardPosition().getX()][additions.getTile(i).getBoardPosition().getY()] = additions.getTile(i);
        }
    }
    /**
     * Given a position on the board, returns the tile found there.
     * @param x the x position
     * @param y the y position
     * @return null or the tile at that location
     */
    public Tile getTile(int x, int y)
    {
        if (x < 0 || x > 14 || y < 0 || y > 14)
        {
            return null;
        }
        return tiles[x][y];
    }
    /**
     * Given a position on the board, returns the tile found there.
     * @param p the Position of the tile
     * @return the tile found at Position p
     */
    public Tile getTile(Position p)
    {
        int x = p.getX();
        int y = p.getY();
        return getTile(x, y);
    }
}