package uz.hh.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import uz.hh.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserCreateDTO {
    @NotBlank(message = "Full name is required")
    String fullName;
    @NotBlank(message = "Username is required")
    String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    String email;
    @NotBlank(message = "Password is required")
    String password;
    @NotBlank(message = "Confirmation password is required")
    String confirmPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^(\\+998)\\d{9}", message = "Phone number is not valid")
    private String phoneNumber;
    @NotBlank
    private String region;
    @NotBlank
    private String companyName;
    private Role role;
}