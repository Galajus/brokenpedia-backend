package pl.galajus.brokenpediabackend.security.model;

public enum UserRole {
    ROLE_ADMIN("ADMIN"),
    ROLE_MODERATOR("MODERATOR"),
    ROLE_CUSTOMER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
