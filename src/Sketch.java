import com.company.Human;
import com.company.Zombie;
import com.company.Counter;
import com.company.Person;
import com.company.ParticleSystem;
import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
    final double EQUAL_ODDS = 0.50;
    ArrayList<Person> humans;
    ArrayList<Person> zombies;
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

        humans = new ArrayList<Person>();
        //addHumans();
        zombies = new ArrayList<Person>();
        //addZombies();

        people = new ArrayList<Person>();
        addPeople();
        setColor();

        humanCounter = new Counter(humans.size());
        zombieCounter = new Counter(zombies.size());

        particleSystems = new ArrayList<ParticleSystem>();

    }

    public void draw() {
        background(255);
        humanCounter.display("Humans",width/2, height -50, this);
        zombieCounter.display("Zombies",width/2, 50+40, this);

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
                //person.outcomes(person2,this, people);
                if (person instanceof Human && person2 instanceof Zombie && person.touching(person2, this)) {
                    if (person.isLarger(person2)) {
                        probability = 0.85;
                    } else {
                        probability = 0.60;
                    }
                } else if (person instanceof Zombie && person2 instanceof Human && person.touching(person2, this)) {
                    if (person.isLarger(person2)) {
                        probability = 0.65;
                    } else {
                        probability = 0.40;
                    }
                }
                double randomSelect = random(1);
                if (person.touching(person2, this)) {
                    if (randomSelect > probability) {
                        //explosions(person.getX(),person.getY(),p);
                        people.remove(person);
                    } else {
                        //explosions(this.getX(),this.getY(),p);
                        people.remove(person2);
                    }
                }
                break;
            }
            break;
        }
/*
        for (Person person : people) {

            if (person instanceof Human) {
                person.setColor(255, 127, 156, (int) random(50, 100), this);
            } else {
                person.setColor(63,244,208, (int)random(50,100),this);
            }
           // person.drawExplosions();

            for (Person person2 : people) {
                //person.outcomes(person2,this, people);
                double randomSelect = random(1);
                if (randomSelect >= person.prob(person2, this)) {
                    //explosions(person.getX(),person.getY(),p);
                    people.remove(person);
                } else {
                    //explosions(this.getX(),this.getY(),p);
                    people.remove(person2);
                }
                break;
            }

        }*/
/*
        for (Person human : humans) {
            //pink humans
            human.setColor(255, 127, 156, (int) random(50, 100), this);
            human.draw(this);
            human.move(this);
        }

        for (Person zombie : zombies) {
            //blue zombies
            zombie.setColor(63,244,208, (int)random(50,100),this);
            zombie.draw(this);
            zombie.move(this);
        }

        for (Person human: humans) {
            for (Person zombie: zombies){
                if (human.touching(zombie, this)) {
                    text("it worked", 100,100);
                    //probabilities();
                    zombies.remove(zombie);
                    //possibleOutcomes();
                }

            }
        }*/
    }

    public void addPeople() {
        for(int i = 0; i < (int)random(50,100); ++i) {
            people.add(new Human((int)random(25,width-25),(int)random(325,475), (int)random(25,50),this));
        }
        for(int i = 0; i < (int)random(50,200); ++i) {
            people.add(new Zombie((int)random(25,width-25),(int)random(25,175), (int)random(25,50),this));
        }
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

/*
    public void addHumans() {
        for(int i = 0; i < (int)random(50,100); ++i) {
            humans.add(new Human((int)random(25,width-25),(int)random(325,475), (int)random(25,50),this));
        }
    }

    public void addZombies() {
        for(int i = 0; i < (int)random(50,200); ++i) {
            zombies.add(new Zombie((int)random(25,width-25),(int)random(25,175), (int)random(25,50),this));
        }
    }*/
}