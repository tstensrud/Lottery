import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;


public class Lottery {
    public static int totaltNumbersPerRow = TicketOperations.totalNumbersPerRow;
    public static int[] currentWinningNumbers = new int[totaltNumbersPerRow];
    public static LinkedList<User> users = new LinkedList<User>();
    public static int[] winningRow = new int[totaltNumbersPerRow];
    public static void main(String[] args) {

        drawUi();
        // generate one generic user for testing
        users.add(new User("Torbjørn", 1234, "34343434", "a@b.c", "Gata til a mor"));
        for (int i = 0; i < 100; i++) {
            TicketOperations.generateTicket(10, 123456);
        }
    }

    public static void drawUi() {

        // dimensions of buttons and textfields
        int buttonLength = 150;
        int buttonHeight = 30;
        int textFieldHeight = 25;
        int textFieldLength = 250;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        
        // main frame
        JFrame mainFrame = new JFrame("Lottery");
        final int mainFrameWidth = 1024;
        final int mainFrameHeight = 768;
        
        // Add user frame
        JFrame addUserFrame = new JFrame("Add new user");
        final int addUserFrameWidth = 500;
        final int addUserFrameHeight = 500;
        
        //center frames on screen
        mainFrame.setLocation((screenWidth / 2) - (mainFrameWidth/2),(screenHeight / 2) - (mainFrameHeight / 2));
        addUserFrame.setLocation((screenWidth / 2) - (addUserFrameWidth/2),(screenHeight / 2) - (addUserFrameHeight / 2));

        // labels mainframe

        // labeles "add new user"-frame
        JLabel userNameLabel = new JLabel ("Username");
        userNameLabel.setBounds(10,50,100,25);
        JLabel emailLabel = new JLabel ("E-mail");
        emailLabel.setBounds(10,100,100,25);
        JLabel adressLabel = new JLabel("Adress");
        adressLabel.setBounds(10,150,100,25);
        JLabel phoneLabel = new JLabel("Phone number");
        phoneLabel.setBounds(10,200,100,25);

        // textareas
        JTextArea mainTextArea = new JTextArea("Output");
        //mainTextArea.setBounds(300, 50, 600, 300);
        JScrollPane mainTextAreaScroll = new JScrollPane(mainTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainTextAreaScroll.setBounds(210,50,750,300);

        // textfields "add new user"-frame
        JTextField userUserNameTF = new JTextField();
        userUserNameTF.setBounds(100, 50, textFieldLength, textFieldHeight);
        JTextField userEmailTF = new JTextField();
        userEmailTF.setBounds(100, 100, textFieldLength, textFieldHeight);
        JTextField userAdressTF = new JTextField();
        userAdressTF.setBounds(100, 150, textFieldLength, textFieldHeight);
        JTextField userPhoneTF = new JTextField();
        userPhoneTF.setBounds(100, 200, textFieldLength, textFieldHeight);

        // buttons "add new user"-frame
        JButton addNewUserButton = new JButton("Add new user");
        addNewUserButton.setBounds(25,250,buttonLength, buttonHeight);

        // buttons mainframe
        JButton newTicketButton = new JButton("New ticket");
        newTicketButton.setBounds(50, 50, buttonLength, buttonHeight);
        JButton winningNumbersButton = new JButton("Winning numbers");
        winningNumbersButton.setBounds(50, 100, buttonLength, buttonHeight);
        JButton newUserButton = new JButton("New user");
        newUserButton.setBounds(50, 150, buttonLength, buttonHeight);
        JButton allTicketsButton = new JButton("Print all ticket IDs");
        allTicketsButton.setBounds(50, 200, buttonLength, buttonHeight);
        JButton findWinningTicketsButton = new JButton("Find winners");
        findWinningTicketsButton.setBounds(50, 250, buttonLength, buttonHeight);
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(50, 300, buttonLength, buttonHeight);
        JButton printTicketButton = new JButton("Print ticket");
        printTicketButton.setBounds(50,350,buttonLength, buttonHeight);

        // NEW TICKET newTicketButton actionlistener
        newTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                Integer[] rowChoice = {1,2,3,4,5,6,7,8,9,10};
                int rows = (Integer)JOptionPane.showInputDialog(null,"Choose number of rows","Rows", JOptionPane.OK_CANCEL_OPTION,null, rowChoice, rowChoice[9]);
                if (rows == JOptionPane.CANCEL_OPTION) {
                    mainTextArea.setText("Cancelled");
                }
                else {
                    TicketOperations.generateTicket(rows, users.get(0).userId);
                    mainTextArea.setText("Ticket created with id: " + TicketOperations.tickets.getLast().getTicketId() + "\n");
                    mainTextArea.append("Ticket belongs to user: " + TicketOperations.tickets.getLast().ticketBelongsToUser() + "\n");
                    
                    int[][] newlyCreatedTicket = TicketOperations.tickets.getLast().getRows();
                    mainTextArea.append("Numbers of new ticket: " + "\n");
                    
                    for (int i = 0; i < newlyCreatedTicket.length; i++) {
                        mainTextArea.append((i+1) + ": ");
                        for (int j = 0; j < newlyCreatedTicket[i].length; j++) {
                            mainTextArea.append(newlyCreatedTicket[i][j] + "\t");
                        }
                        mainTextArea.append("\n");
                    }    
                }
            }
        });

        // WINNING NUMBERS winningNumbersButton actionlistener
        winningNumbersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                if (currentWinningNumbers[0] != 0) {
                    JOptionPane.showMessageDialog(mainFrame, "A set of winning numbers already exists. Reset winnig numbers to create new numbers.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    mainTextArea.setText("Winning numbers are: " + "\n");
                    winningRow = TicketOperations.createWinningNumbers();
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
            }
        });

        // NEW USER newUserButtoon actionlistener
        newUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUserFrame.setVisible(true);
            }
        });
        
        // print all ticket IDs actionlistener
        allTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                if (TicketOperations.tickets.size() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "No tickets in databsase.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    for (int i = 0; i < TicketOperations.tickets.size(); i++) {
                        mainTextArea.append("tID-" + (i+1) + ": " + TicketOperations.tickets.get(i).ticketId + "\n");
                    }
                }
            }
        });

        // FIND WINNING TICKETS actionlistener
        findWinningTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);                
                if (winningRow[0] == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "No winning numbers are drawn yet.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    mainTextArea.setText("Winning numbers are: \n");
                    for (int i = 0; i < winningRow.length; i++) {
                        mainTextArea.append(Integer.toString(winningRow[i]) + "\t");
                    }
                    mainTextArea.append("\n");
                    
                    TicketOperations.findWinningTickets(winningRow);

                    if (TicketOperations.winningTickets.isEmpty()) {
                        mainTextArea.append("No winners this round.");
                    }
                    else {
                        for (int i = 0; i < TicketOperations.winningTickets.size(); i++) {
                            mainTextArea.append("Winner: " + TicketOperations.winningTickets.get(i) + "\n");
                        }
                    }
                }
            }
        });

        // RESET BUTTON actionlistener
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmReset = JOptionPane.showConfirmDialog(mainFrame, "Do you want to reset?", "RESET", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirmReset == JOptionPane.YES_OPTION) {
                    mainTextArea.setText(null);
                    fullReset();
                    mainTextArea.append("Game is reset.");
                }
                else if (confirmReset == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, "Cancelled", "Cancelled", JOptionPane.OK_OPTION);
                }

            }
        });

        // PRINT TICKET actionlistener
        printTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ticketId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Ticket ID", "Enter ticket ID"));
                    mainTextArea.setText(null);

                    for (int i = 0; i < TicketOperations.tickets.size(); i++) {
                        if (TicketOperations.tickets.get(i).getTicketId() == ticketId) {
                            mainTextArea.append("Ticket " + ticketId + ": \n");
                            int[][] fetchRows = TicketOperations.tickets.get(i).getRows();
                            for (int j = 0; j < fetchRows.length; j++) {
                                mainTextArea.append((j+1) + ": ");
                                for (int k = 0; k < fetchRows[j].length; k++) {
                                    mainTextArea.append(fetchRows[j][k] + "\t");
                                }
                                mainTextArea.append("\n");
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(mainFrame, "Ticket ID " + ticketId + " not found!", "Error", JOptionPane.OK_OPTION);
                }
                catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(mainFrame, "Only numbers in ID", "Error", JOptionPane.OK_OPTION);
                    System.out.println("NumberFormatException " + err.getMessage());
                }
            }
        });

        // ADD NEW USER button
        addNewUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String userName = userUserNameTF.getText();
                String userPhone = userPhoneTF.getText();
                String userEmail = userEmailTF.getText();
                String userAdress = userAdressTF.getText();

                // test that email follows standard formation: name@provider.domain
                String emailPattern = "^[\\w.-]+@[-\\w]+[.]+[\\w]{2,4}$";
                Pattern regExPattern = Pattern.compile(emailPattern);
                Matcher regExMatcher = regExPattern.matcher(userEmail);
                
                if (regExMatcher.find() == false) {
                    JOptionPane.showMessageDialog(addUserFrame, "Error in email-format. name@provider.domain", "Error", JOptionPane.DEFAULT_OPTION);
                      
                }
                else {
                    users.add(new User(userName, (users.size() + 1), userPhone, userEmail, userAdress));
                    JOptionPane.showMessageDialog(addUserFrame, "User " + userName + " added.", "User added", JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        // add objects to mainpanel
        //mainFrame.add(mainTextArea);
        mainFrame.add(mainTextAreaScroll);
        mainFrame.add(newTicketButton);
        mainFrame.add(winningNumbersButton);
        mainFrame.add(newUserButton);
        mainFrame.add(allTicketsButton);
        mainFrame.add(findWinningTicketsButton);
        mainFrame.add(resetButton);
        mainFrame.add(printTicketButton);
        mainFrame.setSize(mainFrameWidth, mainFrameHeight);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add objects to "add new user" frame
        addUserFrame.add(userUserNameTF);
        addUserFrame.add(userEmailTF);
        addUserFrame.add(userAdressTF);
        addUserFrame.add(userPhoneTF);
        addUserFrame.add(userNameLabel);
        addUserFrame.add(emailLabel);
        addUserFrame.add(adressLabel);
        addUserFrame.add(phoneLabel);
        addUserFrame.add(addNewUserButton);
        addUserFrame.setSize(addUserFrameWidth, addUserFrameHeight);
        addUserFrame.setLayout(null);
        addUserFrame.setResizable(false);
        addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private static void fullReset() {
        // set winning numbers to 0
        for (int i= 0; i< winningRow.length; i++) {
            winningRow[i] = 0;
        }

        // put current tickets into archive and set current ticket to 0


    }
    public static void generateUser(String userName) {

    }
}
