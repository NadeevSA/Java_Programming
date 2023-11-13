import java.util.Random;

public class Cat extends Animal {
    public final int maxRunDistance;
    public final double maxJumpHeight;

    public Cat(String name) {
        super(name);
        var rand = new Random();
        maxRunDistance = 200 + rand.nextInt(100) - 50;
        maxJumpHeight = 3 + rand.nextDouble(4) - 2;
    }

    public void run(int distance) {
        System.out.println(name + ".run(" + distance + ") -> результат: " + (distance > 0 && distance <= maxRunDistance));
    }

    public void jump(double height) {
        System.out.println(name + ".jump(" + height + ") -> результат: " + (height > 0 && height <= maxJumpHeight));
    }

    public void swim(int distance) {
        System.out.println(name + " не умеет плавать");
    }
}
