package org.example;

public abstract class Animal implements IAnimal{
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void notify(String command, String result) {
        System.out.printf("%s -> %s: %s\n", this.getName(), command, result);
    }
}
