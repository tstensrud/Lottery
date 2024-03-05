public class User {
    String userName;
    int userId;
    int phoneNumber;
    String email;
    String adress;
    User next;

    User(String userName, int userId, int phoneNumber, String email, String adress) {
        this.userName = userName;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adress = adress;
    }
}
