package com.company;

import processing.core.PApplet;
import java.util.ArrayList;

public abstract class Person {
    private PApplet p;
    protected float x,y, radius;
    private int color;
    private double probability;
    protected final static double PROB_RIGHT = 0.30;
    protected final static double PROB_LEFT = 0.30;
    protected static double PROB_UP = 0.40;
    private ArrayList<ParticleSystem> particleSystems;

    public Person(float x, float y, float radius, PApplet p) {
        p = new PApplet();
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public abstract int getCount();

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
        boolean x = false;
        if (this.getClass() != person.getClass()) {
            x = (p.dist(this.getX(), this.getY(), person.getX(), person.getY()) < person.getRadius()/2 + this.getRadius()/2);
        }
        return x;
    }

    public double prob(Person person, PApplet p) {
        if (touching(person, p)) {
            if (person instanceof Zombie) {
                //comparing human to zombie
                if (isLarger(person)) {
                    probability = 0.85;
                } else {
                    probability = 0.60;
                }
            }else if (person instanceof Human) {
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

    public void outcomes(Person person, PApplet p, ArrayList<Person> people) {
        double randomSelect = p.random(1);
        if (touching(person, p)) {
            if (randomSelect >= prob(person, p)) {
                //explosions(person.getX(),person.getY(),p);
                people.remove(person);
            } else {
                //explosions(this.getX(),this.getY(),p);
                people.remove(this);
            }
        }
    }

    public void explosions(float x, float y, PApplet p)  {
        particleSystems.add(new ParticleSystem(x,y,p));
    }

    public void drawExplosions() {
        for (ParticleSystem p : particleSystems) {
            p.draw();
            p.update();
        }
    }


}
