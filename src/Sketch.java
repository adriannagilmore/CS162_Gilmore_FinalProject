import com.company.Human;
import com.company.Zombie;
import com.company.Counter;
import com.company.Person;
import com.company.ParticleSystem;
import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
    final double EQUAL_ODDS = 0.50;
    ArrayList<Person> people;
    Counter humanCounter;
    Counter zombieCounter;
    ArrayList<ParticleSystem> particleSystems;
    double probability;
    double randomSelect;

    public void settings() {
        size(800,500);
    }

    public void setup() {
        people = new ArrayList<Person>();
        addPeople();
        setColor();



        humanCounter = new Counter(countPeople(Human.class));
        zombieCounter = new Counter(countPeople(Zombie.class));

        particleSystems = new ArrayList<ParticleSystem>();

    }

    public void draw() {
        background(255);
        humanCounter.display("Humans", width / 2, height - 50, this);
        zombieCounter.display("Zombies", width / 2, 50 + 40, this);

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
                    textSize(35);
                    text("it is touching", 100, 100);
                    println("circles are touching");

                   // person.prob(person2, this);
                    person.outcomes(person2, this, people);
                    /*
                    double randomSelect = random(1);
                    if (randomSelect >= person.prob(person2, this)) {
                        //explosions(person.getX(),person.getY(),p);
                        people.remove(person);
                    } else {
                        //explosions(this.getX(),this.getY(),p);
                        people.remove(person2);
                    }*/
                    break;
                }
            }
            break;
        }
    }

    public void addPeople() {
        for(int i = 0; i < (int)random(50); ++i) {
            people.add(new Human((int)random(25,width-25),(int)random(325,475), (int)random(25,50),this));
        }
        for(int i = 0; i < (int)random(50); ++i) {
            people.add(new Zombie((int)random(25,width-25),(int)random(25,175), (int)random(25,50),this));
        }
    }

    public int countPeople(Class<?> className) {
        int x = 0;
        for (Person p: people) {
            if (p.getClass() == className ) {
                x = p.getCount();
            }
        }
        return x;
    }

    public void addNewZombie(int x, int y, int r) {
        people.add(new Zombie(x,y,r, this));
    }

    public void setColor () {
        for (Person person : people) {
            if (person instanceof Human) {
                person.setColor(255, 127, 156, (int) random(50, 100), this);
            } else {
                person.setColor(63, 244, 208, (int) random(50, 100), this);
            }
        }
    }
}