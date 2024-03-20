import javax.swing.JFrame;
class Output extends JFrame
{
    private OutputHelper outputHelper;
    public Output(Input input)
    {
        outputHelper = new OutputHelper();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(outputHelper);
        setSize(c.w, c.h);
        setVisible(true);
        outputHelper.addMouseListener(input);
        outputHelper.addMouseMotionListener(input);
        outputHelper.addKeyListener(input);
    }
    public void redraw(Data data)
    {
        outputHelper.redraw(data);
    }
}