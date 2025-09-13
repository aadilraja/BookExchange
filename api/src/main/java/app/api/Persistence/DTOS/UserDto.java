package app.api.Persistence.DTOS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private boolean isEmailVerified;
    private Set<String> roles; // Role names as strings
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<BookDTO> books;


    // Default constructor
    public UserDto() {}

    // Constructor with all fields
    public UserDto(Long id, String email, String name,boolean isEmailVerified, Set<String> roles,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isEmailVerified = isEmailVerified;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
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
    public boolean isEmailVerified() {
        return isEmailVerified;
    }
    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }
    List<BookDTO> getBooks() {
        return books;
    }
    void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}