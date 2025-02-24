package pe.edu.utp.conexify.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotifyDTO {
    private String message;
    private String type;
    private String sender;
    private String link;

    public NotifyDTO(String message, String type, String sender, String link) {
        this.message = message;
        this.type = type;
        this.sender = sender;
        this.link = link;
    }

    public String getIcon() {
        return switch (type) {
            case "warning" -> "bi bi-exclamation-square";
            case "danger" -> "bi bi-radioactive";
            case "success" -> "bi bi-check-square";
            default -> "bi bi-info-circle";
        };
    }

    public String getColor() {
        return switch (type) {
            case "warning" -> "yellow-500";
            case "danger" -> "pink-500";
            case "success" -> "green-400";
            default -> "primary";
        };
    }
}
