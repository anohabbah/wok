package abbah.anoh.istic.m1.ccsr.wok.api;

/**
 * The client, it will have to be simulated as an autonomous entity.
 */
public interface Client extends Runnable {
    /**
     * Is trying to access the restaurant.
     */
    void entering();

    /**
     * Is at the buffet stand.
     */
    void goToBuffet();

    /**
     * Is at the cooking stand.
     */
    void waitAtCooking();

    /**
     * Is eating.
     */
    void eating();

    /**
     * Is leaving the restaurant.
     */
    void exit();
}
