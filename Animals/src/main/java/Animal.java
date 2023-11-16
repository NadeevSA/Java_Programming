
abstract class Animal {
    public String name;
    public double max_run = 0;
    public double max_swim = 0;
    public double max_jump = 0;

    public Animal(String name){
        this.name = name;
    }
    private void DoAction(double distance, String message, double max) {
        if (distance > 0 && distance <= max)
            System.out.println(String.format("%s %s %f м", this.name, message, distance));
        else
            System.out.println(String.format("%s %s %f", this.name, ": Беда, дистанция неправильная! ->", distance));
    }
    public void Run(double distance) {
        DoAction(distance, "плывёт", this.max_run);
    }
    public void Swim(double distance) {
        DoAction(distance, "плывёт", this.max_swim);
    }
    public void Jump(double distance) {
        DoAction(distance, "прыгает", this.max_jump);
    }
}
