package com.company;

import processing.core.PApplet;

public class Zombie extends Person {

    public Zombie(float x, float y, float radius, PApplet p) {
        super(x, y, radius, p);
    }

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
