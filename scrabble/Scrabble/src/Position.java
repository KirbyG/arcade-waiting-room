/**
 * A custom vector class.
 * @author Kirby Gordon
 */
class Position
{
    private int x;
    private int y;
    private static final int X = 0;
    /**
     * Creates a vector with the given components
     * @param x the x component
     * @param y the y component
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * Sets the x and y coordinates of the vector.
     * @param x
     * @param y 
     */
    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * Gets the x coordinate of the vector
     * @return x coordinate
     */
    public int getX()
    {
        return x;
    }
     /**
     * Gets the y coordinate of the vector
     * @return y coordinate
     */
    public int getY()
    {
        return y;
    }
    /**
     * Converts from input to working coordinate spaces
     */
    public void input()
    {
        x -= Constants.WIDTHMARGIN;
        y = Constants.HEIGHT - Constants.HEIGHTMARGIN - y;
    }
    /**
     * Converts from working to device coordinate spaces
     * @return 
     */
    public Position output()
    {
        return new Position(x + Constants.WIDTHMARGIN - 8, Constants.TRUEHEIGHT - 8 + Constants.HEIGHTMARGIN - y);
    }
    /**
     * Overrides the equals function for vectors
     * @param p the second vector
     * @return 
     */
    public boolean equals(Object p)
    {
        
        if (((Position)p).getX() == x && ((Position)p).getY() == y)
        {
            return true;
        }
        return false;
    }
    /**
     * Sets the x coordinate of the vector
     * @param x the new x coordinate
     */
    public void setX(int x)
    {
        this.x = x;
    }
    /**
     * Sets the y coordinate of the vector
     * @param y the new y coordinate
     */
    public void setY(int y)
    {
        this.y = y;
    }
    /**
     * Gets x or y depending on the input value
     * @param i 0<->x, 1<->y
     * @return 
     */
    public int getXorY(int i)
    {
        if (i == X)
        {
            return x;
        }
        return y;
    }
}