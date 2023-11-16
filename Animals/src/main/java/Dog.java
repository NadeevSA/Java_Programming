public class Dog extends Animal {
    public double max_run = 500;
    public double max_jump = 0.5;
    public double max_swim = 10;

    public Dog(String name){
        super(name);
        super.max_run = max_run;
        super.max_jump = max_jump;
        super.max_swim = max_swim;
    }
}
