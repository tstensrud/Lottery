import java.util.ArrayList;
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
    
    // generate a row of totalNumbersPerRow numbers in ascending order. check for duplicate number.
    public static int[] generateTicketRow() {
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
    public static int generateRandomNumber(boolean forTicket, int max) {
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
    public static int[] sortRow(int[] row) {
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

    // Generate a winning row. 
    // For each number nextNumber thats drawed, it is removed from pool of availableNumbers
    public static int[] winningRow() {
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        int[] winningRow = new int[totalNumbersPerRow];

        // fill arraylist with numbers that should be in play
        for (int i = 0; i<totalPlayableNumbers; i++) {
            availableNumbers.add(i+1);
        }


        for (int i = 0; i < winningRow.length; i++) {
            int nextNumber;
            if (i == 0) {
                nextNumber = generateRandomNumber(false, totalPlayableNumbers);
                winningRow[i] = availableNumbers.get(nextNumber);
                availableNumbers.remove(nextNumber);
            }
            else {
                nextNumber = generateRandomNumber(false, totalPlayableNumbers - i);
                winningRow[i] = availableNumbers.get(nextNumber);
                availableNumbers.remove(nextNumber);
            }
        }

        sortRow(winningRow);
        for(int i = 0; i < winningRow.length; i++) {
            System.out.print(winningRow[i] + " - ");
        }
        return winningRow;
    }
    
}
