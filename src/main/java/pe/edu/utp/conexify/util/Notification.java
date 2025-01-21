package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class Notification implements Serializable {
    private List<Notify> notifications;

    @PostConstruct
    public void init() {
        notifications = new ArrayList<>(
            List.of(
                    new Notify("Welcome to Conexify", "info", "Conexify", "/"),
                    new Notify("You have a new message", "warning", "Conexify", "/"),
                    new Notify("You have a new friend request", "danger", "Conexify", "/"),
                    new Notify("You have a new notification", "success", "Conexify", "/")
            )
        );
    }

    public List<Notify> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notify> notifications) {
        this.notifications = notifications;
    }
}
