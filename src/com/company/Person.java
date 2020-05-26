package com.company;
import processing.core.PApplet;
import java.util.Random;

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
    Random randomX = new Random(500); //see if this is right
    //PApplet sketch = new PApplet();
    int x, y;
    ParticleSystem particles;

    public Person(int x, int y, PApplet p) {
        particles = new ParticleSystem(x,y, p);
    }

    public void draw() {
        particles.draw();
    }


}
