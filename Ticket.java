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

    public int[][] getRows() {
        return rows;
    }

    public int ticketBelongsToUser() {
        return belongsToUser;
    }

    public int getTicketId() {
        return ticketId;
    }
}
