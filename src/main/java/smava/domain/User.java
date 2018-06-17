package smava.domain;

public class User {
    private String userEmail;
    private String userPassword;

    public User(String uEmail, String uPassword) {
        this.userEmail = uEmail;
        this.userPassword = uPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
