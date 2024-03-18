import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class Lottery {
    public static int totaltNumbersPerRow = TicketOperations.totalNumbersPerRow;
    public static int[] currentWinningNumbers = new int[totaltNumbersPerRow];
    public static int[] winningRow = new int[totaltNumbersPerRow];

    // Visibility-variables for panels
    static boolean testPanelVisibility = false; 
    static boolean mainPanelVisibility = true; // panel #1
    static boolean usersPanelVisibility = false; // panel#2
    static boolean ticketsPanelVisibility = false; // panel #3
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

        // find screenresolution of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        
        // MENU BARS
        JMenuBar topMenuBar = new JMenuBar();

        // file menu
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemExit = new JMenuItem("Exit");
        JMenuItem menuItemLogOut = new JMenuItem("Log out");
        menuFile.add(menuItemLogOut);
        menuFile.add(menuItemExit);

        // tickets menu
        JMenu menuTickets = new JMenu("Tickets");
        JMenuItem menuItemTickets = new JMenuItem("Tickets");
        JMenuItem menuItemResetGame = new JMenuItem("Reset game");
        menuTickets.add(menuItemTickets);
        menuTickets.add(menuItemResetGame);

        // users menu
        JMenu menuUsers = new JMenu("Users");
        JMenuItem menuItemUsers= new JMenuItem("Users");
        menuUsers.add(menuItemUsers);

        topMenuBar.add(menuFile);
        topMenuBar.add(menuTickets);
        topMenuBar.add(menuUsers);

        // FRAMES
        // Main frame
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
        
        
        /*
         * MAIN PANEL
         * All objects for the MAIN panel
         */
            JPanel mainPanel = new JPanel();
            mainPanel.setBounds(0,0,MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
            mainPanel.setVisible(mainPanelVisibility);
            mainPanel.setLayout(null);
            JLabel welcomeMessage = new JLabel("Use menu to begin");
            welcomeMessage.setBounds(5,0,200,25);
            mainPanel.add(welcomeMessage);

        /*
         * USERS PANEL
         * All objects for the USERS panel
         */
            JPanel usersPanel = new JPanel();
            usersPanel.setBounds(0,0,MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
            usersPanel.setVisible(usersPanelVisibility);
            usersPanel.setLayout(null);
            // buttons
            JButton newUserButton = new JButton("New user");
            newUserButton.setBounds(30, 50, buttonLength, buttonHeight);
            JButton getUserAccountBalanceButton = new JButton("Get user balance");
            getUserAccountBalanceButton.setBounds(30,100,buttonLength,buttonHeight);
            JButton printUserIdsButton = new JButton("Print all user IDs");
            printUserIdsButton.setBounds(30, 150, buttonLength, buttonHeight);
            JButton printUserInfoButton = new JButton("User info");
            printUserInfoButton.setBounds(30,200,buttonLength,buttonHeight);
            // button for add new user window
            JButton addNewUserButton = new JButton("Add");
            addNewUserButton.setBounds(10,250,buttonLength, buttonHeight);
            // labeles "add new user"-frame
            JLabel userNameLabel = new JLabel ("Username");
            userNameLabel.setBounds(10,50,100,25);
            JLabel emailLabel = new JLabel ("E-mail");
            emailLabel.setBounds(10,100,100,25);
            JLabel adressLabel = new JLabel("Adress");
            adressLabel.setBounds(10,150,100,25);
            JLabel phoneLabel = new JLabel("Phone number");
            phoneLabel.setBounds(10,200,100,25);
            // textfields "add new user"-frame
            JTextField userUserNameTextField = new JTextField();
            userUserNameTextField.setBounds(100, 50, textFieldLength, textFieldHeight);
            JTextField userEmailTextField = new JTextField();
            userEmailTextField.setBounds(100, 100, textFieldLength, textFieldHeight);
            JTextField userAdressTextField = new JTextField();
            userAdressTextField.setBounds(100, 150, textFieldLength, textFieldHeight);
            JTextField userPhoneTextField = new JTextField();
            userPhoneTextField.setBounds(100, 200, textFieldLength, textFieldHeight);
            // textarea
            JTextArea userTextArea = new JTextArea("Output");
            JScrollPane userTextAreaScroll = new JScrollPane(userTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            userTextAreaScroll.setBounds(210,50,750,300);

        /*
         * TICKETS PANEL
         * All objects for the TICKETS panel
         */
            JPanel ticketsPanel = new JPanel();
            ticketsPanel.setBounds(0,0,MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
            ticketsPanel.setVisible(ticketsPanelVisibility);
            ticketsPanel.setLayout(null);
            // buttons
            JButton newTicketButton = new JButton("New ticket");
            newTicketButton.setBounds(30, 50, buttonLength, buttonHeight);
            JButton winningNumbersButton = new JButton("Winning numbers");
            winningNumbersButton.setBounds(30, 100, buttonLength, buttonHeight);
            JButton allTicketsButton = new JButton("Print all ticket IDs");
            allTicketsButton.setBounds(30, 150, buttonLength, buttonHeight);
            JButton findWinningTicketsButton = new JButton("Find winners");
            findWinningTicketsButton.setBounds(30, 200, buttonLength, buttonHeight);
            JButton printArchivedTicketsButton = new JButton("Achived tickets");
            printArchivedTicketsButton.setBounds(30,250,buttonLength,buttonHeight);
            JButton printTicketButton = new JButton("Print ticket");
            printTicketButton.setBounds(30,300,buttonLength, buttonHeight);
            // textarea
            JTextArea ticketsTextArea = new JTextArea("Output");
            JScrollPane ticketsTextAreaScroll = new JScrollPane(ticketsTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            ticketsTextAreaScroll.setBounds(210,50,750,300);

        JButton testButton = new JButton("Test");
        testButton.setBounds(30, 650, buttonLength, buttonHeight);

        // MENU-ITEMS ACTIONLISTENERS
        // FILE MENU
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });

        // USERS MENU
        menuItemUsers.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                setVisiblityOfPanels(2);
                mainPanel.setVisible(mainPanelVisibility);
                ticketsPanel.setVisible(ticketsPanelVisibility);
                usersPanel.setVisible(usersPanelVisibility);
            }
        });

        // TICKETS MENU
        menuItemTickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisiblityOfPanels(3);
                mainPanel.setVisible(mainPanelVisibility);
                usersPanel.setVisible(usersPanelVisibility);
                ticketsPanel.setVisible(ticketsPanelVisibility);
            }
        });

        menuItemResetGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmReset = JOptionPane.showConfirmDialog(mainFrame, "Do you want to reset? This is not undoable!", "RESET", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirmReset == JOptionPane.YES_OPTION) {
                    fullReset();
                    JOptionPane.showMessageDialog(mainFrame, "Game is reset", "Reset", JOptionPane.OK_OPTION);
                }
                else if (confirmReset == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, "Cancelled", "Cancelled", JOptionPane.OK_OPTION);
                }
            }
        });


        // BUTTON ACTION LISTENERS
        // NEW TICKET newTicketButton actionlistener
        newTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticketsTextArea.setText(null);
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
                        ticketsTextArea.setText("Cancelled");
                    }
                    else {
                        TicketOperations.generateTicket(rows, userId);

                        ticketsTextArea.setText("Ticket created with id: " + TicketOperations.tickets.getLast().getTicketId() + "\n");
                        ticketsTextArea.append("Ticket belongs to user: " + UserOperations.getUserObject(userId).getUserId() + "\n");
                        
                        int[][] newlyCreatedTicket = TicketOperations.tickets.getLast().getRows();
                        ticketsTextArea.append("Numbers of new ticket: " + "\n");
                        
                        for (int i = 0; i < newlyCreatedTicket.length; i++) {
                            ticketsTextArea.append((i+1) + ": ");
                            for (int j = 0; j < newlyCreatedTicket[i].length; j++) {
                                ticketsTextArea.append(newlyCreatedTicket[i][j] + "\t");
                            }
                            ticketsTextArea.append("\n");
                        }    
                    }
                    
                
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
                ticketsTextArea.setText(null);
                if (currentWinningNumbers[0] != 0) {
                    JOptionPane.showMessageDialog(mainFrame, "A set of winning numbers already exists. Reset winnig numbers to create new numbers.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    ticketsTextArea.setText("Winning numbers are: " + "\n");
                    winningRow = TicketOperations.createWinningNumbers();
                    for (int i = 0; i < winningRow.length; i++) {
                        if (i == winningRow.length - 1) {
                            ticketsTextArea.append(Integer.toString(winningRow[i]));
                        }
                        else {
                            ticketsTextArea.append((winningRow[i] + " - "));
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
        
        // PRINT ALL TICKET IDs actionlistener
        allTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticketsTextArea.setText(null);
                if (TicketOperations.tickets.size() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "No active tickets in databsase.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    for (int i = 0; i < TicketOperations.tickets.size(); i++) {
                        ticketsTextArea.append("Ticket ID " + (i+1) + ": " + TicketOperations.tickets.get(i).getTicketId()+ "\n");
                    }
                }
            }
        });

        // FIND WINNING TICKETS actionlistener
        findWinningTicketsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticketsTextArea.setText(null);                
                if (winningRow[0] == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "No winning numbers are drawn yet.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    ticketsTextArea.setText("Winning numbers are: \n");
                    for (int i = 0; i < winningRow.length; i++) {
                        ticketsTextArea.append(Integer.toString(winningRow[i]) + "\t");
                    }
                    ticketsTextArea.append("\n");
                    
                    TicketOperations.findWinningTickets(winningRow);

                    if (TicketOperations.winningTickets.isEmpty()) {
                        ticketsTextArea.append("No winners this round.");
                    }
                    else {
                        for (int i = 0; i < TicketOperations.winningTickets.size(); i++) {
                            ticketsTextArea.append("Winner: " + TicketOperations.winningTickets.get(i) + "\n");
                        }
                    }
                }
            }
        });

        // PRINT TICKET actionlistener
        printTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ticketId = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Ticket ID", "Enter ticket ID"));
                    ticketsTextArea.setText(null);

                    for (int i = 0; i < TicketOperations.tickets.size(); i++) {
                        if (TicketOperations.tickets.get(i).getTicketId() == ticketId) {
                            ticketsTextArea.append("Ticket " + ticketId + ": \n");
                            int[][] fetchRows = TicketOperations.tickets.get(i).getRows();
                            for (int j = 0; j < fetchRows.length; j++) {
                                ticketsTextArea.append((j+1) + ": ");
                                for (int k = 0; k < fetchRows[j].length; k++) {
                                    ticketsTextArea.append(fetchRows[j][k] + "\t");
                                }
                                ticketsTextArea.append("\n");
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
                userTextArea.setText(null);
                int[] userIds = UserOperations.returnUserIDs();
                for (int i = 0; i < userIds.length; i++) {
                    userTextArea.append("UID-" + i + ": " + userIds[i] + "\n");
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
                    userTextArea.setText(null);
                    userTextArea.setText("User " + userId + " has an account balance of:\n " + UserOperations.getUserObject(userId).getAccountBalance() + "kr");
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
                ticketsTextArea.setText(null);
                if (TicketOperations.archivedTickets.size() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "No archived tickets in databsase.", "Oops", JOptionPane.OK_OPTION);
                }
                else {
                    for (int i = 0; i < TicketOperations.archivedTickets.size(); i++) {
                        ticketsTextArea.append("Ticket numer " + (i+1) + ": " + TicketOperations.archivedTickets.get(i).getTicketId()+ "\n");
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
                    userTextArea.setText(null);
                    userTextArea.append("Name: " + user.getUserName() + "\n");
                    userTextArea.append("Email: " + user.getEmail() + "\n");
                    userTextArea.append("Phone number: " + user.getPhoneNumber() + "\n");
                    userTextArea.append("Adress: " + user.getAdress() + "\n");
                    userTextArea.append("Account balance: " + user.getAccountBalance() + "kr");
                }
                catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(mainFrame, "Only numbers in user-id", "Error", JOptionPane.OK_OPTION);
                    System.out.println("NumberFormatException " + err.getMessage());
                }
            }
        });
       
        // TESTBUTTON  
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {          

            }
        });
       
        // add objects to user-panel
        usersPanel.add(printUserIdsButton);
        usersPanel.add(newUserButton);
        usersPanel.add(getUserAccountBalanceButton);
        usersPanel.add(userTextAreaScroll);

        // add objects to tickets-panel
        ticketsPanel.add(ticketsTextAreaScroll);
        ticketsPanel.add(newTicketButton);
        ticketsPanel.add(allTicketsButton);
        ticketsPanel.add(printTicketButton);
        ticketsPanel.add(printArchivedTicketsButton);
        ticketsPanel.add(winningNumbersButton);
        ticketsPanel.add(findWinningTicketsButton);

        // add stuff to mainFrame
        mainFrame.add(mainPanel);
        mainFrame.add(usersPanel);
        mainFrame.add(ticketsPanel);
        mainFrame.setJMenuBar(topMenuBar);
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

    /* 
    *  Sets visibility of all panels except panelToShow to false 
    *  1 is main panel
    *  2 is users panel
    *  3 is tickets panel
    */
    public static void setVisiblityOfPanels(int panelToShow) {
        switch(panelToShow) {
            case 1: 
                mainPanelVisibility = true;
                usersPanelVisibility = false;
                ticketsPanelVisibility = false;
                break;
            case 2: 
                usersPanelVisibility = true;
                mainPanelVisibility = false;
                ticketsPanelVisibility = false;
                break;
            case 3: 
                ticketsPanelVisibility = true;
                usersPanelVisibility = false;
                mainPanelVisibility = false;
                break;
        }
    }
}