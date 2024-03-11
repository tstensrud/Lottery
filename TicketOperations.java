import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class TicketOperations {

    public static int totalPlayableNumbers = 38;
    public static int totalNumbersPerRow = 8;
    public static int ticketId = 10000;
    public static LinkedList<Ticket> tickets = new LinkedList<Ticket>();
    public static ArrayList<Integer> winningTickets = new ArrayList<>();

    // Generate a ticket with n-amount of rows
    public static void generateTicket(int rows, int userId) {

        int[][] ticket = new int[rows][];
        ticketId++;
        
        for (int i = 0; i < rows; i++) {
                ticket[i] = generateTicketRow();
        }
        
        tickets.add(new Ticket(ticket, ticketId, userId));

    }
    
    // generate a row of totalNumbersPerRow numbers in ascending order. check for duplicate number.
     
    private static int[] generateTicketRow() {
        int[] row = new int[totalNumbersPerRow];
        int number;
        for (int i = 0; i < totalNumbersPerRow; i++) {
            number = generateRandomNumber(true, totalPlayableNumbers);
            
            for (int j = 0; j < totalNumbersPerRow; j++) {
                while (searchForDuplicateNumberInRow(row, number)) {
                    number = generateRandomNumber(true, totalPlayableNumbers);
                }
            }
            row[i] = number;
        }
        /*
        for(int i = 1; i < row.length; i++) {
                if (row[i-1] == row[i]) {
                    System.out.println("DUPLICATE");
                }
        }*/
        return sortRow(row);
    }

    private static boolean searchForDuplicateNumberInRow(int[] row, int number) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == number) {
                return true;
            }
        }
        return false;
    }
    /* 
    *  generate a random number
    *  set forTicket true if the numbers are to be from 1 to max
    *  set forTicket false if the numbers are to be from 0 to max
    */
    private static int generateRandomNumber(boolean forTicket, int max) {
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
    private static int[] sortRow(int[] row) {
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

    /* Generate winning numbers
    *  For each number nextNumber thats drawed, it is removed from pool of availableNumbers
    */
    public static int[] createWinningNumbers() {
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
        return winningRow;
    }   

    /*
     * Will check winningRow up against all ticket-rows and return ID of ticket if loop "j" finishes
     * Returns an arraylist of winning ticketIDs
     */
    public static ArrayList<Integer> findWinningTickets(int[] winningRow) {
        for(int i = 0; i < tickets.size(); i++) {
            
            // Search through ticket i
            newTicket:
            for (int j = 0; j < tickets.get(i).getRows().length; j++) {
                for (int k = 0; k < totalNumbersPerRow; k++) {
                    if(tickets.get(i).getRows()[j][k] != winningRow[k]) {
                      break; // if match is not found, break out of loop and go to next row
                    }
                    // if k-loop completes a winning row is found and loop breaks, continuing to next ticket i.
                    if (k == totalNumbersPerRow-1) {
                        winningTickets.add(tickets.get(i).getTicketId());
                        break newTicket;
                    }
                }
            }
        }
        return winningTickets;
    }
}
