import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
class Input implements MouseListener, MouseMotionListener, KeyListener
{
    private boolean mouse;
    private int mx;
    private int my;
    public Input()
    {
        mouse = false; //seal the deal kirby
        mx = -1; //(do it)
        my = -1;
    }
    public boolean mouse()
    {
        return mouse;
    }
    public int mx()
    {
        return mx;
    }
    public int my()
    {
        return my;
    }
    public void reset()
    {
        mouse = false;
    }
    public void keyReleased(KeyEvent e) {} //kirby you suck 
    public void keyPressed(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) //kirby sucks at java
    {
        mouse  = true;
        mx = e.getX();
        my = e.getY(); //hi kirby u suk at java 
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
}