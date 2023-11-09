import java.util.Random;

public abstract class Animal {
    private String _name;
    private double _runLimit;
    private double _swimLimit;
    private double _jumpLimit;
    private double _score;

    public Animal(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public double getRunLimit() {
        return this._runLimit;
    }

    public void setRunLimit(double limit) {
        if (limit > 0)
            this._runLimit = limit;
        else
            System.out.println("Число должно быть больше 0!");
    }

    public double getSwimLimit() {
        return this._swimLimit;
    }

    public void setSwimLimit(double limit) {
        if (limit > 0)
            this._swimLimit = limit;
        else
            System.out.println("Число должно быть больше 0!");
    }

    public double getJumpLimit() {
        return this._jumpLimit;
    }

    public void setJumpLimit(double limit) {
        if (limit > 0)
            this._jumpLimit = limit;
        else
            System.out.println("Число должно быть больше 0!");
    }

    public double getScore() {
        return this._score;
    }

    public void addScore(double score) {
        if (score > 0)
            this._score += score;
        else
            System.out.println("Число должно быть больше 0!");
    }

    private void doAction(double distance, double limit, String actionName) {
        if (distance > 0 && distance <= limit)
            System.out.println(String.format("%s %s %f м", this._name, actionName, distance));
        else
            System.out.println(String.format("Дистанция должна быть в диапазоне (0; %f]", limit));
    }

    public void run(double distance) {
        doAction(distance, this._runLimit, "пробегает");
    }

    public void swim(double distance) {
        doAction(distance, this._swimLimit, "проплывает");
    }

    public void jump(double distance) {
        doAction(distance, this._jumpLimit, "прыгает на");
    }

    protected double getDeviatedValue(double value, double maxDeviation) {
        if (maxDeviation <= 0 || maxDeviation >= 100)
        {
            System.out.println("Отклонение должно лежать в пределах от 0 до 100");

            return value;
        }

        Random random = new Random();
        double deviationInPercent = random.nextDouble(maxDeviation);

        return value + value / 100 * deviationInPercent;
    }
}
