import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;


public class Lottery {

    public static int[] currentWinningNumbers = new int[7];
    public static LinkedList<User> users = new LinkedList<User>();
    public static int[] winningRow = new int[7];
    static TicketOperations ticketOperations = new TicketOperations();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        drawUi();
        // generate one generic user for testing
        users.add(new User("Torbjørn", 111111));
    }

    public static void drawUi() {
        int buttonLength = 150;
        int buttonHeight = 30;
        // main frame
        JFrame mainFrame = new JFrame("Lottery");
        int mainFrameWidth = 1024;
        int mainFrameHeight = 768;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        

        //center frame on screen
        mainFrame.setLocation((screenWidth / 2) - (mainFrameWidth/2),(screenHeight / 2) - (mainFrameHeight / 2));

        // textareas
        JTextArea mainTextArea = new JTextArea("Output");
        mainTextArea.setBounds(300, 50, 600, 300);

        // buttons
        JButton newTicketButton = new JButton("New ticket");
        newTicketButton.setBounds(50, 50, buttonLength, buttonHeight);
        JButton winningNumbersButton = new JButton("Winning numbers");
        winningNumbersButton.setBounds(50, 100, buttonLength, buttonHeight);
        JButton newUserButton = new JButton("New user");
        newUserButton.setBounds(50, 150, buttonLength, buttonHeight);
        JButton allTicketsButton = new JButton("Print all ticket IDs");
        allTicketsButton.setBounds(50, 200, buttonLength, buttonHeight);

        // NEW TICKET newTicketButton actionlistener
        newTicketButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                Integer[] rowChoice = {1,2,3,4,5,6,7,8,9,10};
                int rows = (Integer)JOptionPane.showInputDialog(null,"Choose number of rows","Rows", JOptionPane.OK_CANCEL_OPTION,null, rowChoice, rowChoice[0]);
                if (rows == JOptionPane.CANCEL_OPTION) {
                    mainTextArea.setText("Cancelled");
                }
                else {
                    ticketOperations.generateTicket(rows, users.get(0).userId);
                    mainTextArea.setText("Ticket created with id: " + ticketOperations.tickets.getLast().getTicketId() + "\n");
                    mainTextArea.append("Ticket belongs to user: " + ticketOperations.tickets.getLast().ticketBelongsToUser() + "\n");
                    
                    int[][] newlyCreatedTicket = ticketOperations.tickets.getLast().getRows();
                    mainTextArea.append("Numbers of new ticket: " + "\n");
    
                    int rowCounter = 1;
                    for (int i = 0; i < newlyCreatedTicket.length; i++) {
                        mainTextArea.append(rowCounter + ": ");
                        for (int j = 0; j < newlyCreatedTicket[i].length; j++) {
                            mainTextArea.append(newlyCreatedTicket[i][j] + "\t");
                        }
                        mainTextArea.append("\n");
                        rowCounter++;
                    }    
                }
            }
        });

        // WINNING NUMBERS winningNumbersButton actionlistener
        winningNumbersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                mainTextArea.setText("Winning numbers are: " + "\n");
                winningRow = ticketOperations.winningRow();
                for (int i = 0; i < winningRow.length; i++) {
                    if (i == winningRow.length - 1) {
                        mainTextArea.append(Integer.toString(winningRow[i]));
                    }
                    else {
                        mainTextArea.append((winningRow[i] + " - "));
                    }
                }
                currentWinningNumbers = winningRow;
            }
        });

        // NEW USER newUserButtoon actionlistener
        newUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "TBA", "TBA", JOptionPane.DEFAULT_OPTION);
            }
        });
        
        // print all ticket IDs actionlistener
        allTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                int ticketCount = 1;
                if (ticketOperations.tickets.size() == 0) {
                    mainTextArea.setText("No tickets in database");
                }
                else {
                    for (int i = 0; i < ticketOperations.tickets.size(); i++) {
                        mainTextArea.append("tID-" + ticketCount + ": " + ticketOperations.tickets.get(i).ticketId + "\n");
                        ticketCount++;
                    }
                }
            }
        });


        // add objects to mainpanel
        mainFrame.add(mainTextArea);
        mainFrame.add(newTicketButton);
        mainFrame.add(winningNumbersButton);
        mainFrame.add(newUserButton);
        mainFrame.add(allTicketsButton);
        mainFrame.setSize(mainFrameWidth, mainFrameHeight);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void generateUser(String userName) {

    }
}
