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

    public Person(float x, float y, float diameter, PApplet p) {
        p = new PApplet();
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        particleSystems = new ArrayList<ParticleSystem>();
    }

    public abstract int getCount();
    public abstract void decrease();

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRadius() {
        return this.diameter/2;
    }

    public void setColor(int red, int green, int blue, int alpha, PApplet p) {
       this.color = p.color(red, green, blue, alpha);
    }

    public int getColor() {
        return this.color;
    }

    public void draw(PApplet p) {
        p.fill(getColor());
        p.circle(this.x, this.y,this.diameter);
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
        if (this.diameter < 32) {
            return 0;
        } else if (this.diameter >= 32 && this.diameter <= 42) {
            return 1;
        } else {
            return 2;
        }
    }

    public boolean isLarger(Person peep) {
        return (this.detectSize() > peep.detectSize());
    }

    public boolean touching (Person person, PApplet p) {
        boolean x = false;
        if (this.getClass() != person.getClass()) {
            x = (p.dist(this.getX(), this.getY(), person.getX(), person.getY()) <= person.getRadius() + this.getRadius());
        }
        return x;
    }

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

    public void explosions(float x, float y, PApplet p, ArrayList<ParticleSystem> particles)  {
        particles.add(new ParticleSystem(x,y,p));
        System.out.println("particle system added");
    }


}
