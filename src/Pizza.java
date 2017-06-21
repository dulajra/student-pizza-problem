import java.util.ArrayList;
import java.util.List;

/**
 * @author Dulaj Atapattu
 */
class Pizza {

    static int s;

    private volatile List<Integer> slices;

    /**
     * New pizza is created with s slices.
     * S is given at the run time by the user.
     */
    Pizza() {
        slices = new ArrayList<>();

        for (int i = 0; i < s; i++) {
            slices.add(i + 1);
        }
    }

    /**
     * Check the whether slices are available.
     * If slice are available return the number of the selected slice.
     * This method access the shared variable 'slices' concurrently.
     * So the method has been made synchronized guarantee the mutual exclusion.
     *
     * @return If the pizza is not empty, the selected slice number. Else -1
     */
    synchronized int pickSlice() {
        if (slices.size() > 0) {
            return slices.remove(0);
        } else {
            return -1;
        }
    }

}