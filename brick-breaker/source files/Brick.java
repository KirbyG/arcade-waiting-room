
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The brick class extends structure to implement the bricks in the game.
 * @author gordo_000
 */
public class Brick extends Structure<Polygon> {
    private boolean powerup;
    private boolean rotated = false;
    
    //public static final Vector gridSize = new Vector(10, 20);
    
    public static final Vector size = new Vector((Game.screen.x - 2 * Game.wallWidth) / Game.gridSize.x, (Game.screen.y - (200)) / Game.gridSize.y);
    
    private PolyRect rectangle;
    private int state;
    
    private Vector grid;
    private static final Vector shift = new Vector(Game.wallWidth, 100);
    
    private static Color zero = new Color(0, 0, 0, 1);
    private static Color one = new Color(195.0/255, 90.0/255, 30.0/255, 1);
    private static Color two = new Color(215.0/255, 60.0/255, 20.0/255, 1);
    private static Color three = new Color(235.0/255, 30.0/255, 10.0/255, 1);
    private static Color four = new Color(1, 0, 0, 1);
    private static Color five = new Color(138.0/255, 138.0/255, 138.0/255, 1);
    private static Color[] colors = new Color[6];
    
    /**
     * converts an x y coordinate to a position on the grid of bricks
     * @param position
     * @return 
     */
    public static Vector posToGrid(Vector position) {
        Vector shifted = position.subtract(shift);
        Vector scaled = shifted.divide(size);
        Vector integers = scaled.truncate();
        return integers;
    }
    
    /**
     * returns the current health of the brick
     * @return 
     */
    public int getState() {
        return state;
    }
    
    /**
     * creates a new brick at the given position, with the given health. powerup
     * indicates whether or not the brick will drop a powerup
     * @param grid
     * @param state
     * @param powerup 
     */
    public Brick(Vector grid, int state, boolean powerup) {
        super(gridToPos(grid));
        
        this.powerup = powerup;
        
        colors[0] = zero;
        colors[1] = one;
        colors[2] = two;
        colors[3] = three;
        colors[4] = four;
        colors[5] = five;
        
        rectangle = makeRect();
        rectangle.setStrokeWidth(2);
        rectangle.setStroke(Color.WHITE);
        this.state = state;
        
        this.grid = grid.copy();
    }
    
    /**
     * converts from a position in the continuous x y plane onto the grid of
     * bricks
     * @param grid
     * @return 
     */
    private static Vector gridToPos(Vector grid) {
        Vector scaled = grid.multiply(size);
        
        Vector shifted = scaled.add(shift.add(size.multiply(0.5)));
        
        //System.out.println(shifted.x + " " + shifted.y);
        
        return shifted;
    }

    /**
     * decreases the health of the brick if the brick is not gray
     */
    @Override
    public void registerImpact() {
        //color = Color.GREEN;
        if (state != 5) {
            state--;
        }
    }
    
    /**
     * updates the rectangle that represents the graphics of this brick
     */
    @Override
    public void updateGraphics() {
        rectangle.translateXProperty().set(getPosition().x);
        rectangle.translateYProperty().set(getPosition().y);
        rectangle.setFill(colors[state]);
    }
    
    /**
     * returns the rectangle that is this brick's shape
     * @return 
     */
    @Override
    public PolyRect getShape() {
        return rectangle;
    }
    
    /**
     * takes all the brick's health away
     */
    public void kill() {
        state = 1;
    }
    
    /**
     * returns true if the brick has lost all its health
     * @return 
     */
    public boolean broken() {
        return state == 0;
    }
    
    /**
     * returns the position of the brick on the grid
     * @return 
     */
    public Vector getGridPos() {
        return grid;
    }
    
    /**
     * returns whether or not this brick contains a power up
     * @return 
     */
    public boolean getPowerup() {
        return powerup;
    }
    
    /**
     * creates a rectangle
     * @return 
     */
    private PolyRect makeRect() {
        return new PolyRect(getPosition(), size.add(new Vector(1, 1)));
    }
}
