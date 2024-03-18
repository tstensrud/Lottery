import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class Lottery {
    public static int totaltNumbersPerRow = TicketOperations.totalNumbersPerRow;
    public static int[] currentWinningNumbers = new int[totaltNumbersPerRow];
    public static int[] winningRow = new int[totaltNumbersPerRow];
    public static void main(String[] args) {

        drawUi();
        // generate one generic user for testing
        UserOperations.users.add(new User("Torbjørn", 1000, "34343434", "a@b.c", "Gata til a mor"));
        for (int i = 0; i < 100; i++) {
            TicketOperations.generateTicket(10, 1000);
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
        mainFrame.setTitle("Lottery");
        final int MAIN_FRAME_WIDTH = 1024;
        final int MAIN_FRAME_HEIGHT = 768;
        
        // Add user frame
        JFrame addUserFrame = new JFrame("Add new user");
        final int ADD_USER_FRAME_WIDTH = 500;
        final int ADD_USER_FRAME_HEIGHT = 500;
        
        //center frames on screen
        mainFrame.setLocation((screenWidth / 2) - (MAIN_FRAME_WIDTH/2),(screenHeight / 2) - (MAIN_FRAME_HEIGHT / 2));
        addUserFrame.setLocation((screenWidth / 2) - (ADD_USER_FRAME_WIDTH/2),(screenHeight / 2) - (ADD_USER_FRAME_HEIGHT / 2));

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
        JTextField userUserNameTextField = new JTextField();
        userUserNameTextField.setBounds(100, 50, textFieldLength, textFieldHeight);
        JTextField userEmailTextField = new JTextField();
        userEmailTextField.setBounds(100, 100, textFieldLength, textFieldHeight);
        JTextField userAdressTextField = new JTextField();
        userAdressTextField.setBounds(100, 150, textFieldLength, textFieldHeight);
        JTextField userPhoneTextField = new JTextField();
        userPhoneTextField.setBounds(100, 200, textFieldLength, textFieldHeight);

        // buttons "add new user"-frame
        JButton addNewUserButton = new JButton("Add new user");
        addNewUserButton.setBounds(25,250,buttonLength, buttonHeight);

        // buttons mainframe
        JButton newTicketButton = new JButton("New ticket");
        newTicketButton.setBounds(30, 50, buttonLength, buttonHeight);
        JButton winningNumbersButton = new JButton("Winning numbers");
        winningNumbersButton.setBounds(30, 100, buttonLength, buttonHeight);
        JButton newUserButton = new JButton("New user");
        newUserButton.setBounds(30, 150, buttonLength, buttonHeight);
        JButton allTicketsButton = new JButton("Print all ticket IDs");
        allTicketsButton.setBounds(30, 200, buttonLength, buttonHeight);
        JButton findWinningTicketsButton = new JButton("Find winners");
        findWinningTicketsButton.setBounds(30, 250, buttonLength, buttonHeight);
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(30, 300, buttonLength, buttonHeight);
        JButton printTicketButton = new JButton("Print ticket");
        printTicketButton.setBounds(30,350,buttonLength, buttonHeight);
        JButton printUserIdsButton = new JButton("Print all user IDs");
        printUserIdsButton.setBounds(30, 400, buttonLength, buttonHeight);
        JButton getUserAccountBalanceButton = new JButton("Get user balance");
        getUserAccountBalanceButton.setBounds(30,450,buttonLength,buttonHeight);
        JButton printArchivedTicketsButton = new JButton("Achived tickets");
        printArchivedTicketsButton.setBounds(30,500,buttonLength,buttonHeight);
        JButton printUserInfoButton = new JButton("User info");
        printUserInfoButton.setBounds(30,550,buttonLength,buttonHeight);

        // NEW TICKET newTicketButton actionlistener
        newTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                Integer[] rowChoice = {1,2,3,4,5,6,7,8,9,10};
                int userId;
                int rows;
                userId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,"Enter user-ID","UserID", JOptionPane.OK_CANCEL_OPTION));
                
                if (UserOperations.findUserID(userId) == false) {
                    JOptionPane.showMessageDialog(mainFrame, "User-ID not found", "Error", JOptionPane.OK_OPTION);
                    return;
                }
                try {
                    rows = (Integer)JOptionPane.showInputDialog(mainFrame,"Choose number of rows","Rows", JOptionPane.OK_CANCEL_OPTION,null, rowChoice, rowChoice[9]);
                    if (rows == JOptionPane.CANCEL_OPTION) {
                        mainTextArea.setText("Cancelled");
                    }
                    else {
                        mainTextArea.setText("Ticket created with id: " + TicketOperations.tickets.getLast().getTicketId() + "\n");
                        mainTextArea.append("Ticket belongs to user: " + UserOperations.getUserObject(userId).getUserId() + "\n");
                        
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
                    TicketOperations.generateTicket(rows, userId);
                
                }
                catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(mainFrame, "Only numbers in user-id", "Error", JOptionPane.OK_OPTION);
                    System.out.println("NumberFormatException " + err.getMessage());
                
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
                    JOptionPane.showMessageDialog(mainFrame, "No active tickets in databsase.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    for (int i = 0; i < TicketOperations.tickets.size(); i++) {
                        mainTextArea.append("tID-" + (i+1) + ": " + TicketOperations.tickets.get(i).getTicketId()+ "\n");
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
                int confirmReset = JOptionPane.showConfirmDialog(mainFrame, "Do you want to reset? This is not undoable!", "RESET", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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

                String userName = userUserNameTextField.getText();
                String userPhone = userPhoneTextField.getText();
                String userEmail = userEmailTextField.getText();
                String userAdress = userAdressTextField.getText();

                // test that email follows standard formation: name@provider.domain
                String emailPattern = "^[\\w.-]+@[-\\w]+[.]+[\\w]{2,4}$";
                Pattern regExPattern = Pattern.compile(emailPattern);
                Matcher regExMatcher = regExPattern.matcher(userEmail);
                
                if (regExMatcher.find() == false) {
                    JOptionPane.showMessageDialog(addUserFrame, "Error in email-format. name@provider.domain", "Error", JOptionPane.DEFAULT_OPTION);
                      
                }
                else {
                    UserOperations.addNewUser(userName, (UserOperations.users.size() + 1001), userPhone, userEmail, userAdress);
                    JOptionPane.showMessageDialog(addUserFrame, "User " + userName + " added.", "User added", JOptionPane.DEFAULT_OPTION);
                    userUserNameTextField.setText(null);
                    userPhoneTextField.setText(null);
                    userEmailTextField.setText(null);
                    userAdressTextField.setText(null);
                }
            }
        });

        // PRINT USER IDs actionlistener
        printUserIdsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                int[] userIds = UserOperations.returnUserIDs();
                for (int i = 0; i < userIds.length; i++) {
                    mainTextArea.append("UID-" + i + ": " + userIds[i] + "\n");
                }
            }
        });

        // GET USER BALANCE actionlistener
        getUserAccountBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userId;
                userId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,"Enter user-ID","UserID", JOptionPane.OK_CANCEL_OPTION));
                
                if (UserOperations.findUserID(userId) == false) {
                    JOptionPane.showMessageDialog(mainFrame, "User-ID not found", "Error", JOptionPane.OK_OPTION);
                    return;
                }
                try {
                    mainTextArea.setText(null);
                    mainTextArea.setText("User " + userId + " has an account blanace of: " + UserOperations.getUserObject(userId).getAccountBalance() + "kr");
                }
                catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(mainFrame, "Only numbers in user-id", "Error", JOptionPane.OK_OPTION);
                    System.out.println("NumberFormatException " + err.getMessage());
                }
            }
        });

        // PRINT ALL ARCHIVED TICKETS actionlistener
        printArchivedTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setText(null);
                if (TicketOperations.archivedTickets.size() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "No archived tickets in databsase.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    for (int i = 0; i < TicketOperations.archivedTickets.size(); i++) {
                        mainTextArea.append("tID-" + (i+1) + ": " + TicketOperations.archivedTickets.get(i).getTicketId()+ "\n");
                    }
                }
            }
        });

        // PRINT USER INFO actionlistener
        printUserInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userId;
                try {
                    userId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Enter user-ID", "User ID", JOptionPane.OK_CANCEL_OPTION));
                    if (UserOperations.findUserID(userId) == false) {
                        JOptionPane.showMessageDialog(mainFrame, "User-ID not found", "Error", JOptionPane.OK_OPTION);
                        return;
                    }
                    User user = UserOperations.getUserObject(userId);
                    mainTextArea.setText(null);
                    mainTextArea.append("Name: " + user.getUserName() + "\n");
                    mainTextArea.append("Email: " + user.getEmail() + "\n");
                    mainTextArea.append("Phone number: " + user.getPhoneNumber() + "\n");
                    mainTextArea.append("Adress: " + user.getAdress() + "\n");
                    mainTextArea.append("Account balance: " + user.getAccountBalance() + "kr");
                }
                catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(mainFrame, "Only numbers in user-id", "Error", JOptionPane.OK_OPTION);
                    System.out.println("NumberFormatException " + err.getMessage());
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
        mainFrame.add(printUserIdsButton);
        mainFrame.add(getUserAccountBalanceButton);
        mainFrame.add(printArchivedTicketsButton);
        mainFrame.add(printUserInfoButton);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add objects to "add new user" frame
        addUserFrame.add(userUserNameTextField);
        addUserFrame.add(userEmailTextField);
        addUserFrame.add(userAdressTextField);
        addUserFrame.add(userPhoneTextField);
        addUserFrame.add(userNameLabel);
        addUserFrame.add(emailLabel);
        addUserFrame.add(adressLabel);
        addUserFrame.add(phoneLabel);
        addUserFrame.add(addNewUserButton);
        addUserFrame.setSize(ADD_USER_FRAME_WIDTH, ADD_USER_FRAME_HEIGHT);
        addUserFrame.setLayout(null);
        addUserFrame.setResizable(false);
        addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    // Resets the game, null-ing winning numbers and placing all existing tickets in archive.
    private static void fullReset() {
        
        // set winning numbers to 0
        for (int i= 0; i< winningRow.length; i++) {
            winningRow[i] = 0;
        }
        // empty active tickets
        TicketOperations.restForNewGame();
    }
}
