import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * Listens for user input and stores the results.
 * @author Kirby Gordon
 */
class Input implements MouseListener, MouseMotionListener, KeyListener
{
    private boolean mouse;
    private boolean enter;
    private Position position = new Position(-1, -1);
    /**
     * Updates the state of enter when the enter key is released.
     * @param e 
     */
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            enter = false;
        }
    }
    /**
     * Updates the state of enter when the enter key is pressed.
     * @param e 
     */
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            enter = true;
        }
    }
    public void keyTyped(KeyEvent e) {}
    /**
     * The current state of the mouse.
     * @return true=pressed, false=not pressed
     */
    public boolean getMouseDown()
    {
        return mouse;
    }
    /**
     * Returns where the mouse is
     * @return the Position of the mouse
     */
    public Position getMousePosition()
    {
        return position;
    }
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    /**
     * Updates mouse when the left mouse button is pressed.
     * @param e 
     */
    public void mousePressed(MouseEvent e)
    {
        mouse = true;
    }
    /**
     * Updates mouse when the left mouse button is released.
     * @param e 
     */
    public void mouseReleased(MouseEvent e)
    {
        mouse = false;
    }
    /**
     * Updates the position of the mouse when the mouse moves.
     * @param e 
     */
    public void mouseMoved(MouseEvent e)
    {
        updatePosition(e);
    }
    /**
     * Updates the position of the mouse when the mouse is dragged.
     * @param e 
     */
    public void mouseDragged(MouseEvent e)
    {
        updatePosition(e);
    }
    /**
     * Changes the x and y of position when the mouse moves.
     * @param e 
     */
    private void updatePosition(MouseEvent e)
    {
        position.setXY(e.getX(), e.getY());
        position.input();
    }
    /**
     * Whether the enter key is pressed or not.
     * @return true=pressed, false=not pressed
     */
    public boolean getEnter()
    {
        return enter;
    }
}