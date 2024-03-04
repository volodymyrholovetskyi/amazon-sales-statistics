package ua.vholovetskyi.amazonsalesstatistics.security.model;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-03-04
 */
public enum UserRole {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
