import com.company.Human;
import com.company.Zombie;
import com.company.Counter;
import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
    ArrayList<Human> humans;
    ArrayList<Zombie> zombies;
    Counter humanCounter;
    Counter zombieCounter;

    public void settings() {
        size(800,500);
    }

    public void setup() {
        humans = new ArrayList<Human>();
        addHumans();
        zombies = new ArrayList<Zombie>();
        addZombies();

        humanCounter = new Counter(humans.size());
        zombieCounter = new Counter(zombies.size());
    }

    public void draw() {
        background(255);
        humanCounter.display("Humans",width/2, height -50, this);
        zombieCounter.display("Zombies",width/2, 50+40, this);
        for (Human human : humans) {
            //pink
            human.setColor(255,127,156, (int)random(50,100),this);
            human.draw(this);
            human.move(this);
        }

        for (Zombie zombie : zombies) {
            //blue
            zombie.setColor(63,244,208, (int)random(50,100),this);
            zombie.draw(this);
            zombie.move(this);
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


}