/**
 * @author Adrianna Gilmore
 * @version 1
 * This class creates the drawing canvas and draws the simulation.
 */

import com.company.Human;
import com.company.Zombie;
import com.company.Counter;
import com.company.Person;
import com.company.ParticleSystem;
import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
    ArrayList<Person> people;
    Counter humanCounter;
    Counter zombieCounter;
    ArrayList<ParticleSystem> particleSystems;

    /**
     * Display settings.
     */
    public void settings() {
        size(800,500);
    }

    /**
     * Sets up the drawing canvas.
     */
    public void setup() {
        people = new ArrayList<Person>();
        addPeople();

        humanCounter = new Counter(countPeople(Human.class));
        zombieCounter = new Counter(countPeople(Zombie.class));

        particleSystems = new ArrayList<ParticleSystem>();

    }

    /**
     * Draws to the canvas.
     */
    public void draw() {
        background(255);
        setColor();
        humanCounter.display("Humans", Human.count,width / 2, height - 50, this);
        zombieCounter.display("Zombies", Zombie.count,width / 2, 50 + 40, this);

        for (Person person : people) {
            person.draw(this);
            person.move(this);
        }

        for (ParticleSystem particles : particleSystems) {
            particles.draw();
            particles.update();
        }

        for (Person person : people) {
            for (Person person2 : people) {
                if (person.touching(person2, this)) {
                    person.outcomes(person2, this, people, particleSystems);
                    break;
                }
            }
            break;
        }
    }

    /**
     * Adds people to the array list of people.
     */
    public void addPeople() {
        for(int i = 0; i < /*(int)random(25)*/15; ++i) {
            people.add(new Human((int)random(25,width-25),(int)random(325,475), (int)random(25,50),this));
        }
        for(int i = 0; i < /*(int)random(25)*/15; ++i) {
            people.add(new Zombie((int)random(25,width-25),(int)random(25,175), (int)random(25,50),this));
        }
    }

    /**
     * Counts the people in the array list based on the type of person.
     * @param className
     * @return x
     */
    public int countPeople(Class<?> className) {
        int x = 0;
        for (Person p: people) {
            if (p.getClass() == className ) {
                x = p.getCount();
            }
        }
        return x;
    }

    /**
     * Sets the color of the people based on the type of people.
     */
    public void setColor () {
        for (Person person : people) {
            if (person.getClass() == Human.class) {
                person.setColor(255, 127, 156, 75, this);
            } else {
                person.setColor(63, 244, 208, 75, this);
            }
        }
    }
}