import java.util.LinkedList;

public class UserOperations {
    public static LinkedList<User> users = new LinkedList<User>();


    // add new user
    public static void addNewUser(String userName, int userId, String userPhone, String userEmail, String userAdress) {
        users.add(new User(userName, userId, userPhone, userEmail, userAdress));
    }

    // find if a user Id exists in users-list
    public static boolean findUserID(int userId) {
        boolean isFound = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId) {
                System.out.println(users.get(i).getUserId() + " - " + userId);
                isFound = true;
            }
        }
        return isFound;
    }

    // return userobject with specific ID
    public static User returnUserObject(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId) {
                return users.get(i);
            }
        }
        return null;
    }
    // return all userIDs in users-list
    public static int[] returnUserIDs() {
        int[] userIds = new int[users.size()];

        for (int i = 0; i < users.size(); i++) {
            userIds[i] = users.get(i).getUserId();
        }

        return userIds;
    }
}
