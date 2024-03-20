
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
/**
 * the powerup object that falls from bricks
 * @author gordo_000
 */
public class Powerup extends Mobile<Path> {
    private static final String[] names = new String[] {"gun", "flip", "multi", "mutate", "slow", "fast", "bomb", "ghost"};
    private static final Vector startV = new Vector(0, 2);
    private Vector startPos;
    private Path path;
    private int type;
    
    StackPane pane = new StackPane();
    
    /*public Powerup(Vector p, int type) {
        this(p);
        this.type = type;
    }*/
    
    /**
     * constructs a new powerup given a brick position and a type
     * @param p
     * @param type 
     */
    public Powerup(Vector p, int type) {
        super(p.subtract(Brick.size.multiply(0.5)), startV);
        
        this.type = type; /*MoreMath.random(0, Game.numPups - 1);*/
        startPos = p.copy();
        Vector bs = Brick.size.add(new Vector(-Brick.size.y-3, 0));
        Vector lowerLeftCorner = new Vector(p.x -bs.x / 2, bs.y / 2);
        Vector lowerRightCorner = lowerLeftCorner.add(new Vector(bs.x, 0));
        Vector upperRightCorner = lowerRightCorner.add(new Vector(0, -bs.y));
        Vector upperLeftCorner = upperRightCorner.add(new Vector(-bs.x, 0));
        MoveTo A = new MoveTo(lowerLeftCorner.x, lowerLeftCorner.y);
        LineTo B = new LineTo(lowerRightCorner.x, lowerRightCorner.y);
        ArcTo C = new ArcTo();
        C.setX(upperRightCorner.x);
        C.setY(upperRightCorner.y);
        C.setLargeArcFlag(false);
        C.setSweepFlag(false);
        C.setRadiusX(bs.y / 2);
        C.setRadiusY(bs.y / 2);
        LineTo D = new LineTo(upperLeftCorner.x, upperLeftCorner.y);
        ArcTo E = new ArcTo();
        E.setX(lowerLeftCorner.x);
        E.setY(lowerLeftCorner.y);
        E.setLargeArcFlag(false);
        E.setSweepFlag(false);
        E.setRadiusX(bs.y / 2);
        E.setRadiusY(bs.y / 2);
        ArrayList<PathElement> pe = new ArrayList<PathElement>();
        pe.add(A);
        pe.add(B);
        pe.add(C);
        pe.add(D);
        pe.add(E);
        path = new Path(pe);
        path.setFill(Color.PURPLE);
        path.setStroke(null);
        
        Text text = new Text(names[type]);
        pane.getChildren().add(path);
        pane.getChildren().add(text);
        updateGraphics();
        //pane.setTranslateX(100);
        //pane.setTranslateY(100);
        //Circle c = new Circle(100, 100, 5);
        //c.setFill(Color.BLACK);
        //pane.getChildren().add(c);
    }
    
    /**
     * returns the path that represents this powerup
     * @return 
     */
    @Override
    public Path getShape() {
        return path;
    }
    
    /**
     * updates the path representing this powerup's graphics
     */
    @Override
    public void updateGraphics() {
        if (Game.pup[Game.FLIP]) {
            setVelocity(startV.multiply(-1));
        } else {
            setVelocity(startV);
        }
        pane.translateXProperty().set(getPosition().x);
        pane.translateYProperty().set(getPosition().y);
    }
    
    /**
     * returns the pane containing this powerup's path and text
     * @return 
     */
    @Override
    public Node getGraphics() {
        return pane;
    }
    
    /**
     * returns the type of this powerup
     * @return 
     */
    public int getType() {
        return type;
    }
}
