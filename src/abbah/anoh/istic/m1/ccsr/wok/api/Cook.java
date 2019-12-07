package abbah.anoh.istic.m1.ccsr.wok.api;

/**
 * The cook is there to cook the guests' dishes once they have their plates
 * full of raw food taken from the buffet. The cook should be simulated as an
 * active entity: it must synchronize with clients (at the cooking) to cook
 * their dish one by one (without necessarily respecting the order of arrival
 * of customers at his stand). The cook waits for a client, cooks his dish
 * (he can only cook one at a time), notifies him once it is cooked and waits
 * for the next client.
 */
public interface Cook extends Runnable {
    /**
     * Is cooking the client dish.
     */
    void cooking();
}
