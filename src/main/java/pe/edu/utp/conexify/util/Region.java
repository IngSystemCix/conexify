package pe.edu.utp.conexify.util;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
        showMessage = true;
        closed = false;
    }

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public void onClose() {
        closed = true;
        showMessage = false; // Ocultar el mensaje después de cerrar
    }

    public boolean isClosed() {
        return closed;
    }
}
