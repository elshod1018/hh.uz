package uz.hh.dto;

public record UserCreateDTO(String username, String password,
                            String confirmPassword, String email) {
}
