package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Named
@ViewScoped
public class Chat implements Serializable {
    private String username;
    private boolean onlineStatus;
    private List<Message> messages;
    private List<Friend> friends;
    private List<UserPlatform> userPlatforms;
    private String searchUsername;
    private String searchUsernamePlatform;


    @PostConstruct
    public void init() {
        this.username = "Pedro Tarasona";
        this.onlineStatus = false;
        messages = new ArrayList<>(Arrays.asList(
                new Message("Luisa Mendoza", "Hola, ¿cómo estás?", LocalDateTime.now().minusMinutes(5), true),
                new Message("Fatima Perez", "Hola, bien gracias, ¿y tú?", LocalDateTime.now().minusMinutes(4), false),
                new Message("Xavi Ugarte", "Bien, gracias por preguntar", LocalDateTime.now().minusMinutes(3), false),
                new Message("Susy Arjona", "¿En qué puedo ayudarte?", LocalDateTime.now().minusMinutes(2), true)
        ));
        friends = new ArrayList<>(Arrays.asList(
                new Friend("Fatima Perez", false),
                new Friend("Xavi Ugarte", false),
                new Friend("Susy Arjona", true),
                new Friend("Luisa Mendoza", false)
        ));
        userPlatforms = new ArrayList<>(Arrays.asList(
                new UserPlatform("Moon Monteza", 3),
                new UserPlatform("Mary Mendoza", 2),
                new UserPlatform("Giovanna Perez", 1),
                new UserPlatform("Fernando Zapata", 0)
        ));
    }

    public List<String> completeUsername(String query) {
        List<String> results = new ArrayList<>();
        for (Friend friend : friends) {
            if (friend.getUsername().toLowerCase().contains(query.toLowerCase())) {
                results.add(friend.getUsername());
            }
        }
        return results.stream().filter(result -> !result.equals(username)).collect(Collectors.toList());
    }

    public List<String> completePlatform(String query) {
        List<String> results = new ArrayList<>();
        for (UserPlatform userPlatform : userPlatforms) {
            if (userPlatform.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(userPlatform.getName());
            }
        }
        return results.stream().filter(result -> !result.equals(username)).collect(Collectors.toList());
    }
}
