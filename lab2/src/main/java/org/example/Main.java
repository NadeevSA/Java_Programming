package org.example;

/**
 * @author BespoyasovaV
 */
public class Main {
    public static void main(String[] args) {
        Animal dog1 = new Dog("dog1");
        System.out.println(dog1.jump(1));
        // с ограничениями
        Animal dog2 = new Dog("dog2", 0, 50, 0.8);
        System.out.println(dog2.jump(0.7));
        Animal dog3 = new Dog("dog3", 0, 50, 0.6);
        System.out.println(dog3.jump(0.7));

        Animal cat1 = new Cat("cat1");
        System.out.println(cat1.swim(100));

        Animal cat2 = new Cat("cat2");
        System.out.println(cat2.jump(0.1));

    }
}