package pe.edu.utp.conexify.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Named
@RequestScoped
public class CenterAccountBean implements Serializable {
    // 2FA
    private boolean switch2FA;
    // Change password
    private String password1;
    private String password2;

    public void changePassword() {
        // Change password logic
    }
}
