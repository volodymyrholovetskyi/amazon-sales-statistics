package ua.vholovetskyi.amazonsalesstatistics.security.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ua.vholovetskyi.amazonsalesstatistics.utils.FieldPatterns.*;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-02-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    @NotBlank(message = "{user.firstName.notBlank}")
    @Size(min = 2, max = 70, message = "{user.firstName.invalid}")
    private String firstName;
    @NotBlank(message = "{user.lastName.notBlank}")
    @Size(min = 2, max = 70, message = "{user.lastName.invalid}")
    private String lastName;
    @Email(regexp = EMAIL_PATTERN, message = "{user.email.invalid}")
    private String username;
    @Pattern(regexp = PASSWORD_PATTERN, message = "{user.password.invalid}")
    private String password;

}
