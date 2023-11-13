public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[6];

        animals[0] = new Cat("cat1");
        animals[1] = new Cat("cat2");
        animals[2] = new Cat("cat3");
        animals[3] = new Dog("dog1");
        animals[4] = new Dog("dog2");
        animals[5] = new Dog("dog3");

        for (int i = 0; i < animals.length; i++) {
            animals[i].run(300);
            animals[i].jump(2);
            animals[i].swim(15);
            System.out.println();
        }
    }
}