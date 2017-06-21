import java.util.Scanner;

/**
 * @author Dulaj Atapattu
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of students from the user
        System.out.print("Enter no of students: ");
        int noOfStudents = scanner.nextInt();

        // Get the number of slices of a pizza from the user
        System.out.print("Enter no of slices in a pizza (s): ");
        Pizza.s = scanner.nextInt();

        Group group = new Group(new Pizza());

        for (int i = 0; i < noOfStudents; i++) {
            // Starting all student threads
            new Student("Student " + (i + 1), group).start();
        }

        // Starting all delivery threads
        new Delivery(group).start();

    }
}
