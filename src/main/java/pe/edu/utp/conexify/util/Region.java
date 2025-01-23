package pe.edu.utp.conexify.util;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Named
@SessionScoped
public class Region implements Serializable {
    private String locale;
    private boolean showMessage;
    private boolean closed;

    public Region() {
        locale = "es";
        showMessage = false;
        closed = false;
    }

    public void onClose() {
        closed = true;
        showMessage = false; // Ocultar el mensaje después de cerrar
    }

}
