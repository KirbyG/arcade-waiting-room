
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The ball that bounces around the screen and breaks bricks.
 * @author gordo_000
 */
public class Ball extends Mobile<Circle> {
    private static final int radius = 8;
    public static final double startSpeed = 8;
    
    private Circle circle;
    
    /**
     * Creates a new ball at the center of the top of the screen with random velocity.
     */
    public Ball() {
        super(new Vector(Game.screen.x / 2, 30), ((Math.random() * 0.8) + 0.1) * Math.PI, startSpeed);
        
        if (Game.pup[Game.FLIP]) {
            getPosition().y = Game.screen.y - 30;
        }
        
        circle = new Circle();
    }
    
    /**
     * updates the circle object that represents the ball's graphics
     */
    @Override
    public void updateGraphics() {
        getShape().setCenterX(getPosition().x);
        getShape().setCenterY(getPosition().y);
        getShape().setRadius(radius);
        
        if (Game.pup[Game.BOMB]) {
            getShape().setFill(Color.ORANGE);
        } else if (Game.pup[Game.GHOST]) {
            getShape().setFill(Color.GREEN);
        } else {
            getShape().setFill(Color.BLACK);
            /*if (Math.random() > 0.999) {
                Game.pup[Game.GHOST] = false;
            }*/
        }
    }
    
    /**
     * returns the circle that is this ball's shape
     * @return 
     */
    @Override
    public Circle getShape() {
        return circle;
    }
}
