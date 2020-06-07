/**
 * @author Adrianna Gilmore
 * @version 1
 * This class creates a Person object in the shape of a circle.
 * It is an abstract parent class.
 */
package com.company;

import processing.core.PApplet;
import java.util.ArrayList;

public abstract class Person {
    private PApplet p;
    protected float x,y, diameter;
    private int color;
    private double probability;
    protected final static double PROB_RIGHT = 0.30;
    protected final static double PROB_LEFT = 0.30;
    protected static double PROB_UP = 0.40;
    private ArrayList<ParticleSystem> particleSystems;

    /**
     * Constructor
     * @param x : type float
     * @param y : type float
     * @param diameter : type float
     * @param p : type PApplet
     */
    public Person(float x, float y, float diameter, PApplet p) {
        p = new PApplet();
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        particleSystems = new ArrayList<ParticleSystem>();
    }

    public abstract int getCount();
    public abstract void decrease();

    /**
     * Gets the value of x
     * @return x : type float
     */
    public float getX() {
        return this.x;
    }

    /**
     * Gets the value of y
     * @return y : type float
     */
    public float getY() {
        return this.y;
    }

    /**
     * Gets the value of the radius
     * @return
     */
    public float getRadius() {
        return this.diameter/2;
    }

    /**
     * Sets the color of a person
     * @param red : type int
     * @param green : type int
     * @param blue : type int
     * @param alpha : type int
     * @param p : type PApplet
     */
    public void setColor(int red, int green, int blue, int alpha, PApplet p) {
       this.color = p.color(red, green, blue, alpha);
    }

    /**
     * Gets the color of the object.
     * @return color : type int
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Draws the person
     * @param p : type PApplet
     */
    public void draw(PApplet p) {
        p.fill(getColor());
        p.circle(this.x, this.y,this.diameter);
    }

    /**
     * Moves object
     * @param p : type PApplet
     */
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

    /**
     * Detects the size of the object
     * @return int
     */
    public int detectSize() {
        if (this.diameter < 32) {
            return 0;
        } else if (this.diameter >= 32 && this.diameter <= 42) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Determines which object is larger
     * @param peep : type Person
     * @return boolean
     */
    public boolean isLarger(Person peep) {
        return (this.detectSize() > peep.detectSize());
    }

    /**
     * Determines if two objects are touching.
     * @param person : type Person
     * @param p : type PApplet
     * @return boolean
     */
    public boolean touching (Person person, PApplet p) {
        boolean x = false;
        if (this.getClass() != person.getClass()) {
            x = (p.dist(this.getX(), this.getY(), person.getX(), person.getY()) <= person.getRadius() + this.getRadius());
        }
        return x;
    }

    /**
     * Determines the probability
     * @param person : type Person
     * @param p : type PApplet
     * @return double
     */
    public double prob(Person person, PApplet p) {
        if (touching(person, p)) {
            if (person.getClass() == Zombie.class) {
                //comparing human to zombie
                if (isLarger(person)) {
                    probability = 0.85;
                } else {
                    probability = 0.60;
                }
            }else if (person.getClass() == Human.class) {
                //comparing zombie to human
                if (isLarger(person)) {
                    probability = 0.50;
                } else {
                    probability = 0.30;
                }
            }
        }
        return probability;
    }

    /**
     * Determines the outcomes if two objects are touching
     * @param person : type Person
     * @param p : type PApplet
     * @param people : type ArrayList<Person>
     * @param particles : type ArrayList<ParticleSystem>
     */
    public void outcomes(Person person, PApplet p, ArrayList<Person> people, ArrayList<ParticleSystem> particles) {
        double randomSelect = p.random(1);
        double nextRand = p.random(1);
            if (randomSelect >= prob(person, p)) {
                explosions(person.getX(),person.getY(),p, particles);
                people.remove(person);
                person.decrease();
                System.out.println("A person should be removed. Probability >= random select");
            } else {
                if (nextRand< 0.50) {
                    people.add( new Zombie(this.getX(), this.getY(), this.diameter, p));
                    people.remove(this);
                    this.decrease();
                    System.out.println("A zombie should be added. Probability < random select");
                    System.out.println("nextRand = "+ nextRand);
                } else {
                    explosions(this.getX(),this.getY(),p, particles);
                    people.remove(this);
                    this.decrease();
                    System.out.println("A Zombie should be removed. Probability < random select");
                    System.out.println("nextRand = "+ nextRand);
                }

            }
    }

    /**
     * Adds a new Particle System
     * @param x : float
     * @param y : float
     * @param p : PApplet
     * @param particles : ArrayList<ParticleSystem>
     */
    public void explosions(float x, float y, PApplet p, ArrayList<ParticleSystem> particles)  {
        particles.add(new ParticleSystem(x,y,p));
        System.out.println("particle system added");
    }


}
