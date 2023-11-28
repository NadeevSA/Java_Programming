public class Animal {
    protected int runLimit;
    protected int jumpLimit;
    protected int swimLimit;

    public Animal(int runLimit, int jumpLimit, int swimLimit) {
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
        this.swimLimit = swimLimit;
    }

    public boolean run(int runDist) {
        return runDist <= runLimit;
    }

    public boolean jump(int jumpDist) {
        return jumpDist <= jumpLimit;
    }


    public boolean swim(int swimDist) {
        return swimDist <= swimLimit;
    }
}
