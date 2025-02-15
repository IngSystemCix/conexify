package pe.edu.utp.conexify.util;

import lombok.*;
import pe.edu.utp.conexify.config.Visibility;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Long id;
    private boolean textPost;
    private boolean linkPost;
    private boolean imagePost;
    private boolean videoPost;
    private String content;
    private String username;
    private LocalDateTime date;
    private Visibility visibility;
    private Long likes;
    private List<Comment> comments;
    private boolean likedByUser;

    public String getVisibility() {
        return switch (visibility) {
            case PUBLIC -> "bi-globe-americas";
            case FRIENDS -> "bi-people";
            case PRIVATE -> "bi-lock";
        };
    }
}
