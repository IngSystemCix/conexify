package pe.edu.utp.conexify.dto;

import lombok.*;
import pe.edu.utp.conexify.config.PostType;
import pe.edu.utp.conexify.config.Visibility;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private Long id;
    private PostType type;
    private String content;
    private String username;
    private LocalDateTime date;
    private Visibility visibility;
    private Long likes;
    private List<CommentDTO> comments;
    private boolean likedByUser;

    public String getVisibility() {
        return switch (visibility) {
            case PUBLIC -> "bi-globe-americas";
            case FRIENDS -> "bi-people";
            case PRIVATE -> "bi-lock";
        };
    }
}
