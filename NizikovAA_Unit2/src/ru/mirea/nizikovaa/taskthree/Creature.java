package ru.mirea.nizikovaa.taskthree;

public class Creature extends GameObject implements Moveable {
    private int hp;

    public Creature() {
    }

    public int getHp() {
        return hp;
    }

    public void move(Position position) {
    }

    public String getName() {
        return super.getName();
    }
}
