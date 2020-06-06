package com.company;

import processing.core.PApplet;

public class Human extends Person {
    public static int count;
    public Human(float x, float y, float radius, PApplet p) {
        super(x, y, radius, p);
        count++;
    }

    public int getCount() {
        return count;
    }
}
