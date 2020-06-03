package com.company;

import processing.core.PApplet;

public abstract class Person {
    PApplet p;
    float x,y, radius;
    private int color;
    final static double PROB_RIGHT = 0.30;
    final static double PROB_LEFT = 0.30;
    final static double PROB_UP = 0.40;
    processing.core.PVector position;

    public Person(float x, float y, float radius, PApplet p) {
        p = new PApplet();
        this.x = x;
        this.y = y;
        this.radius = radius;
        position = new processing.core.PVector(x, y);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRadius() {
        return this.radius;
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

    public int detectSize() {
        if (this.radius < 32) {
            return 0;
        } else if (this.radius >= 32 && this.radius <= 42) {
            return 1;
        } else {
            return 2;
        }
    }

    public boolean isLarger(Person peep) {
        return (this.detectSize() > peep.detectSize());
    }

    public boolean touching (Person person, PApplet p) {
        return (p.dist(this.getX(), this.getY(), person.getX(),person.getY())<person.getRadius()+this.getRadius());
    }


}
