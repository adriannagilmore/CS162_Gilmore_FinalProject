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

    */
    PApplet p;
    float x,y, radius;
    private int color;
    final static double PROB_RIGHT = 0.30;
    final static double PROB_LEFT = 0.30;
    final static double PROB_UP = 0.40;

    public Person(float x, float y, float radius, PApplet p) {
        p = new PApplet();
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void setColor(int red, int green, int blue, int alpha, PApplet p) {
       this.color = p.color(red, green, blue, alpha);
    }

    public int getColor() {
        return this.color;
    }

    public void draw(PApplet p) {
        p.fill(getColor());
        p.circle(this.x, this.y,this.radius);
    }

    public void move(PApplet p) {
        double r = p.random(1);
        if (r<=PROB_RIGHT) {
            this.x++;
        } else if (r<=PROB_LEFT + PROB_RIGHT) {
            this.x--;
        } else if (r <= PROB_LEFT + PROB_RIGHT + PROB_UP) {
            this.y--;
        } else {
            this.y++;
        }
    }


}
