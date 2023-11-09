public class Cat extends Animal {

    public Cat(String name) {
        super(name);

        double _maxDeviation = 50;
        double _runLimitDefault = 200;
        double _jumpLimitDefault = 2;

        this.setRunLimit(super.getDeviatedValue(_runLimitDefault, _maxDeviation));
        this.setJumpLimit(super.getDeviatedValue(_jumpLimitDefault, _maxDeviation));
    }

    public Cat(String name, double runLimit, double jumpLimit) {
        super(name);
        this.setRunLimit(runLimit);
        this.setJumpLimit(jumpLimit);
    }

    @Override
    public void swim(double distance) {
        throw new IllegalArgumentException("Коты боятся воды(");
    }
}
