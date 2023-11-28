public class Main {
    public static void start(Cat cat, Dog dog, int runDist, int jumpDist, int swimDist) {
        System.out.println("CAT Finished: " + (cat.run(runDist) && cat.jump(jumpDist) && cat.swim(swimDist)));
        System.out.println("DOG Finished: " + (dog.run(runDist) && dog.jump(jumpDist) && dog.swim(swimDist)));
    }

    public static void main(String[] args) {
//        ДЗ:
//        1 Создать классы Собака и Кот с наследованием от класса Животное.
//        2 Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина,
//        означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
//        3 У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
//        4 При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
//        TODO 5 * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.

        Cat cat = new Cat();
        Dog dog = new Dog();

        start(cat, dog, 100, 100, 100);
    }
}