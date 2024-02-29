public class Ticket {
    int rows[][];
    int ticketId;
    int belongsToUser;
    Ticket next;

    Ticket (int rows[][], int ticketId, int userId) {
        this.rows = rows;
        this.ticketId = ticketId;
        this.belongsToUser = userId;
    }

    public void getRows() {
        
        
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                if (j != rows[i].length - 1) {
                    System.out.print(rows[i][j] + " - ");    
                }
                else {
                    System.out.print(rows[i][j]);
                }
            }
            System.out.println("\n");
            
        }
    
    }

    public int ticketBelongsToUser() {
        return belongsToUser;
    }

    public int getTicketId() {
        return ticketId;
    }
}
