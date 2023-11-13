import java.util.Random;

public class Dog extends Animal{
    public final int maxSwimDistance;
    public final int maxRunDistance;
    public final double maxJumpHeight;

    public Dog(String name) {
        super(name);
        var rand = new Random();
        maxRunDistance = 500 + rand.nextInt(100) - 50;
        maxJumpHeight = 0.5 + rand.nextDouble(0.5) - 0.25;
        maxSwimDistance = 10 + rand.nextInt(10) - 5;
    }

    public void run(int distance) {
        System.out.println(name + ".run(" + distance + ") -> результат: " + (distance > 0 && distance <= maxRunDistance));
    }

    public void jump(double height) {
        System.out.println(name + ".jump(" + height + ") -> результат: " + (height > 0 && height <= maxJumpHeight));
    }

    public void swim(int distance) {
        System.out.println(name + ".swim(" + distance + ") -> результат: " + (distance > 0 && distance <= maxSwimDistance));
    }
}
