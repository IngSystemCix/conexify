package pe.edu.utp.conexify.util;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Named
@ViewScoped
public class EditorView implements Serializable {
    private String text;
}
