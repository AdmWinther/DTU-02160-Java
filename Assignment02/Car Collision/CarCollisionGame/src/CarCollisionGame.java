import java.util.Objects;
import java.util.Random;


// ...
// write here all missing classes
class Score{
    private int score;
    public void increment(){
        this.score++;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }

    public String toString(){
        return(Integer.toString(score));
    }

}
class Car{

    private int lives = 0;

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return(this.lives);
    }
    public boolean hasLives(){
        return(this.lives>0);
    }

    public void hit(Obstacle obstacle){
        this.lives +=obstacle.getIntensity();
    }

}

class Obstacle {
    private int intensity = 0;
    private int obstacleEffect = 0;
    public void setIntensity(int parameter){
        this.intensity= this.obstacleEffect * parameter;
    }

    public int getIntensity(){
        return this.intensity;
    }

    public void setEffect(int effect){
            this.obstacleEffect= effect;
    }
}

class Truck extends Obstacle{
    public Truck(){
        this.setEffect(-1);
    }
}
class Pillar extends Obstacle{
    public Pillar(){
        this.setEffect(-1);
    }
}
class Life extends Obstacle{
    public Life(){
        this.setEffect(1);
    }
}
// ...


public class CarCollisionGame {
    public static void main(String[] args) {

        Random random = new Random();
        if (args.length > 0) {
            random.setSeed(Long.parseLong(args[0]));
        }
        Car c = new Car();
        c.setLives(10);

        Score s = new Score();
        while(c.hasLives()) {
            if (random.nextDouble() < .75) {
                System.out.println("Ouch! Obstacle hit!");
                Obstacle o = null;
                double r = random.nextDouble();
                if (r < 0.4) {
                    o = new Truck(); // this should decrease the number of lives
                    System.out.println("  That was a truck!");
                } else if ( r > 0.6) {
                    o = new Pillar(); // this should decrease the number of lives
                    System.out.println("  That was a pillar!");
                } else {
                    o = new Life(); // this should increase the number of lives
                    System.out.println("  That was a new life! Hurray :)");
                }
                o.setIntensity(1 + random.nextInt(3));
                c.hit(o);
                System.out.println("  Car has now " + c.getLives() + " lives");
            } else {
                System.out.println("No obstacles hit");
            }
            s.increment();
        }

        System.out.println("Game over");
        System.out.println("Final score is " + s);
    }
}