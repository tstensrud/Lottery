import java.util.LinkedList;
import java.util.Scanner;

public class Lottery {
    public static int totalPlayableNumbers = 40;
    public static int totalNumbersPerRow = 7;
    public static LinkedList<User> users = new LinkedList<User>();
    public static void main(String[] args) {

        // generate one generic user for testing
        users.add(new User("Torbjørn", 111111));

        TicketOperations ticketOperations = new TicketOperations();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the lottery! Please make a choice.");
        System.out.println("[N]ew for new ticket. \n [W] for generating winning numbers. \n [C]heck to check if your ticket is a winner");
        String input = scanner.nextLine();

        if (input.equals("N")) {
            System.out.println("How many ticket rows?(1-10)");
            int rows = Integer.parseInt(scanner.nextLine());
            ticketOperations.generateTicket(rows, users.get(0).userId);
            System.out.println("Ticket created with id: " + ticketOperations.tickets.get(0).getTicketId());
            System.out.println("Ticket belongs to user: " + ticketOperations.tickets.get(0).ticketBelongsToUser());
            ticketOperations.tickets.get(0).getRows();
        }
        scanner.close();
    }
    public static void generateUser(String userName) {

    }
}
