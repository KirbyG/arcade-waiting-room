import javax.swing.JFrame;
/**
 * The window in which the graphics of the game are drawn.
 * @author Kirby Gordon
 */
class Output extends JFrame
{
    private OutputHelper outputHelper;
    /**
     * Constructs a new window and adds input as a listener.
     * @param input the Input that will listen for events in this window
     */
    public Output(Input input)
    {
        outputHelper = new OutputHelper();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(outputHelper);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
        addMouseListener(input);
        addMouseMotionListener(input);
        addKeyListener(input);
    }
    /**
     * Updates the graphics of the game.
     * @param data the Data object containing the state of the game.
     */
    public void redraw(Data data)
    {
        outputHelper.redraw(data);
    }
}