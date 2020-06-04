import com.company.Human;
import com.company.Zombie;
import com.company.Counter;
import com.company.Person;
import com.company.ParticleSystem;
import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
    final double EQUAL_ODDS = 0.50;
    //ArrayList<Person> humans;
   //ArrayList<Person> zombies;
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
        /*
        humans = new ArrayList<Person>();
        addHumans();
        zombies = new ArrayList<Person>();
        addZombies();
        */
        people = new ArrayList<Person>();
        addPeople();

        //humanCounter = new Counter(humans.size());
        //zombieCounter = new Counter(zombies.size());

        particleSystems = new ArrayList<ParticleSystem>();

        probability = 0.0;
        randomSelect = /*random(1)*/1.0;
    }

    public void draw() {
        background(255);
        //humanCounter.display("Humans",width/2, height -50, this);
        //zombieCounter.display("Zombies",width/2, 50+40, this);

        for (Person person : people) {
            if (person instanceof Human) {
                person.setColor(255, 127, 156, (int) random(50, 100), this);
            } else {
                person.setColor(63,244,208, (int)random(50,100),this);
            }
            person.draw(this);
            person.move(this);
           // person.drawExplosions();

            for (Person person2 : people) {
             //   person.outcomes(person2,this, people);
               if (person.touching(person2, this)) {
                    text("it worked", 100, 100);
                    if (person.isLarger(person2)) {
                        probability = 0.85;
                        textSize(45);
                        text("Prob: 85%", 200,100);
                    } else {
                        probability = 0.60;
                        text("Prob: 60%", 200,200);
                    }
                    if (randomSelect > probability) {
                        people.indexOf(person2);
                        //break;
                        text("Remove something here", 300, 300);
                    }
                }
            }

        }
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
                    probabilities();
                    //zombies.remove(zombie);
                    //possibleOutcomes();
                }
            }
        }

        for (ParticleSystem particles : particleSystems) {
            particles.draw();
            particles.update();
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

    public void removePerson(Person person) {
        for (int i = people.size(); i > 0; -- i) {
            people.remove(person);
            break;
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
    }

    public void removeZombie(int index) {
            zombies.remove(index);
    }

    public double probabilities() {
        for (Person human : humans) {
            for (Person zombie : zombies) {
                if (human.isLarger(zombie)) {
                    probability = 0.85;
                } else {
                    probability = 0.60;
                }
            }
        }
        return probability;
    }

    public void possibleOutcomes() {
        for (int humanIndex = humans.size(); humanIndex > 0; --humanIndex) {
            for(int zombieIndex = zombies.size(); zombieIndex > 0; --zombieIndex) {
                if (randomSelect > probability) { //fix all this stuff
                    explosions(zombies.get(zombieIndex).getX(), zombies.get(zombieIndex).getY());
                    zombies.remove(zombieIndex);
                    //break;
                } else {
                    if (randomSelect > EQUAL_ODDS) {
                        explosions(humans.get(humanIndex).getX(), humans.get(humanIndex).getY());
                        humans.remove(humans.get(humanIndex));
                        //break;
                    } else {
                        zombies.add(new Zombie(humans.get(humanIndex).getX(), humans.get(humanIndex).getY(), humans.get(humanIndex).getRadius(), this));
                        humans.remove(humans.get(humanIndex));
                        //break;
                    }
                }
            }
        }
    }

    public void explosions(float x, float y)  {
        particleSystems.add(new ParticleSystem(x,y,this));
    }*/
}