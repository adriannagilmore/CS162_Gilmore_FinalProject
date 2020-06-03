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
        addHumans();
        zombies = new ArrayList<Person>();
        addZombies();

        humanCounter = new Counter(humans.size());
        zombieCounter = new Counter(zombies.size());

        particleSystems = new ArrayList<ParticleSystem>();

        probability = 0.0;
        randomSelect = random(1);
    }

    public void draw() {
        background(255);
        humanCounter.display("Humans",width/2, height -50, this);
        zombieCounter.display("Zombies",width/2, 50+40, this);
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
                    possibleOutcomes();
                }
            }
        }

        for (ParticleSystem particles : particleSystems) {
            particles.draw();
            particles.update();
        }


    }

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

    public void probabilities() {
        for (Person human : humans) {
            for (Person zombie : zombies) {
                if (human.isLarger(zombie)) {
                    probability = 0.85;
                } else {
                    probability = 0.60;
                }
            }
        }
    }

    public void possibleOutcomes() {
        for (int human = humans.size(); human > 0; --human) {
            for(int zombie = zombies.size(); zombie > 0; --zombie) {
                if (randomSelect > probability) {
                    explosions(zombies.get(zombie).getX(), zombies.get(zombie).getY());
                    zombies.remove(zombies.get(zombie));
                } else {
                    if (randomSelect > EQUAL_ODDS) {
                        explosions(humans.get(human).getX(), humans.get(human).getY());
                        humans.remove(humans.get(human));
                    } else {
                        zombies.add(new Zombie(humans.get(human).getX(), humans.get(human).getY(), humans.get(human).getRadius(), this));
                        humans.remove(humans.get(human));
                    }
                }
            }
        }
    }

    public void explosions(float x, float y)  {
        particleSystems.add(new ParticleSystem(x,y,this));
    }
}