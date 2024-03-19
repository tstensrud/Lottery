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
    private static int numberOfPanels = 6;
    private static int currentActivePanel = 0;
    /* 
     * Visibility array for panels
     * [0] = main panel
     * [1] = users panel
     * [2] = tickets panel
     * [3] = ticket stats panel
     * [4] = user stats panel
     * [5] = game options panel
     */
    static boolean[] panelVisibility = {true, false, false, false, false, false}; // initialize visibility of panels.
    public static void main(String[] args) {

        drawUi();
        // generate one generic user for testing
        UserOperations.users.add(new User("Torbjørn", 1000, "34343434", "a@b.c", "Gata til a mor"));
        for (int i = 0; i < 100; i++) {
            TicketOperations.generateTicket(10, 1000);
        }
    }

    public static void drawUi() {

        // dimensions
        int buttonLength = 150;
        int buttonHeight = 30;
        int textFieldHeight = 25;
        int textFieldLength = 250;
        int labelHeight = 25;

        // find screenresolution of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        
        // MENU BARS
        JMenuBar topMenuBar = new JMenuBar();

        // file menu
        JMenu menuFile = new JMenu("File");
        JMenuItem menuGameAdministration = new JMenuItem("Game administration");
        JMenuItem menuItemExit = new JMenuItem("Exit");
        JMenuItem menuItemLogOut = new JMenuItem("Log out");
        menuFile.add(menuGameAdministration);
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

        // game statistics menu
        JMenu menuGameStats = new JMenu("Game stats");
        JMenuItem menuGameStatsTicketStats = new JMenuItem("Ticket stats");
        JMenuItem menuGameStatsUserStats =new JMenuItem("User stats");
        menuGameStats.add(menuGameStatsTicketStats);
        menuGameStats.add(menuGameStatsUserStats);

        topMenuBar.add(menuFile);
        topMenuBar.add(menuTickets);
        topMenuBar.add(menuUsers);
        topMenuBar.add(menuGameStats);

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
         * Generate panels. The total amount is fixed
         */
        JPanel[] panels = new JPanel[numberOfPanels];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setBounds(0,0,MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
            panels[i].setLayout(null);
            panels[i].setVisible(panelVisibility[i]);
        }
        

        /*
         * MAIN PANEL, panel[0]
         * All objects for the MAIN panel
         */
        JLabel welcomeMessage = new JLabel("Use menu to begin");
        welcomeMessage.setBounds(5,0,200,labelHeight);
        panels[0].add(welcomeMessage);

        /*
         * USERS PANEL panel[1]
         * All objects for the USERS panel
         */
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
         * TICKETS PANEL panel[2]
         * All objects for the TICKETS panel
         */
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
        
        /*
         * TICKET STATS PANEL panel[3]
         * All objects  for GAME STATS panel
         */
        // labels
        JLabel totalCurrentTicketsLabel = new JLabel();
        JLabel totalArchivedTicketsLabel = new JLabel();
        JLabel totalPlayedForThisRoundLabel = new JLabel();
        JLabel totalPlayedForTotal = new JLabel();
        totalCurrentTicketsLabel.setBounds(5,0,400,labelHeight);
        totalArchivedTicketsLabel.setBounds(5,25,400,labelHeight);
        totalPlayedForThisRoundLabel.setBounds(5,50, 400,labelHeight);
        totalPlayedForTotal.setBounds(5,75,400,labelHeight);

        /*
         * USER STATS PANEL panel[4]
         * All objects for USER STATS panel
         */
        JLabel totalUsersLabel = new JLabel();
        totalUsersLabel.setBounds(5,0,400,labelHeight);


        /*
         * GAME OPTIONS PANEL panel[5]
         * All objects for GAME OPTIONS panel
         */
        JLabel currentPriceLabel = new JLabel("Current price per row: ");
        currentPriceLabel.setBounds(5,5,150,labelHeight);
        JLabel currentPlayableNumbersLabel = new JLabel ("Current max playable numbers: ");
        JTextField currentPriceTextField = new JTextField();
        currentPriceTextField.setBounds(160,5,30,25);
        JButton updatePriceButton = new JButton("Update");
        updatePriceButton.setBounds(200,5,buttonLength, buttonHeight);

        currentPlayableNumbersLabel.setBounds(5,50,400,labelHeight);
        panels[5].add(currentPriceLabel);
        panels[5].add(currentPlayableNumbersLabel);
        panels[5].add(currentPriceTextField);
        panels[5].add(updatePriceButton);

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
                panels[currentActivePanel].setVisible(false);
                setActivePanel(1);
                panels[currentActivePanel].setVisible(true);

            }
        });

        // TICKETS MENU
        menuItemTickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panels[currentActivePanel].setVisible(false);
                setActivePanel(2);
                panels[currentActivePanel].setVisible(true);

            }
        });

        // RESET GAME actionlistener
        menuItemResetGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmReset = JOptionPane.showConfirmDialog(mainFrame, "Do you want to reset? This is not undoable!", "RESET", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirmReset == JOptionPane.YES_OPTION) {
                    gameReset();
                    JOptionPane.showMessageDialog(mainFrame, "Game is reset", "Reset", JOptionPane.OK_OPTION);
                }
                else if (confirmReset == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, "Cancelled", "Cancelled", JOptionPane.OK_OPTION);
                }
            }
        });

        // GAME ADMINISTRATION actionlistener
        menuGameAdministration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = JOptionPane.showInputDialog(mainFrame, "Enter password", "Password required", JOptionPane.INFORMATION_MESSAGE);
                try {
                    if (password.equals("admin123")) {
                        panels[currentActivePanel].setVisible(false);
                        setActivePanel(5);
                        panels[currentActivePanel].setVisible(true);
                        currentPriceTextField.setText(Integer.toString(TicketOperations.getCostPerRow()));

                    }
                    else {
                        JOptionPane.showMessageDialog(mainFrame, "Wrong password", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (NullPointerException err) {
                    System.out.println("Login cancelled");
                }
            }
        });

        // GAME STATS MENU
        // Ticket stats
        menuGameStatsTicketStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panels[currentActivePanel].setVisible(false);
                setActivePanel(3);
                panels[currentActivePanel].setVisible(true);
                
                totalCurrentTicketsLabel.setText("Total current tickets in play: " + TicketOperations.getTotalActiveTickets());
                totalArchivedTicketsLabel.setText("Total tickets in archive: " + TicketOperations.getTotalArchivedTickets());
                totalPlayedForThisRoundLabel.setText("Total played for current round: " + TicketOperations.getAmountPlayedForThisRound());
                totalPlayedForTotal.setText("Total played for all time: " + TicketOperations.getAmountPlayedForTotal());

            }
        });
        // User stats
        menuGameStatsUserStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panels[currentActivePanel].setVisible(false);
                setActivePanel(4);
                panels[currentActivePanel].setVisible(true);
                totalUsersLabel.setText("Total users in database: " + UserOperations.getTotalUsers());
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
       
        // add objects to user-panel
        panels[1].add(printUserIdsButton);
        panels[1].add(newUserButton);
        panels[1].add(getUserAccountBalanceButton);
        panels[1].add(userTextAreaScroll);

        // add objects to tickets-panel
        panels[2].add(ticketsTextAreaScroll);
        panels[2].add(newTicketButton);
        panels[2].add(allTicketsButton);
        panels[2].add(printTicketButton);
        panels[2].add(printArchivedTicketsButton);
        panels[2].add(winningNumbersButton);
        panels[2].add(findWinningTicketsButton);

        // add objects to ticket stats panel
        panels[3].add(totalCurrentTicketsLabel);
        panels[3].add(totalArchivedTicketsLabel);
        panels[3].add(totalPlayedForThisRoundLabel);
        panels[3].add(totalPlayedForTotal);

        // add objects to user stats panel
        panels[4].add(totalUsersLabel);

        // add panels to mainFrame
        for (int i = 0; i < panels.length; i++) {
            mainFrame.add(panels[i]);
        }

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
    private static void gameReset() {
        
        // set winning numbers to 0
        for (int i= 0; i< winningRow.length; i++) {
            winningRow[i] = 0;
        }
        // empty active tickets
        TicketOperations.restForNewGame();
    }

    public static int getActivePanel() {
        return currentActivePanel;
    }

    public static void setActivePanel(int newActivePanel) {
        currentActivePanel = newActivePanel;
    }
}