package org.example;

/**
 * @author BespoyasovaV
 * класс котов
 */
public class Cat extends Animal {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String run(int length) {
        if (length > 200) {
            return this.getName() + ".run(" + length + ");->результат: run: false";
        } else return this.getName() + ".run(" + length + ");->результат: run: true";
    }

    @Override
    public String swim(int length) {
        return this.getName() + " Не умеет плавать";
    }

    @Override
    public String jump(double length) {
        if (length > 2) {
            return this.getName() + ".jump(" + length + ");->результат: jump: false";
        } else return this.getName() + ".jump(" + length + ");->результат: jump: true";
    }
}
