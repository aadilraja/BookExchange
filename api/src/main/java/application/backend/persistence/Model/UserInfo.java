package application.backend.persistence.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name="user_id", nullable=false)
    private  String user_id;
    @Column(name="user_name", nullable=false,unique=true)
    private  String user_name;
    @Column(name="user_password", nullable=false)
    private  String user_password;
    @Column(name="user_dp_path")
    private  String user_dp_path;



    @Column(name="user_email",nullable = false)
    private  String user_email;

    public UserInfo() {}
    public UserInfo(String user_dp_path, String user_id,String user_email, String user_name, String user_password) {
        this.user_dp_path = user_dp_path;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
    }
    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_dp_path() {
        return user_dp_path;
    }

    public void setUser_dp_path(String user_dp_path) {
        this.user_dp_path = user_dp_path;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


}
