/**
 * @author Dulaj Atapattu
 */
class Group {

    /**
     * Group has only one pizza for all students
     */
    private Pizza pizza;

    /**
     * This condition variable is used to guarantee that only the first student
     * to discover that the group is out of pizza calls Kamal’s Pizza.
     */
    private boolean deliveryCalled = false;

    Group(Pizza pizza) {
        this.pizza = pizza;
    }

    Pizza getPizza() {
        return pizza;
    }

    void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    boolean isDeliveryCalled() {
        return deliveryCalled;
    }

    void setDeliveryCalled(boolean deliveryCalled) {
        this.deliveryCalled = deliveryCalled;
    }
}
