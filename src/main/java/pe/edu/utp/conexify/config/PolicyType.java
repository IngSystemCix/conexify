package pe.edu.utp.conexify.config;

public enum PolicyType {
    PRIVACY, TERMS, COOKIES, COMMUNITY;

    public static PolicyType fromString(String policyType) {
        return switch (policyType) {
            case "PRIVACY" -> PRIVACY;
            case "TERMS" -> TERMS;
            case "COOKIES" -> COOKIES;
            case "COMMUNITY" -> COMMUNITY;
            default -> null;
        };
    }
}
