package org.example;

import java.util.Random;

public class Dog extends Animal {
    protected int runLimit;
    protected int swimLimit;
    protected double jumpLimit;

    public Dog(String name) {
        super(name);
        Random rand = new Random();

        int RUN_FROM = 500;
        int RUN_TO = 800;
        runLimit = rand.nextInt(RUN_FROM, RUN_TO);

        int SWIM_FROM = 5;
        int SWIM_TO = 15;
        swimLimit = rand.nextInt(SWIM_FROM, SWIM_TO);

        double JUMP_FROM = 0.3;
        double JUMP_TO = 1;
        jumpLimit = rand.nextDouble(JUMP_FROM, JUMP_TO);
    }

    public Dog(String name, int runLimit, int swimLimit, double jumpLimit) {
        super(name);
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        this.jumpLimit = jumpLimit;
    }

    public void run(int length) {
        if (length > this.runLimit) {
            this.notify("run", "unsuccessful");
            return;
        }

        this.notify("run", "unsuccessful");
    }

    public void swim(int length) {
        if (length > this.swimLimit) {
            this.notify("swim", "unsuccessful");
            return;
        }

        this.notify("swim", "ok");
    }

    public void jump(double length) {
        if (length > this.jumpLimit) {
            this.notify("jump", "unsuccessful");
            return;
        }

        this.notify("jump", "ok");
    }
}
