package abbah.anoh.istic.m1.ccsr.wok.core;

import abbah.anoh.istic.m1.ccsr.wok.api.Restaurant;

import static abbah.anoh.istic.m1.ccsr.wok.utils.Utils.randomInt;

public class Client extends Thread {
    private static int clientInitNumber;
    /**
     * The restaurant to go.
     */
    private final Restaurant restaurant;
    /**
     * The meat quantity taken.
     */
    private int meat;
    /**
     * The fish quantity taken.
     */
    private int fish;
    /**
     * The noodle quantity taken.
     */
    private int noodle;
    /**
     * The vegetable quantity taken.
     */
    private int vegetable;

    /**
     * Creates a new client and sets the restaurant where he is going.
     *
     * @param r The restaurant.
     */
    public Client(final Restaurant r) {
        super(null, null, "Client-" + nextClientNum());

        restaurant = r;
        this.fish = -1;
        this.meat = -1;
        this.noodle = -1;
        this.vegetable = -1;
    }

    private synchronized static int nextClientNum() {
        return clientInitNumber++;
    }

    /**
     * Is trying to access the restaurant.
     */
    public void entering() {
        this.restaurant.entrance(this);
    }

    /**
     * Is at the buffet stand.
     */
    public void goToBuffet() {
        while (this.meat == -1 || this.fish == -1
                || this.noodle == -1 || this.vegetable == -1) {
            this.restaurant.buffet(this);
        }
    }

    /**
     * Is at the cooking stand.
     */
    public void goToCooking() {
        this.restaurant.cookingStand(this);
    }

    /**
     * Is eating.
     */
    public void eating() {
        try {
            Thread.sleep(randomInt(0, 300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Is leaving the restaurant.
     */
    public void exit() {
        this.restaurant.exit(this);
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
        this.entering();
        this.goToBuffet();
        this.goToCooking();
        this.eating();
        this.exit();
    }

    public int getMeat() {
        return this.meat;
    }

    public void setMeat(int m) {
        this.meat = m;
    }

    public int getFish() {
        return this.fish;
    }

    public void setFish(int f) {
        this.fish = f;
    }

    public int getNoodle() {
        return this.noodle;
    }

    public void setNoodle(int n) {
        this.noodle = n;
    }

    public int getVegetable() {
        return this.vegetable;
    }

    public void setVegetable(int v) {
        this.vegetable = v;
    }
}
