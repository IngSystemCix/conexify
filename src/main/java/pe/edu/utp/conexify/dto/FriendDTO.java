package pe.edu.utp.conexify.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class FriendDTO {
    private String username;
    private boolean onlineStatus;
}
