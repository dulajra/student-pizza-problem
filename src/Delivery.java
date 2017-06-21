/**
 * @author Dulaj Atapattu
 */
public class Delivery extends Thread {

    private final Group group;

    Delivery(Group group) {
        super("Delivery");
        this.group = group;
    }

    private void deliver() {
        /*
        Shared variable deliveryCalled is accessed here.
        So a synchronized block is used for mutual exclusion.
         */
        synchronized (group) {
            while (group.isDeliveryCalled()) {
                group.setPizza(new Pizza());       // Create a new pizza for the group.
                group.setDeliveryCalled(false);     // Resetting the condition variable.
                group.notifyAll();      // Awake all sleeping student threads.
                System.out.println("Kamal delivered new Pizza and woke up all");

                try {
                    System.out.println("Kamal went to sleep");
                    group.wait();       // Delivery thread goes to sleep after delivering the pizza.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        super.run();
        deliver();
    }
}
