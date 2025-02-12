package pe.edu.utp.conexify.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.html.HtmlOutputText;
import jakarta.faces.component.html.HtmlPanelGroup;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.avatar.Avatar;
import org.primefaces.component.commandbutton.CommandButton;
import pe.edu.utp.conexify.adapters.LocalDateTimeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Getter
@Setter
@Named
@ViewScoped
public class CommentsPostView implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(CommentsPostView.class.getName());
    private Long postId;
    private List<Comment> comments;
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create(); // Instancia de GSON para convertir JSON

    @PostConstruct
    public void init() {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            if (params.containsKey("postId")) {
                try {
                    this.postId = Long.parseLong(params.get("postId"));
                } catch (NumberFormatException e) {
                    this.postId = null;
                    LOGGER.severe("Error al convertir postId a Long: " + e.getMessage());
                }
            }

            if (params.containsKey("comments")) {
                String commentsJson = params.get("comments");
                if (commentsJson != null && !commentsJson.isEmpty()) {
                    this.comments = gson.fromJson(commentsJson, new TypeToken<List<Comment>>() {}.getType());
                } else {
                    this.comments = new ArrayList<>();
                }
            } else {
                this.comments = new ArrayList<>();
            }
        } catch (Exception e) {
            LOGGER.severe("Error al inicializar CommentsPostView: " + e.getMessage());
            this.comments = new ArrayList<>();
        }
    }

}