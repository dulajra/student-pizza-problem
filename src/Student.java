import java.util.Random;

/**
 * @author Dulaj Atapattu
 */
class Student extends Thread {

    private static final Random random = new Random();

    private String name;
    private final Group group;

    Student(String name, Group group) {
        super(name);
        this.name = name;
        this.group = group;
    }

    /**
     * This method is called by each student thread continously.
     */
    private void eat() {
        // Try to get a pizza slice
        int sliceNo = group.getPizza().pickSlice();

        // If the pizza is empty, -1 will get as the slice number
        if (sliceNo == -1) {
            try {
                /*
                Shared variable deliveryCalled is accessed here. So a synchronized block is used for mutual exclusion.
                All student threads are synchronized using the group object's monitor.
                 */
                synchronized (group) {
                    if (!group.isDeliveryCalled()) {
                        group.notify();     // Awake the currently sleeping delivery thread.

                        /*
                        Update the condition variable to make sure that only the first student thread
                        to see the group is out if pizza to call Kamal's pizza.
                         */
                        group.setDeliveryCalled(true);
                        System.out.println(name + " called Kamal");
                    }
                    System.out.println(name + " went to sleep");

                    /*
                    Sleep the delivery thread after completing the delivery once.
                    Calling wait inside a synchronization is not a problem in java.
                    The object's monitor is released after thread went to sleep.
                     */
                    group.wait();
                    eat();      // Try to eat a slice again after waking up
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // If the student was succeeded in acquiring a slice, slice number is printed.
            System.out.println(name + " got slice " + sliceNo);
        }
    }

    private void study() {
        // This function is added to have some work load for the studying time of the student thread.
        System.out.println(name + " is studying");
        for (int i = 0; i < random.nextInt(100000); i++) {
            Math.sqrt(i);
        }
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            eat();
            study();
        }
    }
}
