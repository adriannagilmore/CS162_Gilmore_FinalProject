/**
 * @author Adrianna Gilmore
 * @version 1
 * This class creates a zombie object.
 * This class is a child of the Person class.
 */
package com.company;

import processing.core.PApplet;

public class Zombie extends Person {
    public static int count;

    /**
     * Constructor
     * @param x : type float
     * @param y : type float
     * @param diameter : type float
     * @param p : type PApplet
     */
    public Zombie(float x, float y, float diameter, PApplet p) {
        super(x, y, diameter, p);
        count++;
    }

    /**
     * Gets the number of objects created
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * Decreases the number of objects created
     */
    public void decrease() {
        count--;
    }

    /**
     * Moves the object
     * @param p : type PApplet
     */
    @Override
    public void move(PApplet p) {
        double r = p.random(1);
        if (r<=PROB_RIGHT) {
            x++;
        } else if (r<=PROB_LEFT + PROB_RIGHT) {
            x--;
        } else if (r <= PROB_LEFT + PROB_RIGHT + PROB_UP) {
            y++;
        } else {
            y--;
        }
    }
}
