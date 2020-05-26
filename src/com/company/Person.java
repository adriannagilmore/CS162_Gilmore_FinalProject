package com.company;

import processing.core.PApplet;

public abstract class Person {
    /*This class will store the behaviors for
    both humans and zombies.
    Behaviors include:
        forward random motion
        size (small, medium, larger)
        color (maybe abstract so it can be overridden)
        probabilities based on size (being a zombie will lower the probabilities of success)

    It will need to use the particle system to represent them.

    It will also use a counter object
     */
    int xStart, xEnd, yStart,yEnd;
    ParticleSystem particles;

    public Person(PApplet p) {
        particles = new ParticleSystem(xStart, xEnd,yStart,yEnd, p);
    }

    public Person(int xStart, int xEnd, int yStart, int yEnd, PApplet p) {
        particles = new ParticleSystem(xStart,xEnd,yStart,yEnd, p);
    }

    public void draw() {
        particles.draw();
    }

    public void move() {
        particles.update();
    }

    public void setXStart(int x) {
        this.xStart = x;
    }
     public int getXStart() {
        return this.xStart;
     }

     public void setYStart(int y) {
        this.yStart = y;
     }

     public int getYStart() {
        return this.yStart;
     }

     public void setColor(int red, int green, int blue, int opacity) {
        particles.setColor(red, green, blue, opacity);
     }


}
