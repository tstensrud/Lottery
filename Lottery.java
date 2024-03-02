import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;


public class Lottery {
    public static int totalPlayableNumbers = 40;
    public static int totalNumbersPerRow = 7;
    public static LinkedList<User> users = new LinkedList<User>();
    public static int[] winningRow = new int[7];
    public static boolean running = true;
    static TicketOperations ticketOperations = new TicketOperations();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        drawUi();
        // generate one generic user for testing
        users.add(new User("Torbjørn", 111111));

        //TicketOperations ticketOperations = new TicketOperations();
        /*
        Scanner scanner = new Scanner(System.in);
        String input = null;
        System.out.println("Welcome to the lottery! Please make a choice.");
        while(running == true) {            
            System.out.println("[N]ew for new ticket. \n [W] for generating winning numbers. \n [C]heck to check if your ticket is a winner");
            input = scanner.nextLine();
            if (input.equals("N") || input.equals("n")) {
               newTicket();
               running = true;
            }
    
            if (input.equals("W") || input.equals("w")) {
                winningTicket();
                running = true;
            }
        }
        

        scanner.close();*/
    }

    public static void drawUi() {

        // main frame
        JFrame mainFrame = new JFrame("Lottery");
        int mainFrameWidth = 1024;
        int mainFrameHeight = 768;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();


        mainFrame.setSize(mainFrameWidth, mainFrameHeight);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //center frame on screen
        mainFrame.setLocation((screenWidth / 2) - (mainFrameWidth/2),(screenHeight / 2) - (mainFrameHeight / 2));


        // buttons
        JButton newTicketButton = new JButton("New ticket");
        JButton winningNumbersButton = new JButton("Winning numbers");
        JButton newUserButton = new JButton("New user");

        // place newTicketButton with actionlistener
        newTicketButton.setBounds(50, 50, 150, 30);
        newTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("new ticket");
            }
        });

        // place winningNumbersButton with actionlistener
        winningNumbersButton.setBounds(50, 100, 150, 30);
        winningNumbersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("new winning numbers");
            }
        });

        // place newUserButtoon with actionlistener
        newUserButton.setBounds(50, 150, 150, 30);
        newUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("new user");
            }
        });

        // add objects to mainframe
        mainFrame.add(newTicketButton);
        mainFrame.add(winningNumbersButton);
        mainFrame.add(newUserButton);

        

    }
    public static void newTicket() {
        System.out.println("How many ticket rows?(1-10)");
        int rows = Integer.parseInt(scanner.nextLine());
        ticketOperations.generateTicket(rows, users.get(0).userId);
        System.out.println("Ticket created with id: " + ticketOperations.tickets.get(0).getTicketId());
        System.out.println("Ticket belongs to user: " + ticketOperations.tickets.get(0).ticketBelongsToUser());
        ticketOperations.tickets.get(0).getRows();
    }

    public static void winningTicket() {
        System.out.println("Genrating numbers: " + winningRow.length);
        winningRow = ticketOperations.winningRow();
        for (int i = 0; i < winningRow.length; i++) {
            if (i == winningRow.length - 1) {
                System.out.print(winningRow[i]);
            }
            else {
                System.out.print(winningRow[i] + " - ");
            }
        }
    }

    public static void generateUser(String userName) {

    }
}
