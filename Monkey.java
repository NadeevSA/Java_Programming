package maraphon;

public class Monkey extends Animal {
    public Monkey(String name) {
        this.name = name;
    }

    public Monkey(String name, double maxJumpHeight, double maxRunLength, double maxSwimLength) {
       this(name);
       this.maxJumpHeight = maxJumpHeight;
       this.maxRunLength = maxRunLength;
       this.maxSwimLength = maxSwimLength;
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
        return this.maxSwimLength >= length;
    }
}
