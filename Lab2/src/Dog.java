public class Dog extends Animal{

    public Dog(String name, double maxJumpHeight, double maxRunLength, double maxSwimLength) {
        super( name,  maxSwimLength, maxRunLength, maxJumpHeight);
    }

    public Dog(String name) {
        super( name,  10, 500, 0.5);
    }
}
