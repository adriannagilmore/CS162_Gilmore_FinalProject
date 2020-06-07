/**
 * @author Adrianna Gilmore
 * @version 1
 * This class creates a particle System.
 */
package com.company;
import processing.core.PApplet;
import java.util.ArrayList;

public class ParticleSystem {

    final int NUMBER_OF_PARTICLES = 100;
    ArrayList<Particle> particles;
    float x;
    float y;

    /**
     * Contstructor
     * @param p : type PApplet
     */
   public ParticleSystem(PApplet p) {
        particles = new ArrayList<Particle>();
        x = p.width / 2;
        y = p.height / 2;
        for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
            particles.add(new Particle(x, y,p));
        }
    }

    /**
     * Constructor
     * @param x : type float
     * @param y : type float
     * @param p : type PApplet
     */
    public ParticleSystem(float x, float y, PApplet p) {
        this.x = x;
        this.y = y;
        particles = new ArrayList<Particle>();
        for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
            particles.add(new Particle(x, y,p));
        }
    }

    /**
     * Updates/moves particles.
     */
    public void update() {
        for (Particle p : particles) {
            p.move();
        }
    }

    /**
     * Draws particles.
     */
    public void draw() {
        for (Particle p : particles) p.draw();
    }

}