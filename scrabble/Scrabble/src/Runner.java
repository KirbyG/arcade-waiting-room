import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Class that runs the actual program.
 * @author Kirby Gordon
 */
class Runner
{
    private static Data data = new Data();
    private static Input input = new Input();
    private static Output output = new Output(input);
    private static int tileAt;
    private static Position drop;
    private static final Position START = new Position(7, 7);
    private static boolean turn1 = true;
    private static boolean touching = false;
    private static ArrayList<Word> words = new ArrayList<Word>();
    private static int score = 0;
    private static int or = 0;
    /**
     * Main method that runs the game.
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {
        output.redraw(data);
        while (true)
        {
            Thread.sleep(0);
            if (input.getMouseDown())
            {
                tileAt = onTile();
                if (tileAt != 0)
                {
                    if (tileAt > 0)
                    {
                        data.getLimbo().addTile(data.getPRack().getTile(tileAt - 1));
                        data.getPRack().delete(tileAt - 1);
                    }
                    else
                    {
                        Tile temp = data.getLimbo().getTile((tileAt * -1) - 1);
                        data.getLimbo().delete((tileAt * -1) - 1);
                        data.getLimbo().addTile(temp);
                    }
                    while (input.getMouseDown())
                    {
                        data.getLimbo().getTile(data.getLimbo().getNumTiles() - 1).setPosition(new Position(input.getMousePosition().getX(), input.getMousePosition().getY()));
                        output.redraw(data);
                    }
                    drop = getGrid();
                    if (drop == null)
                    {
                        data.getPRack().addTile(data.getLimbo().getTile(data.getLimbo().getNumTiles() - 1));
                        data.getLimbo().delete(data.getLimbo().getNumTiles() - 1);
                    }
                    else
                    {
                        data.getLimbo().getTile(data.getLimbo().getNumTiles() - 1).setBoardPosition(drop);
                        data.getLimbo().getTile(data.getLimbo().getNumTiles() - 1).lock();
                    }
                    output.redraw(data);
                }
            }
            if (legalPlay())
            {
                doMove();
            }
        }
    }
    /**
     * Checks whether a play is allowed.
     * @return true or false
     */
    private static boolean legalPlay()
    {
        return input.getEnter() && placement() && valid();
    }
    /**
     * Determines if the placement of tiles is allowed.
     * @return true or false
     */
    private static boolean placement()
    {
        if (data.getLimbo().getNumTiles() == 0)
        {
            return false;
        }
        if (turn1)
        {
            return contiguous() && onStart();
        }
        return contiguous();
    }
    /**
     * Checks if a word is valid.
     * @return true or false
     */
    private static boolean valid()
    {
        words.clear();
        fillWords();
        boolean flag = false;
        for (Word w: words)
        {
            if (!data.getDictionary().lookUp(w.getWord()))
            {
                return false;
            }
            if (w.containsBoard(data.getBoard()) || turn1)
            {
                flag = true;
            }
        }
        if (flag)
        {
            return true;
        }
        return false;
    }
    /**
     * Fills in the words played.
     */
    private static void fillWords()
    {
        mainWord();
        crosswords();
    }
    /**
     * Builds the crosswords of the played word.
     */
    private static void crosswords()
    {
        for (int i = 0; i < data.getLimbo().getNumTiles(); i++)
        {
            buildWord(data.getLimbo().getTile(i).getBoardPosition(), or);
        }
    }
    /**
     * Builds the main word played.
     */
    private static void mainWord()
    {
        buildWord(data.getLimbo().getTile(0).getBoardPosition(), 1 - or);
    }
    /**
     * Builds a word at a position.
     * @param p the position of the word
     * @param which direction of the word
     */
    private static void buildWord(Position p, int which)
    {
        Word w = new Word();
        w.addTile(data.getLimbo().getTile(p));
        Position p2;
        for (int i = -1; i <= 1; i += 2)
        {
            p2 = new Position(p.getX(), p.getY());
            incposxory(p2, which, i);
            while (data.getBoard().getTile(p2) != null || data.getLimbo().getTile(p2) != null)
            {
                w.addTile(data.getBoard().getTile(p2), which, i);
                w.addTile(data.getLimbo().getTile(p2), which, i);
                incposxory(p2, which, i);
            }
        }
        if (w.getLength() != 1)
        {
            words.add(w);
        }
    }
    /**
     * Moves along the word in the x or y direction
     * @param p the start position
     * @param which the x or y direction
     * @param inc how far to move
     */
    private static void incposxory(Position p, int which, int inc)
    {
        if (which == 0)
        {
            p.setX(p.getX() + inc);
        }
        else
        {
            p.setY(p.getY() + inc);
        }
    }
    /**
     * Makes sure the first move is played on the start square
     * @return true or false
     */
    private static boolean onStart()
    {
        for (int i = 0; i < data.getLimbo().getNumTiles(); i++)
        {
            if (data.getLimbo().getTile(i).getBoardPosition().equals(START))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Determines whether or not the word is contiguous
     * @return true or false
     */
    private static boolean contiguous()
    {
        return onALine() && noGaps();
    }
    /**
     * Makes sure the word is all on the same line.
     * @return true or false
     */
    private static boolean onALine()
    {
        return checkAxis(0) || checkAxis(1);
    }
    /**
     * Checks along an axis given the direction to go
     * @param which direction to go
     * @return true or false
     */
    private static boolean checkAxis(int which)
    {
        if (data.getLimbo().getNumTiles() == 1)
        {
            return true;
        }
        for (int i = 1; i < data.getLimbo().getNumTiles(); i++)
        {
            if (data.getLimbo().getTile(i).getBoardPosition().getXorY(which) != data.getLimbo().getTile(i - 1).getBoardPosition().getXorY(which))
            {
                return false;
            }
        }
        or = which;
        return true;
    }
    /**
     * Checks to see if the word has gaps in it
     * @return true or false
     */
    private static boolean noGaps()
    {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int i = 0; i < data.getLimbo().getNumTiles(); i++)
        {
            vals.add(data.getLimbo().getTile(i).getBoardPosition().getXorY(or));
        }
        for (int i = 1; i < vals.size(); i++)
        {
            int diff = vals.get(i) - vals.get(i - 1);
            if (diff != 1)
            {
                //touching = true;
                for (int j = 1; j < diff; j++)
                {
                    //skip++;
                    if (or == 0)
                    {
                        if (data.getBoard().getTile(vals.get(i - 1) + j, data.getLimbo().getTile(0).getBoardPosition().getY()) == null)
                        {
                            return false;
                        }
                        //word.addTile(data.getBoard().getTile(vals[i - 1] + j, data.getLimbo().getTile(0).getBoardPosition().getY()));
                    }
                    else
                    {
                        if (data.getBoard().getTile(data.getLimbo().getTile(0).getBoardPosition().getX(), vals.get(i - 1) + j) == null)
                        {
                            return false;
                        }
                        //word.addTileStart(data.getBoard().getTile(data.getLimbo().getTile(0).getBoardPosition().getX(), vals[i - 1] + j));
                    }
                }
            }
            /*if (or == 0)
            {
                word.addTile(data.getLimbo().getTile(unsorted.indexOf(sorted.get(0) + i + skip)));
            }
            else
            {
                word.addTileStart(data.getLimbo().getTile(unsorted.indexOf(sorted.get(0) + i + skip)));
            }*/
        }
        return true;
    }
    /**
     * Lets a player make a move.
     * @throws InterruptedException 
     */
    private static void doMove() throws InterruptedException
    {
        score = 0;
        for (Word w: words)
        {
            score += w.score(data.getBoard());
        }
        data.updateScore(score);
        words.clear();
        data.getPRack().draw(data.getBag());
        output.redraw(data);
        data.getBoard().add(data.getLimbo());
        data.getLimbo().clear();
        turn1 = false;
        Thread.sleep(500);
        data.setMode(true);
        output.redraw(data);
        if (data.getPRack().getNumTiles() == 0)
        {
            while (true) {}
        }
        while (!input.getEnter())
        {
            Thread.sleep(0);
        }
        data.setMode(false);
        data.nextTurn();
        output.redraw(data);
    }
    /**
     * Checks if the mouse is on a tile.
     * @return which tile the mouse is on
     * @throws InterruptedException 
     */
    private static int onTile() throws InterruptedException
    {
        int rackSize = data.getPRack().getNumTiles();
        int limboSize = data.getLimbo().getNumTiles();
        Position t;
        for (int i = 0; i < rackSize; i++)
        {
            t = data.getPRack().getTile(i).getPosition();
            if (checkPos(t))
            {
                return i + 1;
            }
        }
        for (int i= 0; i < limboSize; i++)
        {
            t = data.getLimbo().getTile(i).getPosition();
            if (checkPos(t))
            {
                return (i + 1) * -1;
            }
        }
        return 0;
    }
    /**
     * Checks if a tile to be placed overlaps an already played tile
     * @param t the position of the tile
     * @return true or false
     */
    private static boolean overlap(Position t)
    {
        for (int i = 0; i < data.getLimbo().getNumTiles() - 1; i++)
        {
            if (data.getLimbo().getTile(i).getBoardPosition().equals(t))
            {
                return true;
            }
        }
        if (data.getBoard().getTile(t) != null)
        {
            return true;
        }
        return false;
    }
    /**
     * Gets the position on the grid
     * @return grid position
     */
    private static Position getGrid()
    {
        Position m = new Position(input.getMousePosition().getX(), input.getMousePosition().getY());
        if (m.getX() > Constants.BOARDPOSITION.getX() - Constants.BOARDWIDTH / 2 + Constants.TILEBORDERTHICKNESS / 2 && m.getX() < Constants.BOARDPOSITION.getX() + Constants.BOARDWIDTH / 2 - Constants.TILEBORDERTHICKNESS / 2 && m.getY() > Constants.BOARDPOSITION.getY() - Constants.BOARDHEIGHT / 2 + Constants.TILEBORDERTHICKNESS / 2 && m.getY() < Constants.BOARDPOSITION.getY() + Constants.BOARDHEIGHT / 2 - Constants.TILEBORDERTHICKNESS / 2)
        {
            m.setX(m.getX() - ((Constants.TRUEWIDTH - Constants.BOARDWIDTH) / 2 + Constants.TILEBORDERTHICKNESS / 2));
            m.setY(m.getY() - (Constants.BOARDPOSITION.getY() - Constants.BOARDHEIGHT / 2 + Constants.TILEBORDERTHICKNESS / 2));
            m.setX((int)((double)m.getX() / (double)(Constants.BOARDWIDTH - Constants.TILEBORDERTHICKNESS) * 15));
            m.setY((int)((double)m.getY() / (double)(Constants.BOARDHEIGHT - Constants.TILEBORDERTHICKNESS) * 15));
            if (m.getX() == 15)
            {
                m.setX(14);
            }
            if (m.getY() == 15)
            {
                m.setY(14);
            }
            if (overlap(m))
            {
                return null;
            }
            return m;
        }
        return null;
    }
    /**
     * Checks that the position is within a tile
     * @param t position
     * @return true or false
     * @throws InterruptedException 
     */
    public static boolean checkPos(Position t) throws InterruptedException
    {
        Thread.sleep(0);
        Position m = input.getMousePosition();
        int mx = m.getX();
        int my = m.getY();
        int tx = t.getX();
        int ty = t.getY();
        if (mx > tx - Constants.TILEWIDTH / 2 && mx < tx + Constants.TILEWIDTH / 2 && my > ty - Constants.TILEHEIGHT / 2 && my < ty + Constants.TILEHEIGHT / 2)
        {
            return true;
        }
        return false;
    }
    /*private static boolean crosswords()
    {
        for (int i = 0; i < word.getLength(); i++)
        {
            Tile cur = word.getTile(i);
            int dir = word.getDirection();
            Word wor = new Word();
            Position pos = new Position(cur.getBoardPosition().getX(), cur.getBoardPosition().getY());
            wor.addTile(word.getTile(i));
            if (dir == 0)
            {
                pos = new Position(cur.getBoardPosition().getX(), cur.getBoardPosition().getY() - 1);
                while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
                {
                    wor.addTile(data.getBoard().getTile(pos.getX(), pos.getY()));
                    pos.setY(pos.getY() - 1);
                }
                pos = new Position(cur.getBoardPosition().getX(), cur.getBoardPosition().getY() + 1);
                while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
                {
                    wor.addTileStart(data.getBoard().getTile(pos.getX(), pos.getY()));
                    pos.setY(pos.getY() + 1);
                }
            }
            else
            {
                pos = new Position(cur.getBoardPosition().getX() - 1, cur.getBoardPosition().getY());
                while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
                {
                    wor.addTileStart(data.getBoard().getTile(pos.getX(), pos.getY()));
                    pos.setX(pos.getX() - 1);
                }
                pos = new Position(cur.getBoardPosition().getX() + 1, cur.getBoardPosition().getY());
                while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
                {
                    wor.addTile(data.getBoard().getTile(pos.getX(), pos.getY()));
                    pos.setX(pos.getX() + 1);
                }
            }
            if (wor.getLength() > 1)
            {
                touching = true;
                if (!data.getDictionary().lookUp(wor.getWord()))
                {
                    return false;
                }
                words.add(wor);
            }
        }
        return true;
    }
    private static boolean onStart()
    {
        if (!turn1)
        {
            return true;
        }
        touching = true;
        for (int i = 0; i < data.getLimbo().getNumTiles(); i++)
        {
            if (data.getLimbo().getTile(i).getBoardPosition().equals(START))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean ascending(int or)
    {
        Integer[] vals = new Integer[data.getLimbo().getNumTiles()];
        for (int i = 0; i < data.getLimbo().getNumTiles(); i++)
        {
            vals[i] = data.getLimbo().getTile(i).getBoardPosition().getXorY(or);
        }
        List<Integer> unsorted = Arrays.asList(vals.clone());
        Arrays.sort(vals);
        List<Integer> sorted = Arrays.asList(vals);
        word.clear();
        word.setDirection(or);
        word.addTile(data.getLimbo().getTile(unsorted.indexOf(sorted.get(0))));
        int skip = 0;
        for (int i = 1; i < vals.length; i++)
        {
            int diff = vals[i] - vals[i - 1];
            if (diff == 1)
            {
                
            }
            else
            {
                touching = true;
                for (int j = 1; j < diff; j++)
                {
                    skip++;
                    if (or == 0)
                    {
                        if (data.getBoard().getTile(vals[i - 1] + j, data.getLimbo().getTile(0).getBoardPosition().getY()) == null)
                        {
                            return false;
                        }
                        word.addTile(data.getBoard().getTile(vals[i - 1] + j, data.getLimbo().getTile(0).getBoardPosition().getY()));
                    }
                    else
                    {
                        if (data.getBoard().getTile(data.getLimbo().getTile(0).getBoardPosition().getX(), vals[i - 1] + j) == null)
                        {
                            return false;
                        }
                        word.addTileStart(data.getBoard().getTile(data.getLimbo().getTile(0).getBoardPosition().getX(), vals[i - 1] + j));
                    }
                }
                
            }
            if (or == 0)
            {
                word.addTile(data.getLimbo().getTile(unsorted.indexOf(sorted.get(0) + i + skip)));
                //word += Character.toString(data.getLimbo().getTile(unsorted.indexOf(sorted.get(0) + i + skip)).getLetter());
            }
            else
            {
                word.addTileStart(data.getLimbo().getTile(unsorted.indexOf(sorted.get(0) + i + skip)));
            }  
        }
        if (or == 0)
        {
            Position pos = new Position(sorted.get(0) - 1, data.getLimbo().getTile(0).getBoardPosition().getY());
            while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
            {
                word.addTileStart(data.getBoard().getTile(pos.getX(), pos.getY()));
                pos.setX(pos.getX() - 1);
            }
            pos = new Position(sorted.get(sorted.size() - 1) + 1, data.getLimbo().getTile(0).getBoardPosition().getY());
            while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
            {
                word.addTile(data.getBoard().getTile(pos.getX(), pos.getY()));
                pos.setX(pos.getX() + 1);
            }
        }
        else
        {
            Position pos = new Position(data.getLimbo().getTile(0).getBoardPosition().getX(), sorted.get(0) - 1);
            while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
            {
                word.addTile(data.getBoard().getTile(pos.getX(), pos.getY()));
                pos.setY(pos.getY() - 1);
            }
            pos = new Position(data.getLimbo().getTile(0).getBoardPosition().getX(), sorted.get(sorted.size() - 1) + 1);
            while (data.getBoard().getTile(pos.getX(), pos.getY()) != null)
            {
                word.addTileStart(data.getBoard().getTile(pos.getX(), pos.getY()));
                pos.setY(pos.getY() + 1);
            }  
        }
        return true;
    }
    private static int oneSame()
    {
        if (data.getLimbo().getNumTiles() == 1)
        {
            return 0;
        }
        boolean indicator;
        for (int which = 0; which < 2; which++)
        {
            indicator = false;
            for (int i = 1; i < data.getLimbo().getNumTiles(); i++)
            {
                if (data.getLimbo().getTile(i).getBoardPosition().getXorY(which) != data.getLimbo().getTile(i - 1).getBoardPosition().getXorY(which))
                {
                    i = data.getLimbo().getNumTiles();
                    indicator = true;
                }
            }
            if (indicator)
            {
                return which;
            } 
        }
        return -1;
    }
    private static boolean contiguous()
    {
        int result = oneSame();
        if (result == -1)
        {
            return false;
        }
        return ascending(result);
    }
    
    */
}