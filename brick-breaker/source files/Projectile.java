
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The projectile that the player can shoot at the bricks.
 * @author gordo_000
 */
public class Projectile extends Mobile<Triangle> {
    private static final Vector startVel = new Vector(0, -5);
    
    private Triangle triangle;
    private double width = 20;
    
    /**
     * constructs a new projectile at the given position
     * @param p 
     */
    public Projectile(Vector p) {
        super(p, startVel);
        triangle = new Triangle(width / 2, 0, 0, -25, -width / 2, 0);
        triangle.setFill(Color.YELLOW);
    }
    
    /**
     * returns the triangle that is this projectile's shape
     * @return 
     */
    @Override
    public Triangle getShape() {
        return triangle;
    }
    
    /**
     * updates this projectile's triangle graphics
     */
    @Override
    public void updateGraphics() {
        if (Game.pup[Game.FLIP]) {
            setVelocity(startVel.multiply(-1));
            triangle.getPoints().set(3, 25d);
        } else {
            setVelocity(startVel);
            triangle.getPoints().set(3, -25d);
        }
        
        triangle.translateXProperty().set(getPosition().x);
        triangle.translateYProperty().set(getPosition().y);
    }
    
}
