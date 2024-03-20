/**
 * The "bag" that contains all unplayed tiles
 * @author Kirby Gordon
 */
class Bag extends TileCollection
{
    /**
     * Constructs a bag with the correct distribution of 100 letters.
     */
    public Bag()
    {
        super();
        for (int i = 0; i < distribution.length; i++)
        {
            for (int j = 0; j < distribution[i]; j++)
            {
                tiles.add(new Tile(i));
            }
        }
    }
}