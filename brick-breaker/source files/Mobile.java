
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Superclass for all classes that will move
 * @author gordo_000
 */
public abstract class Mobile<T extends Shape> extends Element<T> {
    private Vector velocity;
    
    /**
     * constructs a new mobile using a position and a velocity
     * @param position
     * @param velocity 
     */
    public Mobile(Vector position, Vector velocity) {
        super(position);
        this.velocity = new Vector(velocity.x, velocity.y);
    }
    
    /**
     * constructs a new mobile using a position vector, an angle, and a speed
     * @param position
     * @param angle
     * @param speed 
     */
    public Mobile(Vector position, double angle, double speed) {
        super(position);
        velocity = new Vector();
        velocity.setAngleLength(angle, speed);
    }
    
    //public abstract ArrayList<Collision> getCollisions(ArrayList<ArrayList<Element>> elements);
    
    /**
     * returns the speed of the mobile
     * @return 
     */
    public double getSpeed() {
        return velocity.length();
    }
    
    /**
     * returns the velocity vector of the mobile
     * @return 
     */
    public Vector getVelocity() {
        return velocity;
    }
    
    /**
     * sets the velocity
     * @param velocity 
     */
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    
    /**
     * changes the position by the velocity, split up into increments based
     * on the number of ticks per frame
     * @param ticks 
     */
    public void move(int ticks) {
        Vector delta = velocity.multiply(1.0 / ticks);
        setPosition(getPosition().add(delta));
    }
}
