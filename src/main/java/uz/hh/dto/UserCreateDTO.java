package uz.hh.dto;

import java.util.Objects;

public class UserCreateDTO {
    private final String username;
    private final String password;
    private final String confirmPassword;
    private final String email;

    public UserCreateDTO(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String confirmPassword() {
        return confirmPassword;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserCreateDTO) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.confirmPassword, that.confirmPassword) &&
                Objects.equals(this.email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, confirmPassword, email);
    }

    @Override
    public String toString() {
        return "UserCreateDTO[" +
                "username=" + username + ", " +
                "password=" + password + ", " +
                "confirmPassword=" + confirmPassword + ", " +
                "email=" + email + ']';
    }

}
