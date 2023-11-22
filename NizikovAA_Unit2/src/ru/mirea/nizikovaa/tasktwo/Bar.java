package ru.mirea.nizikovaa.tasktwo;

public class Bar {
    private Bartender bartender;
    private Waitress waitress;
    private Bouncer bouncer;

    public Bar(Bartender bartender, Waitress waitress, Bouncer bouncer) {
        this.bartender = bartender;
        this.waitress = waitress;
        this.bouncer = bouncer;
    }

    public Alcohol makeOrder(int age) {
        return bartender.serve();
    }

    public Food makeOrder() {
        return waitress.serve();
    }

    public void sleep() {
    }

    public void kickoutBoozer() {
        bouncer.boozerOut();
    }
}