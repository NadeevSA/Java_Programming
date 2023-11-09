package org.example;

/**
 * @author BespoyasovaV
 * класс собак
 */
public class Dog extends Animal {
    private String name;
    /**
     * Ограничения
     */
    public int runLimitation;
    public int swimLimitation;
    public double jumpLimitation;

    public int getRunLimitation() {
        return runLimitation;
    }

    public double getJumpLimitation() {
        return jumpLimitation;
    }

    public int getSwimLimitation() {
        return swimLimitation;
    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Dog(String name, int runLimitation, int swimLimitation, double jumpLimitation) {
        this.name = name;
        this.runLimitation = runLimitation;
        this.swimLimitation = swimLimitation;
        this.jumpLimitation = jumpLimitation;
    }

    @Override
    public String run(int length) {
        if (this.getRunLimitation() == 0) {
            if (length > 500) {
                return this.getName() + ".run(" + length + ");->результат: run: false";
            } else return this.getName() + ".run(" + length + ");->результат: run: true";
        } else {
            if (length > this.getRunLimitation()) {
                return this.getName() + ".run(" + length + ");->результат: run: false";
            } else return this.getName() + ".run(" + length + ");->результат: run: true";

        }
    }

    @Override
    public String swim(int length) {
        if (this.getSwimLimitation() == 0) {
            if (length > 10) {
                return this.getName() + ".swim(" + length + ");->результат: swim: false";
            } else return this.getName() + ".swim(" + length + ");->результат: swim: true";
        } else {
            if (length > this.getSwimLimitation()) {
                return this.getName() + ".swim(" + length + ");->результат: swim: false";
            } else return this.getName() + ".swim(" + length + ");->результат: swim: true";

        }
    }

    @Override
    public String jump(double length) {
        if (this.getJumpLimitation() == 0) {
            if (length > 0.5) {
                return this.getName() + ".jump(" + length + ");->результат: jump: false";
            } else return this.getName() + ".jump(" + length + ");->результат: jump: true";
        } else {
            if (length > this.getJumpLimitation()) {
                return this.getName() + ".jump(" + length + ");->результат: jump: false";
            } else return this.getName() + ".jump(" + length + ");->результат: jump: true";

        }
    }
}
