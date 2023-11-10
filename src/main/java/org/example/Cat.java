package org.example;

import java.util.Random;

public class Cat extends Animal {
    protected int runLimit;
    protected double jumpLimit;

    public Cat(String name) {
        super(name);
        Random rand = new Random();

        int RUN_FROM = 200;
        int RUN_TO = 400;
        runLimit = rand.nextInt(RUN_FROM, RUN_TO);

        double JUMP_FROM = 1.5;
        double JUMP_TO = 3;
        jumpLimit = rand.nextDouble(JUMP_FROM, JUMP_TO);
    }

    public Cat(String name, int runLimit, double jumpLimit)  {
        super(name);
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    public void run(int length) {
        if (length > this.runLimit) {
            this.notify("run", "unsuccessful");
            return;
        }

        this.notify("run", "ok");
    }

    public void swim(int length) {
        this.notify("swim", "cannot");
    }

    public void jump(double length) {
        if (length > jumpLimit) {
            this.notify("jump", "unsuccessful");
            return;
        }

        this.notify("jump", "ok");
    }
}
