import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        var animals = new ArrayList<Animal>(5);
        animals.add(new Cat("Myrsik", 200, 2));
        animals.add(new Cat("Garfield", 300, 1.5));
        animals.add(new Dog("Sharik", 500, 0.5, 10));
        animals.add(new Dog("SobakaVkurtke", 550, 1, 12));
        animals.add(new Dog("Bobik", 450, 1.5, 15));

        // Бежим
        System.out.println("1 stage");
        double distRun = ThreadLocalRandom.current().nextDouble(150, 550);
        for (Animal animal : animals.stream().toList()) {
            if (animal.Run(distRun)){
                animals.remove(animal);
            }
        }

        //Препядствия
        System.out.println("2 stage");
        for (int i = 0; i < 3; ++i){
            System.out.println("barrier " + i);
            double distJump = ThreadLocalRandom.current().nextDouble(0.3, 2);
            for (Animal animal : animals.stream().toList()) {
                if (animal.Jump(distJump)){
                    animals.remove(animal);
                }
            }
        }

        // Плыть
        System.out.println("3 stage");
        double distSwim = ThreadLocalRandom.current().nextDouble(7, 15);
        for (Animal animal : animals.stream().toList()) {
            if (animal.Swim(distSwim)){
                animals.remove(animal);
            }
        }

        System.out.println("WINNERS:");
        for (Animal animal : animals.stream().toList()) {
            System.out.println(animal.nickname);
        }
    }
}