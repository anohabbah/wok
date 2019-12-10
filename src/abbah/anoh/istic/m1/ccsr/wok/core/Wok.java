package abbah.anoh.istic.m1.ccsr.wok.core;

import abbah.anoh.istic.m1.ccsr.wok.api.Restaurant;

import java.util.Objects;
import java.util.Random;

import static abbah.anoh.istic.m1.ccsr.wok.utils.Utils.randomInt;

public class Wok implements Restaurant {
    /**
     * The maximum number of clients the restaurant can take.
     */
    private static final int MAX_SIZE = 25;

    /**
     * The maximum volume of bins in grams (g).
     */
    private static final int MAX_BINS_VOLUME = 1000;

    /**
     * The minimum volume of bins in grams (g).
     */
    private static final int MIN_BINS_VOLUME = 100;

    /**
     * The current fish bin volume, initializes at the maximum volume.
     */
    private int fishBin = MAX_BINS_VOLUME;

    private boolean shouldEmployeeIdle = true;

    /**
     * The current noodle bin volume, initializes at the maximum volume.
     */
    private int noodleBin = MAX_BINS_VOLUME;

    /**
     * The current meat bin volume, initializes at the maximum volume.
     */
    private int meatBin = MAX_BINS_VOLUME;

    /**
     * The current vegetable bin volume, initializes at the maximum volume.
     */
    private int vegetableBin = MAX_BINS_VOLUME;

    /**
     * The total number of clients present in the restaurant.
     * Is initialize at {@code 0}.
     */
    private int clientsCount;

    /**
     * The client whose cook is cooking the dish.
     */
    private abbah.anoh.istic.m1.ccsr.wok.core.Client clientAtCooking;

    public Wok() {
        this.clientsCount = 0;
    }

    /**
     * Restaurant entrance.
     *
     * @param c The client who is entering.
     */
    @Override
    public synchronized void entrance(Client c) {
        while (this.clientsCount >= Wok.MAX_SIZE) {
            try {
                System.out.printf("The %s is waiting at the entrance.\n", c.getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("The %s is entered.\n", c.getName());
        ++this.clientsCount;
    }

    /**
     * A client is leaving the restaurant.
     *
     * @param c The client who is leaving.
     */
    @Override
    public synchronized void exit(Client c) {
        System.out.printf("The %s leaved the restaurant.\n", c.getName());
        --this.clientsCount;
        notifyAll();
    }

    /**
     * Is the buffet stand.
     *
     * @param client The client who wants to access the buffet.
     */
    @Override
    public void buffet(Client client) {
        try {
            if (client.getMeat() == -1)
                useMeatBin(client, randomInt(0, 100));
            if (client.getFish() == -1)
                fishBin(client, randomInt(0, 100));
            if (client.getNoodle() == -1)
                noodleBin(client, randomInt(0, 100));
            if (client.getVegetable() == -1)
                vegetableBin(client, randomInt(0, 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void useMeatBin(final Client client, final int quantity) throws InterruptedException {
        while (quantity > this.meatBin) {
            this.shouldEmployeeIdle = false;
            notifyAll();
            System.out.printf("The %s is waiting for the employee to fill the meat bin.\n", client.getName());
            wait();
        }

        this.meatBin -= quantity;
        client.setMeat(quantity);
        System.out.printf("The %s is using the meat bin.\n", client.getName());
        this.sleep();
    }

    private synchronized void fishBin(final Client client, final int quantity) throws InterruptedException {
        while (quantity > this.fishBin) {
            this.shouldEmployeeIdle = false;
            notifyAll();
            System.out.printf("The %s is waiting for the employee to fill the fish bin.\n", client.getName());
            wait();
        }

        this.fishBin -= quantity;
        client.setFish(quantity);
        System.out.printf("The %s is using the fish bin.\n", client.getName());
        this.sleep();
    }

    private synchronized void noodleBin(final Client client, final int quantity) throws InterruptedException {
        while (quantity > this.noodleBin) {
            this.shouldEmployeeIdle = false;
            notifyAll();
            System.out.printf("The %s is waiting for the employee to fill noodles bin.\n", client.getName());
            wait();
        }

        this.noodleBin -= quantity;
        client.setNoodle(quantity);
        System.out.printf("The %s is using noodles bin.\n", client.getName());
        this.sleep();
    }

    private synchronized void vegetableBin(final Client client, final int quantity) throws InterruptedException {
        while (quantity > this.vegetableBin) {
            this.shouldEmployeeIdle = false;
            notifyAll();
            System.out.printf("The %s is waiting for the employee to fill vegetables bin.\n", client.getName());
            wait();
        }

        this.vegetableBin -= quantity;
        client.setVegetable(quantity);
        System.out.printf("The %s is using vegetables bin.\n", client.getName());
        this.sleep();
    }

    /**
     * Watches the bins
     */
    @Override
    public synchronized void watchBuffet() {
        while (shouldEmployeeIdle) {
            try {
                System.out.println("The buffet employee is watching.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        fillFishBin();
        fillMeatBin();
        fillNoodleBin();
        fillVegetableBin();

        this.shouldEmployeeIdle = true;
        notifyAll();
    }

    private synchronized void fillMeatBin() {
        if (this.meatBin <= Wok.MIN_BINS_VOLUME) {
            System.out.print("The buffet employee is filling the meat bin.\n");
            this.meatBin = Wok.MAX_BINS_VOLUME;
        }
    }

    private synchronized void fillFishBin() {
        if (this.fishBin <= Wok.MIN_BINS_VOLUME) {
            System.out.print("The buffet employee is filling the fish bin.\n");
            this.fishBin = Wok.MAX_BINS_VOLUME;
        }
    }

    private synchronized void fillNoodleBin() {
        if (this.noodleBin <= Wok.MIN_BINS_VOLUME) {
            System.out.print("The employee is filling noodles bin.\n");
            this.noodleBin = Wok.MAX_BINS_VOLUME;
        }
    }

    private synchronized void fillVegetableBin() {
        if (this.vegetableBin <= Wok.MIN_BINS_VOLUME) {
            System.out.print("The employee is filling vegetables bin.\n");
            this.vegetableBin = Wok.MAX_BINS_VOLUME;
        }
    }

    /**
     * Is the cooking stand.
     *
     * @param client The client who is waiting for the cook.
     */
    public void cookingStand(Client client) {
        enqueue(client);
        atCooking(client);
    }

    private synchronized void enqueue(final Client client) {
        while (Objects.nonNull(this.clientAtCooking)) {
            System.out.printf("The %s is lining up at the cooking booth.\n", client.getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        clientAtCooking = client;
    }

    private boolean isCooking = false;
    private synchronized void atCooking(final Client client) {
        isCooking = true;
        notifyAll();
        while (isCooking) {
            System.out.printf("The %s is waiting at the cooking.\n", client.getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        clientAtCooking = null;
        notifyAll();
        System.out.printf("The %s is eating.\n", client.getName());
    }

    /**
     * Has finished cooking.
     */
    @Override
    public synchronized void cooking() {
        try {
            while (!isCooking) {
                System.out.println("The cook is waiting for a client.");
                wait();
            }

            System.out.printf("The cook is cooking %s dish.\n", clientAtCooking.getName());
            Thread.sleep(new Random().nextInt(1000));
            System.out.printf("The cook has finished the %s dish.\n", clientAtCooking.getName());
            this.isCooking = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(randomInt(200, 300));
    }
}
