package application.backend.persistence.DTO;


public class UserInfoDTO {

    private String userId;
    private String userName;
    private String userDpPath;



    private  String user_email;

    public UserInfoDTO() {}

    public UserInfoDTO(String userId, String userName, String userDpPath) {
        this.userId = userId;
        this.userName = userName;
        this.userDpPath = userDpPath;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDpPath() {
        return userDpPath;
    }

    public void setUserDpPath(String userDpPath) {
        this.userDpPath = userDpPath;
    }
}

