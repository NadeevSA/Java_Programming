package maraphon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Maraphon {
    private ArrayList<MaraphonType> maraphonTypes;
    private double runDistance;
    private double swimDistance;
    private double jumpDistance;

    public Maraphon(ArrayList<MaraphonType> maraphonTypes, double runDistance, double swimDistance, double jumpDistance) {
        this.maraphonTypes = maraphonTypes;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        this.jumpDistance = jumpDistance;
    }

    private void swimEvent(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            System.out.print("Swim (distance: " + runDistance + "): ");
            if (animal.swim(swimDistance)) {
                System.out.println(animal.name + " finished!");
            } else {
                System.out.println(animal.name + " loos! (distance: " + animal.maxSwimLength + ")");
            }
        }
    }

    private void runEvent(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            System.out.print("Run (distance: " + runDistance + "): ");
            if (animal.run(runDistance)) {
                System.out.println(animal.name + " finished!");
            } else {
                System.out.println(animal.name + " loos! (distance: " + animal.maxRunLength + ")");
            }
        }
    }

    private void jumpEvent(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            System.out.print("Jump (distance: " + jumpDistance + "): ");
            if (animal.jump(jumpDistance)) {
                System.out.println(animal.name + " finished!");
            } else {
                System.out.println(animal.name + " loos! (distance: " + animal.maxJumpHeight + ")");
            }
        }
    }

    public void start(ArrayList<Animal> animals) {
        for (var maraphonType : this.maraphonTypes) {
            switch (maraphonType) {
                case Run -> runEvent(animals);
                case Jump -> jumpEvent(animals);
                case Swim -> swimEvent(animals);
                default -> throw new ClassCastException();
            }
        }
    }
}
