package pe.edu.utp.conexify.util;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for managing HTTP sessions in a JSF context.
 */
public class ControllerSession {

    /**
     * Saves a key-value pair in the current HTTP session.
     *
     * @param key   the key under which the value is stored
     * @param value the value to be stored in the session
     */
    public void saveSession(String key, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null && context.getExternalContext() != null) {
            // Guarda el valor en la sesión actual
            context.getExternalContext().getSessionMap().put(key, value);
        } else {
            // Si no se puede obtener el contexto, lanza una excepción
            throw new IllegalStateException("No se pudo obtener el contexto de FacesContext.");
        }
    }

    /**
     * Retrieves a value from the current HTTP session by its key.
     *
     * @param key the key of the value to be retrieved
     * @return the value associated with the key, or null if the key does not exist
     */
    public Object getSession(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null && context.getExternalContext() != null) {
            // Retorna el valor de la sesión
            return context.getExternalContext().getSessionMap().get(key);
        } else {
            // Si no se puede obtener el contexto, lanza una excepción
            throw new IllegalStateException("No se pudo obtener el contexto de FacesContext.");
        }
    }

    /**
     * Removes an attribute from the current HTTP session.
     *
     * @param key the key of the attribute to be removed
     */
    public void removeSession(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null && context.getExternalContext() != null) {
            // Elimina el atributo de la sesión
            context.getExternalContext().getSessionMap().remove(key);
        } else {
            // Si no se puede obtener el contexto, lanza una excepción
            throw new IllegalStateException("No se pudo obtener el contexto de FacesContext.");
        }
    }

    /**
     * Invalidates the current HTTP session.
     */
    public void invalidateSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null && context.getExternalContext() != null) {
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            if (session != null) {
                // Invalida la sesión actual
                session.invalidate();
            } else {
                // Si la sesión no es válida, lanza una excepción
                throw new IllegalStateException("No se pudo obtener la sesión para invalidarla.");
            }
        } else {
            // Si no se puede obtener el contexto, lanza una excepción
            throw new IllegalStateException("No se pudo obtener el contexto de FacesContext.");
        }
    }
}
