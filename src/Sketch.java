import com.company.Human;
import com.company.Zombie;
import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch extends PApplet{
    ArrayList<Human> humans;
    ArrayList<Zombie> zombies;
    Human human;

    public void settings() {
        size(800,500);
    }

    public void setup() {
        humans = new ArrayList<Human>();
        addHumans();
        zombies = new ArrayList<Zombie>();
        addZombies();

        human = new Human(50,100,50,this);
    }

    public void draw() {
        background(255);
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

        //human.draw(this);
        //human.move(this);
    }

    public void addHumans() {
        for(int i = 0; i < (int)random(50,100); ++i) {
            humans.add(new Human((int)random(25,475),(int)random(325,475), (int)random(25,50),this));
        }
    }

    public void addZombies() {
        for(int i = 0; i < (int)random(50,100); ++i) {
            zombies.add(new Zombie((int)random(25,475),(int)random(25,175), (int)random(25,50),this));
        }
    }


}