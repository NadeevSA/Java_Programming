package maraphon;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Dolphin("Yappy", 2, 800));
        animals.add(new Dolphin("Gimmy", 1.6, 920));
        animals.add(new Dog("Gromp", 1.2, 440));
        animals.add(new Dog("Richard", 1.0, 400));
        animals.add(new Monkey("Sally", 0.8, 320, 250));
        animals.add(new Monkey("Sunshine", 1.2, 250, 250));

        ArrayList<MaraphonType>[] maraphonTypes = new ArrayList[] {
                new ArrayList<MaraphonType>(),
                new ArrayList<MaraphonType>(),
                new ArrayList<MaraphonType>(),
        };
        maraphonTypes[0].add(MaraphonType.Run);
        maraphonTypes[0].add(MaraphonType.Jump);
        maraphonTypes[1].add(MaraphonType.Run);
        maraphonTypes[1].add(MaraphonType.Swim);
        maraphonTypes[2].add(MaraphonType.Jump);
        maraphonTypes[2].add(MaraphonType.Swim);

        for (var mp : maraphonTypes) {
            new Maraphon(mp, 350, 500, 1.1)
                    .start(animals);
        }
    }
}