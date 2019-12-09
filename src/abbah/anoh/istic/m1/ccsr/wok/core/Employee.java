package abbah.anoh.istic.m1.ccsr.wok.core;

import abbah.anoh.istic.m1.ccsr.wok.api.Restaurant;

public class Employee extends Thread {

    private final Restaurant restaurant;

    public Employee(final Restaurant r) {
        restaurant = r;
        setDaemon(true);
    }

    /**
     * Goes around the bins to check the remaining quantities of each.
     */
    public void watch() {
        while (true) {
            this.restaurant.watchBuffet();
        }
    }

    @Override
    public void run() {
        this.watch();
    }
}
