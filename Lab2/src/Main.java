import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        var animals = new ArrayList<Animal>();
        animals.add(new Dog("Dog1", 1.5,350,12));
        animals.add(new Dog("Dog2"));
        animals.add(new Dog("Dog3", 0.5,600,10));
        animals.add(new Cat("Cat1", 200,2));
        animals.add(new Cat("Cat2"));
        animals.add(new Cat("Cat3", 250,1.4));

        for (var animal: animals) {
            var runDistance = 400;
            var jumpDistance = 1.5;
            var swimDistance = 10;
            System.out.println(animal.getName() + ".run(" + runDistance + ");->результат: run: " + animal.run(runDistance));
            System.out.println(animal.getName() + ".jump(" + jumpDistance + ");->результат: jump: " + animal.jump(jumpDistance));
            System.out.println(animal.getName() + ".swim(" + swimDistance + ");->результат: swim: " + animal.jump(swimDistance));
        }
    }
}
