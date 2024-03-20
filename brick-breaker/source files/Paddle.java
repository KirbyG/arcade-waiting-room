
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * Represents the paddle that the user controls
 * @author gordo_000
 */
public class Paddle extends Mobile<Polygon> {
    private static final Vector startPos = new Vector(Game.screen.x / 2, Game.screen.y);
    
    private static final Color color = Color.MEDIUMAQUAMARINE;
    
    private static final double speed = 20;
    
    private Polygon rectangle = new Polygon();
    
    private double left = 0;
    private double right = 0;
    
    private int form = 0;
    
    private static final Double[][] forms = new Double[][] {
        {-75d, 0d, 75d, 0d, 35d, -20d, -35d, -20d},
        {-115d, 0d, 115d, 0d, 75d, -20d, -75d, -20d},
    };
    private double[] heights = new double[forms.length];
    
    /**
     * creates a paddle at the default start location and at 0 speed
     */
    public Paddle() {
        super(startPos, new Vector(0, 0));
        
        for (int i = 0; i < forms.length; i++) {
            ArrayList<Double> points = MoreMath.arrayToList(forms[i]);
            for (int j = 0; j < points.size(); j++) {
                points.remove(j);
            }
            heights[i] = MoreMath.range(points); 
        }
        
        updateGraphics();
    }
    
    /**
     * updates the polygon that is the paddle
     */
    @Override
    public void updateGraphics() {
        rectangle.toFront();
        rectangle.translateXProperty().set(getPosition().x);
        if (Game.pup[Game.FLIP]) {
            getPosition().y = 0;
            rectangle.getPoints().setAll(pairReverse(flip(forms[form])));
        } else {
            getPosition().y = startPos.y;
            rectangle.getPoints().setAll(forms[form]);
        }
        rectangle.translateYProperty().set(getPosition().y);
        rectangle.setFill(color);
    }
    
    /**
     * flips the paddle onto the ceiling
     * @param coords
     * @return 
     */
    private Double[] flip(Double[] coords) {
        Double[] flipped = new Double[coords.length];
        for (int i = 0; i < coords.length; i++) {
            if (i % 2 == 0) {
                flipped[i] = coords[i];
            } else {
                flipped[i] = -coords[i];
            }
        }
        return flipped;
    }
    
    /**
     * because normal vectors are needed for bouncing, all shapes must be defined counterclockwise
     * so that normals will be correctly calculated. this method flips the order
     * of coordinates, which is necessary when the paddle is inverted to be
     * on the ceiling
     * @param array
     * @return 
     */
    private Double[] pairReverse(Double[] array) {
        Double[] reversed = new Double[array.length];
        for (int i = array.length - 1; i >= 0; i-=2) {
            reversed[array.length - i] = array[i];
            reversed[array.length - 1 - i] = array[i - 1];
        }
        return reversed;
    }
    
    /**
     * responds to user key presses
     * @param type
     * @param code 
     */
    public void takeInput(EventType type, KeyCode code) {
        if (type.equals(KeyEvent.KEY_PRESSED)) {
            if (code.equals(KeyCode.LEFT)) {
                left = 1;
            }
            if (code.equals(KeyCode.RIGHT)) {
                right = 1;
            }
        }
        if (type.equals(KeyEvent.KEY_RELEASED)) {
            if (code.equals(KeyCode.LEFT)) {
                left = 0;
            }
            if (code.equals(KeyCode.RIGHT)) {
                right = 0;
            }
        }
        getVelocity().x = -speed * left + speed * right;
    }
    
    /**
     * returns the polygon that is the paddle's shape
     * @return 
     */
    @Override
    public Polygon getShape() {
        return rectangle;
    }
    
    /**
     * returns the maximum height of the paddle
     * @return 
     */
    public double getHeight() {
        return heights[form];
    }
    
    /**
     * changes the form of the paddle
     */
    public void mutate() {
        form = (form + MoreMath.random(1, forms.length - 1)) % forms.length;
    }
}
