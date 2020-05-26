package com.company;

import processing.core.PApplet;
import java.util.ArrayList;


class ParticleSystem {

    final int NUMBER_OF_PARTICLES = 100;
    ArrayList<Particle> particles;
    PApplet pap = new PApplet();
    int x;
    int y;

    ParticleSystem(PApplet p) {
        particles = new ArrayList<Particle>();
        x = p.width / 2;
        y = p.height / 2;
        for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
            particles.add(new Particle(x, y,p));
        }
    }

    ParticleSystem(int xStart, int xEnd, int yStart, int yEnd, PApplet p) {
        this.x = (int)p.random(xStart,xEnd);
        this.y = (int)p.random(yStart, yEnd);
        particles = new ArrayList<Particle>();
        for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
            particles.add(new Particle(x, y,p));
            this.x = (int)pap.random(xStart,xEnd);
            this.y = (int)pap.random(yStart, yEnd);
        }
    }

    void update() {
        for (Particle p : particles) {
            p.move();
        }
    }

    void draw() {
        for (Particle p : particles) p.draw();
    }

    public void setColor(int red, int green, int blue, int opacity) {
        for (Particle p : particles) {
            p.setColor(red, green, blue, opacity);
            //opacity = (int)pap.random(15,100);
        }
    }

}