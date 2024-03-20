import java.util.ArrayList;
/**
 * A word containing multiple tiles
 * @author Kirby Gordon
 */
class Word
{
    private ArrayList<Tile> tiles;
    private int direction;
    /**
     * Creates a new empty word.
     */
    public Word()
    {
        tiles = new ArrayList<Tile>();
    }
    /**
     * Set the direction of the word.
     * @param direction an int representing the direction
     */
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    /**
     * Adds a tile to the end of the word
     * @param tile the new tile
     */
    public void addTile(Tile tile)
    {
        tiles.add(tile);
    }
    /**
     * Adds a tile to the beginning or end, in either direction
     * @param tile the new tile
     * @param or up-down vs. left-right
     * @param dir front or back
     */
    public void addTile(Tile tile, int or, int dir)
    {
        if (tile != null)
        {
            if (or == 0)
            {
                if (dir == -1)
                {
                    addTileStart(tile);
                }
                else
                {
                    addTile(tile);
                }
            }
            else
            {
                if (dir == -1)
                {
                    addTile(tile);
                }
                else
                {
                    addTileStart(tile);
                }
            }
        }
    }
    /**
     * Gets the string of this word.
     * @return a lowercase string
     */
    public String getWord()
    {
        String word = "";
        for (Tile t: tiles)
        {
            word += Character.toString(t.getLetter());
        }
        return word;
    }
    /**
     * Clears the letters from the word
     */
    public void clear()
    {
        tiles.clear();
    }
    /**
     * Adds a tile to the beginning of the word.
     * @param tile the new tile
     */
    public void addTileStart(Tile tile)
    {
        tiles.add(0, tile);
    }
    /**
     * Returns the length of the word.
     * @return the word's length
     */
    public int getLength()
    {
        return tiles.size();
    }
    /**
     * Returns the tile at a given position
     * @param i the index
     * @return the tile
     */
    public Tile getTile(int i)
    {
        return tiles.get(i);
    }
    /**
     * Gets the direction of this word
     * @return an int representing the direction
     */
    public int getDirection()
    {
        return direction;
    }
    /**
     * Checks if this word is on the board
     * @param b the board
     * @return true or false
     */
    public boolean containsBoard(Board b)
    {
        for (Tile t: tiles)
        {
            if (b.getTile(t.getBoardPosition()) != null)
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Scores the word
     * @param board the board on which the word is played
     * @return the score
     */
    public int score(Board board)
    {
        int multiplier = 1;
        int score = 0;
        int letterMultiplier;
        Position p;
        boolean flag = false;
        int lettersPlayed = 0;
        for (Tile t: tiles)
        {
            p = t.getBoardPosition();
            letterMultiplier = 1;
            if (board.getTile(p) == null)
            {
                flag = true;
                lettersPlayed++;
                if (Constants.DLS.contains(p))
                {
                    letterMultiplier = 2;
                }
                else
                {
                    if (Constants.TLS.contains(p))
                    {
                        letterMultiplier = 3;
                    }
                    else
                    {
                        if (Constants.DWS.contains(p))
                        {
                            multiplier *= 2;
                        }
                        else
                        {
                            if (Constants.TWS.contains(p))
                            {
                                multiplier *= 3;
                            }
                        }
                    }
                }
            }
            score += t.getPoints() * letterMultiplier;
        }
        score *= multiplier;
        if (lettersPlayed == 7)
        {
            score += 50;
        }
        if (flag)
        {
            return score;
        }
        return 0;
    }
}