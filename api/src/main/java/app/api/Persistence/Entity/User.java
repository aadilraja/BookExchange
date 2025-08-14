package app.api.Persistence.Entity;

import jakarta.persistence.*;
import app.api.Persistence.Entity.Role;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="email",nullable = false, unique = true)
    private String email;
    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;
//    @Column(name="profile-image-url")
//    private String profileImageUrl;

//    @Enumerated(EnumType.STRING)
//    private AuthProviderEnum provider; // GOOGLE, GITHUB, etc.
//
//    private String providerId; // e.g. Google userId


   
    @Column(name="is-email-verified")
    private boolean isEmailVerified;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();

    @Column(name="createdAt")
    private LocalDateTime createdAt;
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;



    public User() {}
    public User(String email, String name,String password, String profileImageUrl,
                boolean isEmailVerified, LocalDateTime createdAt, LocalDateTime updatedAt)
    {
        this.email = email;
        this.name = name;
        this.password = password;
//        this.profileImageUrl = profileImageUrl;
        this.isEmailVerified = isEmailVerified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    public String getProfileImageUrl() {
//        return profileImageUrl;
//    }
//
//    public void setProfileImageUrl(String profileImageUrl) {
//        this.profileImageUrl = profileImageUrl;
//    }
//
    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }





    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



}






