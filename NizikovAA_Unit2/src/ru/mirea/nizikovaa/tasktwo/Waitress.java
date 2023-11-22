package ru.mirea.nizikovaa.tasktwo;

public class Waitress implements Servicable<Food> {
    private String name;

    public Waitress(String name) {
        this.name = name;
    }

    public Food serve() {
        return new Food();
    }

    public String getName() {
        return name;
    }
}