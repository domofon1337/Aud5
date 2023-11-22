package ru.mirea.nizikovaa.tasktwo;

public interface Servicable<T> {
    T serve();
    String getName();
}