package abbah.anoh.istic.m1.ccsr.wok.api;


/**
 * Watches the Restaurant buffet stand.
 * The employee is responsible for ensuring that there is enough food left
 * in each bin. To do this, he goes around the bins to check the remaining
 * quantities of each: if a bin has less than 100g of remaining food, he
 * fills it (so that his new quantity is again 1kg). The employee is never
 * short of food. It is important to ensure that the employee does not
 * fill a bin while a customer is using (except of course, if this
 * customer is waiting for it to be filled.)
 */
public interface Employee extends Runnable {
    /**
     * Goes around the bins to check the remaining quantities of each.
     */
    void watch();
}
