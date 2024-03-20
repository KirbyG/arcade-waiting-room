
import javafx.scene.shape.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * implementation of a rectangle that extends polygon
 * @author gordo_000
 */
public class PolyRect extends Polygon {
    
    /**
     * static method that constructs the double array representing the rectangle's
     * coordinates
     * @param center
     * @param size
     * @return 
     */
    private static double[] makePolyRect(Vector center, Vector size) {
        double x = center.x;
        double y = center.y;
        
        double w = size.x / 2;
        double h = size.y / 2;
        
        return new double[] {w, h,
                             w, -h,
                             -w, -h,
                             -w, h};
    }
    
    /**
     * creates a rectangle given a center and a size vector
     * @param center
     * @param size 
     */
    public PolyRect(Vector center, Vector size) {
        super(makePolyRect(center, size));
    }
}
