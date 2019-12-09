package abbah.anoh.istic.m1.ccsr.wok.api;

import abbah.anoh.istic.m1.ccsr.wok.core.Client;

/**
 * Is the main program. It has a limited number of places.
 */
public interface Restaurant {
    /**
     * Restaurant entrance.
     *
     * @param c The client who is entering.
     */
    void entrance(Client c);

    /**
     * A client is leaving the restaurant.
     *
     * @param c The client who is leaving.
     */
    void exit(Client c);

    /**
     * Is the buffet stand.
     *
     * @param client The client who wants to access the buffet.
     */
    void buffet(Client client);

    /**
     * Watches the bins
     */
    void watchBuffet();

    /**
     * Is the cooking stand.
     *
     * @param c The client who is waiting for the cook.
     */
    void cookingStand(Client c);

    /**
     * Has finished cooking.
     */
    void cooking();
}
