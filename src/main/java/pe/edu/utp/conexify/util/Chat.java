package pe.edu.utp.conexify.util;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import pe.edu.utp.conexify.service.EmojinService;
import pe.edu.utp.conexify.service.EmojinServiceImpl;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    private List<Message> messagesChatTransmitted;
    private List<Message> messagesChatReceived;
    private List<Friend> friends;
    private List<UserPlatform> userPlatforms;
    private String searchUsername;
    private String searchUsernamePlatform;
    private boolean selectedChat;
    private String valueInputMessage = "";
    private final EmojinService emojinService;
    private Set<LocalDate> shownDates = new HashSet<>();
    private Map<String, List<String>> emojisByCategory = new ConcurrentHashMap<>();
    private static final Map<String, String> CATEGORY_EMOJIS = Map.of(
            "smileys-emotion", "😀",
            "people-body", "👨",
            "animals-nature", "🐶",
            "food-drink", "🍔",
            "travel-places", "🚗",
            "activities", "🎮",
            "objects", "📦",
            "symbols", "❤️",
            "flags", "🏳️"
    );
    private final List<String> categories = List.copyOf(CATEGORY_EMOJIS.keySet());
    private String category;

    @Inject
    public Chat() {
        this.category = "smileys-emotion";
        this.emojinService = new EmojinServiceImpl();
        this.username = null;
        this.onlineStatus = false;
        this.selectedChat = false;
        this.messagesChatTransmitted = new ArrayList<>();
        this.messagesChatReceived = new ArrayList<>();
        this.messages = new ArrayList<>(Arrays.asList(
                Message.builder().username("Fatima Perez").lastMessage("Te extraño 😔").timeSendMessage(LocalDateTime.now().minusMinutes(4)).isOnline(false).transmittedMessages(
                                new LinkedHashMap<>(Map.of("Hola, ¿cómo estás guapo?", LocalTime.now().minusMinutes(4),
                                        "¿En qué puedo ayudarte?", LocalTime.now().minusMinutes(3),
                                        "Quiero verte pronto", LocalTime.now().minusMinutes(2)
                                ))
                        ).receivedMessages(
                                new LinkedHashMap<>(Map.of("Hola Fatima", LocalTime.now().minusMinutes(4),
                                        "¿Cuándo nos vemos?", LocalTime.now().minusMinutes(3),
                                        "Te extraño 😔", LocalTime.now().minusMinutes(2)
                                ))
                        ).build(),
                Message.builder().username("Xavi Ugarte").lastMessage("Que onda hermano que tal a sido de tu vida ya no te veo por aquí que es de tu esposa la Sandra.").timeSendMessage(LocalDateTime.now().minusMinutes(3)).isOnline(false).
                        transmittedMessages(
                                new LinkedHashMap<>(Map.of("Hola, ¿cómo estás?", LocalTime.now().minusMinutes(3),
                                        "¿En qué puedo ayudarte?", LocalTime.now().minusMinutes(2),
                                        "Soy Xavi, tu amigo de la infancia", LocalTime.now().minusMinutes(1)
                                ))
                        ).receivedMessages(
                                new LinkedHashMap<>(Map.of("Hola Xavi", LocalTime.now().minusMinutes(3),
                                        "¿Cuándo nos vemos?", LocalTime.now().minusMinutes(2),
                                        "Que onda hermano que tal a sido de tu vida ya no te veo por aquí que es de tu esposa la Sandra.", LocalTime.now().minusMinutes(1)
                                ))
                        ).build(),
                Message.builder().username("Susy Arjona").lastMessage("yo tmb 😔").timeSendMessage(LocalDateTime.now().minusMinutes(2)).isOnline(true)
                        .transmittedMessages(
                                new LinkedHashMap<>(Map.of("Hola, ¿cómo estás?", LocalTime.now().minusMinutes(2),
                                        "Te extraño 😔", LocalTime.now()
                                ))
                        ).receivedMessages(
                                new LinkedHashMap<>(Map.of("Hola Susy", LocalTime.now().minusMinutes(2),
                                        "¿Cuándo nos vemos?", LocalTime.now().minusMinutes(1),
                                        "yo tmb 😔", LocalTime.now()
                                ))).build(),
                Message.builder().username("Luisa Mendoza").lastMessage("😔").timeSendMessage(LocalDateTime.now().minusMinutes(1)).isOnline(true).
                        transmittedMessages(
                                new LinkedHashMap<>(Map.of("Hola, ¿cómo estás?", LocalTime.now().minusMinutes(1),
                                        "¿En qué puedo ayudarte?", LocalTime.now(),
                                        "Te echo de menos 😔", LocalTime.now().plusMinutes(1)
                                ))
                        ).receivedMessages(
                                new LinkedHashMap<>(Map.of("Hola Luisa", LocalTime.now().minusMinutes(1),
                                        "¿Cuándo nos vemos?", LocalTime.now(),
                                        "😔", LocalTime.now().plusMinutes(1)
                                ))
                        ).build()
        ));
        this.friends = new ArrayList<>(Arrays.asList(
                new Friend("Fatima Perez", false),
                new Friend("Xavi Ugarte", false),
                new Friend("Susy Arjona", true),
                new Friend("Luisa Mendoza", false)
        ));
        this.userPlatforms = new ArrayList<>(Arrays.asList(
                new UserPlatform("Moon Monteza", 3),
                new UserPlatform("Mary Mendoza", 2),
                new UserPlatform("Giovanna Perez", 1),
                new UserPlatform("Fernando Zapata", 0)
        ));
    }

    @PostConstruct
    public void init() {
        categories.parallelStream().forEach(category -> {
            try {
                Map<String, List<String>> result = emojinService.getAllEmojis(category);
                if (result != null && result.containsKey(category)) {
                    emojisByCategory.put(category, Collections.unmodifiableList(result.get(category)));
                }
            } catch (Exception e) {
                // 5. Manejo de errores adecuado
                LOGGER.severe("Error loading emojis for category: " + category);
            }
        });
    }

    public String getEmoticonTab(String category) {
        return CATEGORY_EMOJIS.getOrDefault(category, "❓");
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
        // Verificar que los mapas no sean nulos antes de procesarlos
        if (message.getTransmittedMessages() != null) {
            this.messagesChatTransmitted = message.getTransmittedMessages().entrySet().stream()
                    .map(entry -> Message.builder()
                            .username(message.getUsername())
                            .lastMessage(entry.getKey()) // Guardar el contenido del mensaje
                            .timeSendMessage(LocalDateTime.of(message.getTimeSendMessage().toLocalDate(), entry.getValue()))
                            .isOnline(message.isOnline())
                            .transmitted(true)
                            .build())
                    .collect(Collectors.toList());
        } else {
            this.messagesChatTransmitted = new ArrayList<>();
        }

        if (message.getReceivedMessages() != null) {
            this.messagesChatReceived = message.getReceivedMessages().entrySet().stream()
                    .map(entry -> Message.builder()
                            .username(message.getUsername())
                            .lastMessage(entry.getKey()) // Guardar el contenido del mensaje
                            .timeSendMessage(LocalDateTime.of(message.getTimeSendMessage().toLocalDate(), entry.getValue()))
                            .isOnline(message.isOnline())
                            .transmitted(false)
                            .build())
                    .collect(Collectors.toList());
        } else {
            this.messagesChatReceived = new ArrayList<>();
        }

        this.username = username;
        this.onlineStatus = onlineStatus;
        this.selectedChat = true;
    }

    public void closeChat() {
        this.username = null;
        this.onlineStatus = false;
        this.selectedChat = false;
        this.messagesChatTransmitted.clear();
    }

    public void addEmojin(String emoji) {
        this.valueInputMessage += emoji;
        PrimeFaces.current().executeScript("PF('inputWriteMessage').val('" + this.valueInputMessage + "');");
        LOGGER.info("Emoji agregado: " + emoji);
    }

    public List<Message> getAllMessagesSorted() {
        List<Message> allMessages = new ArrayList<>();
        if (messagesChatTransmitted != null) {
            allMessages.addAll(messagesChatTransmitted);
        }
        if (messagesChatReceived != null) {
            allMessages.addAll(messagesChatReceived);
        }
        allMessages.sort(Comparator.comparing(Message::getTimeSendMessage)); // Orden por fecha y hora
        return allMessages;
    }

    public String convertTime(LocalDateTime time) {
        if (time == null) {
            return "";
        }

        LocalDate today = LocalDate.now();
        LocalDate messageDate = time.toLocalDate();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (messageDate.isEqual(today)) {
            return "hoy " + time.format(timeFormatter);
        } else if (messageDate.isEqual(today.minusDays(1))) {
            return "ayer " + time.format(timeFormatter);
        } else {
            return time.format(fullFormatter);
        }
    }

    public boolean shouldShowDivider(LocalDateTime time) {
        LocalDate messageDate = time.toLocalDate();
        if (!shownDates.contains(messageDate)) {
            shownDates.add(messageDate);
            return true;
        }
        return false;
    }

    public void send() {
        if (valueInputMessage != null && !valueInputMessage.trim().isEmpty()) {
            Message currentMessage = messages.stream()
                    .filter(m -> m.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);

            if (currentMessage != null) {
                LocalTime currentTime = LocalTime.now();

                // Crear un nuevo mensaje con el nuevo texto y hora
                Message newMessage = Message.builder()
                        .username(username)
                        .lastMessage(valueInputMessage)
                        .timeSendMessage(LocalDateTime.now())
                        .isOnline(true) // Ajusta según sea necesario
                        .transmittedMessages(new HashMap<>(currentMessage.getTransmittedMessages())) // Copia el mapa de mensajes transmitidos
                        .receivedMessages(new HashMap<>(currentMessage.getReceivedMessages())) // Copia el mapa de mensajes recibidos
                        .build();

                // Agregamos el nuevo mensaje transmitido al mapa de mensajes recibidos
                currentMessage.getReceivedMessages().put(valueInputMessage, currentTime);

                // Agregamos el mensaje a la lista de mensajes transmitidos
                messagesChatReceived.add(newMessage);

                // Limpiamos el input del mensaje
                valueInputMessage = "";

                // Actualizamos la interfaz con PrimeFaces
                PrimeFaces.current().ajax().update("chatWithMessage:inputWriteMessage", "chatWithMessage");
                PrimeFaces.current().executeScript("scrollToBottom()");
            }
        }
    }


}
