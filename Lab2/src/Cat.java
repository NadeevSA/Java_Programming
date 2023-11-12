public class Cat extends Animal{

    public Cat(String name, double maxRunLength, double maxJumpHeight) {
        super( name,  0, maxRunLength, maxJumpHeight);
    }

    public Cat(String name) {
        super( name,  0, 200, 2);
    }
}
