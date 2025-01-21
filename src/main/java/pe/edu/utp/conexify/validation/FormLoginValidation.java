package pe.edu.utp.conexify.validation;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class FormLoginValidation implements Serializable {

    private String email;
    private String password;
    private boolean rememberMe;
    private boolean showMessage;
    private boolean closed;

    public FormLoginValidation() {
        email = "";
        password = "";
        rememberMe = false;
        closed = false;
        showMessage = false;
    }

    public FormLoginValidation(String email, String password, boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public void onClose() {
        closed = true;
        showMessage = false; // Ocultar el mensaje después de cerrarlo
    }

    public boolean isClosed() {
        return closed;
    }

    public void login() {
        if (email.equals("user@fff.com") && password.equals("dasdaaf")) {
            showMessage = false; // Ocultar mensaje de error en caso de éxito
        }
        showMessage = true;
        closed = false;
    }
}
