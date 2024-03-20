import java.awt.*;
import javax.swing.JPanel;
class OutputHelper extends JPanel
{
    Data data;
    public OutputHelper()
    {
        super();
        setBackground(c.BACKGROUNDCOLOR);
    }
    public void redraw(Data data)
    {
        this.data = data;
        repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        rect(g, c.w / 2 - c.fact * data.getDifficulty(), c.h / 2, c.pw, c.rh * data.getDifficulty() + 100);
        rect(g, c.w / 2, c.h / 2, c.pw, c.rh * data.getDifficulty() + 100);
        rect(g, c.w / 2 + c.fact * data.getDifficulty(), c.h / 2, c.pw, c.rh * data.getDifficulty() + 100);
        rect(g, c.w / 2, c.h / 2 + (c.rh * data.getDifficulty() + 100) / 2 + c.pw / 2, 2 * c.fact * data.getDifficulty() + c.pw + c.fact * data.getDifficulty(), c.pw);
        for (int i = 0; i < 4; i++)
        {
            g.setColor(Color.GRAY);
            button(g, data.getButtons()[i]);
        }
        if (data.solve())
        {
            g.setColor(Color.RED);
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < data.rings()[i].stack().size(); j++)
                {
                    rect(g, c.w / 2 + ((c.fact * data.getDifficulty()) * (i - 1)), c.h / 2 + (c.rh * data.getDifficulty() + 100) / 2 - c.rh / 2 - c.rh * j, c.fact * data.rings()[i].stack().get(j), c.rh);
                }
            }
        }
    }
    private void button(Graphics g, Button b)
    {
        rect(g, b.x(), b.y(), b.w(), b.h());
        g.setColor(Color.BLUE);
        g.setFont(c.font);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(b.txt(), b.x() - (fm.stringWidth(b.txt()) / 2), b.y() + (fm.getAscent() / 2) - fm.getDescent() / 2);
    }
    private void rect(Graphics g, int x, int y, int w, int h)
    {
        g.fillRect(x - w / 2, y - h / 2, w, h);
    }
}