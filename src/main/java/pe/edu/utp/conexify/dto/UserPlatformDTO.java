package pe.edu.utp.conexify.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class UserPlatformDTO {
    private String name;
    private int amountMutualFriends;
}
