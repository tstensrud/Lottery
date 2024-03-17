public class User {
    private String userName;
    private int userId;
    private String phoneNumber;
    private String email;
    private String adress;
    private int accountBalance;
    User next;

    User(String userName, int userId, String phoneNumber, String email, String adress) {
        this.userName = userName;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adress = adress;
        this.accountBalance = 0;
    }

    public String getUserName() {
        return this.userName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAdress() {
        return this.adress;
    }

    public int getAccountBalande() {
        return accountBalance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumbere(String number) {
        this.phoneNumber = number;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void addToAccountBalane(int sum) {
        accountBalance = accountBalance + sum;
    }
}
