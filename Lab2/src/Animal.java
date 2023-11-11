public abstract class Animal {
    String nickname;
    double maxDistRun;
    double maxJumpHeight;

    public Animal(String nickname, double maxDistance, double maxHeight) {
        maxDistRun = maxDistance;
        maxJumpHeight = maxHeight;
        this.nickname = nickname;
    }

    abstract boolean Run(double distance);
    abstract boolean Jump(double height);
    abstract boolean Swim(double distance);
}
