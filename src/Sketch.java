import processing.core.PApplet;
import com.company.Human;
import java.util.Random;

public class Sketch extends PApplet{
    Human humans;

    public void settings() {
        size(500,500);
    }

    public void setup() {
        humans = new Human((int)random(500), (int)random(300,500), this);
    }

    public void draw() {
        humans.draw();
    }


}