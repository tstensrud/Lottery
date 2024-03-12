import java.util.LinkedList;

public class UserOperations {
    public static LinkedList<User> users = new LinkedList<User>();

    public static boolean findUserID(int userId) {
        boolean isFound = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == userId); {
                isFound = true;
            }
        }
        return isFound;
    }
}
