package app.api.Persistence.DTOS;

import app.api.utils.CustomAnnotation.PasswordMatches;
import app.api.utils.CustomAnnotation.ValidEmail;
import jakarta.validation.constraints.*;

import java.util.Set;

@PasswordMatches
public class UserCreateDto extends BaseDtoAudit {
    @NotBlank
    @Size(max=50)
    @ValidEmail
    private String email;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    private String confirmPassword;
    private Set<String> role;
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;


    public UserCreateDto() {}
    public UserCreateDto(String email, String name, String password, String confirmPassword, Set<String> role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public @NotBlank @Size(min = 3, max = 20) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 3, max = 20) String name) {
        this.name = name;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 6, max = 40) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 6, max = 40) String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }




}
