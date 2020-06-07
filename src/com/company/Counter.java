/**
 * @author Adrianna Gilmore
 * @version 1
 * This class creates a counter object.
 */
package com.company;

import processing.core.PApplet;
import processing.core.PConstants;

public class Counter {
    int startNum;

    /**
     * Parameterless constructor
     */
    public Counter() {
        startNum = 0;
    }

    /**
     * Parameterized constructor
     * @param startNum
     */
    public Counter(int startNum) {
        this.startNum = startNum;
    }

    /**
     * Displays the counter object on the drawing canvas.
     * @param displayText
     * @param count
     * @param x
     * @param y
     * @param p
     */
    public void display(String displayText, int count, int x, int y, PApplet p) {
        p.fill(0);
        p.textSize(40);
        p.textAlign(PConstants.CENTER);
        p.text(displayText +": " + count, x, y);
    }
}
