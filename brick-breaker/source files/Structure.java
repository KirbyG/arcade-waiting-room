
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * superclass of all immobile elements
 * @author gordo_000
 */
public abstract class Structure<T extends Shape> extends Element<T> {
    /**
     * do something about an impact with a mobile
     */
    public abstract void registerImpact();
    
    /**
     * creates a new structure at the given position
     * @param position 
     */
    public Structure(Vector position) {
        super(position);
    }
}
