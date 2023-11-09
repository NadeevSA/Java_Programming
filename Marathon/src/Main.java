import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final String[] existingAnimals = {"cat", "dog"};
        final int animalsNum = 10;
        Animal[] animals = new Animal[animalsNum];

        for (int i = 0; i < animalsNum; i++) {
            Random random = new Random();
            int animalType = random.nextInt(existingAnimals.length);
            Animal animal = null;

            switch (animalType) {
                case 0:
                    animal = new Cat(String.format("кошка%d", i));
                    break;
                case 1:
                    animal = new Dog(String.format("собачка%d", i));
                    break;
            }


            // Беговая дорожка
            for (int j = 0; j < 5; j++) {
                double runLimit = animal.getRunLimit();
                double distance = random.nextDouble(runLimit);
                animal.run(distance);
                animal.addScore(distance);
            }

            // Прыжки
            for (int j = 0; j < 5; j++) {
                double jumpLimit = animal.getJumpLimit();
                double distance = random.nextDouble(jumpLimit);
                animal.jump(distance);
                animal.addScore(distance);
            }
        }

        Animal winner = animals[0];

        for (int i = 1; i < animalsNum; i++) {
            if (animals[i].getScore() > winner.getScore()) {
                winner = animals[i];
            }
        }

        System.out.println(
                String.format("Победило животное по кличке %s, заработав суммарно %f очков!",
                        winner.getName(),
                        winner.getScore())
        );
    }
}
