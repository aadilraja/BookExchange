package application.backend.persistence.DTO;


public class UserCreateDTO {

    private String userName;
    private String userPassword;
    private String userEmail;
    private String userDpPath;

    public UserCreateDTO() {}

    public UserCreateDTO(String userName, String userPassword, String userEmail, String userDpPath) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userDpPath = userDpPath;
    }

    // Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDpPath() {
        return userDpPath;
    }

    public void setUserDpPath(String userDpPath) {
        this.userDpPath = userDpPath;
    }
}
