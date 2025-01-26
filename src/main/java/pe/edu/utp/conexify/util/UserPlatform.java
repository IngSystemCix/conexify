package pe.edu.utp.conexify.util;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class UserPlatform {
    private String name;
    private int amountMutualFriends;
}
