public class User {
    String userName;
    int userId;
    String phoneNumber;
    String email;
    String adress;
    User next;

    User(String userName, int userId, String phoneNumber, String email, String adress) {
        this.userName = userName;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adress = adress;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }
}
