package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import pe.edu.utp.conexify.dto.NotifyDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class NotificationBean implements Serializable {
    private List<NotifyDTO> notifications;

    @PostConstruct
    public void init() {
        notifications = new ArrayList<>(
            List.of(
                    new NotifyDTO("Welcome to Conexify", "info", "Conexify", "/"),
                    new NotifyDTO("You have a new message", "warning", "Conexify", "/"),
                    new NotifyDTO("You have a new friend request", "danger", "Conexify", "/"),
                    new NotifyDTO("You have a new notification", "success", "Conexify", "/")
            )
        );
    }

    public List<NotifyDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotifyDTO> notifications) {
        this.notifications = notifications;
    }
}
