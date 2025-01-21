package pe.edu.utp.conexify.util;

import jakarta.faces.context.FacesContext;

import java.util.ResourceBundle;

public class Bundle {
    public static String getAttributeI18N(String value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
        return bundle.getString(value);
    }
}
