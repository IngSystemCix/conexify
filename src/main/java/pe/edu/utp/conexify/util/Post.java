package pe.edu.utp.conexify.util;

import jakarta.inject.Inject;
import lombok.*;

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
    private boolean isPublic;
    private Long likes;
    private List<Comment> comments;
    private boolean likedByUser;
}
