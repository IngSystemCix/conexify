package pe.edu.utp.conexify.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import pe.edu.utp.conexify.adapters.LocalDateTimeAdapter;
import pe.edu.utp.conexify.dto.CommentDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Getter
@Setter
@Named
@ViewScoped
public class CommentsPostBean implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(CommentsPostBean.class.getName());
    private Long postId;
    private List<CommentDTO> comments;
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create(); // Instancia de GSON para convertir JSON
    private CommentDTO commentIdByUser;
    private String inputComment;

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
                    this.comments = gson.fromJson(commentsJson, new TypeToken<List<CommentDTO>>() {}.getType());
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

    public String getColorBorder(String username) {
        // Genera un color de fondo en formato HSLA con valores de 100% para saturación, luminosidad y opacidad
        int hue = Math.abs(username.hashCode() % 40) * 9; // Calcula el matiz basado en el hash del nombre
        return String.format("hsla(%d,50%%,50%%,1)", hue); // Devuelve el color en formato HSLA
    }


    public void likeComment(Long commentId) {
        if (comments == null) {
            LOGGER.severe("La lista de comentarios es nula");
            return;
        }

        CommentDTO comment = findCommentById(comments, commentId);
        if (comment != null) {
            if (comment.isLikedCommentsByUser()) {
                comment.setLikes(comment.getLikes() - 1);
                comment.setLikedCommentsByUser(false);
            } else {
                comment.setLikes(comment.getLikes() + 1);
                comment.setLikedCommentsByUser(true);
            }
            LOGGER.info("Comentario con ID " + commentId + " actualizado. Likes: " + comment.getLikes());
        } else {
            LOGGER.warning("Comentario con ID " + commentId + " no encontrado.");
        }
    }

    public void showInputComment(Long commentId) {
        if (comments == null) {
            LOGGER.severe("La lista de comentarios es nula");
            return;
        }

        CommentDTO comment = findCommentById(comments, commentId);
        if (comment != null) {
            comment.setReplyByUser(!comment.isReplyByUser());
        } else {
            LOGGER.warning("Comentario con ID " + commentId + " no encontrado.");
        }
    }

    public void replyComment(Long commentId, String textReply) {
        if (comments == null) {
            LOGGER.severe("La lista de comentarios es nula");
            return;
        }

        CommentDTO comment = findCommentById(comments, commentId);
        if (comment != null) {
            if (comment.getComments() == null) {
                comment.setComments(new ArrayList<>()); // Inicializa la lista si es nula
            }

            CommentDTO newComment = CommentDTO.builder()
                    .id(System.currentTimeMillis())
                    .username("Juan Romero") // Nombre de usuario fijo
                    .date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)) // Fecha sin segundos
                    .text(textReply)
                    .likes(0L)
                    .comments(new ArrayList<>())
                    .likedCommentsByUser(false)
                    .replyByUser(false)
                    .textReply("")
                    .build();

            comment.setTextReply("");
            comment.setReplyByUser(false);
            comment.getComments().add(newComment);

            LOGGER.info("Respuesta agregada al comentario con ID " + commentId);
        } else {
            LOGGER.warning("Comentario con ID " + commentId + " no encontrado.");
        }
    }

    public void deleteComment() {
        if (comments == null) {
            LOGGER.severe("La lista de comentarios es nula");
            return;
        }

        if (commentIdByUser != null) {
            CommentDTO parentComment = findParentComment(comments, commentIdByUser.getId());
            if (parentComment != null) {
                parentComment.getComments().removeIf(c -> c.getId().equals(commentIdByUser.getId()));
                LOGGER.info("Subcomentario eliminado con ID " + commentIdByUser.getId());
            } else {
                // Si no es subcomentario, intenta eliminarlo de la lista principal
                comments.removeIf(c -> c.getId().equals(commentIdByUser.getId()));
                LOGGER.info("Comentario raíz eliminado con ID " + commentIdByUser.getId());
            }
        } else {
            LOGGER.warning("Comentario no encontrado para eliminar.");
        }
    }

    private CommentDTO findParentComment(List<CommentDTO> comments, Long commentId) {
        for (CommentDTO comment : comments) {
            if (comment.getComments() != null) {
                for (CommentDTO reply : comment.getComments()) {
                    if (reply.getId().equals(commentId)) {
                        return comment;
                    }
                }
                // Búsqueda recursiva en subniveles
                CommentDTO found = findParentComment(comment.getComments(), commentId);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public void showEditInputComment() {
        CommentDTO comment = findCommentById(comments, commentIdByUser.getId());
        if (comment != null) {
            comment.setEditingByUser(true);
        }
    }

    public void cancelEdit() {
        CommentDTO comment = findCommentById(comments, commentIdByUser.getId());
        if (comment != null) {
            comment.setEditingByUser(false);
        }
    }

    public void updateComment(String newText) {
        CommentDTO comment = findCommentById(comments, commentIdByUser.getId());
        if (comment != null) {
            comment.setText(newText);
            comment.setEditingByUser(false);
            // Aquí podrías persistir el cambio si estás trabajando con base de datos
            LOGGER.info("Comentario actualizado: " + comment.getId());
        }
    }

    private CommentDTO findCommentById(List<CommentDTO> comments, Long commentId) {
        for (CommentDTO comment : comments) {
            if (comment.getId().equals(commentId)) {
                return comment;
            }
            if (comment.getComments() != null && !comment.getComments().isEmpty()) {
                CommentDTO found = findCommentById(comment.getComments(), commentId);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public void addComment(String text) {
        if (comments == null) {
            LOGGER.severe("La lista de comentarios es nula");
            return;
        }

        CommentDTO newComment = CommentDTO.builder()
                .id(System.currentTimeMillis())
                .username("Juan Romero") // Nombre de usuario fijo
                .date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)) // Fecha sin segundos
                .text(text)
                .likes(0L)
                .comments(new ArrayList<>())
                .likedCommentsByUser(false)
                .replyByUser(false)
                .textReply("")
                .build();

        comments.add(newComment);
        inputComment = ""; // Limpia el campo de texto
        LOGGER.info("Comentario agregado: " + newComment.getId());
    }
}