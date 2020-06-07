/**
 * @author Adrianna Gilmore
 * @version 1
 * This class creates a human object.
 * This object is a child class of the Person class.
 */
package com.company;

import processing.core.PApplet;

public class Human extends Person {
    public static int count;

    /**
     * Constructor
     * @param x : type float
     * @param y : type float
     * @param diameter : type float
     * @param p : type PApplet
     */
    public Human(float x, float y, float diameter, PApplet p) {
        super(x, y, diameter, p);
        count++;
    }

    /**
     * Gets the count of how many objects have been made
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * Decreases the number of objects counted
     */
    public void decrease() {
        count--;
    }
}
