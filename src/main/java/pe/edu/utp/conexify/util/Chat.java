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
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Getter
@Setter
@Named
@ViewScoped
public class Chat implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(Chat.class.getName());
    private String username;
    private boolean onlineStatus;
    private List<Message> messages;
    private List<Message> messagesChat;
    private List<Friend> friends;
    private List<UserPlatform> userPlatforms;
    private String searchUsername;
    private String searchUsernamePlatform;
    private boolean selectedChat;
    private String valueInputMessage = "";

    @PostConstruct
    public void init() {
        this.username = null;
        this.onlineStatus = false;
        this.selectedChat = false;
        this.messagesChat = new ArrayList<>();
        messages = new ArrayList<>(Arrays.asList(
                new Message("Luisa Mendoza", Arrays.asList(
                        "Hola, ¿cómo estás?",
                        "¿En qué puedo ayudarte?"
                ), LocalDateTime.now().minusMinutes(5), true),
                new Message("Fatima Perez", Arrays.asList(
                        "Hola, ¿cómo estás guapo?",
                        "¿En qué puedo ayudarte?",
                        "Quiero verte pronto"
                ), LocalDateTime.now().minusMinutes(4), false),
                new Message("Xavi Ugarte", Arrays.asList(
                        "Hola, ¿cómo estás?",
                        "¿En qué puedo ayudarte?",
                        "Soy Xavi, tu amigo de la infancia"
                ), LocalDateTime.now().minusMinutes(3), false),
                new Message("Susy Arjona", Arrays.asList(
                        "Hola, ¿cómo estás?",
                        "¿En qué puedo ayudarte?",
                        "Te extraño 😔"
                ), LocalDateTime.now().minusMinutes(2), true)
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
        return friends.stream()
                .map(Friend::getUsername)
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .filter(name -> !name.equals(username))
                .collect(Collectors.toList());
    }

    public List<String> completePlatform(String query) {
        return userPlatforms.stream()
                .map(UserPlatform::getName)
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .filter(name -> !name.equals(username))
                .collect(Collectors.toList());
    }

    public void selectChat(Message message, String username, boolean onlineStatus) {
        List<String> newMessages = new ArrayList<>(message.getMessages());

        this.messagesChat.add(new Message(username, newMessages, LocalDateTime.now().minusMinutes(5), onlineStatus));

        this.username = username;
        this.onlineStatus = onlineStatus;
        this.selectedChat = true;
    }

    public void closeChat() {
        this.username = null;
        this.onlineStatus = false;
        this.selectedChat = false;
        this.messagesChat.clear();
    }

    public void addEmojin(String emoji) {
        LOGGER.info("emoji: " + emoji);
        if (valueInputMessage == null) {
            valueInputMessage = emoji;
        } else {
            valueInputMessage += emoji;
        }
        LOGGER.info("valueInputMessage: " + valueInputMessage);
    }
}
