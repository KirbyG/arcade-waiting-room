/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Implementation of a basic mathematical 2d vector
 * @author gordo_000
 */
public class Vector {
    public double x;
    public double y;
    
    /**
     * creates a new vector with the given x and y
     * @param x
     * @param y 
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector() {}
    
    /**
     * sets the vector's coordinates to integers
     * @return 
     */
    public Vector truncate() {
        return new Vector((int)x, (int)y);
    }
    
    /**
     * compares two vectors for equality on their x and y
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Vector) {
            Vector v = (Vector)o;
            return x == v.x && y == v.y;
        }
        return false;
    }
    
    /**
     * returns a string representing the vector
     * @return 
     */
    @Override
    public String toString() {
        return x + " " + y;
    }
    
    /**
     * constructs the vector based on an angle and a length. This method is not
     * an overloaded constructor because it would have the same signature as the
     * other constructor
     * @param angle
     * @param length
     * @return 
     */
    public Vector setAngleLength(double angle, double length) {
        Vector newVector = angleToUnit(angle).multiply(length);
        
        x = newVector.x;
        y = newVector.y;
        
        return this;
    }
    
    /**
     * divides this vector's components by the given vector's
     * @param v
     * @return 
     */
    public Vector divide(Vector v) {
        return new Vector(x / v.x, y / v.y);
    }
    
    /**
     * multiplies this vector's components by the given vector's
     * @param v
     * @return 
     */
    public Vector multiply(Vector v) {
        return new Vector(x * v.x, y * v.y);
    }
    
    /**
     * converts an angle to a unit vector
     * @param angle
     * @return 
     */
    public Vector angleToUnit(double angle) {
        return new Vector(Math.cos(angle), Math.sin(angle));
    }
    
    /**
     * multiplies this vector by a scalar
     * @param scalar
     * @return 
     */
    public Vector multiply(double scalar) {
        return new Vector(scalar * x, scalar * y);
    }
    
    /**
     * adds two vectors component by component
     * @param a
     * @return 
     */
    public Vector add(Vector a) {
        return new Vector(x + a.x, y + a.y);
    }
    
    /**
     * returns the length of this vector
     * @return 
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * converts this vector to a unit vector
     * @return 
     */
    public Vector toUnit() {
        double scaleFactor = length();
        return new Vector(x / scaleFactor, y / scaleFactor);
    }
    
    /**
     * converts this vector to an angle
     * @return 
     */
    public double toAngle() {
        return Math.atan2(y, x);
    }
    
    /**
     * subtracts two vectors componentwise
     * @param v
     * @return 
     */
    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }
    
    /**
     * finds the angle from this vector to another
     * @param p2
     * @return 
     */
    public double angleTo(Vector p2) {
        Vector vDiff = p2.subtract(this);
        return vDiff.toAngle();
    }
    
    /**
     * finds the distance to the given vector
     * @param v
     * @return 
     */
    public double distanceTo(Vector v) {
        return subtract(v).length();
    }
    
    /**
     * returns a copy of this vector
     * @return 
     */
    public Vector copy() {
        return new Vector(x, y);
    }
}
