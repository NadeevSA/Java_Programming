public abstract class Animal {

    // Имя животного
    protected String name;

    // Максимально возможная длина прыжка
    protected double maxJumpHeight;

    // Максимально возможная длина забега
    protected double maxRunLength;

    // Максимально возможная длина заплыва
    protected double maxSwimLength;

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public double getMaxRunLength() {
        return maxRunLength;
    }

    public double getMaxSwimLength() {
        return maxSwimLength;
    }

    public String getName() {
        return name;
    }

    public Animal(String name, double maxSwimLength, double maxRunLength, double maxJumpHeight) {
           this.maxJumpHeight = maxJumpHeight;
           this.maxRunLength = maxRunLength;
           this.maxSwimLength = maxSwimLength;
           this.name = name;
    }

    public boolean jump(double height) {
        return maxJumpHeight >= height;
    }

    public boolean run(double length) {
        return maxRunLength >= length;
    }

    public boolean swim(double length) {
        return maxSwimLength >= length;
    }
}
