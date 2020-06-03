package com.company;

import processing.core.PApplet;

public class Particle {

    PApplet p;

    final double ACCELERATION = 0.1;

    float x;
    float y;
    float xVelocity;
    float yVelocity;
    int c;
    float size;

    public Particle(PApplet p) {
        x = p.mouseX;
        y = p.mouseY;
        this.p=p;
        xVelocity = p.random(-5, 5);
        yVelocity = p.random(-4, 0);
        c = p.color(p.random(100, 255), p.random(100, 255), p.random(100, 255), 75);
        size = p.random(5, 15);
    }

    public Particle(float x, float y, PApplet p) {
        this.x = x;
        this.y = y;
        this.p=p;
        xVelocity = p.random(-2, 2);
        yVelocity = p.random(-4, 0);
        c = p.color(p.random(100, 255), p.random(100, 255), p.random(100, 255), 75);
        size = p.random(5, 15);
    }

    public void draw() {
        p.stroke(150);
        p.fill(c);
        p.ellipse(x, y, size, size);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
        yVelocity += ACCELERATION;
    }

    public void setColor(int red, int green, int blue, int opacity) {
        this.c = p.color(red, green, blue, opacity);
    }

}