public class Dog extends Animal {

    public Dog(String name) {
        super(name);

        double _maxDeviation = 50;
        double _runLimitDefault = 250;
        double _jumpLimitDefault = 0.5;
        double _swimLimitDefault = 10;

        this.setRunLimit(super.getDeviatedValue(_runLimitDefault, _maxDeviation));
        this.setJumpLimit(super.getDeviatedValue(_jumpLimitDefault, _maxDeviation));
        this.setSwimLimit(super.getDeviatedValue(_swimLimitDefault, _maxDeviation));
    }

    public Dog(String name, double runLimit, double jumpLimit, double swimLimit) {
        super(name);
        this.setRunLimit(runLimit);
        this.setJumpLimit(jumpLimit);
        this.setSwimLimit(swimLimit);
    }
}
