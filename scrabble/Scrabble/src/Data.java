/**
 * A collection of fields holding the current state of the game.
 * @author Kirby Gordon
 */
class Data
{
    private Bag bag;
    private Rack pRack;
    private Rack cRack;
    private TileCollection limbo;
    private Dictionary dictionary;
    private boolean turn;
    private Board board;
    private boolean mode;
    //score
    private int s1;
    private int s2;
    /**
     * Constructs a new game.
     */
    public Data()
    {
        bag = new Bag();
        pRack = new Rack(bag);
        cRack = new Rack(bag);
        limbo = new TileCollection();
        dictionary = new Dictionary();
        turn = false;
        board = new Board();
        mode = false;
        //score
        s1 = 0;
        s2 = 0;
    }
    /**
     * Increases the current player's score by the given value.
     * @param increment the amount by which to increase the score
     */
    public void updateScore(int increment)
    {
        if (turn)
        {
            s2 += increment;
        }
        else
        {
            s1 += increment;
        }
    }
    /**
     * Returns the score of the specified player
     * @param b true=player2, false=player1
     * @return the score of specified player
     */
    public int getScore(boolean b)
    {
        if (b)
        {
            return s2;
        }
        return s1;
    }
    /**
     * Updates the current game mode
     * @param mode the new game mode
     */
    public void setMode(boolean mode)
    {
        this.mode = mode;
    }
    /**
     * Returns the current game mode/
     * @return the current game mode
     */
    public boolean getMode()
    {
        return mode;
    }
    /**
     * Returns the Rack of the current player.
     * @return the Rack of the current player
     */
    public Rack getPRack()
    {
        if (turn)
        {
            return cRack;
        }
        return pRack;
    }
    /**
     * Returns the tiles that a player has placed on the board but not yet
     * player
     * @return the TileCollection limbo
     */
    public TileCollection getLimbo()
    {
        return limbo;
    }
    /**
     * Returns a Dictionary object containing every valid Scrabble word.
     * @return the dictionary
     */
    public Dictionary getDictionary()
    {
        return dictionary;
    }
    /**
     * Updates the current turn.
     */
    public void nextTurn()
    {
        if (turn)
        {
            turn = false;
        }
        else
        {
            turn = true;
        }
    }
    /**
     * Get the bag of remaining tiles.
     * @return the bag
     */
    public Bag getBag()
    {
        return bag;
    }
    /**
     * Get the current game board
     * @return the current game board
     */
    public Board getBoard()
    {
        return board;
    }
    /**
     * A boolean representing the current turn.
     * @return true=player2, false=player1
     */
    public boolean getTurn()
    {
        return turn;
    }
}