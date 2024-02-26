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

    public int getTicketId() {
        return ticketId;
    }
}
