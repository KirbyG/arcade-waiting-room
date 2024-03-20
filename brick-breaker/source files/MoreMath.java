
import java.util.ArrayList;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Useful methods not specifically related to a single class.
 * @author gordo_000
 */
public final class MoreMath {
    /*public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(random(1, 10));
        }
    }*/
    
    /**
     * ensures that a given angle is positive
     * @param angle
     * @return 
     */
    public static double angleToPositive(double angle) {
        while (angle < 0) {
            angle += Math.PI * 2;
        }
        return angle;
    }
    
    /**
     * generates a standard arraylist from an observable list
     * @param <T>
     * @param list
     * @return 
     */
    public static <T> ArrayList<T> listObservableToNormal(ObservableList<T> list) {
        ArrayList<T> copied = new ArrayList<T>();
        
        for (int i = 0; i < list.size(); i++) {
            copied.add(list.get(i));
        }
        
        return copied;
    }
    
    /**
     * returns a random integer in the given inclusive range
     * @param min
     * @param max
     * @return 
     */
    public static int random(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
    /**
     * returns the range of a given set of data
     * @param list
     * @return 
     */
    public static double range(ArrayList<Double> list) {
        return max(list) - min(list);
    }
    
    /**
     * returns the maximum of a list
     * @param list
     * @return 
     */
    public static double max(ArrayList<Double> list) {
        double max = Double.MIN_VALUE;
        for (Double d: list) {
            if (d > max) {
                System.out.println(d);
                max = d;
            }
        }
        return max;
    }
    
    /**
     * returns the minimum of a list
     * @param list
     * @return 
     */
    public static double min(ArrayList<Double> list) {
        double min = Double.MAX_VALUE;
        for (Double d: list) {
            if (d < min) {
                min = d;
            }
        }
        return min;
    }
    
    /**
     * converts an array to an arraylist
     * @param <T>
     * @param array
     * @return 
     */
    public static <T> ArrayList<T> arrayToList(T[] array) {
        ArrayList<T> list = new ArrayList<T>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }
}
