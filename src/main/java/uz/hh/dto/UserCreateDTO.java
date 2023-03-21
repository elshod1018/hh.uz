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
    @NotBlank(message = "auth.user.fullname.required")
    String fullName;
    @NotBlank(message = "auth.user.username.required")
    String username;
    @NotBlank(message = "auth.user.email.required")
    @Email(message = "auth.user.email.required")
    String email;
    @NotBlank(message = "auth.user.password.required")
    String password;
    @NotBlank(message = "auth.user.confirmation.password.required")
    String confirmPassword;
    @NotBlank(message = "auth.user.firstName.required")
    private String firstName;
    @NotBlank(message = "auth.user.lastName.required")
    private String lastName;
    @NotBlank(message = "auth.user.phoneNumber.required")
    @Pattern(regexp = "^(\\+998)\\d{9}", message = "pattern.phone.number")
    private String phoneNumber;
    @NotBlank(message = "auth.user.region.required")
    private String region;
    @NotBlank(message = "auth.user.companyName.required")
    private String companyName;
    private Role role;
}