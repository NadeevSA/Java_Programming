package maraphon;

public class Dog extends Animal {
    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, double maxJumpHeight, double maxRunLength) {
       this(name);
       this.maxJumpHeight = maxJumpHeight;
       this.maxRunLength = maxRunLength;
    }

    @Override
    public boolean jump(double height) {
        return this.maxJumpHeight >= height;
    }

    @Override
    public boolean run(double length) {
        return this.maxRunLength >= length;
    }

    @Override
    public boolean swim(double length) {
        return false;
    }
}
