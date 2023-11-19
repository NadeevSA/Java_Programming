package maraphon;

public class Dolphin extends Animal {
    public Dolphin(String name) {
        this.name = name;
    }

    public Dolphin(String name, double maxJumpHeight, double maxSwimLength) {
       this(name);
       this.maxJumpHeight = maxJumpHeight;
       this.maxSwimLength = maxSwimLength;
    }

    @Override
    public boolean jump(double height) {
        return this.maxJumpHeight >= height;
    }

    @Override
    public boolean run(double length) {
        return false;
    }

    @Override
    public boolean swim(double length) {
        return this.maxSwimLength >= length;
    }
}
