/**
 * Represents a rack of tiles.
 * @author Kirby Gordon
 */
class Rack extends TileCollection
{
    /**
     * Creates a new rack by selecting 7 tiles from the bag.
     * @param bag the bag of tiles
     */
    public Rack(Bag bag)
    {
        super();
        for (int tile = 0; tile < 7; tile++)
        {
            tiles.add(bag.pick());
        }
        fixPositions();
        
    }
    /**
     * Adds a given tile to the rack.
     * @param tile the tile to be added
     */
    public void addTile(Tile tile)
    {
        super.addTile(tile);
        fixPositions();
    }
    /**
     * Deletes the tile in the ith position
     * @param i the position of the tile
     */
    public void delete(int i)
    {
        super.delete(i);
        fixPositions();
    }
    /**
     * Corrects the positions of the tiles on the rack.
     */
    private void fixPositions()
    {
        for (int tile = 0; tile < tiles.size(); tile++)
        {
            tiles.get(tile).setPosition(new Position(Constants.XCENTER + (tile - 3) * (Constants.TILEBORDERTHICKNESS + Constants.TILEWIDTH), Constants.RACKPOSITION.getY()));
        }   
    }
    /**
     * Picks random letters until the rack is full.
     * @param bag the bag from which to pick
     */
    public void draw(Bag bag)
    {
        while (tiles.size() < 7 && bag.getNumTiles() > 0)
        {
            tiles.add(bag.pick());
        }
        //"tiles" is a list of the tiles on the player's rack. "bag.getNumTiles()" is the number of tiles left in the bag. This code 
        fixPositions();
    }
}