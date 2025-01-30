package pe.edu.utp.conexify.util;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Message {
    private String username;
    private List<String> messages;
    private LocalDateTime timeSendMessage;
    private boolean isOnline;
//    private boolean isRead;
//    private boolean isSent;
//    private boolean isReceived;
//    private boolean isDelivered;
//    private boolean isSeen;
//    private boolean isTyping;
//    private List<String> transmittedMessages;
//    private List<String> receivedMessages;
}
