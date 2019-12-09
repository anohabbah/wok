package abbah.anoh.istic.m1.ccsr.wok.core;

import abbah.anoh.istic.m1.ccsr.wok.api.Restaurant;

public class Cook extends Thread {
    private final Restaurant restaurant;

    public Cook(final Restaurant r) {
        restaurant = r;
        setDaemon(true);
    }

    /**
     * Is cooking the client dish.
     */
    public void cooking() {
        while (true) {
            this.restaurant.cooking();
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        this.cooking();
    }
}
