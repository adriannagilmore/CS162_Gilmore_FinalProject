import com.company.Human;
import com.company.Zombie;
import processing.core.PApplet;

public class Sketch extends PApplet{
    Human humans;
    Zombie zombies;

    public void settings() {
        size(500,500);
    }

    public void setup() {
        humans = new Human(50, 450, 50,200,this);
        zombies = new Zombie(50,450,300,450,this);
    }

    public void draw() {
        humans.draw();
        humans.setColor(255,127,156,100);
        zombies.draw();
        zombies.setColor(0,191,255,100);
    }


}