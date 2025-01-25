package pe.edu.utp.conexify.util;

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
public class Chat implements Serializable {
    private String username;
    private boolean onlineStatus;

    @PostConstruct
    public void init() {
        this.username = "Pedro Tarasona";
        this.onlineStatus = false;
    }
}
