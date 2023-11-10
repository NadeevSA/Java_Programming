package org.example;

import java.util.ArrayList;

/**
 * @author BespoyasovaV
 */
public class Main {


    public static void main(String[] args) {
        Animal dog1 = new Dog("dog1");
        Animal dog2 = new Dog("dog2", 0, 50, 0.8);
        Animal dog3 = new Dog("dog3", 0, 50, 0.6);

        Animal cat1 = new Cat("cat1");


        Animal cat2 = new Cat("cat2");

        Animal[] animals = {dog1, dog2, dog3, cat1, cat2};
        for (int i = 0; i < animals.length; i++) {
            System.out.println(animals[i].jump(0.8));
            System.out.println(animals[i].swim(2));
            System.out.println(animals[i].run(100));
        }
    }
}