public class Ticket {
    private int rows[][];
    private int ticketId;
    private int belongsToUser;
    Ticket next;

    Ticket (int rows[][], int ticketId, int userId) {
        this.rows = rows;
        this.ticketId = ticketId;
        this.belongsToUser = userId;
    }

    public int[][] getRows() {
        return this.rows;
    }

    public int getBelongsToUser() {
        return this.belongsToUser;
    }

    public int getTicketId() {
        return this.ticketId;
    }
}
