package pe.edu.utp.conexify.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Named
@RequestScoped
public class PreferencesBean implements Serializable {
    private boolean switchComments;
    private boolean switchFriendRequests;
    private boolean switchBirthdayNotifications;

    @PostConstruct
    public void init() {
        switchComments = true;
        switchFriendRequests = true;
        switchBirthdayNotifications = true;
    }
}
