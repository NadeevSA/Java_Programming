package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        IAnimal[] animals = new IAnimal[]{
                new Dog("dog1"),
                new Dog("dog2"),
                new Dog("dog3"),
                new Dog("dog4"),
                new Cat("cat1"),
                new Cat("cat2"),
                new Cat("cat3"),
                new Cat("cat4")
        };

        Random rand = new Random();

        for (IAnimal a: animals) {
            a.run(rand.nextInt(300, 1000));
            a.jump(rand.nextDouble(0.1, 4));
            a.swim(rand.nextInt(3, 30));
        }
    }
}