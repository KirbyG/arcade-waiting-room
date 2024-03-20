import java.awt.Color;
import java.util.List;
import java.util.Arrays;
import java.awt.Font;
/**
 * Publicly accessible constants defining the appearance of the game.
 * @author Kirby Gordon
 */
public final class Constants
{
    public static final Font SCOREFONT = new Font("Roman", Font.BOLD, 50);
    public static final int WIDTH = 1366;    
    public static final int HEIGHT = 768 - 40;
    public static final int WIDTHMARGIN = 8;    
    public static final int HEIGHTMARGIN = 9;
    public static final int TRUEWIDTH = WIDTH - WIDTHMARGIN - 9;
    public static final int TRUEHEIGHT = HEIGHT - HEIGHTMARGIN - 31;
    public static final int XCENTER = TRUEWIDTH / 2;
    public static final int YCENTER = TRUEHEIGHT / 2;
    public static final Position BOARDPOSITION = new Position(XCENTER, 400);
    public static final Position RACKPOSITION = new Position(XCENTER, 40);
    public static final Position SCOREPOSITION = new Position(100, 700);
    public static final int TILEWIDTH = 30;
    public static final int TILEHEIGHT = 35;
    public static final int TILEBORDERTHICKNESS = 3;
    public static final Color BACKGROUNDCOLOR = new Color(161, 24, 24);
    public static final Color BOARDBACKGROUNDCOLOR = Color.WHITE;
    public static final Color RACKBACKGROUNDCOLOR = Color.BLACK;
    public static final Color SPACECOLOR = Color.GRAY;
    public static final Color DLSCOLOR = Color.BLUE;
    public static final Color TLSCOLOR = Color.GREEN;
    public static final Color DWSCOLOR = Color.RED;
    public static final Color TWSCOLOR = new Color(255, 100, 30);
    public static final Color TILECOLOR = new Color(200, 200, 100);
    public static final int BOARDWIDTH = Constants.TILEWIDTH * 15 + Constants.TILEBORDERTHICKNESS * 16;
    public static final int BOARDHEIGHT = Constants.TILEHEIGHT * 15 + Constants.TILEBORDERTHICKNESS * 16;
    private static final Position[] DLSTEMP = {new Position(3, 0), new Position(11, 0), new Position(6, 2), new Position(8, 2), new Position(0, 3), new Position(7, 3), new Position(14, 3), new Position(2, 6), new Position(6, 6), new Position(8, 6), new Position(12, 6), new Position(3, 7), new Position(11, 7), new Position(2, 8), new Position(6, 8), new Position(8, 8), new Position(12, 8), new Position(0, 11), new Position(7, 11), new Position(14, 11), new Position(6, 12), new Position(8, 12), new Position(3, 14), new Position(11, 14)};
    public static final List<Position> DLS = Arrays.asList(DLSTEMP);
    private static final Position[] TLSTEMP = {new Position(5, 1), new Position(9, 1), new Position(1, 5), new Position(5, 5), new Position(9, 5), new Position(13, 5), new Position(1, 9), new Position(5, 9), new Position(9, 9), new Position(13, 9), new Position(5, 13), new Position(9, 13)};
    public static final List<Position> TLS = Arrays.asList(TLSTEMP);
    private static final Position[] DWSTEMP = {new Position(7, 7), new Position(1, 1), new Position(2, 2), new Position(3, 3), new Position(4, 4), new Position(10, 10), new Position(11, 11), new Position(12, 12), new Position(13, 13), new Position(13, 1), new Position(12, 2), new Position(11, 3), new Position(10, 4), new Position(4, 10), new Position(3, 11), new Position(2, 12), new Position(1, 13)};
    public static final List<Position> DWS = Arrays.asList(DWSTEMP);
    private static final Position[] TWSTEMP = {new Position(0, 0), new Position(7, 0), new Position(14, 0), new Position(0, 7), new Position(14, 7), new Position(0, 14), new Position(7, 14), new Position(14, 14)};
    public static final List<Position> TWS = Arrays.asList(TWSTEMP);
    /**
     * Generates a random integer in the given range.
     * @param min the inclusive minimum of the range
     * @param max the inclusive maximum of the range
     * @return the random integer
     */
    public static int random(int min, int max)
    {
        return (int)(Math.random()*(max - min + 1) + min);
    }
    public static final Font TILEFONT = new Font("Roman", Font.BOLD, 25);
    public static final Font NUMBERFONT = new Font("Roman", Font.BOLD, 10);
}