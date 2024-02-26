public class User {
    String userName;
    int userId;
    int phoneNumber;
    String email;
    String adress;
    User next;

    User(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }
}
