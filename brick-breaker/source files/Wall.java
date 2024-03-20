/**
 * Represents the walls on the edge of the screen
 * @author gordo_000
 */
public class Wall extends Structure<PolyRect> {
    private Vector size;
    
    private PolyRect wall;
    
    /**
     * constructs a new wall given a position and a size
     * @param position
     * @param size 
     */
    public Wall(Vector position, Vector size) {
        super(position);
        this.size = size;
        wall = new PolyRect(getPosition(), size);
        updateGraphics();
    }
    
    /**
     * walls do not respond to impact
     */
    @Override
    public void registerImpact() {
        //do nothing
    }
    
    
    /**
     * updates this wall's rectangle
     */
    @Override
    public void updateGraphics() {
        wall.translateXProperty().set(getPosition().x);
        wall.translateYProperty().set(getPosition().y);
    }
    
    /**
     * returns the rectangle that is the shape of this wall
     * @return 
     */
    @Override
    public PolyRect getShape() {
        return wall;
    }
}
