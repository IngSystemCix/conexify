package pe.edu.utp.conexify.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    private String username;
    private String lastMessage;
    private LocalDateTime timeSendMessage;
    private boolean isOnline;
    private Map<String, LocalTime> transmittedMessages;
    private Map<String, LocalTime> receivedMessages;
    private boolean transmitted;
    private boolean truncated;

}
