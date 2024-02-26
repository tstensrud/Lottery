import java.util.LinkedList;
import java.util.Random;

public class TicketOperations {

    public static int totalPlayableNumbers = 40;
    public static int totalNumbersPerRow = 7;
    public static LinkedList<Ticket> tickets = new LinkedList<Ticket>();

    public void generateTicket(int rows) {
        int[][] kupong = new int[rows][];
        
        int ticketId = tickets.size() + 1;

        for (int i = 0; i < kupong.length; i++) {
            kupong[i] = generateTicketRow();
        }
        
        tickets.add(new Ticket(kupong, ticketId, 1234));
        System.out.println("Total tickets: " + tickets.size());
        /*
        for (int i = 0; i < kupong.length; i++) {
            for (int j = 0; j < totalNumbersPerRow; j++) {
                if (j != totalNumbersPerRow - 1) {
                    System.out.print(kupong[i][j] + " - ");    
                }
                else {
                    System.out.print(kupong[i][j]);
                }
            }
            System.out.println("\n");
            
        }*/
    }

    public int[] generateTicketRow() {
        int[] row = new int[totalNumbersPerRow];
        for (int i = 0; i < totalNumbersPerRow; i++) {
            int number = generateRandomNumber(true, totalPlayableNumbers);
            for (int j = 0; j < totalNumbersPerRow; j++) {
                if (row[j] == number) {
                    number = generateRandomNumber(true, totalPlayableNumbers);
                }
            }
            row[i] = number;
        }

        return sortRow(row);
    }

        // generate a random number
    // set forTicket true if the numbers are to be from 1 to max
    // set forTicket false if the numbers are to be from 0 to max
    public int generateRandomNumber(boolean forTicket, int max) {
        Random ran = new Random();
        int number;
        if (forTicket) {
            number = 1 + ran.nextInt(max);
        }
        else {
            number = ran.nextInt(max);
        }
        return number;
    }

    // sort row in ascending order
    public int[] sortRow(int[] row) {
        int temp = 0;
        for (int i = 0; i < row.length; i++){
            for (int j = i+1; j < row.length; j++) {
                if (row[i] > row[j]) {
                    temp = row[i];
                    row[i] = row[j];
                    row[j] = temp;
                }
            }
        }
        return row;        
    }
    
}
