package pe.edu.utp.conexify.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Message {
    private String username;
    private String message;
    private LocalDateTime timeSendMessage;
    private boolean isOnline;
}
