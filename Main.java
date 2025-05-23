import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int totalTicket = 5;  // number of avilable tickets; can be adjusted to whatever
        int totalCustomers = 10; // number of customers; can be adjusted to whatever

        TicketPool ticketPool = new TicketPool(totalTicket);
        List<Thread> threads = new ArrayList<>();

        // creates virtual threads for customers 
        for (int i = 1; i <= totalCustomers; i++ ) {
            Customer customer = new Customer("Customer-" + i, ticketPool);

            Thread thread = Thread.startVirtualThread(customer);
            threads.add(thread);
        }

        // waits for all threads to finish 
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("All reservations have been completed.");
    }
}
