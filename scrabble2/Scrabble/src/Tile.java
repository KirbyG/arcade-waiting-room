import java.util.ArrayList;
/**
 * Represents a game tile.
 * @author Kirby Gordon
 */
class Tile
{
    private char letter;
    private int points;
    private static int[] pointValues = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private Position position;
    private Position boardPosition;
    /**
     * Creates a new tile with the given character
     * @param letter the character
     */
    public Tile(char letter)
    {
        this();
        this.letter = letter;
        points = pointValues[char2int(letter)];
    }
    /**
     * Creates a tile with a letter represented by an int
     * @param letter the letter type
     */
    public Tile(int letter)
    {
        this();
        this.letter = int2char(letter);
        points = pointValues[letter];
    }
    /**
     * Creates a blank new tile
     */
    public Tile()
    {
        position = new Position(-1, -1);
        boardPosition = new Position(-1, -1);
    }
    /**
     * Converts an integer to a character
     * @param letter the integer
     * @return the letter
     */
    private char int2char(int letter)
    {
        return Character.toChars(97 + letter)[0];
    }
    /**
     * Converts a character to an integer
     * @param letter the character
     * @return the integer
     */
    private int char2int(char letter)
    {
        return Character.compare(letter, '`');
    }
    /**
     * Returns the letter of this tile
     * @return 
     */
    public char getLetter()
    {
        return letter;
    }
    /**
     * Sets the position of the tile.
     * @param p the new position
     */
    public void setPosition(Position p)
    {
        position = p;
    }
    /**
     * Returns the position of the tile
     * @return position of the tile
     */
    public Position getPosition()
    {
        return position;
    }
    /**
     * Returns the point value of the tile
     * @return point value
     */
    public int getPoints()
    {
        return points;
    }
    /**
     * Sets the board position of the tile
     * @param p the new board position
     */
    public void setBoardPosition(Position p)
    {
        boardPosition = new Position(p.getX(), p.getY());
    }
    /**
     * Gets the board position of the tile
     * @return the board position
     */
    public Position getBoardPosition()
    {
        return boardPosition;
    }
    /**
     * Locks the tile into its board position
     */
    public void lock()
    {
        position = toRealPos(boardPosition);
    }
    /**
     * Converts a board position to an actual position
     * @param p the board position
     * @return the actual position
     */
    private Position toRealPos(Position p)
    {
        Position np = new Position(p.getX(), p.getY());
        np.setX(np.getX() * (Constants.TILEWIDTH + Constants.TILEBORDERTHICKNESS));
        np.setY(np.getY() * (Constants.TILEHEIGHT + Constants.TILEBORDERTHICKNESS));
        np.setX(np.getX() + (Constants.TRUEWIDTH - Constants.BOARDWIDTH) / 2 + Constants.TILEBORDERTHICKNESS + Constants.TILEWIDTH / 2);
        np.setY(np.getY() + (Constants.BOARDPOSITION.getY() - Constants.BOARDHEIGHT / 2 + Constants.TILEBORDERTHICKNESS + Constants.TILEHEIGHT / 2));
        return np;
    }
    /**
     * Clones this tile
     * @return the cloned tile
     */
    public Tile clone()
    {
        Tile clone = new Tile(letter);
        clone.setBoardPosition(boardPosition);
        clone.lock();
        return clone;
    }
}