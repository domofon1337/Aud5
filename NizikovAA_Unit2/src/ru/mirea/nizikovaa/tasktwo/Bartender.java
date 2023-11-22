package ru.mirea.nizikovaa.tasktwo;

public class Bartender implements Servicable<Alcohol> {
    private String name;

    public Bartender(String name) {
        this.name = name;
    }

    public Alcohol serve() {
        return new Alcohol();
    }

    public String getName() {
        return name;
    }
}