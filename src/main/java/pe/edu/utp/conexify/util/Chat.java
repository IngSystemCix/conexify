package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.badge.BadgeModel;
import org.primefaces.model.badge.DefaultBadgeModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Named
@RequestScoped
public class Chat implements Serializable {
    private String username;
    private boolean onlineStatus;
    private List<Message> messages;
    private BadgeModel badge;

    @PostConstruct
    public void init() {
        this.username = "Pedro Tarasona";
        this.onlineStatus = false;
        messages = new ArrayList<>(Arrays.asList(
                new Message("Pedro Tarasona", "Hola, ¿cómo estás?", LocalDateTime.now().minusMinutes(5), true),
                new Message("Juan Perez", "Hola, bien gracias, ¿y tú?", LocalDateTime.now().minusMinutes(4), false),
                new Message("Pedro Tarasona", "Bien, gracias por preguntar", LocalDateTime.now().minusMinutes(3), false),
                new Message("Juan Perez", "¿En qué puedo ayudarte?", LocalDateTime.now().minusMinutes(2), true)
        ));
        badge = DefaultBadgeModel.builder().value(String.valueOf(messages.size())).severity("danger").build();
    }
}
