
import javafx.scene.Node;
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * superclass of all game elements
 * @author gordo_000
 */
public abstract class Element<T extends Shape> {    
    private Vector position;
    
    /**
     * creates an element at the specified position
     * @param position 
     */
    public Element(Vector position) {
        setPosition(position);
    }
    
    /**
     * returns the node to draw for this element
     * @return 
     */
    public Node getGraphics() {
        updateGraphics();
        
        return getShape();
    }
    
    /**
     * returns the shape that this element contains
     * @return 
     */
    public abstract T getShape();
    
    /**
     * returns the position of this element
     * @return 
     */
    public Vector getPosition() {
        return position;
    }
    
    /**
     * sets the position of this element
     * @param position 
     */
    public void setPosition(Vector position) {
        this.position = new Vector(position.x, position.y);
    }
    
    /**
     * updates the graphics of this element
     */
    public abstract void updateGraphics();
}
