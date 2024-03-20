import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
/**
 * The panel within Output on which graphics are drawn.
 * @author Kirby Gordon
 */
class OutputHelper extends JPanel
{
    private static final Position s1pos = new Position(0, Constants.TRUEHEIGHT - 50);
    private static final Position s2pos = new Position(Constants.TRUEWIDTH - 100, Constants.TRUEHEIGHT - 50);
    Data data;
    /**
     * Creates a new Panel with the proper background color.
     */
    public OutputHelper()
    {
        super();
        setBackground(Constants.BACKGROUNDCOLOR);
    }
    /**
     * Updates the graphics of the panel
     * @param data the current state of the game
     */
    public void redraw(Data data)
    {
        this.data = data;
        repaint();
    }
    /**
     * Draws every component of the game.
     * @param g 
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Constants.RACKBACKGROUNDCOLOR);
        drawRect(g, new Position(100, 50), 2, 2);
        drawRect(g, Constants.RACKPOSITION, Constants.TILEWIDTH * 9 + Constants.TILEBORDERTHICKNESS * 6, Constants.TILEHEIGHT + 2 * Constants.TILEBORDERTHICKNESS);
        g.setColor(Constants.BOARDBACKGROUNDCOLOR);
        drawRect(g, Constants.BOARDPOSITION, Constants.BOARDWIDTH, Constants.BOARDHEIGHT);
        //tile slots
        for (int y = 0; y < 15; y++)
        {
            for (int x = 0; x < 15; x++)
            {
                g.setColor(getColor(x, y));
                drawRect(g, new Position((Constants.TILEWIDTH + Constants.TILEBORDERTHICKNESS) * x + Constants.BOARDPOSITION.getX() - Constants.BOARDWIDTH / 2 + Constants.TILEWIDTH / 2 + Constants.TILEBORDERTHICKNESS, ((Constants.TILEHEIGHT + Constants.TILEBORDERTHICKNESS) * y) + Constants.BOARDPOSITION.getY() - (Constants.BOARDHEIGHT / 2) + (Constants.TILEHEIGHT / 2) + Constants.TILEBORDERTHICKNESS), Constants.TILEWIDTH, Constants.TILEHEIGHT);
            }
        }
        //player's rack
        for (int pos = 0; pos < data.getPRack().getNumTiles(); pos++)
        {
            g.setColor(Constants.TILECOLOR);
            Position p = data.getPRack().getTile(pos).getPosition();
            drawRect(g, p, Constants.TILEWIDTH, Constants.TILEHEIGHT);
            drawLetter(g, p, data.getPRack().getTile(pos).getLetter());
            drawNumber(g, p, data.getPRack().getTile(pos).getPoints());
        }
        //tiles on board
        for (int y = 0; y < 15; y++)
        {
            for (int x = 0; x < 15; x++)
            {
                if (data.getBoard().getTile(x, y) != null)
                {
                    g.setColor(Constants.TILECOLOR);
                    Position p = data.getBoard().getTile(x, y).getPosition();
                    drawRect(g, p, Constants.TILEWIDTH, Constants.TILEHEIGHT);
                    drawLetter(g, p, data.getBoard().getTile(x, y).getLetter());
                    drawNumber(g, p, data.getBoard().getTile(x, y).getPoints());
                }
            }
        }
        //tiles in limbo
        for (int pos = 0; pos < data.getLimbo().getNumTiles(); pos++)
        {
            g.setColor(Constants.TILECOLOR);
            Position p = data.getLimbo().getTile(pos).getPosition();
            drawRect(g, p, Constants.TILEWIDTH, Constants.TILEHEIGHT);
            drawLetter(g, p, data.getLimbo().getTile(pos).getLetter());
            drawNumber(g, p, data.getLimbo().getTile(pos).getPoints());
        }
        //cover screen between turns
        if (data.getMode())
        {
            g.setColor(Color.BLACK);
            drawRect(g, new Position(Constants.XCENTER, Constants.YCENTER), Constants.TRUEWIDTH, Constants.TRUEHEIGHT);
        }
        //score
        drawNumber(g, false, data);
        drawNumber(g, true, data);
    }
    /**
     * Draws the score
     * @param g
     * @param turn which player
     * @param d current game state
     */
    private void drawNumber(Graphics g, boolean turn, Data d)
    {
        g.setColor(Color.WHITE);
        g.setFont(Constants.SCOREFONT);
        Position p;
        if (turn)
        {
            p = s2pos;
        }
        else
        {
            p = s1pos;
        }
        Position newCenter = p.output();
        g.drawString(d.getScore(turn) + "", newCenter.getX(), newCenter.getY());
    }
    /**
     * draw point value
     * @param g 
     * @param p tile location
     * @param n point value 1-10
     */
    private void drawNumber(Graphics g, Position p, int n)
    {
        g.setColor(Color.BLACK);
        g.setFont(Constants.NUMBERFONT);
        Position newCenter = p.output();
        if (n == 10)
        {
            g.drawString(n + "", newCenter.getX() + 5, newCenter.getY() + 16);
        }
        else
        {
            g.drawString(n + "", newCenter.getX() + 10, newCenter.getY() + 16);
        }
    }
    /**
     * Get the color of a square
     * @param x square x position
     * @param y square y position
     * @return the color of the tile
     */
    private Color getColor(int x, int y)
    {
        if (Constants.DLS.contains(new Position(x, y)))
        {
            return Constants.DLSCOLOR;
        }
        else
        {
            if (Constants.TLS.contains(new Position(x, y)))
            {
                return Constants.TLSCOLOR;
            }
            else
            {
                if (Constants.DWS.contains(new Position(x, y)))
                {
                    return Constants.DWSCOLOR;
                }
                else
                {
                    if (Constants.TWS.contains(new Position(x, y)))
                    {
                        return Constants.TWSCOLOR;
                    }
                    return Constants.SPACECOLOR;
                }
            }
        }
    }
    /**
     * Draws a rectangle given a center and a width and height
     * @param g
     * @param center the center of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    private void drawRect(Graphics g, Position center, int width, int height)
    {
        Position newCenter = center.output();
        g.fillRect(newCenter.getX() - width / 2, newCenter.getY() - height / 2, width, height);
    }
    /**
     * Draws a letter given the center of the letter
     * @param g
     * @param center the center position of the letter
     * @param letter the character to be drawn
     */
    private void drawLetter(Graphics g, Position center, char letter)
    {
        g.setColor(Color.BLACK);
        g.setFont(Constants.TILEFONT);
        Position newCenter = center.output();
        g.drawString(Character.toString(letter).toUpperCase(), newCenter.getX() - 10, newCenter.getY() + 10);
    }
}