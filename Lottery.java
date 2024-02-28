import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Lottery {
    public static int totalPlayableNumbers = 40;
    public static int totalNumbersPerRow = 7;
    public static LinkedList<User> users = new LinkedList<User>();
    public static void main(String[] args) {

        TicketOperations ticketOperations = new TicketOperations();

        for(int i = 0; i < 5; i++) {
            ticketOperations.generateTicket(5);
        }
    }
    



    public static void generateUser(String userName) {

    }
}
